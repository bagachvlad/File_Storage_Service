package com.filestorage.tagExtension;

import java.util.Set;

public enum TagFileExtension {

    AUDIO("Audio", Set.of("mp3", "wav", "mpc", "msv")),
    VIDEO("Video", Set.of("mp4", "webm", "flv", "avi")),
    IMAGE("Image", Set.of("jpeg", "jpg", "png", "gif")),
    TEXT("Text", Set.of("doc", "docx", "pdf", "txt")),
    ARCHIVE("Archive", Set.of("zip", "rar", "iso, tar"));

    private final String name;
    private final Set<String> extensions;

    TagFileExtension(String name, Set<String> extensions) {
        this.name = name;
        this.extensions = extensions;
    }

    public String getName() {
        return name;
    }

    public Set<String> getExtensions() {
        return extensions;
    }
}
