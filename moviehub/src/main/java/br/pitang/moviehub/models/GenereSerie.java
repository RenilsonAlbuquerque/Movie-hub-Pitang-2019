package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
@Builder
@Table(name = "tb_gen_serie")
@AllArgsConstructor
@NoArgsConstructor
public class GenereSerie {

    @Id
    @Column(name = "gen_cl_id")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "gen_cl_name", nullable = false)
    private String name;

}
