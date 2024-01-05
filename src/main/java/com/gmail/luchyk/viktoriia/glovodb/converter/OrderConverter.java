package com.gmail.luchyk.viktoriia.glovodb.converter;

import com.gmail.luchyk.viktoriia.glovodb.dto.Order;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;

public class OrderConverter {
    public static Order toOrder(OrderEntity orderEntity) {
        return Order.builder()
                .number(orderEntity.getNumber())
                .customer(CustomerConverter.toCustomer(orderEntity.getCustomer()))
                .address(AddressConverter.toAddress(orderEntity.getAddress()))
                .build();
    }

    public static OrderEntity toOrderEntity(Order order) {
        return OrderEntity.builder()
                .number(order.getNumber())
                .customer(CustomerConverter.toCustomerEntity(order.getCustomer()))
                .address(AddressConverter.toAddressEntity(order.getAddress()))
                .build();
    }
}
