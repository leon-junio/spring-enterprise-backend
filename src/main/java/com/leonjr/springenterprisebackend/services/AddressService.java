package com.leonjr.springenterprisebackend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.leonjr.springenterprisebackend.dto.AddressDto;
import com.leonjr.springenterprisebackend.exceptions.ServiceException;
import com.leonjr.springenterprisebackend.models.Address;
import com.leonjr.springenterprisebackend.models.mappers.AddressMapper;
import com.leonjr.springenterprisebackend.repositories.AddressRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public List<AddressDto> getAll() throws ServiceException {
        try {
            var addresses = addressRepository.findAll();
            return addresses.stream().map(addressMapper).toList();
        } catch (Exception e) {
            throw new ServiceException("Address search failed due to a service exception: " + e.getMessage());
        }
    }

    public AddressDto createAddress(@NonNull Address address) throws ServiceException {
        try {
            address = addressRepository.save(address);
            return addressMapper.apply(address);
        } catch (Exception e) {
            throw new ServiceException("Address creation failed due to a service exception: " + e.getMessage());
        }
    }
}
