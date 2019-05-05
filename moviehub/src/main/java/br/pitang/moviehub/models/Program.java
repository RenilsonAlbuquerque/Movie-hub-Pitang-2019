package br.pitang.moviehub.models;



import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;


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

    @NotEmpty
    @Column(name = "pro_cl_description", nullable = false)
    private String description;

    @Column(name = "pro_cl_country")
    private String country;

    @Column(name = "pro_cl_linguage")
    private String language;

    @Column(name = "pro_cl_releaseYear")
    private Long releaseYear;

    @Column(name = "pro_cl_duration")
    private double durationInMinutes;

    @ManyToMany(targetEntity = Genere.class)
    @JoinTable(name = "mtm_program_generes",
            joinColumns = @JoinColumn(name = "mtm_gen_prog_progId", referencedColumnName = "pro_cl_id"),
            inverseJoinColumns =  @JoinColumn(name = "mtm_gen_prog_genId", referencedColumnName = "gen_cl_id"))
    private List<Genere> generes;


    @Column(name = "pro_cl_vote_average")
    private Double voteAverage;

    @Column(name = "pro_cl_vote_count")
    private Long voteCount;

    @Column(name = "pro_cl_backdrop_path")
    private String backdropPath;

}
