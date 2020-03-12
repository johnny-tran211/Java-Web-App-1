/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.registration;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class RegistrationDAO implements Serializable{
    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;
    public void closeConnection() throws SQLException{
        if(rs != null){
            rs.close();
        }
        if(ptmt != null){
            ptmt.close();
        }
        if(con != null){
            con.close();
        }
    }
    public UserDTO checkLogin(String username, String password) throws SQLException, NamingException{
        UserDTO user = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select email, name, role from Registration where email = ? and password = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, password);
            rs = ptmt.executeQuery();
            if(rs.next()){
                user = new UserDTO(rs.getString(1), rs.getString(2), rs.getString(3));
            }
        } finally{
            closeConnection();
        }
        return user;
    }
    public boolean signUpAccount(String username, String fullname, String password) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into Registration(email, name, password, role, status) values(?,?,?,?,?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, username);
            ptmt.setString(2, fullname);
            ptmt.setString(3, password);
            ptmt.setString(4, "member");
            ptmt.setString(5, "New");
            int result = ptmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return false;
    }
}
