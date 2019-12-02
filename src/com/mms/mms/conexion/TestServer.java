package com.mms.mms.conexion;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


import com.mms.mms.clases.Camarero;
import com.mms.mms.clases.Categories;
import com.mms.mms.clases.comandas.Comanda;

import baseDeDatos.AddCamarero;
import baseDeDatos.ConsultarCamareros;
import baseDeDatos.SubirComanda;
import funciones.GenerarXMLComanda;
import interfaz.Principal;
import lipermi.handler.CallHandler;
import lipermi.net.IServerListener;
import lipermi.net.Server;

public class TestServer implements TestService {
	public TestServer() {
		try {
			CallHandler callHandler = new CallHandler();
			callHandler.registerGlobal(TestService.class, this);
			Server server = new Server();
			server.bind(7777, callHandler);
			server.addServerListener(new IServerListener() {

				@Override
				public void clientDisconnected(Socket socket) {
					System.out.println("Cliente desconectado: " + socket.getInetAddress());
				}

				@Override
				public void clientConnected(Socket socket) {
					System.out.println("Cliente conectado: " + socket.getInetAddress());
				}
			});
			System.out.println("Servidor iniciado.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Camarero> cogerCamareros() {
		ConsultarCamareros cc = new ConsultarCamareros();
		System.out.println("Se ha solicitado la lista de camareros.");
		return cc.getCamareros();
	}

	@Override
	public HashMap<String, Categories> cogerProductos() {
		System.out.println("Se ha solicitado el listado de productos.");
		return Principal.categorias;
	}

	@Override
	public void enviarComanda(HashMap<Integer, Comanda> comandas, int numeroMesa) {
		System.out.println("Se ha recibido una comanda en la mesa " + numeroMesa + ".");

		// COMPRUEBO SI ESTA COMANDA ES UNA ACTUALIZACION DE UNA COMANDA PREVIA
		boolean actualizacion = false;
		if (Principal.comandas.containsKey(numeroMesa)) {
			actualizacion = true;
		}
		Principal.comandas = comandas;

		// COMPRUEBO SI LA COMANDA TIENE PRODUCTOS
		if (comandas.get(numeroMesa).getProductosPedidos().size() == 0) {
			comandas.remove(numeroMesa); // SI NO TIENE PRODUCTOS LA BORRO
		} else {
			// CREO EL XML CON LOS PRODUCTOS DE LA COMANDA
			GenerarXMLComanda cxc = new GenerarXMLComanda(comandas.get(numeroMesa).getProductosPedidos(), numeroMesa);
			cxc.crearXML();

			SubirComanda sc = new SubirComanda(comandas.get(numeroMesa), numeroMesa);
			if (actualizacion == false) {
				// SUBO LA COMANDA A LA BASE DE DATOS
				sc.subir();
			} else {
				sc.borrarComanda(); //ACUALIZO LA COMANDA DE LA BASE DE DATOS
				sc.subir();
			}
		}
	}

	@Override
	public HashMap<Integer, Comanda> cogerComandas() {
		System.out.println("Se han solicitado las comandas.");
		return Principal.comandas;
	}

	@Override
	public void enviarCamarero(String nombre, String password, byte[] image) {
		AddCamarero ac = new AddCamarero(nombre, password, image);
		ac.subir();
	}

}
