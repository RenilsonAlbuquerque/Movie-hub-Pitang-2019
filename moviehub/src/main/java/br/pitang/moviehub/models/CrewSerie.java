package br.pitang.moviehub.models;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@Table(name="mtm_crew_serie")
@AllArgsConstructor
@NoArgsConstructor
public class CrewSerie {

    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("programId")
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("personId")
    private Person person;

    @Column(name = "cre_mtm_department")
    private String department;

    @Column(name = "cre_mtm_job")
    private String job;
}
