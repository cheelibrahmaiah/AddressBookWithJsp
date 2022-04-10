package com.address.book.app.service;

import com.address.book.app.dao.PersonDao;
import com.address.book.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonDao personDao;

    @Override
    public Person savePerson(Person person) {
        return personDao.savePerson(person);
    }

    @Override
    public List<Person> findPerson() {
        return personDao.findPerson();
    }

    @Override
    public Person findPerson(Integer id) {
        return personDao.findPerson(id);
    }

    @Override
    public boolean removePerson(Integer id) {
        return personDao.removePerson(id);
    }

    @Override
    public List<Person> findPersonsSearchKey(String search) {
        return personDao.findPersonsSearchKey(search);
    }

    @Override
    public Person updatePerson(Person person) {
        return personDao.updatePerson(person);
    }

}
