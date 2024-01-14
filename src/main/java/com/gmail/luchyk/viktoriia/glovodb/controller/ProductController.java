package com.gmail.luchyk.viktoriia.glovodb.controller;

import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@AllArgsConstructor
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    @GetMapping("/{id}")
    public ProductDto get(@PathVariable int id) {
        try {
            return productService.get(id);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @PostMapping
    public ProductDto save(@RequestBody ProductDto productDto) {
        return productService.save(productDto);
    }

    @PutMapping
    public ProductDto update(@RequestBody ProductDto productDto) {
        try {
            return productService.update(productDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }

    @DeleteMapping
    public void delete(@RequestBody ProductDto productDto) {
        try {
            productService.delete(productDto);
        } catch (ObjectNotFoundException e) {
            throw new ResponseStatusException(NOT_FOUND);
        }
    }
}
