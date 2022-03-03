package com.address.book.app.controller;

import com.address.book.app.entity.Person;
import com.address.book.app.response.ResponseDto;
import com.address.book.app.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/person/")
public class PersonController {

    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity savePerson(@RequestBody Person person) {
        Person savedPerson = personService.savePerson(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }
    @GetMapping
    public ResponseEntity findAllPerson() {
        List<Person> persons = personService.findPerson();
        return ResponseEntity.ok(persons);
    }
    @GetMapping("{id}")
    public ResponseEntity findPerson(@PathVariable Integer id) {
        Person person = personService.findPerson(id);
        return ResponseEntity.ok(person);
    }
    @DeleteMapping("{id}")
    public ResponseEntity removePerson(@PathVariable Integer id) {
        ResponseDto responseDto = personService.removePerson(id);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }
}
