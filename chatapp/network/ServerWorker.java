package com.yukti.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

//Thread == Worker
public class ServerWorker extends Thread{
	private Socket clientsocket;
	private InputStream in;
	private OutputStream out;
	private Server server;
    //once job is created via Runnable then write the job logic inside the run() function
	//assign the job to a thread
	public ServerWorker(Socket clientsocket, Server server) throws IOException {
		this.server = server;
		this.clientsocket = clientsocket;
		in= clientsocket.getInputStream(); //client data read
		out=clientsocket.getOutputStream(); //client data send
		
	}
	@Override
	public void run() {
		//job to perform
		//read data from the client and broadcast the data to all
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		String line;
		try {
		while(true) {
				line=br.readLine(); //it needs \n to identify where to end
				if(line.equalsIgnoreCase("quit"))
					break; //client chat end
				//out.write(line.getBytes()); //client send
				//broadcast to all clients
				for(ServerWorker serverworker : server.workers) {
					line = line + "\n";
					serverworker.out.write(line.getBytes());
				}
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
			if(br!=null)
				br.close();
			if(in!=null)
				in.close();
			if(out!=null)
				out.close();
			if(clientsocket!=null)
				clientsocket.close();
			}
			catch(Exception ex) 
			{
				ex.printStackTrace();
			}
	}
		
}
}
