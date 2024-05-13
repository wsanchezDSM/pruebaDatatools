package com.prueba.datatools.utileria;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Utileria {

	public Date obtenerFechaActual() {
		return new Date();
	}
}
