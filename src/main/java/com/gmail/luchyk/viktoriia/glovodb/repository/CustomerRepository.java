package com.gmail.luchyk.viktoriia.glovodb.repository;

import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Integer> {
}