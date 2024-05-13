package com.prueba.datatools.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prueba.datatools.entities.DatosUsuario;

@Repository
public interface DatosUsuarioRepository extends JpaRepository<DatosUsuario,String> {

	Optional<DatosUsuario> findByIdentificacion(String identificacion);
	
}
