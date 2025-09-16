package com.elearningplatform.entity;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressInfo {
	
    @Size(max = 50, message = "Country cannot exceed 50 characters")
    private String country;

    @Size(max = 50, message = "State cannot exceed 50 characters")
    private String state;

    @Size(max = 50, message = "City cannot exceed 50 characters")
    private String city;

    @Size(max = 100, message = "Street cannot exceed 100 characters")
    private String street;

    @Size(max = 20, message = "Postal code cannot exceed 20 characters")
    private String postalCode;
}
