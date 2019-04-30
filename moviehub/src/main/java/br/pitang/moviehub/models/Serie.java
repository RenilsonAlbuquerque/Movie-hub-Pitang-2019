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
@Table(name = "tb_serie")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Serie extends Program{

    @OneToMany(targetEntity = Season.class,mappedBy = "serie",cascade = CascadeType.ALL , orphanRemoval = true)
    private Set<Season> seasons;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CastSerie> cast;


}
