package com.arquetipo.inicial.model.common;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AuditoriaAbstractEntity {
    @Column(name ="FECHA_ALTA")
    private Date fechaAlta;
    @Column(name="FECHA_BAJA")
    private Date fechaBaja;
    @Column(name ="FECHA_MODIFICA")
    private Date fechaModifica;
    @Column(name="IND_ACTIVO")
    private Boolean indActivo;
}
