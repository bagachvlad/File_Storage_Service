package com.filestorage.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.filestorage.document.FileInformation;
import com.filestorage.dto.RequestFileDto;
import com.filestorage.dto.ResponseFileDto;
import com.filestorage.dto.SearchRequestDto;
import com.filestorage.exception.FileInformationNotFoundException;
import com.filestorage.mapper.FileMapper;
import com.filestorage.repository.FileInformationRepository;
import com.filestorage.utils.SearchFileUtil;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Service
public class FileInformationService {

    private FileInformationRepository repository;
    private FileMapper fileMapper;
    private FileInformationTagIdentifier fileInformationTagIdentifier;
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private final RestHighLevelClient client;

    @Autowired
    public FileInformationService(@Qualifier("elasticsearchClient") RestHighLevelClient client) {
        this.client = client;
    }

    public ResponseFileDto save(RequestFileDto fileDto) {
        FileInformation fileInformation = fileMapper.mapDtoToFileInformation(fileDto);
        fileInformationTagIdentifier.identifierTags(fileInformation);

        FileInformation savedObject = repository.save(fileInformation);

        return fileMapper.mapFileInformationToDto(savedObject);
    }

    public void deleteFileById(String id) {
        repository.deleteById(id);
    }

    public void addTags(String fileId, List<String> tags) {
        String exceptionMessagePattern = "No file information with specified id '%s'";
        String exceptionMessage = String.format(exceptionMessagePattern, fileId);
        FileInformation fileById = repository
                .findById(fileId)
                .orElseThrow(() -> new FileInformationNotFoundException(exceptionMessage));
        fileById.getTags().addAll(tags);
        repository.save(fileById);
    }

    public void deleteTagsFromFile(String fileId , List<String> tags){
        String exceptionMessagePattern = "No file information with specified id '%s'";
        String exceptionMessage = String.format(exceptionMessagePattern, fileId);
        FileInformation fileById = repository
                .findById(fileId)
                .orElseThrow(() -> new FileInformationNotFoundException(exceptionMessage));
        Set<String> fileByIdTags = fileById.getTags();
        if(!fileByIdTags.containsAll(tags)){
            throw new RuntimeException();
        }
        fileByIdTags.removeAll(tags);
        repository.save(fileById);
    }

    public List<FileInformation> search(SearchRequestDto dto) {
         SearchRequest request = SearchFileUtil.buildSearchRequest(
                "fileInformation",
                dto
        );

        if (request == null) {
            return Collections.emptyList();
        }

        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);

             SearchHit[] searchHits = response.getHits().getHits();
             List<FileInformation> vehicles = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                vehicles.add(
                        MAPPER.readValue(hit.getSourceAsString(), FileInformation.class)
                );
            }

            return vehicles;
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    @Autowired
    public void setRepository(FileInformationRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setFileMapper(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    @Autowired
    public void setFileInformationTagIdentifier(FileInformationTagIdentifier fileInformationTagIdentifier) {
        this.fileInformationTagIdentifier = fileInformationTagIdentifier;
    }
}
