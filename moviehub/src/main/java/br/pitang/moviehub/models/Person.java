package br.pitang.moviehub.models;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
@Entity
@Table(name="tb_person")
@AllArgsConstructor
@NoArgsConstructor
public  class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "per_cl_name", nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "per_cl_biography", nullable = false)
    private String biography;


    @NotEmpty
    @Column(name = "per_cl_height", nullable = false)
    private String height;

    @NotEmpty
    @Column(name = "per_cl_city", nullable = false)
    private String birthCity;

    @NotEmpty
    @Column(name = "per_cl_country", nullable = false)
    private String countryWhereLive;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "per_cl_genre")
    private Genre genre;

    @Column(name = "per_cl_profile_picture")
    private String profilePiturePath;

    /*

    @OneToMany(targetEntity = Cast.class, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cast> cast;
    */

}
