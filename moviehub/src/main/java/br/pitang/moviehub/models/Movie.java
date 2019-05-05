package br.pitang.moviehub.models;


import lombok.Builder;
import lombok.Data;


import javax.persistence.*;
import java.util.List;



@Entity
@Data
@Table(name="tb_movie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movie extends Program{

    @Column(name = "mov_tb_tagline", length = 400)
    private String tagline;


    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = CastMovie.class)
    private List<CastMovie> cast;

    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL, orphanRemoval = true,targetEntity = CrewMovie.class)
    private List<CrewMovie> crew;

    @Builder
    public Movie(String title, String description, String country, String language, Long releaseyear,
                 double durationInMinutes, List<Genere> generes, Double voteAverage, Long voteCount,
                 String backdropPath, String tagline, List<CastMovie> cast, List<CrewMovie> crew){
        super();
        super.setTitle(title);
        super.setDescription(description);
        super.setCountry(country);
        super.setLanguage(language);
        super.setReleaseYear(releaseyear);
        super.setDurationInMinutes(durationInMinutes);
        super.setGeneres(generes);
        super.setVoteAverage(voteAverage);
        super.setVoteCount(voteCount);
        super.setBackdropPath(backdropPath);
        this.setTagline(tagline);
        this.setCast(cast);
        this.setCrew(crew);
    }


}
