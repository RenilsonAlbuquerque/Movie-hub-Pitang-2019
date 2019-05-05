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
@Table(name="mtm_crew_serie")
@AllArgsConstructor
@NoArgsConstructor
public class CrewSerie implements Serializable {

    @EmbeddedId
    private CastID id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("programId")
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @MapsId("personId")
    private Person person;

    @Column(name = "cre_mtm_department")
    private String department;

    @Column(name = "cre_mtm_job")
    private String job;
}
