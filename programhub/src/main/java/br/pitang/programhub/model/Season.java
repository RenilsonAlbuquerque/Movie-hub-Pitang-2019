package br.pitang.programhub.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;



@Entity
@Table(name = "tb_season")
public class Season {

    @Id
    @Column(name = "sea_cl_id")
    @GeneratedValue
    private Long id;

    @Column(name = "sea_cl_name")
    private String name;

    @Column(name = "sea_cl_overview", length = 2500)
    private String overview;



    @Column(name = "sea_cl_episodes")
    private int episodeCount;

    @Column(name = "sea_cl_poster")
    private String posterPath;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ser_key_id")
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

    public Season(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }
}
