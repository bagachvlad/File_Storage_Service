package com.filestorage.exception;

public class FileInformationNotFoundException extends RuntimeException {
    public FileInformationNotFoundException(String message) {
        super(message);
    }
}
