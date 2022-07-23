package com.kq.springframework.core.io.support;

import com.kq.springframework.core.io.FileSystemResource;
import com.kq.springframework.util.ClassUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

/**
 * @author kq
 * @date 2022-07-18 10:30
 * @since 2020-0630
 */
public class PathMatchingResourcePatternResolver {

    private ClassLoader classLoader;


    public FileSystemResource[] getResources(String locationPattern) throws IOException {

        String[] locations = findAllClassPathResources(locationPattern);

        Set<FileSystemResource> result = new LinkedHashSet<>();
        for(String location : locations) {
            result.addAll(this.doFindPathMatchingFileResources(location,"**/*.class"));
        }

        return result.toArray(new FileSystemResource[0]);

    }


    protected String[] findAllClassPathResources(String location) throws IOException {
        String path = location;
        if (path.startsWith("/")) {
            path = path.substring(1);
        } // path = com/kq/di/autowired/
        Set<String> result = doFindAllClassPathResources(path);

        return result.toArray(new String[0]);
    }

    /**
     *
     * @param path = com/kq/di/autowired/
     * @return
     * @throws IOException
     */
    protected Set<String> doFindAllClassPathResources(String path) throws IOException {
        Set<String> result = new LinkedHashSet<>(16);
        ClassLoader cl = getClassLoader();
        Enumeration<URL> resourceUrls = (cl != null ? cl.getResources(path) : ClassLoader.getSystemResources(path));
        while (resourceUrls.hasMoreElements()) {
            URL url = resourceUrls.nextElement();
            result.add(url.getFile());
        }
//        if (!StringUtils.hasLength(path)) {
//            // The above result is likely to be incomplete, i.e. only containing file system references.
//            // We need to have pointers to each of the jar files on the classpath as well...
//            addAllClassLoaderJarRoots(cl, result);
//        }
        return result;
    }




    protected Set<FileSystemResource> doFindPathMatchingFileResources(String rootDirResource, String subPattern)
            throws IOException {

        File rootDir;
        try {
//            rootDir = rootDirResource.getFile().getAbsoluteFile();
            rootDir = new File(rootDirResource).getAbsoluteFile();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return Collections.emptySet();
        }
        return doFindMatchingFileSystemResources(rootDir, subPattern);
    }

    protected Set<FileSystemResource> doFindMatchingFileSystemResources(File rootDir, String subPattern) throws IOException {

        Set<File> matchingFiles = retrieveMatchingFiles(rootDir, subPattern);
        Set<FileSystemResource> result = new LinkedHashSet<>(matchingFiles.size());
        for (File file : matchingFiles) {
            result.add(new FileSystemResource(file));
        }
        return result;
    }

    protected Set<File> retrieveMatchingFiles(File rootDir, String pattern) throws IOException {
        if (!rootDir.exists()) {
            // Silently skip non-existing directories.

            return Collections.emptySet();
        }
        if (!rootDir.isDirectory()) {
            // Complain louder if it exists but is no directory.

            return Collections.emptySet();
        }
        if (!rootDir.canRead()) {

            return Collections.emptySet();
        }
        String fullPattern = replace(rootDir.getAbsolutePath(), File.separator, "/");
        if (!pattern.startsWith("/")) {
            fullPattern += "/";
        }
        fullPattern = fullPattern + replace(pattern, File.separator, "/");
        Set<File> result = new LinkedHashSet<>(8);
        doRetrieveMatchingFiles(fullPattern, rootDir, result);
        return result;
    }


    protected void doRetrieveMatchingFiles(String fullPattern, File dir, Set<File> result) throws IOException {

        for (File content : listDirectory(dir)) {
            String currPath = replace(content.getAbsolutePath(), File.separator, "/");
//            if (content.isDirectory() && getPathMatcher().matchStart(fullPattern, currPath + "/")) {
            if (content.isDirectory()) {
                if (!content.canRead()) {

                }
                else {
                    doRetrieveMatchingFiles(fullPattern, content, result);
                }
            }
//            if (getPathMatcher().match(fullPattern, currPath)) {
            if (currPath.endsWith("class")) {
                result.add(content);
            }
        }
    }

    protected File[] listDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files == null) {

            return new File[0];
        }
        Arrays.sort(files, Comparator.comparing(File::getName));
        return files;
    }


    public ClassLoader getClassLoader() {
        return (this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader());
    }

    public static void main(String[] args) throws Exception{
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();

        FileSystemResource[] resources = patternResolver.getResources("com/kq");

        for(FileSystemResource fileSystemResource : resources) {
            System.out.println(fileSystemResource);
        }


    }

    public static boolean hasLength(String str) {
        return (str != null && !str.isEmpty());
    }

    public static String replace(String inString, String oldPattern, String newPattern) {
        if (!hasLength(inString) || !hasLength(oldPattern) || newPattern == null) {
            return inString;
        }
        int index = inString.indexOf(oldPattern);
        if (index == -1) {
            // no occurrence -> can return input as-is
            return inString;
        }

        int capacity = inString.length();
        if (newPattern.length() > oldPattern.length()) {
            capacity += 16;
        }
        StringBuilder sb = new StringBuilder(capacity);

        int pos = 0;  // our position in the old string
        int patLen = oldPattern.length();
        while (index >= 0) {
            sb.append(inString, pos, index);
            sb.append(newPattern);
            pos = index + patLen;
            index = inString.indexOf(oldPattern, pos);
        }

        // append any characters to the right of a match
        sb.append(inString, pos, inString.length());
        return sb.toString();
    }


}
