package br.pitang.moviehub.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "mov_cl_name", nullable = false)
    private String username;

    @NotEmpty
    @Column(name = "mov_cl_password", nullable = false)
    private String password;
}
