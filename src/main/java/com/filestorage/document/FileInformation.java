package com.filestorage.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.util.List;
import java.util.Set;

@Document(indexName = "fileInformation")
@Setting(settingPath = "static/es-settings.json")
public class FileInformation {

    @Id
    private String id;

    @Field(type = FieldType.Text , name = "name")
    private String name;

    @Field(type = FieldType.Integer , name = "size")
    private int size;

    @Field(type = FieldType.Text , name = "tags")
    private Set<String> tags;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
