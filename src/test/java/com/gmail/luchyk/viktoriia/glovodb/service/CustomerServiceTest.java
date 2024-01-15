package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.dto.CustomerDto;
import com.gmail.luchyk.viktoriia.glovodb.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    private CustomerService customerService;
    private CustomerDto expected;

    @BeforeEach
    public void init() {
        customerService = new CustomerService(customerRepository);
        expected = CustomerDto.builder().id(1).firstName("John").middleName("A.").lastName("Smith").phone("+380501231212").build();
    }
}