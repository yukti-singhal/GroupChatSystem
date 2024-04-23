package com.yukti.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.yukti.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serversocket;
	ArrayList<ServerWorker> workers = new ArrayList<>();
	public Server() throws IOException {
		int Port = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serversocket = new ServerSocket(Port);
		handleclientRequest();
	}
	
	//Multiple Client Handshaking
	public void handleclientRequest() throws IOException {
		while(true) {
			Socket clientsocket = serversocket.accept();
			//per client per thread
			ServerWorker serverworker = new ServerWorker(clientsocket, this); //creating a new worker/thread
		    workers.add(serverworker);
			serverworker.start();
			}
	}
	
	
	/* Single Client*/
	/*
	public Server() throws IOException {
		int Port = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serversocket = new ServerSocket(Port);
		System.out.println("Server Started and waiting for the client");
		Socket socket = serversocket.accept(); //Handshaking
		System.out.println("Client joins the server");
		InputStream in = socket.getInputStream();
		byte arr[]=in.readAllBytes();
		String str = new String(arr); //bytes converted into string
		System.out.println("Message received from the client "+str);
		in.close();
		socket.close();
	}
*/
	public static void main(String[] args) throws IOException {
        Server server = new Server();
	}

}
