package com.address.book.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON_ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "STREET")
    private String street;

    @Column(name = "CITY_NAME")
    private String city;

    @Column(name = "STATE_NAME")
    private String state;

    @Column(name = "COUNTRY_NAME")
    private String country;

    @Column(name = "ZIP_CODE")
    private String zipCode;
}
