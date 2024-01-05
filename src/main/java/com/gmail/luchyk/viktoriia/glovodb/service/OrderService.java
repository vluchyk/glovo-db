package com.gmail.luchyk.viktoriia.glovodb.service;


import com.gmail.luchyk.viktoriia.glovodb.converter.OrderConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.Address;
import com.gmail.luchyk.viktoriia.glovodb.dto.Customer;
import com.gmail.luchyk.viktoriia.glovodb.dto.Order;
import com.gmail.luchyk.viktoriia.glovodb.dto.Product;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;
import com.gmail.luchyk.viktoriia.glovodb.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public Order get(int id) {
        return orderRepository.findById(id).map(OrderConverter::toOrder).orElseThrow();
    }


    public Order save(Order order) {
        OrderEntity orderEntity = OrderConverter.toOrderEntity(order);
        return OrderConverter.toOrder(orderRepository.save(orderEntity));
    }


    public Order update(int id, Order order) {
        return null;
    }


    public Order update(int id, String productName, Product updated) {
        return null;
    }


    public Order updateCustomer(int id, Customer customer) {
        return null;
    }


    public Order update(int id, Address address) {
        return null;
    }


    public Order patch(int id, Product product) {
        return null;
    }


    public Order delete(int id, String productName) {
        return null;
    }


    public void delete(int id) {

    }
}
