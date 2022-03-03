package com.address.book.app.controller;

import com.address.book.app.utils.TestUtils;
import com.address.book.app.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.address.book.app.utils.TestUtils.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;


    @Test
    public void shouldSavePerson() throws Exception {
        //given
        given(personService.savePerson(person)).willReturn(person);

        //under test
        this.mockMvc.perform(post(API_PERSONS)
                        .contentType(MediaType.APPLICATION_JSON)
                .content(TestUtils.objectToJson())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$.lastName").value("Cheeli"))
                .andReturn();

        //verify that person service was called
        verify(personService, times(1)).savePerson(person);
    }

    @Test
    public void shouldFindAllPerson() throws Exception {
        //given
        given(personService.findPerson()).willReturn(Arrays.asList(person));

        //under test
        this.mockMvc.perform(get(API_PERSONS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$[0].lastName").value("Cheeli"))
                .andReturn();

        //verify that person service was called
        verify(personService, times(1)).findPerson();
    }

    @Test
    public void shouldFindPerson() throws Exception {
        //given
        given(personService.findPerson(1)).willReturn(person);

        //under test
        this.mockMvc.perform(get(API_PERSONS+1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$.lastName").value("Cheeli"))
                .andReturn();

        //verify that person service was called
        verify(personService, times(1)).findPerson(person.getId());
    }

    @Test
    public void shouldRemovePerson() throws Exception {
        //given
        given(personService.removePerson(1)).willReturn(successResponse);

        //under test
        this.mockMvc.perform(delete(API_PERSONS+1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.message").value(PERSON_DELETED_SUCCESSFULLY))
                .andReturn();

        //verify that repository was called
        verify(personService, times(1)).removePerson(1);
    }

    @Test
    public void shouldNotRemovePerson() throws Exception {
        //given
        given(personService.removePerson(1)).willReturn(failureResponse);

        //under test
        this.mockMvc.perform(delete(API_PERSONS+1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.message").value(FAILED_TO_DELETE_PERSON))
                .andReturn();

        //verify that repository was called
        verify(personService, times(1)).removePerson(1);
    }
}
