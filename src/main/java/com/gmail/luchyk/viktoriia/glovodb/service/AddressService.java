package com.gmail.luchyk.viktoriia.glovodb.service;

import com.gmail.luchyk.viktoriia.glovodb.converter.AddressConverter;
import com.gmail.luchyk.viktoriia.glovodb.dto.AddressDto;
import com.gmail.luchyk.viktoriia.glovodb.entity.AddressEntity;
import com.gmail.luchyk.viktoriia.glovodb.enums.Message;
import com.gmail.luchyk.viktoriia.glovodb.exception.ObjectNotFoundException;
import com.gmail.luchyk.viktoriia.glovodb.repository.AddressRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AddressService {
    private AddressRepository addressRepository;

    public AddressDto get(int id) throws ObjectNotFoundException {
        return addressRepository.findById(id).map(AddressConverter::toDto).orElseThrow(() -> new ObjectNotFoundException(Message.ADDRESS_NOT_FOUND.getMessage()));
    }

    public AddressDto save(AddressDto addressDto) {
        AddressEntity addressEntity = AddressConverter.toEntity(addressDto);
        return AddressConverter.toDto(addressRepository.save(addressEntity));
    }

    public AddressDto update(AddressDto addressDto) throws ObjectNotFoundException {
        AddressEntity addressEntity = addressRepository.findById(addressDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.ADDRESS_NOT_FOUND.getMessage()));
        addressEntity.setStreet(addressDto.getStreet());
        addressEntity.setNumber(addressDto.getNumber());
        addressEntity.setApartmentNumber(addressDto.getApartmentNumber());
        return AddressConverter.toDto(addressRepository.save(addressEntity));
    }

    public void delete(AddressDto addressDto) throws ObjectNotFoundException {
        AddressEntity addressEntity = addressRepository.findById(addressDto.getId()).orElseThrow(() -> new ObjectNotFoundException(Message.ADDRESS_NOT_FOUND.getMessage()));
        addressRepository.delete(addressEntity);
    }
}