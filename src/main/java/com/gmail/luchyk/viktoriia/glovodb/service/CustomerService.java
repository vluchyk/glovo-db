package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.AddressConverter;
import com.gmail.luchyk.viktoriia.glovodb.converter.CustomerConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
import com.gmail.luchyk.viktoriia.glovodb.entity.CustomerEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class CustomerService {
    CustomerRepository customerRepository;

    public CustomerDto get(int id) throws ObjectNotFoundException {
        return customerRepository.findById(id).map(CustomerConverter::toDto).orElseThrow(() -> new ObjectNotFoundException(Message.CUSTOMER_NOT_FOUND.getMessage()));
    }

    public CustomerDto save(CustomerDto customerDto) {
        CustomerEntity customerEntity = CustomerConverter.toEntity(customerDto);
        customerRepository.save(customerEntity);
        return CustomerConverter.toDto(customerEntity);
    }

    public CustomerDto update(CustomerDto customerDto) throws ObjectNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.CUSTOMER_NOT_FOUND.getMessage()));
        customerEntity.setFirstName(customerDto.getFirstName());
        customerEntity.setMiddleName(customerDto.getMiddleName());
        customerEntity.setLastName(customerDto.getLastName());
        customerEntity.setPhone(customerDto.getPhone());
        customerEntity.setRegistrationAddress(customerDto.getRegistrationAddress());
        customerEntity.setDeliveryAddresses(customerDto.getDeliveryAddresses());
        customerRepository.save(customerEntity);
        return CustomerConverter.toDto(customerEntity);
    }

    public CustomerDto patch(int id, AddressDto addressDto) throws ObjectNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Message.CUSTOMER_NOT_FOUND.getMessage()));
        Set<AddressEntity> deliveryAddresses = customerEntity.getDeliveryAddresses();
        deliveryAddresses.add(AddressConverter.toEntity(addressDto));
        customerRepository.save(customerEntity);
        return CustomerConverter.toDto(customerEntity);
    }

    public CustomerDto deleteDeliveryAddress(int id, AddressDto addressDto) throws ObjectNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException(Message.CUSTOMER_NOT_FOUND.getMessage()));
        Set<AddressEntity> deliveryAddresses = customerEntity.getDeliveryAddresses();
        deliveryAddresses.remove(AddressConverter.toEntity(addressDto));
        customerRepository.save(customerEntity);
        return CustomerConverter.toDto(customerEntity);
    }

    public void delete(CustomerDto customerDto) throws ObjectNotFoundException {
        CustomerEntity customerEntity = customerRepository.findById(customerDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.CUSTOMER_NOT_FOUND.getMessage()));
        customerRepository.delete(customerEntity);
    }
}