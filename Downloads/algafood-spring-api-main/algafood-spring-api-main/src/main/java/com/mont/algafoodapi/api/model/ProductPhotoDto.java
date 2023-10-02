package com.mont.algafoodapi.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPhotoDto {

    private String fileName;
    private String description;
    private String contentType;
    private Long size;

}
