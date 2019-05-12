package br.pitang.moviehub.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.PersonOverviewDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.service.PersonService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin
@Api(value = "Person")
@RequestMapping("person")
public class PersonController {

	@Autowired
    private PersonService personService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<PersonOverviewDTO>> listAllOverview(@RequestBody PaginationFilter filter){
        return new ResponseEntity<Page<PersonOverviewDTO>>(personService.listAllPersonsCover(filter), HttpStatus.OK);
    }
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Person> findOneById(@PathVariable long id){
        return new ResponseEntity<Person>(personService.findPersonById(id)
                .orElseThrow(() -> new ResourceNotFoundException("A pessoa com id " + id + " não foi encontrada")), HttpStatus.OK);
    }
    @PostMapping(value = "/filter",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Page<PersonOverviewDTO>> filter(@RequestBody PaginationFilter filter,
    													@RequestParam(required = false) String name){
    	HashMap<String,Object> queryParams = new HashMap<String,Object>();
    	if(name != null) {queryParams.put("name",name);}
    		
    	 return new ResponseEntity<Page
    			 <PersonOverviewDTO>>(personService.filterPerson(queryParams,filter), HttpStatus.OK);
    }
}
