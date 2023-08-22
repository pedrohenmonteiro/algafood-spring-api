package com.mont.algafoodapi.infraestructure.service.storage;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import com.mont.algafoodapi.domain.service.PhotoStorageService;

@Service
public class LocalPhotoStorageService implements PhotoStorageService{

    @Value("${algafood.storage.local.photos-directory}")
    private Path photoDirectory;


    @Override
    public void store(NewPhoto newPhoto) {
        
        try {
            Path filePath = getFilePath(newPhoto.getFileName());
            FileCopyUtils.copy(newPhoto.getInputStream(), Files.newOutputStream(filePath));
        } catch (Exception e) {
            throw new StorageException("It was not possible to store file", e);
        }
    }

   

    @Override
    public void remove(String fileName) {
        try {
            Path filePath = getFilePath(fileName);
            Files.deleteIfExists(filePath);
        } catch (Exception e) {
            throw new StorageException("It was not possible to delete file", e);
        }
    }

     private Path getFilePath(String fileName) {
        return photoDirectory.resolve(Path.of(fileName));
    }
}
