package com.filestorage.tagExtension;

import java.util.Set;

public enum FileExtensionTag {
    AUDIO("Audio"),
    VIDEO("Video"),
    IMAGE("Image"),
    TEXT("Text"),
    ARCHIVE("Archive");
    private String tagName;

    FileExtensionTag(String tagName) {
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }
}
