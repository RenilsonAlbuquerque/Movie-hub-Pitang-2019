package br.pitang.moviehub.models;


import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_serie")
public class Serie extends Program{

    @OneToMany(targetEntity = Season.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Season> seasons;


}
