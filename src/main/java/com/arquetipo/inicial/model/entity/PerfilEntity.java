package com.arquetipo.inicial.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PERFILES")
@Entity
public class PerfilEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PERFIL")
    private Integer idPerfil;

    @Column(name = "NOMBRE")
    private String nombre;


    @OneToMany(mappedBy = "idPerfil")
    private List<UsuarioEntity> usuarios;

}
