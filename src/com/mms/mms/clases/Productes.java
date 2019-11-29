package com.mms.mms.clases;

import java.io.Serializable;

public class Productes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom, descripcion;
    private float preu;
    private int id;
    byte[] imagen;

    public Productes(String nom, String descripcion, float preu, int id, byte[] image) {
        this.nom = nom;
        this.descripcion = descripcion;
        this.imagen = image;
        this.preu = preu;
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public byte[] getImage() {
        return this.imagen;
    }

    public void setImage(byte[] data) {
        this.imagen = data;
    }

    public float getPreu() {
        return preu;
    }

    public void setPreu(float preu) {
        this.preu = preu;
    }
    
    public int getId() {
    	return this.id;
    }
}
