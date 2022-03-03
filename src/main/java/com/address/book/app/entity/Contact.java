package com.address.book.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON_CONTACTS")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "EMAIL")
    private String emailAddress;

    @Column(name = "MOBILE")
    private String mobileNumber;

    @Column(name = "LAND_LINE")
    private String landLineNumber;
}
