package com.address.book.app.service;

import com.address.book.app.entity.Person;
import com.address.book.app.repository.PersonRepository;
import com.address.book.app.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.address.book.app.response.ResponseDto.prepareResponse;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    public static final String PERSON_DELETED_SUCCESSFULLY = "Person deleted successfully";
    public static final String FAILED_TO_DELETE_PERSON = "Failed to delete person";

    @Override
    public Person savePerson(Person person) {
        return personRepository.save(person);
    }

    @Override
    public List<Person> findPerson() {
        return personRepository.findAll();
    }

    @Override
    public Person findPerson(Integer id) {
        return personRepository.findById(id).orElse(new Person());
    }

    @Override
    public ResponseDto removePerson(Integer id){
        personRepository.deleteById(id);
        Optional<Person> person =  personRepository.findById(id);
        return prepareResponse((!person.isPresent() ? PERSON_DELETED_SUCCESSFULLY:FAILED_TO_DELETE_PERSON), !person.isPresent());
    }
}
