package br.pitang.moviehub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_movie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Movie extends Program{

    @Column(name = "mov_tb_tagline")
    private String tagline;


    @OneToMany(mappedBy = "movie",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CastMovie> cast;


}
