package com.filestorage.tagExtension;

import java.util.Set;

import static com.filestorage.tagExtension.FileExtensionTag.AUDIO;
import static com.filestorage.tagExtension.FileExtensionTag.ARCHIVE;
import static com.filestorage.tagExtension.FileExtensionTag.IMAGE;
import static com.filestorage.tagExtension.FileExtensionTag.TEXT;
import static com.filestorage.tagExtension.FileExtensionTag.VIDEO;

public enum FileExtension {
    MP3("mp3" , AUDIO),
    WAV("wav" , AUDIO);
    MPC("mpc" , AUDIO);
    MSV("msv" , AUDIO);
    MP4("mp4" , VIDEO);
    WEBM("webm" , VIDEO);
    FLV("flv" , VIDEO);
    AVI("avi" , VIDEO);
    JPEG("jpeg" , IMAGE);
    JPG("jpg" , IMAGE);
    PNG("png" , IMAGE);
    GIF("gif" , IMAGE);
    DOC("doc" , TEXT);
    DOXC("doxc" , TEXT);
    PDF("pdf" , TEXT);
    TXT("txt" , TEXT);
    ZIP("zip" , ARCHIVE);
    RAR("rar" , ARCHIVE);
    ISO("iso" , ARCHIVE);
    TAR("tar" , ARCHIVE);

    private String extension;
    private FileExtensionTag correspondingTag;

    public String getExtension() {
        return extension;
    }

    public FileExtensionTag getCorrespondingTag() {
        return correspondingTag;
    }

    FileExtension(String extension, FileExtensionTag correspondingTag) {
        this.extension = extension;
        this.correspondingTag = correspondingTag;
    }
}
