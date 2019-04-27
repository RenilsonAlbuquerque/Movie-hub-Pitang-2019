package br.pitang.moviehub.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;



@Entity
@Data
@Table(name="tb_movie")
@AllArgsConstructor
@NoArgsConstructor
public class Movie extends Program{

    @Column(name = "mov_tb_tagline")
    private String tagline;

}
