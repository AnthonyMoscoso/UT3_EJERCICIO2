package com.isf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class Cliente {
	// creamos una variable estatica final que sera nuestro puerto debido a que a la hora de necesitar cambiar el codigo
	// podemos cambiar el Puerto directamente desde aqui
	public static final int PUERTO=6003;
	public static final String host ="192.168.30.17";
	public static final String RUTA=".\\src\\com\\isf\\pregunta.txt";
	public static void main (String[] args) throws IOException{
		 
	
		 int in;
		 byte[] byteArray;
		 //Fichero a transferir
		
		 // creamos nuestro socket cliente
		 Socket cliente = new Socket(host, PUERTO);
		 File file = new File(RUTA);
		 InputStream entrada;
		 BufferedInputStream bufferInput= new BufferedInputStream(new FileInputStream(file));
		 BufferedOutputStream bufferOutput = new BufferedOutputStream(cliente.getOutputStream());
	
		 //Enviamos el fichero
		
		 
		 byteArray = new byte[(int) file.length()];
		while ((in = bufferInput.read(byteArray)) != -1){
			 bufferOutput.write(byteArray,0,in);
			
			 bufferOutput.flush();
		 }
		
		 System.out.println("enviado pregunta del fichero"+file);;
		 
		
		
		entrada=cliente.getInputStream();
		System.out.println("Recibiendo del servidor...");
		
		BufferedReader br= new BufferedReader(new InputStreamReader(entrada));
	   
	    System.out.println("Respuesta recibida\n"+br.readLine());
	    
		bufferOutput.close();
		bufferInput.close(); 
		//entrada.close();
		
		cliente.close();
		 
		
		 }
		}

