package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_serie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Serie extends Program{

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "mtm_serie_generes",
            joinColumns = @JoinColumn(name = "mtm_gen_prog_progId", referencedColumnName = "pro_cl_id"),
            inverseJoinColumns =  @JoinColumn(name = "mtm_gen_prog_genId", referencedColumnName = "gen_cl_id"))
    private List<GenereSerie> generes;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<Season> seasons;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CastSerie> cast;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<CrewSerie> crew;

    @Builder
    public Serie(String title, String description, String country, String language, Long releaseyear,
                 double durationInMinutes, List<GenereSerie> generes, Double voteAverage, Long voteCount,
                 String backdropPath,Double popularity, List<CastSerie> cast, List<Season> seasons, List<CrewSerie> crew){
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
        super.setPopularity(popularity);
        this.setGeneres(generes);
        this.setSeasons(seasons);
        this.setCast(cast);
        this.setCrew(crew);
    }


}
