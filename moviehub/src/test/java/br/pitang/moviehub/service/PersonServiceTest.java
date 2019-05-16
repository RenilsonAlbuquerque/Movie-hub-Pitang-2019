package br.pitang.moviehub.service;

import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.repository.PersonDAO;
import br.pitang.moviehub.utils.PersonGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonDAO personDAO;

    private Page<Person> peopleInRepository;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        peopleInRepository = new PageImpl<>(PersonGenerator.generatePeople(10));
        Whitebox.setInternalState(personService, "personDAO",personDAO );
    }
    @Test
    public void listPeopleOverviewTest(){
        PaginationFilter filter = new PaginationFilter(1,10);
        Mockito.when(personDAO.findAll(Mockito.any(PageRequest.class)))
                .thenReturn(peopleInRepository);
        assertEquals(9,personService.listPersonOverview(filter).getElements().size());
    }

    @Test
    public void findPersonByValidId(){
        Mockito.when(personDAO.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(peopleInRepository.getContent().get(0)));
        PersonDetailDTO found = personService.findPersonById(1L);
        assertEquals(Long.valueOf(1), found.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findMovieByInexistentId(){
        Mockito.when(personDAO.findById(Mockito.anyLong())).thenReturn(Optional.empty());
        personService.findPersonById(1L);
    }

    @Test
    public void searchMovieByName(){
        Mockito.when(personDAO.findAll(Mockito.any(Specification.class),Mockito.any(PageRequest.class)))
                .thenReturn(peopleInRepository);
        PaginationFilter filter = new PaginationFilter(1,10);
        HashMap<String,Object> queryParams = new HashMap<String,Object>();
        queryParams.put("title", "Avengers");

        CustomPage<PersonOverviewDTO> output = personService.filterPerson(queryParams,filter);
        assertEquals(9,output.getElements().size());
    }
}
