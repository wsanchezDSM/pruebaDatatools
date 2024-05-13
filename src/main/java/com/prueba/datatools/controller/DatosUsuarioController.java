package com.prueba.datatools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.prueba.datatools.models.DatosUsuarioDTO;
import com.prueba.datatools.service.DatosUsuarioService;

@RestController
@RequestMapping("/api/crud/")
public class DatosUsuarioController {

	
	@Autowired
	private DatosUsuarioService datosUsuarioService;
	
	@GetMapping("obtenerDatos/")
	public HashMap<String, Object> obtenerDatos(){
		return datosUsuarioService.obtenerDatos();
	}
	
	@PostMapping("guardarDatos/")
	public HashMap<String, String> guardarDatos(@RequestBody DatosUsuarioDTO body){
		return datosUsuarioService.guardarDatos(body);
	}
	
	@PutMapping("actualizarDatos/")
	public HashMap<String, String> actualizarDatos(@RequestBody DatosUsuarioDTO body){
		return datosUsuarioService.actualizarDatos(body);
	}
	
	@DeleteMapping("eliminarDatos/")
	public HashMap<String, String> eliminarDatos(@RequestParam String id){
		return datosUsuarioService.eliminarDatos(id);
	}
}
