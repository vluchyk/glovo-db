package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.AddressConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.AddressRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {
    @Mock
    private AddressRepository addressRepository;

    private AddressService addressService;
    private AddressDto expected;
    private AddressDto updated;

    @BeforeEach
    public void init() {
        addressService = new AddressService(addressRepository);
        expected = AddressDto.builder().id(1).number("1000").street("Drayton str").apartmentNumber(7).build();
        updated = AddressDto.builder().id(1).number("2000").street("Seven Hills str").apartmentNumber(1).build();
    }

    @Test
    public void getTest() throws ObjectNotFoundException {
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.of(AddressConverter.toEntity(expected)));

        AddressDto result = addressService.get(anyInt());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void getNotFoundTest() {
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> addressService.get(anyInt()));
        Assertions.assertEquals(Message.ADDRESS_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void saveTest() {
        Mockito.when(addressRepository.save(any())).thenReturn(AddressConverter.toEntity(expected));

        AddressDto result = addressService.save(new AddressDto());
        Assertions.assertEquals(expected, result);
    }

    @Test
    public void updateTest() throws ObjectNotFoundException {
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.of(AddressConverter.toEntity(expected)));
        Mockito.when(addressRepository.save(any())).thenReturn(AddressConverter.toEntity(updated));

        AddressDto result = addressService.update(new AddressDto());
        Assertions.assertEquals(updated, result);
    }

    @Test
    public void updateNotFoundTest() {
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> addressService.update(new AddressDto()));
        Assertions.assertEquals(Message.ADDRESS_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }

    @Test
    public void deleteTest() throws ObjectNotFoundException {
        int wantedNumberOfInvocations = 1;
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.of(AddressConverter.toEntity(new AddressDto())));

        addressService.delete(new AddressDto());

        verify(addressRepository, times(wantedNumberOfInvocations)).delete(AddressConverter.toEntity(new AddressDto()));
    }

    @Test
    public void deleteNotFoundTest() {
        Mockito.when(addressRepository.findById(any())).thenReturn(Optional.empty());

        ObjectNotFoundException objectNotFoundException = Assertions.assertThrows(ObjectNotFoundException.class, () -> addressService.delete(new AddressDto()));
        Assertions.assertEquals(Message.ADDRESS_NOT_FOUND.getMessage(), objectNotFoundException.getMessage());
    }
}