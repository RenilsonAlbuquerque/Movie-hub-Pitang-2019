package br.pitang.moviehub.utils;

import java.util.List;

import org.springframework.data.domain.Page;

import br.pitang.moviehub.dto.CustomPage;

public class Utils {
	
	public static CustomPage<?> convertPage(Page inputPage){
		return CustomPage.builder().elements(inputPage.getContent())
									.currentPageNumber
											(inputPage.getNumber() +1)
									.totalOfPages(inputPage.getTotalPages())
									.size(inputPage.getSize())
									.first(inputPage.isFirst())
									.last(inputPage.isLast())
									.build();
	}
	public static CustomPage<?> convertPage(Page inputPage, List<Object> elements){
		return CustomPage.builder().elements(elements)
									.currentPageNumber
											(inputPage.getNumber() +1 )
									.totalOfPages(inputPage.getTotalPages())
									.size(inputPage.getSize())
									.first(inputPage.isFirst())
									.last(inputPage.isLast())
									.build();
	}
	
	
}
