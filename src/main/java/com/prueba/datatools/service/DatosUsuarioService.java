package com.prueba.datatools.service;


import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.prueba.datatools.entities.DatosUsuario;
import com.prueba.datatools.entities.RolesPersonal;
import com.prueba.datatools.models.DatosUsuarioDTO;
import com.prueba.datatools.models.RolesPersonalDTO;
import com.prueba.datatools.repository.DatosUsuarioRepository;
import com.prueba.datatools.repository.RolesPersonalRepository;
import com.prueba.datatools.utileria.Utileria;

@Service
public class DatosUsuarioService {

	@Autowired
	private DatosUsuarioRepository datosUsuarioRepository;
	
	@Autowired
	private RolesPersonalRepository rolesPersonalRepository;
	
	@Autowired
	private Utileria utileria;
	
	public HashMap<String, Object> obtenerDatos(){
		HashMap<String, Object> salida=new HashMap<>();
		List<DatosUsuarioDTO> lsSalida=new ArrayList<>();
		List<DatosUsuario> lsObtenido=datosUsuarioRepository.findAll();
		if(lsObtenido.size()>0) {
			lsSalida=lsObtenido.stream().map(y->{
				DatosUsuarioDTO r=new DatosUsuarioDTO();
				r.setApellidos(y.getApellidos());
				r.setNombres(y.getNombres());
				r.setIdentificacion(y.getIdentificacion());
				r.setEsActivo(y.getEsActivo());
				r.setEdad(y.getEdad());
				r.setFechaCreacion(y.getFechaCreacion());
				r.setFechaModificacion(y.getFechaModificacion());
				r.setTipoIdentificacion(y.getTipoIdentificacion());
				List<RolesPersonalDTO> lsRoles=y.getLsRolesPersonals().stream().map(z->{
					RolesPersonalDTO rol=new RolesPersonalDTO();
					rol.setId(z.getId());
					rol.setDescripcion(z.getDescripcion());
					return rol;
				}).collect(Collectors.toList());
				r.setLsRoles(lsRoles);
				return r;
			}).collect(Collectors.toList());
		}
		salida.put("success",Boolean.TRUE);
		salida.put("datos", lsSalida);
		return salida;
	}
	
	
	public DatosUsuarioDTO obtenerDatosIdentificacion(String identificacion){
		DatosUsuarioDTO r=new DatosUsuarioDTO();
		datosUsuarioRepository.findByIdentificacion(identificacion).ifPresent(y->{
			
			r.setApellidos(y.getApellidos());
			r.setNombres(y.getNombres());
			r.setIdentificacion(y.getIdentificacion());
			r.setEsActivo(y.getEsActivo());
			r.setEdad(y.getEdad());
			r.setFechaCreacion(y.getFechaCreacion());
			r.setFechaModificacion(y.getFechaModificacion());
			r.setTipoIdentificacion(y.getTipoIdentificacion());

		});
		
		return r;
	}
	
	
	public List<DatosUsuarioDTO>  obtenerDatosPlantilla(){
		List<DatosUsuarioDTO> lsSalida=new ArrayList<>();
		List<DatosUsuario> lsObtenido=datosUsuarioRepository.findAll();
		if(lsObtenido.size()>0) {
			lsSalida=lsObtenido.stream().map(y->{
				DatosUsuarioDTO r=new DatosUsuarioDTO();
				r.setApellidos(y.getApellidos());
				r.setNombres(y.getNombres());
				r.setIdentificacion(y.getIdentificacion());
				r.setEsActivo(y.getEsActivo());
				r.setEdad(y.getEdad());
				r.setFechaCreacion(y.getFechaCreacion());
				r.setFechaModificacion(y.getFechaModificacion());
				r.setTipoIdentificacion(y.getTipoIdentificacion());
				List<RolesPersonalDTO> lsRoles=y.getLsRolesPersonals().stream().map(z->{
					RolesPersonalDTO rol=new RolesPersonalDTO();
					rol.setId(z.getId());
					rol.setDescripcion(z.getDescripcion());
					return rol;
				}).collect(Collectors.toList());
				r.setLsRoles(lsRoles);
				return r;
			}).collect(Collectors.toList());
		}
		
		return lsSalida;
	}
	
	public HashMap<String, String> guardarDatos(DatosUsuarioDTO body){
		HashMap<String, String> salida=new HashMap<>();
		datosUsuarioRepository.findByIdentificacion(body.getIdentificacion()).ifPresentOrElse(x->{
			salida.put("success", "false");
			salida.put("mensaje", "La identificacion ya existe");
		},()->{
			DatosUsuario reg=new DatosUsuario();
			reg.setApellidos(body.getApellidos());
			reg.setEdad(body.getEdad());
			reg.setEsActivo("S");
			reg.setFechaCreacion(utileria.obtenerFechaActual());
			reg.setIdentificacion(body.getIdentificacion());
			reg.setNombres(body.getNombres());
			reg.setTipoIdentificacion(body.getTipoIdentificacion());
			reg.setLsRolesPersonals(null);
			DatosUsuario regSave=datosUsuarioRepository.save(reg);
			body.getLsRoles().forEach(y->{
				RolesPersonal rol=new RolesPersonal();
				rol.setDatosUsuario(regSave);
				rol.setEsActivo("S");
				rol.setDescripcion(y.getDescripcion());
				rol.setFechaCreacion(utileria.obtenerFechaActual());
				rolesPersonalRepository.save(rol);
				
			});
			salida.put("success", "true");
			salida.put("mensaje", "Datos guardados");
		});
		
		return salida;
	}
	
	public HashMap<String, String> actualizarDatos(DatosUsuarioDTO body){
		HashMap<String, String> salida=new HashMap<>();
		datosUsuarioRepository.findByIdentificacion(body.getIdentificacion()).ifPresentOrElse(x->{
			x.setApellidos(body.getApellidos());
			x.setEdad(body.getEdad());
			x.setEsActivo("S");
			x.setFechaCreacion(utileria.obtenerFechaActual());
			x.setNombres(body.getNombres());
			x.setTipoIdentificacion(body.getTipoIdentificacion());
			x.setLsRolesPersonals(null);

			DatosUsuario regSave=datosUsuarioRepository.save(x);
			body.getLsRoles().forEach(y->{
				RolesPersonal rol=new RolesPersonal();
				rol.setDatosUsuario(regSave);
				rol.setEsActivo("S");
				rol.setDescripcion(y.getDescripcion());
				rol.setFechaCreacion(utileria.obtenerFechaActual());
				rolesPersonalRepository.save(rol);
				
			});
			salida.put("success", "true");
			salida.put("mensaje", "Datos modificados");
			
			
		},()->{
			salida.put("success", "false");
			salida.put("mensaje", "La identificacion no existe");
		});
		
		return salida;
	}
	
	public HashMap<String, String> actualizarDatosPlantilla(String identificacion,DatosUsuarioDTO body){
		HashMap<String, String> salida=new HashMap<>();
		datosUsuarioRepository.findByIdentificacion(identificacion).ifPresentOrElse(x->{
			x.setApellidos(body.getApellidos());
			x.setEdad(body.getEdad());
			x.setEsActivo("S");
			x.setFechaCreacion(utileria.obtenerFechaActual());
			x.setNombres(body.getNombres());
			x.setTipoIdentificacion(body.getTipoIdentificacion());
			x.setLsRolesPersonals(null);

			DatosUsuario regSave=datosUsuarioRepository.save(x);
			body.getLsRoles().forEach(y->{
				RolesPersonal rol=new RolesPersonal();
				rol.setDatosUsuario(regSave);
				rol.setEsActivo("S");
				rol.setDescripcion(y.getDescripcion());
				rol.setFechaCreacion(utileria.obtenerFechaActual());
				rolesPersonalRepository.save(rol);
				
			});
			salida.put("success", "true");
			salida.put("mensaje", "Datos modificados");
			
			
		},()->{
			salida.put("success", "false");
			salida.put("mensaje", "La identificacion no existe");
		});
		
		return salida;
	}
	
	
	public HashMap<String, String> eliminarDatos(String id){
		HashMap<String, String> salida=new HashMap<>();
		datosUsuarioRepository.findByIdentificacion(id).ifPresentOrElse(x->{
			List<RolesPersonal> lsRoles=x.getLsRolesPersonals();
			rolesPersonalRepository.deleteAll(lsRoles);
			datosUsuarioRepository.delete(x);
			salida.put("success", "true");
			salida.put("mensaje", "Se elimino el registro:"+id);
			
		},()->{
			salida.put("success", "false");
			salida.put("mensaje", "La identificaicon no existe");
		});
		
		return salida;
	}
}
