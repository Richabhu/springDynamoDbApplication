package com.spring.application.dto;

import com.spring.application.models.enumeration.City;
import com.spring.application.models.enumeration.Country;
import com.spring.application.models.enumeration.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String id;

    private String line1;
    private String line2;
    private City city;
    private State state;
    private String zip;
    private Country country;
}
