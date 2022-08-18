package com.migi.migi_project.utils;

import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class FileUtils {
    public static String getResourceBasePath() {
        // Get the directory
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            // nothing to do
        }
        if (path == null || !path.exists()) {
            path = new File("");
        }

        String pathStr = path.getAbsolutePath();
        // If it is running in eclipse, it will be in the same level as the target. If the jar is deployed to the server, the default is the same as the jar package.
        pathStr = pathStr.replace("\\target\\classes", "");

        return pathStr;
    }
}
