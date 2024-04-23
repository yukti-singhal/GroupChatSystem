package com.yukti.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.yukti.chatapp.dao.UserDAO;
import com.yukti.chatapp.dto.UserDTO;
import com.yukti.chatapp.utils.UserInfo;

public class UserScreen extends JFrame{
	private JTextField useridtextField;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		 UserScreen window = new UserScreen();
		}
	private void doLogin() {
		String userid= useridtextField.getText();
    	char []password= passwordField.getPassword(); //getPassword gives char array
    	//creating object of UserDAO
    	UserDAO userdao = new UserDAO();
    	UserDTO userdto = new UserDTO(userid, password);
    	try {
			if(userdao.isLogin(userdto)) {
				String message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose(); //disposed from the memory
				Dashboard dashboard = new Dashboard(message);
				dashboard.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(this, "Invalid Userid or Password");
			}
		} catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException e) {
			e.printStackTrace();
		}
	}
    private void register() {
    	String userid= useridtextField.getText();
    	char []password= passwordField.getPassword(); //getPassword gives char array
    	//creating object of UserDAO
    	UserDAO userdao = new UserDAO();
    	UserDTO userdto = new UserDTO(userid, password);
    	try {
    	int result=userdao.add(userdto);
    	if(result>0)
    		JOptionPane.showMessageDialog(this, "Registered Successfully");
    		//System.out.println("Record Added");
    	else
    		JOptionPane.showMessageDialog(this, "Registration Failed");
    	}
    	catch(ClassNotFoundException |SQLException ex) {
    		System.out.println("Database Issue");
    		 ex.printStackTrace();
    	}
    	catch(Exception ex) {
    		System.out.println("Some generic exception raised");
    		ex.printStackTrace(); //where is the exception
    	}
    }
    
	/**
	 * Create the application.
	 */
	public UserScreen() {
		setResizable(false);
		setTitle("LOGIN");
		getContentPane().setBackground(Color.WHITE);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(274, 11, 123, 40);
		getContentPane().add(lblNewLabel);
		
		useridtextField = new JTextField();
		useridtextField.setBounds(315, 89, 184, 28);
		getContentPane().add(useridtextField);
		useridtextField.setColumns(10);
		
		JLabel useridlbl = new JLabel(" UserID");
		useridlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		useridlbl.setHorizontalAlignment(SwingConstants.CENTER);
		useridlbl.setBounds(165, 90, 81, 22);
		getContentPane().add(useridlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setHorizontalAlignment(SwingConstants.CENTER);
		pwdlbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		pwdlbl.setBounds(176, 150, 81, 22);
		getContentPane().add(pwdlbl);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(315, 149, 184, 28);
		getContentPane().add(passwordField);
		
		JButton loginbt = new JButton("Login");
		loginbt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		loginbt.setBounds(199, 214, 89, 23);
		getContentPane().add(loginbt);
		
		JButton registerbt = new JButton("Register");
		registerbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		registerbt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		registerbt.setBounds(361, 214, 89, 23);
		getContentPane().add(registerbt);
		setBackground(Color.WHITE);
		setSize(833,440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}
}
