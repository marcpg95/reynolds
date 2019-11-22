package com.mms.mms.clases.comandas;

import com.mms.mms.clases.Productes;

import java.io.Serializable;

public class ProductosComanda implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Productes producto;
    int cantidad;

    public ProductosComanda(Productes producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Productes getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
