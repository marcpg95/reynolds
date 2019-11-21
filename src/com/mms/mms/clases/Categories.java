package com.mms.mms.clases;

import java.io.Serializable;
import java.util.HashMap;

public class Categories implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<Integer, Productes> productos; //ESTE INTEGER ES LA ID DEL PRODUCTO

    public Categories(HashMap<Integer, Productes> productos) {
        this.productos = productos;
    }

    public HashMap<Integer, Productes> getProductos() {
        return productos;
    }
}
