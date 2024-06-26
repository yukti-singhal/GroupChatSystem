package com.yukti.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

//client's data read
public class ClientWorker extends Thread{
	private InputStream in;
	private JTextArea textarea;
    public ClientWorker(InputStream in, JTextArea textarea) {
    	this.in = in;
    	this.textarea = textarea;
    }
    @Override
    public void run() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(in));
    	String line;
    	try {
    	while(true) {
				line = br.readLine();
				line = line +"\n";
				textarea.setText(textarea.getText()+line);
				
			} 
    	} 
    	catch (IOException e) {
				e.printStackTrace();
			}
    	finally {
    		if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    	}
    	}
    }
