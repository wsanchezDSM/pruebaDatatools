package com.prueba.datatools.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name ="datos_personales", schema = "dbo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper=false)
public class DatosUsuario extends Auditoria {

	 @Id
	 @Column(name = "identificacion")
	 private String identificacion;
	 
	 @Column(name = "tipo_identificacion")
	 private String tipoIdentificacion;
	 
	 @Column(name = "nombres")
	 private String nombres;
	 
	 @Column(name = "apellidos")
	 private String apellidos;
	 
	 @Column(name = "edad")
	 private Long edad;
	 
	 @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "datosUsuario")
	 @org.springframework.lang.Nullable
	 @ToString.Exclude
	 private List<RolesPersonal> lsRolesPersonals;
	 
	
}
