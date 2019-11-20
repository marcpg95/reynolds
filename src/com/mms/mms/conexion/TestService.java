package com.mms.mms.conexion;

import java.util.ArrayList;

public interface TestService {

	public void enviarComanda(ArrayList<String> dataProducts, String camarero, int numeroMesa);
	public ArrayList<String> cogerCamareros();
}