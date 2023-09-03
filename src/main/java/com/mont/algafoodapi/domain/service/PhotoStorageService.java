package com.mont.algafoodapi.domain.service;

import java.io.InputStream;
import java.util.UUID;

import lombok.Builder;
import lombok.Getter;

public interface PhotoStorageService {

    InputStream recover(String fileName); 
    
    void store(NewPhoto newPhoto);

    void remove(String fileName);

    default void replace(String oldFileName, NewPhoto newPhoto) {
        this.store(newPhoto);

        if(oldFileName != null) {
            this.remove(oldFileName);
        }
    }

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
