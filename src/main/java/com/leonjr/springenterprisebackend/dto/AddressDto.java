package com.leonjr.springenterprisebackend.dto;

import io.micrometer.common.lang.NonNull;

public record AddressDto(
        @NonNull String id,
        @NonNull String street,
        @NonNull String number,
        @NonNull String complement,
        @NonNull String neighborhood,
        @NonNull String city,
        @NonNull String state,
        @NonNull String zipCode
) {
}
