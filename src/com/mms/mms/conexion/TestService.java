package com.mms.mms.conexion;

import java.util.ArrayList;
import java.util.HashMap;

import com.mms.mms.clases.Categories;

public interface TestService {

	public void enviarComanda(ArrayList<String> dataProducts, String camarero, int numeroMesa);
	public ArrayList<String> cogerCamareros();
	public HashMap<String, Categories> cogerProductos();
	
}