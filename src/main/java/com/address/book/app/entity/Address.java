package com.address.book.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Address {
    private Integer id;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
