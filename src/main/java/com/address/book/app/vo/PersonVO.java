package com.address.book.app.vo;

import com.address.book.app.entity.Address;
import com.address.book.app.entity.Contact;
import com.address.book.app.entity.Person;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonVO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String mobileNumber;
    private String landLineNumber;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public Person deepCopyToPerson() {
        Person person = new Person();
        person.setId(this.getId());
        person.setFirstName(this.getFirstName());
        person.setLastName(this.getLastName());

        Address address = new Address();
        address.setStreet(this.getStreet());
        address.setCity(this.getCity());
        address.setState(this.getState());
        address.setCountry(this.getCountry());
        address.setZipCode(this.getZipCode());
        person.setPersonAddress(address);

        Contact contact = new Contact();
        contact.setEmailAddress(this.getEmailAddress());
        contact.setMobileNumber(this.getMobileNumber());
        contact.setLandLineNumber(this.getLandLineNumber());
        person.setPersonContact(contact);

        return person;
    }

    public static PersonVO deepCopyFromPerson(Person person) {
        PersonVO personVO = new PersonVO(person.getId(), person.getFirstName(), person.getLastName(), person.getPersonContact().getEmailAddress(),
                person.getPersonContact().getMobileNumber(),person.getPersonContact().getLandLineNumber(),
                person.getPersonAddress().getStreet(),person.getPersonAddress().getCity(),person.getPersonAddress().getState(),
                person.getPersonAddress().getCountry(), person.getPersonAddress().getZipCode());
        return personVO;
    }
}
