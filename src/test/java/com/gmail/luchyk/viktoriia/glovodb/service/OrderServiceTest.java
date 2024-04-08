package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.OrderConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.OrderDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.ProductDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.OrderEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    private OrderService orderService;
    private OrderDto expected;
    private List<ProductDto> productsExpected = new ArrayList<>();
    private CustomerDto customerExpected;
    private AddressDto registrationAddressExpected;
    private Set<AddressDto> deliveryAddressesExpected = new HashSet<>();

    @BeforeEach
    public void init() {
        orderService = new OrderService(orderRepository);
        productsExpected.add(ProductDto.builder().id(1).name("candy").cost(3.75).build());
        productsExpected.add(ProductDto.builder().id(2).name("water").cost(1.25).build());
        registrationAddressExpected = AddressDto.builder().id(1).street("Drayton str").number("1a").apartmentNumber(7).build();
        deliveryAddressesExpected.add(registrationAddressExpected);
        deliveryAddressesExpected.add(AddressDto.builder().id(2).street("Main str").number("2/3").apartmentNumber(5).build());
        customerExpected = CustomerDto.builder()
                .id(1)
                .firstName("John")
                .middleName("A.")
                .lastName("Smith")
                .phone("+380501231212")
                .registrationAddress(registrationAddressExpected)
                .deliveryAddresses(deliveryAddressesExpected)
                .build();
        expected = OrderDto.builder()
                .id(1)
                .number(1000)
                .products(productsExpected)
                .customer(customerExpected)
                .build();
    }

    @Test
    public void getTest() throws ObjectNotFoundException {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(OrderConverter.toEntity(expected)));

        OrderDto result = orderService.get(anyInt());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getNotFoundTest() {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> orderService.get(anyInt()));
        Assertions.assertEquals(Message.ORDER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void saveTest() {
        Mockito.when(orderRepository.save(any())).thenReturn(OrderConverter.toEntity(expected));

        OrderDto result = orderService.save(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateTest() throws ObjectNotFoundException {
        OrderDto existing = OrderDto.builder()
                .id(expected.getId())
                .products(expected.getProducts())
                .customer(expected.getCustomer())
                .build();
        OrderEntity orderEntity = OrderConverter.toEntity(existing);
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(any())).thenReturn(orderEntity);

        OrderDto result = orderService.update(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateNotFoundTest() {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> orderService.update(expected));
        Assertions.assertEquals(Message.ORDER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void patchTest() throws ObjectNotFoundException {
        OrderEntity orderEntity = OrderConverter.toEntity(expected);
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(any())).thenReturn(orderEntity);

        ProductDto another = ProductDto.builder().id(3).name("Nuts").cost(7).build();
        OrderDto result = orderService.patch(expected.getId(), another);

        productsExpected.add(another);
        expected.setProducts(productsExpected);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void deleteProductTest() throws ObjectNotFoundException {
        OrderEntity orderEntity = OrderConverter.toEntity(expected);
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));
        Mockito.when(orderRepository.save(any())).thenReturn(orderEntity);

        ProductDto removed = ProductDto.builder().id(2).name("water").cost(1.25).build();
        OrderDto result = orderService.delete(expected.getId(), removed);

        productsExpected.remove(removed);
        expected.setProducts(productsExpected);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void deleteTest() throws ObjectNotFoundException {
        int wantedNumberOfInvocations = 1;
        OrderEntity orderEntity = OrderConverter.toEntity(expected);
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.of(orderEntity));

        orderService.delete(expected);

        verify(orderRepository, times(wantedNumberOfInvocations)).delete(orderEntity);
    }

    @Test
    public void deleteNotFoundTest() {
        Mockito.when(orderRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> orderService.delete(expected));
        Assertions.assertEquals(Message.ORDER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }
}