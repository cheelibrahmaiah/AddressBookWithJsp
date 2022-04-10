package com.address.book.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    private Integer id;

    private String emailAddress;

    private String mobileNumber;

    private String landLineNumber;
}
