package com.mms.mms.conexion;

import java.io.File;
import java.util.ArrayList;

public interface TestService {

	public void enviarComanda(File data);
	public ArrayList<String> cogerCamareros();
}