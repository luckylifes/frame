package com.dj.cn;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @version V1.0
 * @Title: DirectoryUtils
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/7/22 17:05
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class DirectoryUtils {
    public DirectoryUtils() {
    }

    public static boolean createIfNon(File directory) {
        return directory.exists() ? true : directory.mkdirs();
    }

    public static boolean createIfNon(String directory) {
        return createIfNon(new File(directory));
    }

    public static String getProjectDirectory() {
        File file = new File("");
        return file.getAbsolutePath();
    }

    public static String getResourcesDirectory() {
        URL url = DirectoryUtils.class.getClassLoader().getResource("");
        URI uri = null;

        try {
            uri = url.toURI();
        } catch (URISyntaxException var3) {
            var3.printStackTrace();
            return null;
        }

        if (uri == null) {
            return null;
        } else {
            File file = new File(uri);
            return !file.exists() ? null : Paths.get(file.getParent(), "classes").toString();
        }
    }

    public static String toAbsolutePath(String relativePath) throws IOException {
        if (relativePath.indexOf("./") != 0 && relativePath.indexOf("../") != 0) {
            String projectDirectory = getProjectDirectory();
            return Paths.get(projectDirectory, relativePath).toString();
        } else {
            return (new File(relativePath)).getCanonicalPath();
        }
    }

    public static String getDateDirectory() {
        return getDateDirectory("");
    }

    public static String getDateDirectory(String rootDir) {
        return getDateDirectory(rootDir, LocalDate.now());
    }

    public static String getDateDirectory(String rootDir, LocalDate date) {
        return Paths.get(rootDir, String.valueOf(date.getYear()), String.valueOf(date.getMonthValue()), String.valueOf(date.getDayOfMonth())).toString();
    }

    public static String getDateDirectory(String rootDir, LocalDateTime dateTime) {
        return Paths.get(rootDir, String.valueOf(dateTime.getYear()), String.valueOf(dateTime.getMonthValue()), String.valueOf(dateTime.getDayOfMonth())).toString();
    }
}
