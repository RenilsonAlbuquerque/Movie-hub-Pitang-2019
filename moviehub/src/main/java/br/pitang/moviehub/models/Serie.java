package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "tb_serie")
@AllArgsConstructor
@NoArgsConstructor
public class Serie extends Program{

    @OneToMany(targetEntity = Season.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Season> seasons;


}
