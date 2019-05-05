package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.util.List;



@Entity
@Data
@Table(name="tb_movie")
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movie extends Program{

    @Column(name = "mov_tb_tagline", length = 400)
    private String tagline;

    @ManyToMany(targetEntity = GenereMovie.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "mtm_movie_generes",
            joinColumns = @JoinColumn(name = "mtm_gen_mov_progId", referencedColumnName = "pro_cl_id"),
            inverseJoinColumns =  @JoinColumn(name = "mtm_gen_mov_genId", referencedColumnName = "gen_cl_id"))
    private List<GenereMovie> generes;


    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,targetEntity = CastMovie.class)
    private List<CastMovie> cast;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL,targetEntity = CrewMovie.class)
    private List<CrewMovie> crew;

    @Builder
    public Movie(String title, String description, String country, String language, Long releaseyear,
                 double durationInMinutes, List<GenereMovie> generes, Double voteAverage, Long voteCount,
                 String backdropPath, String tagline, List<CastMovie> cast, List<CrewMovie> crew){
        super();
        super.setTitle(title);
        super.setDescription(description);
        super.setCountry(country);
        super.setLanguage(language);
        super.setReleaseYear(releaseyear);
        super.setDurationInMinutes(durationInMinutes);
        super.setVoteAverage(voteAverage);
        super.setVoteCount(voteCount);
        super.setBackdropPath(backdropPath);
        this.setTagline(tagline);
        this.setGeneres(generes);
        this.setCast(cast);
        this.setCrew(crew);
    }


}
