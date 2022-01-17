package com.filestorage.repository;

import com.filestorage.document.FileInformation;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface FileInformationRepository extends ElasticsearchRepository<FileInformation , String> {
}
