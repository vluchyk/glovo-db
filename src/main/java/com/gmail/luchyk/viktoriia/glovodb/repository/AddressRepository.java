package com.gmail.luchyk.viktoriia.glovodb.repository;

import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}