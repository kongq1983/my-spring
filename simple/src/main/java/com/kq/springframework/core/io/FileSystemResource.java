package com.kq.springframework.core.io;


import java.io.File;
import java.nio.file.Path;

/**
 * @author kq
 * @date 2022-07-18 11:03
 * @since 2020-0630
 */
public class FileSystemResource {

    private final String path;

    private final File file;

    private final Path filePath;

    public FileSystemResource(File file) {
//        this.path = StringUtils.cleanPath(file.getPath());
        this.path = file.getPath();
        this.file = file;
        this.filePath = file.toPath();
    }


    @Override
    public String toString() {
        return "FileSystemResource{" +
                "path='" + path + '\'' +
                ", file=" + file +
                ", filePath=" + filePath +
                '}';
    }
}
