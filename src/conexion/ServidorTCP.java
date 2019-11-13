package conexion;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;

public class ServidorTCP {

	ServerSocket ss;
	private static final int puerto = 1234; // PUERTO A TRAVES DEL CUAL HACEMOS LA CONEXION
	int numeroComanda = 0;

	public ServidorTCP() {
		super();
	}

	public void conexion() {
		try {
			System.out.println("LocalHost = " + InetAddress.getLocalHost().toString()); // MUESTRO LA IP DEL SERVER
		} catch (UnknownHostException uhe) {

		}

		// CREAMOS UN SOCKET DE SERVIDOR
		ServerSocket ss = null;
		try {
			ss = new ServerSocket(puerto);
		} catch (IOException ioe) {

		}

		int entrada;

		while (true) {
			try {
				// ESPERAMOS QUE EL MOVIL ENVIE DATOS
				Socket sock = ss.accept();

				// LEO LOS DATOS
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				entrada = dis.readInt();

				System.out.println("ENTRADA: " + entrada);
				dis.close();
				sock.close();
			} catch (Exception e) {
				
			}
		}
	}
}
