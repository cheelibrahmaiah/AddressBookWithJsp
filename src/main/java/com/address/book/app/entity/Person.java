package com.address.book.app.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;

    @Column(name = "FIRST_NAME")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String firstName;

    @Column(name = "LAST_NAME")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Address personAddress;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Contact personContact;
}
