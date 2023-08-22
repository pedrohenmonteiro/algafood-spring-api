package com.mont.algafoodapi.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {
    
    void store(NewPhoto newPhoto);

    default String generateFileName(String fileName) {
        return UUID.randomUUID().toString() + "_" + fileName;
    }
        
    @Getter
    @Builder
    class NewPhoto {

        private String fileName;
        private InputStream inputStream;
    }
}
