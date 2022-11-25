package com.arquetipo.inicial.model.entity;

import com.arquetipo.inicial.model.common.AuditoriaAbstractEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "USUARIOS")
@Entity
public class UsuarioEntity extends AuditoriaAbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USUARIO")
    private Integer idUsuario;

    @Column(name = "NOMBRE")
    private  String nombre;

    @Column(name = "PATERNO")
    private String paterno;

    @Column(name = "MATERNO")
    private   String materno;

    @Column(name = "NOMBRE_USUARIO")
    private String nombreUsuario;

    @Column(name = "PASSWORD")
    private String password;

    @ManyToOne
    @JoinColumn(name = "ID_PERFIL")
    private PerfilEntity idPerfil;

}
