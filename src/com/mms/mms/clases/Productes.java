package com.mms.mms.clases;

import java.io.Serializable;

public class Productes implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nom, descripcion, image;
    private float preu;
    private int id;

    public Productes(String nom, String descripcion, String image, float preu, int id) {
        this.nom = nom;
        this.descripcion = descripcion;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
