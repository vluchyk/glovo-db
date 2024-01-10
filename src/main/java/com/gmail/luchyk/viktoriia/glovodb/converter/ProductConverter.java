package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;

public class ProductConverter {
    public static ProductDto toDto(ProductEntity productEntity){
        return ProductDto.builder()
                .id(productEntity.getId())
                .name(productEntity.getName())
                .cost(productEntity.getCost())
                .build();
    }

    public static ProductEntity toEntity(ProductDto productDto){
        return ProductEntity.builder()
                .id(productDto.getId())
                .name(productDto.getName())
                .cost(productDto.getCost())
                .build();
    }
}
