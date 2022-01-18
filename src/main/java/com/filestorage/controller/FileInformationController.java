package com.filestorage.controller;

import com.filestorage.document.FileInformation;
import com.filestorage.dto.RequestFileDto;
import com.filestorage.dto.ResponseFileDto;
import com.filestorage.dto.SearchRequestDto;
import com.filestorage.service.FileInformationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> deleteFileById(@PathVariable("id") String id) {
        service.deleteFileById(id);
        return new ResponseEntity<String>(id , HttpStatus.OK);
    }

    @PostMapping("/{id}/tags")
    public ResponseEntity<FileInformation> assignTagsToFile(@PathVariable("id") String id, @RequestBody List<String> tags) {
        return new ResponseEntity<>(service.addTags(id, tags) , HttpStatus.OK);
    }

    @DeleteMapping("/{id}/tags")
    public ResponseEntity<FileInformation> deleteTagsById(@PathVariable("id") String id, @RequestBody List<String> tags) {
        return new ResponseEntity<>(service.deleteTagsFromFile(id, tags) , HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<FileInformation> search(@RequestBody SearchRequestDto dto) {
        return service.search(dto);
    }
}
