package br.pitang.moviehub.service;

import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.pitang.moviehub.dto.CustomPage;
import br.pitang.moviehub.dto.MovieDetailDTO;
import br.pitang.moviehub.dto.MovieOverviewDTO;
import br.pitang.moviehub.dto.PaginationFilter;
import br.pitang.moviehub.dto.PersonOverviewDTO;
import br.pitang.moviehub.exception.ResourceNotFoundException;
import br.pitang.moviehub.models.Person;
import br.pitang.moviehub.models.Program;
import br.pitang.moviehub.repository.MovieDAO;
import br.pitang.moviehub.repository.PersonDAO;
import br.pitang.moviehub.specification.PersonSpecification;
import br.pitang.moviehub.specification.ProgramSpecification;
import br.pitang.moviehub.utils.Utils;

@Service
public class PersonService {
	
	 @Autowired
	 private PersonDAO personDAO;

	 @SuppressWarnings("unchecked")
	public CustomPage<PersonOverviewDTO> listAllPersonsCover(PaginationFilter filter){

		 Page<Person> page = personDAO.findAll(PageRequest.of(filter.getPage() -1, filter.getSize(), Sort.by("popularity").descending()));
	        return (CustomPage<PersonOverviewDTO>) Utils.convertPage(
	        		page,
	        		page.stream().map( person -> PersonOverviewDTO.builder()
	        			.id(person.getId())
	        			.name(person.getName())
	        			.profilePicturePath(person.getProfilePiturePath())
	        			.build())
	        		.collect(Collectors.toList()));
	 }
	 public Optional<Person> findPersonById(Long id) throws ResourceNotFoundException{
	        return personDAO.findById(id);
	 }
	 
	 @SuppressWarnings("unchecked")
	 public CustomPage<PersonOverviewDTO> filterPerson(HashMap<String, Object> params,PaginationFilter filter){
	    Specification<Person> specification = PersonSpecification.searchPerson(params);
	    
	    Page<Person> page = this.personDAO.findAll(specification,PageRequest.of(filter.getPage() -1, 
				filter.getSize(),Sort.by("popularity").descending()));
	    return (CustomPage<PersonOverviewDTO>) Utils.convertPage(
        		page,
        		page.stream().map( person -> PersonOverviewDTO.builder()
        			.id(person.getId())
        			.name(person.getName())
        			.profilePicturePath(person.getProfilePiturePath())
        			.build())
        		.collect(Collectors.toList()));
	    }

}
