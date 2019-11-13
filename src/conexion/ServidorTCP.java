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

	public void iniciarServidor() {
		try {
			ss = new ServerSocket(20); // INICIO EL SOCKET SERVIDOR
			System.out.println("IP: " + getIP());
			buscarComandas();
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(null, "No se ha podido iniciar el servidor.", "ATENCION",
					JOptionPane.PLAIN_MESSAGE);
		}
	}

	public String getIP() {
		try {
			return InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e) {
			return null;
		}
	}

	@SuppressWarnings("resource")
	private void buscarComandas() {
		while (true) {
			try {
				Socket sock = ss.accept();
				DataInputStream dis = new DataInputStream(sock.getInputStream());
				while(true) {
					System.out.println("Leido: " + dis.readInt());
				}
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Se ha producido un error leyendo la comanda.", "ATENCION",
						JOptionPane.PLAIN_MESSAGE);
			}
		}
	}
}
