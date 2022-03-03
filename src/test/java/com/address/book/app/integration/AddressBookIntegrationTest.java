package com.address.book.app.integration;

import com.address.book.app.AddressBookApiApplication;
import com.address.book.app.utils.TestUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.address.book.app.utils.TestUtils.API_PERSONS;
import static com.address.book.app.utils.TestUtils.PERSON_DELETED_SUCCESSFULLY;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = AddressBookApiApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AddressBookIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should1SavePerson() throws Exception {
        this.mockMvc.perform(post(API_PERSONS)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(TestUtils.objectToJson())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$.lastName").value("Cheeli"))
                .andReturn();
    }

    @Test
    public void should2FindAllPersons() throws Exception {
        this.mockMvc.perform(get(API_PERSONS)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[0].firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$[0].lastName").value("Cheeli"))
                .andReturn();
    }

    @Test
    public void should3FindPerson() throws Exception {
        this.mockMvc.perform(get(API_PERSONS+1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.firstName").value("Brahmaiah"))
                .andExpect(jsonPath("$.lastName").value("Cheeli"))
                .andReturn();
    }

    @Test
    public void should4RemovePerson() throws Exception {
        this.mockMvc.perform(delete(API_PERSONS+1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").exists())
                .andExpect(jsonPath("$.success").exists())
                .andExpect(jsonPath("$.message").isNotEmpty())
                .andExpect(jsonPath("$.message").value(PERSON_DELETED_SUCCESSFULLY))
                .andReturn();
    }
}
