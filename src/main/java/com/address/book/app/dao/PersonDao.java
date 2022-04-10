package com.address.book.app.dao;

import com.address.book.app.entity.Person;

import java.util.List;

public interface PersonDao {
    public Person savePerson(Person person);
    public List<Person> findPerson();
    public Person findPerson(Integer id);
    public boolean removePerson(Integer id);
    public List<Person> findPersonsSearchKey(String search);
    public Person updatePerson(Person person);
}
