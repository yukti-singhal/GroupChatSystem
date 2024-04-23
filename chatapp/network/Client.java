package com.yukti.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
//import java.util.Scanner;

import javax.swing.JTextArea;

import com.yukti.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream out;
	InputStream in;
	ClientWorker worker;
	JTextArea textarea;
	public Client(JTextArea textarea) throws UnknownHostException, IOException {
		int Port = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"), Port);
	    out = socket.getOutputStream();
	    in = socket.getInputStream();
	    this.textarea = textarea;
	    readMessages();
		/*System.out.println("Client Activated");
	    System.out.println("Enter the message to send to the server");
	    Scanner scanner = new Scanner(System.in);
	    String message = scanner.nextLine();
	    OutputStream out = socket.getOutputStream();
	    out.write(message.getBytes());
	    System.out.println("Message sent to the server");
	    scanner.close();
	    out.close();
	    socket.close(); */
	}
	public void sendMessage(String message) throws IOException {
		message = message + "\n";
		out.write(message.getBytes());
	}
	
	public void readMessages() {
		worker = new ClientWorker(in,textarea);//calling a read thread
		worker.start();
	}

	/*public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client();
	}*/

}
