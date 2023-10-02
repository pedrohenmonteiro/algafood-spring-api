package com.mont.algafoodapi.api.model.input;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.mont.algafoodapi.core.validation.FileContentType;
import com.mont.algafoodapi.core.validation.FileSize;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductPhotoInputDto {
    
    @NotNull
    @FileSize(max = "500KB")
    @FileContentType(allowed = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    private MultipartFile file;

    @NotBlank
    private String description;
}
