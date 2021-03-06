package br.pitang.moviehub.specification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.pitang.moviehub.models.Person;


public class PersonSpecification {
	
	public static Specification<Person> searchPerson(HashMap<String, Object> params){
		
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<Predicate>();
			
				params.forEach((key, value) -> 
					predicates.add(criteriaBuilder.or(criteriaBuilder.like(root.get(key),"%" + value.toString()+ "%"))));
				return criteriaBuilder.or(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}

}
