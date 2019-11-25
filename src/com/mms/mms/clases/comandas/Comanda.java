package com.mms.mms.clases.comandas;

import java.io.Serializable;
import java.util.ArrayList;

public class Comanda implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4479301926189608612L;
	
	ArrayList<ProductosComanda> productosPedidos;
    String camarero;

    public Comanda(String camarero) {
        this.productosPedidos = new ArrayList<>();
        this.camarero = camarero;
    }

    public Comanda(ArrayList<ProductosComanda> array, String camarero) {
    	this.productosPedidos = array;
        this.camarero = camarero;
    }

    public ArrayList<ProductosComanda> getProductosPedidos() {
        return productosPedidos;
    }

    public String getCamarero() {
        return camarero;
    }

    public int devolverPosicion(String nombre) {
        int pos = -1;
        for(int i = 0; i < productosPedidos.size(); i++) {
            if(productosPedidos.get(i).getProducto().getNom() == nombre) {
                pos = i;
                break;
            }
        }
        return pos;
    }
}
