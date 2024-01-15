package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.ProductConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductDto get(int id) throws ObjectNotFoundException {
        return productRepository.findById(id).map(ProductConverter::toDto).orElseThrow(() -> new ObjectNotFoundException(Message.PRODUCT_NOT_FOUND.getMessage()));
    }

    public ProductDto save(ProductDto productDto) {
        ProductEntity productEntity = ProductConverter.toEntity(productDto);
        return ProductConverter.toDto(productRepository.save(productEntity));
    }

    public ProductDto update(ProductDto productDto) throws ObjectNotFoundException {
        ProductEntity productEntity = productRepository.findById(productDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.PRODUCT_NOT_FOUND.getMessage()));
        productEntity.setName(productDto.getName());
        productEntity.setCost(productDto.getCost());
        return ProductConverter.toDto(productRepository.save(productEntity));
    }

    public void delete(ProductDto productDto) throws ObjectNotFoundException {
        ProductEntity productEntity = productRepository.findById(productDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.PRODUCT_NOT_FOUND.getMessage()));
        productRepository.delete(productEntity);
    }
}
