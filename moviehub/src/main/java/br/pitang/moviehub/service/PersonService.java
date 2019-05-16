package br.pitang.moviehub.service;

import java.util.HashMap;
import java.util.stream.Collectors;

import br.pitang.moviehub.contracts.services.IPersonService;
import br.pitang.moviehub.dto.*;
import br.pitang.moviehub.mapper.PersonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.repository.PersonDAO;
import br.pitang.moviehub.specification.PersonSpecification;
import br.pitang.moviehub.utils.PaginationGenerator;

@Service
public class PersonService implements IPersonService {
	
	 @Autowired
	 private PersonDAO personDAO;

	 @Autowired
	 private PersonMapper personMapper;

	 @SuppressWarnings("unchecked")
	public CustomPage<PersonOverviewDTO> listPersonOverview(PaginationFilter filter){

		 Page<Person> page = personDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(), Sort.by("popularity").descending()));
	        return (CustomPage<PersonOverviewDTO>) PaginationGenerator.convertPage(
	        		page,
	        		page.stream().map( person -> this.personMapper.entityToOverview(person))
	        		.collect(Collectors.toList()));
	 }
	 public PersonDetailDTO findPersonById(Long id) throws ResourceNotFoundException{
	 		Person person = personDAO.findById(id).
					orElseThrow(() -> new ResourceNotFoundException("O filme com id " + id + " não foi encontrado"));
	        return personMapper.entityToDetail(person);

	 }
	 
	 @SuppressWarnings("unchecked")
	 public CustomPage<PersonOverviewDTO> filterPerson(HashMap<String, Object> params,PaginationFilter filter){
	    Specification<Person> specification = PersonSpecification.searchPerson(params);
	    
	    Page<Person> page = this.personDAO.findAll(specification,PageRequest.of(filter.getPage() -1, 
				filter.getSize(),Sort.by("popularity").descending()));
	    return (CustomPage<PersonOverviewDTO>) PaginationGenerator.convertPage(
        		page,
        		page.stream().map( person -> this.personMapper.entityToOverview(person))
        		.collect(Collectors.toList()));
	    }

}
