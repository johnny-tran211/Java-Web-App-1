/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.comment;

import hoangtd.utils.Utilities;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class CommentDAO implements Serializable {

    Connection con = null;
    PreparedStatement ptmt = null;
    ResultSet rs = null;

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ptmt != null) {
            ptmt.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public ArrayList<CommentDTO> searchComment(String title) throws SQLException, NamingException {
        ArrayList<CommentDTO> listComment = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select username, date, comment, title "
                    + "from Comment where title = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, title);
            rs = ptmt.executeQuery();
            if (listComment == null) {
                listComment = new ArrayList<CommentDTO>();
            }
            while (rs.next()) {
                listComment.add(new CommentDTO(rs.getString(1), rs.getTimestamp(2), rs.getString(3), rs.getString(4)));
            }
        } finally {
            closeConnection();
        }
        return listComment;
    }
        public int getLastIndexIdComment() throws NamingException, SQLException{
        int lastIndex = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT TOP 1 id FROM Comment order by id DESC";
            ptmt = con.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if(rs.next()){
                return rs.getInt(1);
            }
        } finally{
            closeConnection();
        }
        return lastIndex;
    }
    public boolean postComment(int id, String username, Timestamp date, String comment, String title) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into Comment(id, username, date, comment, title) values(?,?,?,?,?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, id);
            ptmt.setString(2, username);
            ptmt.setTimestamp(3, date);
            ptmt.setString(4, comment);
            ptmt.setString(5, title);
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
