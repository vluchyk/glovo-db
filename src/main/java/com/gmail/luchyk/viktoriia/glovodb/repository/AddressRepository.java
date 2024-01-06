package com.gmail.luchyk.viktoriia.glovodb.repository;

import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<AddressEntity, Integer> {
}