package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Builder
@Entity
@Table(name = "tb_season")
@AllArgsConstructor
@NoArgsConstructor
public class Season {

    @Id
    @Column(name = "sea_cl_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "sea_cl_name")
    private String name;

    @Column(name = "sea_cl_overview", length = 2500)
    private String overview;

    @Column(name = "sea_cl_air_date")
    private LocalDate airDate;

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
}
