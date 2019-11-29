package com.mms.mms.clases;

import java.io.Serializable;

public class Camarero implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4934622896020934111L;
	
	String nombre;
	String password;
	byte[] imagen;
	
	public Camarero(String nombre, String password, byte[] imagen) {
		super();
		this.nombre = nombre;
		this.password = password;
		this.imagen = imagen;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public byte[] getImagen() {
		return imagen;
	}	

}