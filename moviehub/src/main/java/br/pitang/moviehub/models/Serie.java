package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_serie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Serie extends Program{

    @OneToMany(targetEntity = Season.class,mappedBy = "serie",cascade = CascadeType.ALL , orphanRemoval = true)
    private List<Season> seasons;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CastSerie> cast;

    @Builder
    public Serie(String title, String description, String country, String language, Long releaseyear,
                 double durationInMinutes, List<Genere> generes, Double voteAverage, Long voteCount,
                 String backdropPath, List<CastSerie> cast, List<Season> seasons){
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
        this.setSeasons(seasons);
        this.setCast(cast);
    }


}
