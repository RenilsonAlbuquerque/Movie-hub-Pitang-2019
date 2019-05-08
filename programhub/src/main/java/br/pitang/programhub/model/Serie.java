package br.pitang.programhub.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "tb_serie")
public class Serie {

    @Id
    @Column(name = "ser_cl_id")
    @GeneratedValue
    private Long id;

    @Column(name = "ser_cl_name")
    private String name;

    @OneToMany(mappedBy = "serie", orphanRemoval = true)
    private List<Season> seasons;

    public Serie(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }
}
