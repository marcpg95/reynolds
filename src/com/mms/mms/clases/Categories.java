package com.mms.mms.clases;

import java.io.Serializable;
import java.util.HashMap;

public class Categories implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<Integer, Productes> productos; //ESTE INTEGER ES LA ID DEL PRODUCTO
	byte[] imagen;

    public Categories(HashMap<Integer, Productes> productos, byte[] image) {
        this.productos = productos;
        this.imagen = image;
    }

    public HashMap<Integer, Productes> getProductos() {
        return productos;
    }
    
    public byte[] getArrayImage() {
    	return this.imagen;
    }
}