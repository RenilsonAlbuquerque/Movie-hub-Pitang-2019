package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Builder
@Table(name = "tb_genere")
@AllArgsConstructor
@NoArgsConstructor
public class Genere {

    @Id
    @Column(name = "gen_cl_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "gen_cl_name", nullable = false)
    private String name;

}
