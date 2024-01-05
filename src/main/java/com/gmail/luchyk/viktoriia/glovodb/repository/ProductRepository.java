package com.gmail.luchyk.viktoriia.glovodb.repository;

import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}