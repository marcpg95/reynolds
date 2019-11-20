package com.mms.mms.conexion;

import java.net.Socket;
import java.util.ArrayList;

import baseDeDatos.ConsultarCamareros;
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
			System.out.println("Server Listening");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public ArrayList<String> cogerCamareros() {
		ConsultarCamareros cc = new ConsultarCamareros();
		System.out.println("Se ha solicitado la lista de camareros.");
		return cc.getCamareros();
	}


	@Override
	public void enviarComanda(ArrayList<String> dataProducts, String camarero, int numeroMesa) {
		System.out.println(camarero + " atiende mesa " + numeroMesa);		
	}




}
