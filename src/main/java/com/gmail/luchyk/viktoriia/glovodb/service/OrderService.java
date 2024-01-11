package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.OrderConverter;
import com.gmail.luchyk.viktoriia.glovodb.converter.ProductConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.OrderDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;

    public OrderDto get(int id) throws ObjectNotFoundException {
        return orderRepository.findById(id).map(OrderConverter::toDto).orElseThrow(() -> new ObjectNotFoundException(Message.ORDER_NOT_FOUND.getMessage()));
    }

    public OrderDto save(OrderDto orderDto) {
        OrderEntity orderEntity = OrderConverter.toEntity(orderDto);
        orderRepository.save(orderEntity);
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto update(OrderDto orderDto) throws ObjectNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.ORDER_NOT_FOUND.getMessage()));
        orderEntity.setNumber(orderDto.getNumber());
        orderEntity.setProducts(orderDto.getProducts());
        orderEntity.setCustomer(orderDto.getCustomer());
        orderRepository.save(orderEntity);
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto patch(int id, ProductDto productDto) throws ObjectNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Message.ORDER_NOT_FOUND.getMessage()));
        List<ProductEntity> products = orderEntity.getProducts();
        products.add(ProductConverter.toEntity(productDto));
        orderRepository.save(orderEntity);
        return OrderConverter.toDto(orderEntity);
    }

    public OrderDto delete(int id, ProductDto productDto) throws ObjectNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Message.ORDER_NOT_FOUND.getMessage()));
        List<ProductEntity> products = orderEntity.getProducts();
        products.remove(ProductConverter.toEntity(productDto));
        orderRepository.save(orderEntity);
        return OrderConverter.toDto(orderEntity);
    }

    public void delete(OrderDto orderDto) throws ObjectNotFoundException {
        OrderEntity orderEntity = orderRepository.findById(orderDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.ORDER_NOT_FOUND.getMessage()));
        orderRepository.delete(orderEntity);
    }
}