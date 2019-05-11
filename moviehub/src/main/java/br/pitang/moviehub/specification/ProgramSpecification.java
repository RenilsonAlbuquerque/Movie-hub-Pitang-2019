package br.pitang.moviehub.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.assertj.core.util.Arrays;
import org.springframework.data.jpa.domain.Specification;

import br.pitang.moviehub.models.Program;
import lombok.Builder;


public class ProgramSpecification {

	

	public static Specification<Program> searchProgram(HashMap<String, Object> params){
		
		return new Specification<Program>() {
			@Override
			public Predicate toPredicate(Root<Program> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
			
				params.forEach((key, value) -> 
					predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get(key),"%" + value.toString()+ "%"))));
				return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
	

}
