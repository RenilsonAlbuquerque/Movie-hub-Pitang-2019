package br.pitang.moviehub.specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;

import br.pitang.moviehub.models.Program;

public class ProgramSpecificationBuilder {
	 private final List<SearchCriteria> params;
	 
	    public ProgramSpecificationBuilder() {
	        params = new ArrayList<SearchCriteria>();
	    }
	 
	    public ProgramSpecificationBuilder with(String key, String operation, Object value) {
	        params.add(new SearchCriteria(key, operation, value));
	        return this;
	    }
	
	   
}
