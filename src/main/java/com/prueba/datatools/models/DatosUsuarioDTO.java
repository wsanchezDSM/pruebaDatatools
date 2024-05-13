package com.prueba.datatools.models;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DatosUsuarioDTO {

	private String identificacion;
	private String tipoIdentificacion;
	private String nombres;
	private String apellidos;
	private Long edad;
	private Date fechaModificacion;
	private Date fechaCreacion;
	private String esActivo;
	private List<RolesPersonalDTO> lsRoles;
}
