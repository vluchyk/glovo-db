package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.ProductConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    private ProductService productService;
    private ProductDto expected;
    private ProductDto updated;

    @BeforeEach
    public void init() {
        productService = new ProductService(productRepository);
        expected = ProductDto.builder().id(1).name("candy").cost(5.25).build();
        updated = ProductDto.builder().id(1).name("cake").cost(7).build();
    }

    @Test
    public void getTest() throws ObjectNotFoundException {
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(ProductConverter.toEntity(expected)));

        ProductDto result = productService.get(anyInt());
        Assertions.assertEquals(expected, result);
    }
    @Test
    public void getNotFoundTest() {
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> productService.get(anyInt()));
        Assertions.assertEquals(Message.PRODUCT_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void saveTest() {
        Mockito.when(productRepository.save(any())).thenReturn(ProductConverter.toEntity(expected));

        ProductDto result = productService.save(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateTest() throws ObjectNotFoundException {
        ProductDto existing = ProductDto.builder().id(updated.getId()).build();
        ProductEntity productEntity = ProductConverter.toEntity(existing);
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(productEntity));
        Mockito.when(productRepository.save(any())).thenReturn(productEntity);

        ProductDto result = productService.update(updated);
        Assertions.assertEquals(updated, result);
    }

    @Test
    public void updateNotFoundTest() {
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> productService.update(expected));
        Assertions.assertEquals(Message.PRODUCT_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void deleteTest() throws ObjectNotFoundException {
        int wantedNumberOfInvocations = 1;
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.of(ProductConverter.toEntity(expected)));

        productService.delete(expected);

        verify(productRepository, times(wantedNumberOfInvocations)).delete(ProductConverter.toEntity(expected));
    }

    @Test
    public void deleteNotFoundTest() {
        Mockito.when(productRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> productService.delete(expected));
        Assertions.assertEquals(Message.PRODUCT_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }
}