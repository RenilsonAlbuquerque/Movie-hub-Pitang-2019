package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_season")
@AllArgsConstructor
@NoArgsConstructor
public class Season {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sea_tb_name")
    private String name;

    @Column(name = "sea_tb_overview")
    private String overview;

    @Column(name = "sea_tb_air_date")
    private Date airDate;

    @Column(name = "sea_tb_episodes")
    private int episodeCount;

    @Column(name = "sea_tb_poster")
    private String posterPath;

    @ManyToOne(targetEntity = Serie.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="sea_tb_serieId", referencedColumnName="pro_cl_id")
    private Serie serie;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Season season = (Season) o;
        return Objects.equals(id, season.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
