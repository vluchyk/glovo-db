package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.OrderDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;

public class OrderConverter {
    public static OrderDto toDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .number(orderEntity.getNumber())
                .products(orderEntity.getProducts())
                .customer(orderEntity.getCustomer())
                .build();
    }

    public static OrderEntity toEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .id(orderDto.getId())
                .number(orderDto.getNumber())
                .products(orderDto.getProducts())
                .customer(orderDto.getCustomer())
                .build();
    }
}