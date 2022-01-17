package com.filestorage.controller;

import com.filestorage.document.FileInformation;
import com.filestorage.dto.RequestFileDto;
import com.filestorage.dto.ResponseFileDto;
import com.filestorage.dto.SearchRequestDto;
import com.filestorage.service.FileInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/file")
public class FileInformationController {
    private final FileInformationService service;

    public FileInformationController(FileInformationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseFileDto uploadFile(@RequestBody RequestFileDto file) {
        return service.save(file);
    }

    @DeleteMapping("/{id}")
    public void deleteFileById(@PathVariable("id") String id) {
        service.deleteFileById(id);
    }

    @PostMapping("/{id}/tags")
    public void assignTagsToFile(@PathVariable("id") String id, @RequestBody List<String> tags) {
        service.addTags(id, tags);
    }

    @DeleteMapping("/{id}/tags")
    public void deleteTagsById(@PathVariable("id") String id, @RequestBody List<String> tags) {
        service.deleteTagsFromFile(id, tags);
    }

    @PostMapping("/search")
    public List<FileInformation> search(@RequestBody SearchRequestDto dto) {
        return service.search(dto);
    }

}
