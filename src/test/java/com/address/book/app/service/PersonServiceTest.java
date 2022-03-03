package com.address.book.app.service;

import com.address.book.app.entity.Person;
import com.address.book.app.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static com.address.book.app.utils.TestUtils.person;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSavePerson() {
        //given
        given(personRepository.save(person)).willReturn(person);

        //calling method under the test
        Person personResult = personService.savePerson(person);

        //assert
        assertPerson(personResult);

        //verify that repository was called
        verify(personRepository, times(1)).save(person);
    }

    @Test
    public void testFindPerson() {
        //given
        given(personRepository.findById(1)).willReturn(Optional.of(person));

        //calling method under the test
        Person personResult = personService.findPerson(person.getId());

        //assert
        assertPerson(personResult);

        //verify that repository was called
        verify(personRepository, times(1)).findById(person.getId());

    }

    @Test
    public void testRemovePerson() {
        //calling method under the test
        personService.removePerson(1);

        //verify that repository was called
        verify(personRepository, times(1)).deleteById(1);
    }

    private void assertPerson(Person person) {
        assertThat(person.getId()).isInstanceOf(Integer.class);
        assertThat(person.getId()).isEqualTo(1);
        assertThat(person.getFirstName()).isEqualTo("Brahmaiah");
        assertThat(person.getLastName()).isEqualTo("Cheeli");
        assertThat(person.getPersonAddress()).isNotNull();
        assertThat(person.getPersonAddress().getId()).isInstanceOf(Integer.class);
        assertThat(person.getPersonAddress().getId()).isEqualTo(1);
        assertThat(person.getPersonAddress().getCity()).isEqualTo("Hyderabad");
        assertThat(person.getPersonAddress()).isNotNull();
        assertThat(person.getPersonContact().getId()).isInstanceOf(Integer.class);
        assertThat(person.getPersonContact().getId()).isEqualTo(1);
        assertThat(person.getPersonContact().getEmailAddress()).isEqualTo("test@gmail.com");

    }
}
