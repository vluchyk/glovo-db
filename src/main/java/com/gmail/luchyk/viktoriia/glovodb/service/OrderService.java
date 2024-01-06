package com.gmail.luchyk.viktoriia.glovodb.service;


import com.gmail.luchyk.viktoriia.glovodb.converter.AddressConverter;
import com.gmail.luchyk.viktoriia.glovodb.converter.CustomerConverter;
import com.gmail.luchyk.viktoriia.glovodb.converter.OrderConverter;
import com.gmail.luchyk.viktoriia.glovodb.converter.ProductConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.Address;
import com.gmail.luchyk.viktoriia.glovodb.dto.Customer;
import com.gmail.luchyk.viktoriia.glovodb.dto.Order;
import com.gmail.luchyk.viktoriia.glovodb.dto.Product;
import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.ProductEntity;
import com.gmail.luchyk.viktoriia.glovodb.repository.AddressRepository;
import com.gmail.luchyk.viktoriia.glovodb.repository.CustomerRepository;
import com.gmail.luchyk.viktoriia.glovodb.repository.OrderRepository;
import com.gmail.luchyk.viktoriia.glovodb.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private ProductRepository productRepository;
    private AddressRepository addressRepository;
    private CustomerRepository customerRepository;

    public Order get(int id) {
        return orderRepository.findById(id).map(OrderConverter::toOrder).orElseThrow();
    }


    public Order save(Order order) {
        OrderEntity orderEntity = OrderConverter.toOrderEntity(order);
        customerRepository.save(orderEntity.getCustomer());
        addressRepository.save(orderEntity.getAddress());
        orderRepository.save(orderEntity);
        orderEntity.getProducts().forEach(p -> p.setOrderId(orderEntity.getId()));
        productRepository.saveAll(orderEntity.getProducts());
        return OrderConverter.toOrder(orderEntity);
    }


    public Order update(int id, Order order) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        OrderEntity targetEntity = OrderConverter.toOrderEntity(order);

        targetEntity.setId(sourceEntity.getId());
        targetEntity.getCustomer().setId(sourceEntity.getCustomer().getId());
        targetEntity.getAddress().setId(sourceEntity.getAddress().getId());

        List<ProductEntity> sourceProductEntities = sourceEntity.getProducts();
        List<ProductEntity> targetProductEntities = targetEntity.getProducts();
        if (sourceProductEntities.size() == targetProductEntities.size()) {
            for (int i = 0; i < sourceProductEntities.size(); i++) {
                targetProductEntities.get(i).setId(sourceProductEntities.get(i).getId());
                targetProductEntities.get(i).setOrderId(sourceProductEntities.get(i).getOrderId());
            }
        }

        customerRepository.save(targetEntity.getCustomer());
        addressRepository.save(targetEntity.getAddress());
        productRepository.saveAll(targetEntity.getProducts());
        orderRepository.save(targetEntity);

        return OrderConverter.toOrder(targetEntity);
    }


    public Order update(int id, String productName, Product updated) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        List<ProductEntity> sourceProductEntities = sourceEntity.getProducts();
        for (int i = 0; i < sourceProductEntities.size(); i++) {
            if (sourceProductEntities.get(i).getName().equals(productName)) {
                ProductEntity sourceProductEntity = sourceProductEntities.get(i);
                ProductEntity targetProductEntity = ProductConverter.toProductEntity(updated);
                targetProductEntity.setId(sourceProductEntity.getId());
                targetProductEntity.setOrderId(sourceProductEntity.getOrderId());
                productRepository.save(targetProductEntity);
                break;
            }
        }
        return OrderConverter.toOrder(sourceEntity);
    }


    public Order updateCustomer(int id, Customer customer) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        CustomerEntity sourceCustomerEntity = sourceEntity.getCustomer();
        CustomerEntity targetCustomerEntity = CustomerConverter.toCustomerEntity(customer);
        targetCustomerEntity.setId(sourceCustomerEntity.getId());
        customerRepository.save(targetCustomerEntity);
        return OrderConverter.toOrder(sourceEntity);
    }


    public Order update(int id, Address address) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        AddressEntity sourceAddressEntity = sourceEntity.getAddress();
        AddressEntity targetAddressEntity = AddressConverter.toAddressEntity(address);
        targetAddressEntity.setId(sourceAddressEntity.getId());
        addressRepository.save(targetAddressEntity);
        return OrderConverter.toOrder(sourceEntity);
    }


    public Order patch(int id, Product product) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        List<ProductEntity> sourceProductEntities = sourceEntity.getProducts();
        ProductEntity targetProductEntity = ProductConverter.toProductEntity(product);
        targetProductEntity.setOrderId(sourceEntity.getId());
        sourceProductEntities.add(targetProductEntity);
        productRepository.save(targetProductEntity);
        return OrderConverter.toOrder(sourceEntity);
    }


    public Order delete(int id, String productName) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        List<ProductEntity> sourceProductEntities = sourceEntity.getProducts();
        for (int i = 0; i < sourceProductEntities.size(); i++) {
            if (sourceProductEntities.get(i).getName().equals(productName)) {
                ProductEntity sourceProductEntity = sourceProductEntities.get(i);
                sourceProductEntities.remove(i);
                productRepository.delete(sourceProductEntity);
                break;
            }
        }
        return OrderConverter.toOrder(sourceEntity);
    }


    public void delete(int id) {
        OrderEntity sourceEntity = orderRepository.findById(id).orElseThrow();
        orderRepository.delete(sourceEntity);
    }
}