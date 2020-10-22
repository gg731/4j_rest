package auth.controller;

import auth.AuthApplication;
import auth.domain.Person;
import auth.repository.PersonRepository;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = AuthApplication.class)
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private PersonRepository persons;


    @Test
    void findAll() {
        RestTemplate template = new RestTemplate();

        Person[] person = template.getForObject("http://localhost:8080/person/", Person[].class);
        Iterable<Person> result = Arrays.asList(person);

        when(persons.findAll()).thenReturn(result);

        assertThat(result, is(persons.findAll()));
    }

    @Test
    void findById() {
        RestTemplate template = new RestTemplate();
        Person person = template.getForObject("http://localhost:8080/person/2", Person.class);
        assertThat(person.getId(), is(2));
    }

    @Test
    void create() throws URISyntaxException {
        RestTemplate template = new RestTemplate();
        Person person = new Person("login_test", "pas_test");
        URI uri = new URI("http://localhost:8080/person/");

        ResponseEntity result = template.postForEntity(uri, person, String.class);

        assertThat(result.getStatusCodeValue(), is(201));
    }

    @Test
    void delete() throws Exception {
        this.mock.perform(MockMvcRequestBuilders
                .delete("http://localhost:8080/person/{id}", "1")
                .contentType(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk());
    }
}