package com.isf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Servidor {
	// creo una variable estatica de mi puerto para cualquier cambio realizarlo directamente desde aqui
	public static int PUERTO=1950;
	public static void main(String[] arg) throws IOException {
		ServerSocket servidor = new ServerSocket(PUERTO);
		Socket clienteConectado = null;
		InputStream entrada = null;
		PrintWriter print=null;
		OutputStream salida =null;
	
		System.out.println("Esperando al cliente.....");
		try {
			clienteConectado = servidor.accept();
		}
		catch (	IOException ex) {
			System.out.println("No se ha podido conectar con el cliente");
		}
		try {
			// CREO FLUJO DE ENTRADA DEL CLIENTE
			System.out.println("Leyendo del clientes....");
			entrada = clienteConectado.getInputStream();
		}
		catch (IOException ex) {
			System.out.println("error de conexion");
		}
		
		byte[] bytes = new byte[2000];
		
		
		int contador;
		String pregunta="";
		String respuesta="";
		// leemos byte a byte el mensaje
		byte b;
		boolean fin=false;
		
		contador=entrada.read(bytes);
		for(int i=0;i<bytes.length;i++) {
				//lo casteamos con un char
				char letra=(char)bytes[i];
				pregunta=pregunta+letra;
				// lo imprimimos
				if(letra=='?') {
					break;
				}  
			}
		 
		System.out.println("Me han preguntado \n"+pregunta);
		
		salida=clienteConectado.getOutputStream();
		print = new PrintWriter(salida,true);
		
		if(pregunta.equals("Como te llamas?")) {
			respuesta="Soy anthony Ejercicio 2 Programacion multiproceso 10/12/2019 ";
			print.println(respuesta);
		}else if(pregunta.equals("Cuantos anios tienes?")) {
			respuesta="Soy anthony y tengo 23 annos , un saludo  Programacion multiproceso 10/12/2019";
			print.println(respuesta);
		}
		else {
			respuesta="no entiendo la pregunta";
			print.println(respuesta);
		}
		System.out.println("he enviado la respuesta:\n"+respuesta);
		 entrada.close();
		 print.close();
		 salida.close();
		servidor.close();
	  }
}