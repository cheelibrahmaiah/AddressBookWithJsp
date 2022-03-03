package com.address.book.app.service;

import com.address.book.app.entity.Person;
import com.address.book.app.response.ResponseDto;

import java.util.List;

public interface PersonService {
    public Person savePerson(Person person);
    public List<Person> findPerson();
    public Person findPerson(Integer id);
    public ResponseDto removePerson(Integer id);
}
