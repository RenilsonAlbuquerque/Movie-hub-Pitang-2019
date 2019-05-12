package br.pitang.moviehub.dto;

import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomPage<T> {
	
	private List<T> elements;
	private int currentPageNumber;
	private int totalOfPages;
	private int size;
	private boolean first;
	private boolean last;

}
