package com.leonjr.springenterprisebackend.models.mappers;

import java.util.function.Function;

import org.springframework.stereotype.Service;
import com.leonjr.springenterprisebackend.dto.AddressDto;
import com.leonjr.springenterprisebackend.models.Address;

@Service
public class AddressMapper implements Function<Address, AddressDto> {

    @Override
    public AddressDto apply(Address arg0) {
       return new AddressDto(
           arg0.getUuid(),
           arg0.getStreet(),
           arg0.getNumber(),
           arg0.getComplement(),
           arg0.getNeighborhood(),
           arg0.getCity(),
           arg0.getState(),
           arg0.getZipCode()
       );
    }
    
}
