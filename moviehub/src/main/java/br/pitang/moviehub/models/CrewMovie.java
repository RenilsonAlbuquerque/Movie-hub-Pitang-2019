package br.pitang.moviehub.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Builder
@Table(name="mtm_crew_movie")
@AllArgsConstructor
@NoArgsConstructor
public class CrewMovie implements Serializable {

    @EmbeddedId
    private CrewID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("programId")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @Column(name = "cre_mtm_department")
    private String department;

}
