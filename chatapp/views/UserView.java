package com.yukti.chatapp.views;

import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.JButton;
import javax.swing.*;

public class UserView extends JFrame {
	int counter;
	public UserView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400,400);
		setResizable(false);
		setTitle("Login");
		setLocationRelativeTo(null);//center to the screen
		//setLocation(500,150);
		JLabel welcome = new JLabel("Login");
		welcome.setFont(new Font("Arial",Font.BOLD,40));
		Container container=this.getContentPane();
		container.setLayout(null);//don't want the container to layout for me. i want to arrange items as my preference
		welcome.setBounds(100, 70, 200, 60);
		container.add(welcome);
		JButton button = new JButton("Count"); //source
		//anonymous class without object as interface doesn't have object
		button.addActionListener(new ActionListener() {
			@Override
		public void actionPerformed(ActionEvent event) {
			counter++;
			welcome.setText("Count "+ counter);
		}	
		});
		button.setBounds(100, 300, 200, 50);
		container.add(button);
		setVisible(true);
	 }
   public static void main(String args[]){
	   UserView userview = new UserView();
   }
}