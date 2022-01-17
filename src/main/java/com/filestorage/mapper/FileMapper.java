package com.filestorage.mapper;

import com.filestorage.document.FileInformation;
import com.filestorage.dto.RequestFileDto;
import com.filestorage.dto.ResponseFileDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FileMapper {

    public FileInformation mapDtoToFileInformation(RequestFileDto file){
        FileInformation info = new FileInformation();
        String fileNameOfRequest = file.getName();
        info.setName(fileNameOfRequest);
        info.setSize(file.getSize());
        info.setTags(new HashSet<>());
        return info;
    }

    public ResponseFileDto mapFileInformationToDto(FileInformation file){
        ResponseFileDto fileResponseDto = new ResponseFileDto();
        fileResponseDto.setId(file.getId());
        return fileResponseDto;
    }

    public List<FileInformation> mapDtoListToFileInformationList(List<RequestFileDto> dtoList){
        return dtoList.stream().map(this::mapDtoToFileInformation).collect(Collectors.toList());
    }

    public List<ResponseFileDto> mapFileInformationListToDto(List<FileInformation> file){
        return file.stream().map(this::mapFileInformationToDto).collect(Collectors.toList());
    }
}
