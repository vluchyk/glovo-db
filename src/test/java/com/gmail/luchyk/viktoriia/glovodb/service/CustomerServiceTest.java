package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.CustomerConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;
    private CustomerDto expected;
    private AddressDto registrationAddressExpected;
    private Set<AddressDto> deliveryAddressesExpected = new HashSet<>();

    @BeforeEach
    public void init() {
        customerService = new CustomerService(customerRepository);
        registrationAddressExpected = AddressDto.builder().id(1).street("Drayton str").number("1a").apartmentNumber(7).build();
        deliveryAddressesExpected.add(registrationAddressExpected);
        deliveryAddressesExpected.add(AddressDto.builder().id(2).street("Main str").number("2/3").apartmentNumber(5).build());
        expected = CustomerDto.builder()
                .id(1)
                .firstName("John")
                .middleName("A.")
                .lastName("Smith")
                .phone("+380501231212")
                .registrationAddress(registrationAddressExpected)
                .deliveryAddresses(deliveryAddressesExpected)
                .build();
    }

    @Test
    public void getTest() throws ObjectNotFoundException {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(CustomerConverter.toEntity(expected)));

        CustomerDto result = customerService.get(anyInt());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getNotFoundTest() {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> customerService.get(anyInt()));
        Assertions.assertEquals(Message.CUSTOMER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void saveTest() {
        Mockito.when(customerRepository.save(any())).thenReturn(CustomerConverter.toEntity(expected));

        CustomerDto result = customerService.save(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateTest() throws ObjectNotFoundException {
        CustomerDto existing = CustomerDto.builder()
                .id(expected.getId())
                .registrationAddress(expected.getRegistrationAddress())
                .deliveryAddresses(expected.getDeliveryAddresses())
                .build();
        CustomerEntity customerEntity = CustomerConverter.toEntity(existing);
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));
        Mockito.when(customerRepository.save(any())).thenReturn(customerEntity);

        CustomerDto result = customerService.update(expected);
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateNotFoundTest() {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> customerService.update(expected));
        Assertions.assertEquals(Message.CUSTOMER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void patchTest() throws ObjectNotFoundException {
        CustomerEntity customerEntity = CustomerConverter.toEntity(expected);
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));
        Mockito.when(customerRepository.save(any())).thenReturn(customerEntity);

        AddressDto another = AddressDto.builder().id(3).street("New str").number("2").apartmentNumber(4).build();
        CustomerDto result = customerService.patch(expected.getId(), another);

        deliveryAddressesExpected.add(another);
        expected.setDeliveryAddresses(deliveryAddressesExpected);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void deleteDeliveryAddressTest() throws ObjectNotFoundException {
        CustomerEntity customerEntity = CustomerConverter.toEntity(expected);
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));
        Mockito.when(customerRepository.save(any())).thenReturn(customerEntity);

        AddressDto removed = AddressDto.builder().id(1).street("Drayton str").number("1a").apartmentNumber(7).build();
        CustomerDto result = customerService.deleteDeliveryAddress(expected.getId(), removed);

        deliveryAddressesExpected.remove(removed);
        expected.setDeliveryAddresses(deliveryAddressesExpected);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void deleteTest() throws ObjectNotFoundException {
        int wantedNumberOfInvocations = 1;
        CustomerEntity customerEntity = CustomerConverter.toEntity(expected);
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.of(customerEntity));

        customerService.delete(expected);

        verify(customerRepository, times(wantedNumberOfInvocations)).delete(customerEntity);
    }

    @Test
    public void deleteNotFoundTest() {
        Mockito.when(customerRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> customerService.delete(expected));
        Assertions.assertEquals(Message.CUSTOMER_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }
}