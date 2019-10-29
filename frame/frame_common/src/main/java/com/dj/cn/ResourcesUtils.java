package com.dj.cn;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @version V1.0
 * @Title: ResourcesUtils
 * @Package: com.dj.cn
 * @author: Lenovo
 * @date: 2019/7/22 17:03
 * @Copyright: 2019 www.lenovo.com Inc. All rights reserved.
 */
public class ResourcesUtils {
    public ResourcesUtils() {
    }

    public static InputStream getInputStream(String resource) throws URISyntaxException, IOException {
        String projectDirectory = DirectoryUtils.getProjectDirectory();
        URL url = DirectoryUtils.class.getClassLoader().getResource(resource);
        if (url != null && "file".equals(url.getProtocol())) {
            File file = new File(url.toURI());
            String path = file.getAbsolutePath();
            if (file.exists() && path.indexOf(projectDirectory) == 0) {
                return new FileInputStream(file);
            }
        }
        Path resourcePath = Paths.get(projectDirectory, resource);
        File resourceFile = resourcePath.toFile();
        if (resourceFile.exists()) {
            return new FileInputStream(resourceFile);
        } else {
            if (url != null && "file".equals(url.getProtocol())) {
                File file = new File(url.toURI());
                if (file.exists()) {
                    return new FileInputStream(file);
                }
            }
            return url != null && "jar".equals(url.getProtocol()) ? url.openStream() : null;
        }
    }
}
