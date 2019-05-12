package br.pitang.moviehub.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonOverviewDTO {

	 private Long id;
	 private String name;
	 private String profilePicturePath;
}
