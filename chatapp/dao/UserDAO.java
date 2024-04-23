package com.yukti.chatapp.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.yukti.chatapp.dto.UserDTO;
import com.yukti.chatapp.utils.Encryption;

//USER CRUD Operations
public class UserDAO {
	public boolean isLogin(UserDTO userdto) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException {
		Connection con=null;
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		final String SQL = "select userid from users where userid=? and password=?";
		try {
			con = CommonDAO.createConnection();
			pstmt=con.prepareStatement(SQL);
			pstmt.setString(1, userdto.getUserid());
			pstmt.setString(2, Encryption.passwordEncrypt(new String(userdto.getPassword())));
			rs=pstmt.executeQuery();
			return rs.next(); //.next returns boolean value
		}
		finally {
			if(rs!=null) {
				rs.close();
			}
			if(pstmt!=null)
				pstmt.close();
			if(con!=null)
				con.close();
		}
	}
   /* public int add(String userid, String password) {
    	this is not practically possible because there can be multiple arguments to pass
    }*/
	//so to recover we will wrap all the arguments into a class
	
	//receiving fromUserDTO
	public int add(UserDTO userdto) throws ClassNotFoundException, SQLException, NoSuchAlgorithmException , Exception{
		Connection connection = null;
		Statement stmt = null; //Statement is an interface which is used to insert query
		try {
		connection = CommonDAO.createConnection();
		stmt = connection.createStatement();
 		int record=stmt.executeUpdate("insert into users (userid, password) values('"+userdto.getUserid()+"','"+Encryption.passwordEncrypt(new String(userdto.getPassword()))+"')"); //insert,delete,update
 		return record; //will execute after finally
		}
		finally { //always execute either exception occurs or not
			if(stmt!=null) {
 		stmt.close();
			}
			if(connection!=null) {
		connection.close();
			}
		}
	}
}
