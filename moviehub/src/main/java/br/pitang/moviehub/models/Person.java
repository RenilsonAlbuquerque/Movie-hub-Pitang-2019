package br.pitang.moviehub.models;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;


@Entity
@Builder
@Data
@Table(name="tb_person")
@AllArgsConstructor
@NoArgsConstructor
public class Person {


    @Id
    //@GeneratedValue(strategy = GenerationType.)
    private Long id;

    @NotEmpty
    @Column(name = "per_cl_name", nullable = false)
    private String name;

    @Column(name = "per_cl_biography", nullable = false, length = 3000)
    private String biography;


    @Column(name = "per_cl_height")
    private Integer height;


    @Column(name = "per_cl_city", nullable = false)
    private String birthCity;


    @Column(name = "per_cl_country", nullable = false)
    private String countryWhereLive;


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "per_cl_genre")
    private Genre genre;

    @Column(name = "per_cl_profile_picture")
    private String profilePiturePath;



    @OneToMany(mappedBy = "person")
    private List<CastSerie> castSeries;


    @OneToMany(mappedBy = "person")
    private List<CastMovie> castMovie;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
