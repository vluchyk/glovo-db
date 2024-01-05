package com.gmail.luchyk.viktoriia.glovodb.repository;


import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<OrderEntity, Integer> {
}