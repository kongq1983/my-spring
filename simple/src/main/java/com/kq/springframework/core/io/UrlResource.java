package com.kq.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * @author kq
 * @date 2022-07-18 10:32
 * @since
 */
public class UrlResource {

    /**
     * Original URI, if available; used for URI and File access.
     */
//    private final URI uri;

    /**
     * Original URL, used for actual access.
     */
//    private final URL url;

    /**
     * Cleaned URL (with normalized path), used for comparisons.
     */
    private volatile URL cleanedUrl;


    /**
     * Create a new {@code UrlResource} based on the given URI object.
     * @param uri a URI
     * @throws MalformedURLException if the given URL path is not valid
     * @since 2.5
     */
//    public UrlResource(URI uri) throws MalformedURLException {
//        this.uri = uri;
//        this.url = uri.toURL();
//    }

    /**
     * Create a new {@code UrlResource} based on the given URL object.
     * @param url a URL
     */
//    public UrlResource(URL url) {
//        this.uri = null;
//        this.url = url;
//    }

    /**
     * Create a new {@code UrlResource} based on a URL path.
     * <p>Note: The given path needs to be pre-encoded if necessary.
     * @param path a URL path
     * @throws MalformedURLException if the given URL path is not valid
     * @see java.net.URL#URL(String)
     */
//    public UrlResource(String path) throws MalformedURLException {
//        this.uri = null;
//        this.url = new URL(path);
////        this.cleanedUrl = getCleanedUrl(this.url, path);
//    }


}
