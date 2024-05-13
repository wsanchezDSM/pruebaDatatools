package com.prueba.datatools.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;


@Data
@MappedSuperclass
public abstract class Auditoria{

	@Column(name="fecha_creacion")
	private Date fechaCreacion;
	
	@Column(name="fecha_modificacion")
	private Date fechaModificacion;
	
	@Column(name="es_activo")
	private String esActivo;
}
