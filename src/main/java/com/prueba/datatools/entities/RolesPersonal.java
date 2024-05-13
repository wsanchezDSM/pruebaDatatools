package com.prueba.datatools.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="roles_personal", schema = "dbo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class RolesPersonal extends Auditoria{
	
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "id", nullable = false)
	 private Long id;
	 
	 @Column(name = "descripcion")
	 private String descripcion;
	 
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name = "identificacion")
	 private DatosUsuario datosUsuario;
		

}
