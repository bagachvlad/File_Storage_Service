package com.filestorage.service;

import com.filestorage.document.FileInformation;
import com.filestorage.tagExtension.FileExtension;
import com.filestorage.tagExtension.FileExtensionTag;
import org.springframework.stereotype.Service;

import java.util.Arrays;

import static com.filestorage.utils.FileUtils.getFileExtension;

@Service
public class FileInformationTagIdentifier {

    public void identifierTags(FileInformation fileInformation) {
        identifierByFileExtension(fileInformation);
    }

    private void identifierByFileExtension(FileInformation fileInformation) {
        String fileName = fileInformation.getName();
        String extensionOfFile = getFileExtension(fileName);

        if (isKnownExtension(extensionOfFile)) {
            populateDefaultTag(fileInformation);
        }
    }

    private boolean isKnownExtension(String extensionOfFile) {
        return Arrays.stream(FileExtension.values())
                .map(FileExtension::getExtension)
                .anyMatch(extensionOfFile::equals);
    }

    private void populateDefaultTag(FileInformation fileInformation) {
        String fileName = fileInformation.getName();
        String extensionOfFile = getFileExtension(fileName);
        FileExtensionTag correspondingTag = FileExtension.valueOf(extensionOfFile).getCorrespondingTag();
        fileInformation.getTags().add(correspondingTag.getTagName());
    }
}
