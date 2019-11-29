package com.mms.mms.conexion;

import java.util.ArrayList;
import java.util.HashMap;

import com.mms.mms.clases.Camarero;
import com.mms.mms.clases.Categories;
import com.mms.mms.clases.comandas.Comanda;

public interface TestService {
    public void enviarComanda(HashMap<Integer, Comanda> comandas, int numeroMesa);
    public ArrayList<Camarero> cogerCamareros();
    public HashMap<String, Categories> cogerProductos();
    public HashMap<Integer, Comanda> cogerComandas();
    public void enviarCamarero(String nombre, String password, byte[] image);
    
}