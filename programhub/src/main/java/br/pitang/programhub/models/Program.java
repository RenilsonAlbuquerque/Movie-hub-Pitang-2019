package br.pitang.moviehub.models;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;



@Data
@MappedSuperclass
public abstract class Program implements Serializable {

    @Id
    @Column(name = "pro_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "pro_cl_title", nullable = false)
    private String title;

 
    @Column(name = "pro_cl_description", length = 2500)
    private String description;

    @Column(name = "pro_cl_country")
    private String country;

    @Column(name = "pro_cl_linguage")
    private String language;

    @Column(name = "pro_cl_releaseYear")
    private Long releaseYear;

    @Column(name = "pro_cl_duration")
    private double durationInMinutes;


    @Column(name = "pro_cl_vote_average")
    private Double voteAverage;

    @Column(name = "pro_cl_vote_count")
    private Long voteCount;

    @Column(name = "pro_cl_backdrop_path")
    private String backdropPath;
    
    @Column(name = "pro_cl_popularity")
    private Double popularity;

}
