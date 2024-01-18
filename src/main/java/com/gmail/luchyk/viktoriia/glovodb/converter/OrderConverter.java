package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.OrderDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;

import java.util.stream.Collectors;

public class OrderConverter {
    public static OrderDto toDto(OrderEntity orderEntity){
        return OrderDto.builder()
                .id(orderEntity.getId())
                .number(orderEntity.getNumber())
                .products(orderEntity.getProducts().stream().map(ProductConverter::toDto).collect(Collectors.toList()))
                .customer(CustomerConverter.toDto(orderEntity.getCustomer()))
                .build();
    }

    public static OrderEntity toEntity(OrderDto orderDto){
        return OrderEntity.builder()
                .id(orderDto.getId())
                .number(orderDto.getNumber())
                .products(orderDto.getProducts().stream().map(ProductConverter::toEntity).collect(Collectors.toList()))
                .customer(CustomerConverter.toEntity(orderDto.getCustomer()))
                .build();
    }
}