package com.filestorage.utils;

public final class FileUtils {

    private FileUtils() {
    }

    public static String getFileExtension(String fileName){
        return fileName.substring(fileName.indexOf('.') + 1);
    }
}
