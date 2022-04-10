package com.address.book.app.dao;

import com.address.book.app.utils.PersonUtils;
import com.address.book.app.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PersonDaoImpl implements PersonDao{

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PersonUtils personUtils;

    @Override
    public Person savePerson(Person person) {

        KeyHolder addressHolder = new GeneratedKeyHolder();
        int addressRecord = jdbcTemplate.update(PersonUtils.saveAddress(person), addressHolder);
        int addressId = addressHolder.getKey().intValue();

        KeyHolder contactHolder = new GeneratedKeyHolder();
        int contactRecord = jdbcTemplate.update(PersonUtils.saveContact(person), contactHolder);
        int contactId = contactHolder.getKey().intValue();

        person.getPersonContact().setId(contactId);
        person.getPersonAddress().setId(addressId);

        KeyHolder personHolder = new GeneratedKeyHolder();
        int record = jdbcTemplate.update(PersonUtils.savePerson(person), personHolder);
        int personId = personHolder.getKey().intValue();
        person.setId(personId);

        return person;
    }

    @Override
    public List<Person> findPerson() {
        return personUtils.findAllPersons();
    }

    @Override
    public Person findPerson(Integer id) {
        return personUtils.findPersonById(id);
    }

    @Override
    public boolean removePerson(Integer id) {
        return personUtils.removePersonById(id);
    }

    @Override
    public List<Person> findPersonsSearchKey(String search) {
        return personUtils.findPersonsBySearchKey(search);
    }

    @Override
    public Person updatePerson(Person person) {
        Person person1 = findPerson(person.getId());
        person.setId(person1.getId());
        person.getPersonAddress().setId(person1.getPersonAddress().getId());
        person.getPersonContact().setId(person1.getPersonContact().getId());

        return personUtils.updatePerson(person);
    }
}
