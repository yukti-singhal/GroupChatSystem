package com.yukti.chatapp.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yukti.chatapp.network.Client;
import com.yukti.chatapp.utils.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textarea;
    private Client client;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
					try {
						ClientChatScreen frame = new ClientChatScreen();
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	}
	private void sendBtn() {
		String message = textField.getText();
		try {
			client.sendMessage(UserInfo.USER_NAME+" - "+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ClientChatScreen() throws UnknownHostException, IOException {
		textarea = new JTextArea();
		client = new Client(textarea);
		setTitle("Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 712, 456);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 6, 676, 349);
        contentPane.add(scrollPane);
        
        
        textarea.setFont(new Font("Tahoma", Font.PLAIN, 16));
        scrollPane.setViewportView(textarea);
        
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        textField.setBounds(10, 366, 526, 40);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JButton sendbtn = new JButton("Send Message");
        sendbtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		sendBtn();
        	}
        });
        sendbtn.setFont(new Font("Tahoma", Font.BOLD, 12));
        sendbtn.setBounds(558, 370, 128, 32);
        contentPane.add(sendbtn);
        setVisible(true);
	}
}
