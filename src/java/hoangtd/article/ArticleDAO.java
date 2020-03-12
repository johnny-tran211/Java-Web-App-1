/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.article;

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
public class ArticleDAO implements Serializable {

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

    public int getQuantityOfArtitleResult(String status, String content) throws NamingException, SQLException {
        int quantity = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where status = ? and contentOfBlog LIKE ? ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setString(2, "%" + content + "%");
            rs = ptmt.executeQuery();
            if (rs.next()) {
                quantity = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return quantity;
    }

    public ArrayList<ArticleDTO> searchArticle(String content, int numberPage, int amountOfArtitcle, String status) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date "
                    + "from Article "
                    + "where status = ? and contentOfBlog LIKE ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setString(2, "%" + content + "%");
            ptmt.setInt(3, numberPage);
            ptmt.setInt(4, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }

    public ArrayList<String> searchTitle(int numberPage, int amountOfArtitcle, String status) throws SQLException, NamingException {
        ArrayList<String> listTitle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title from Article where status = ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, numberPage);
            ptmt.setInt(3, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listTitle == null) {
                listTitle = new ArrayList<String>();
            }
            while (rs.next()) {
                listTitle.add(rs.getString(1));
            }
        } finally {
            closeConnection();
        }
        return listTitle;
    }

    public ArticleDTO searchArticleDetail(String title, String status) throws SQLException, NamingException {
        ArticleDTO articleDetail = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, contentOfBlog, author, date "
                    + "from Article "
                    + "where title = ? and status = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, title);
            ptmt.setString(2, status);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                articleDetail = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5));
            }
        } finally {
            closeConnection();
        }
        return articleDetail;
    }
    public ArticleDTO searchArticleDetailAdmin(String title) throws SQLException, NamingException {
        ArticleDTO articleDetail = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, contentOfBlog, author, date, status "
                    + "from Article "
                    + "where title = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, title);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                articleDetail = new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getTimestamp(5), rs.getString(6));
            }
        } finally {
            closeConnection();
        }
        return articleDetail;
    }
     
    public int getRow(String status) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where status = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent(String content) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContentTitle(String content, String title) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and title LIKE ? ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent1Checkbox(String content, String checkbox) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and status = ? ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, checkbox);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent2Checkbox(String content, String checkbox1, String checkbox2) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and (status = ? or status = ?) ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, checkbox1);
            ptmt.setString(3, checkbox2);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent3Checkbox(String content, String checkbox1, String checkbox2, String checkbox3) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and (status = ? or status = ? or status = ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, checkbox1);
            ptmt.setString(3, checkbox2);
            ptmt.setString(4, checkbox3);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent1CheckboxTitle(String content, String checkbox, String title) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and title LIKE ? and status = ? ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, checkbox);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent2CheckboxTitle(String content, String checkbox1, String checkbox2, String title) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and title LIKE ? and (status = ? or status = ?) ";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, checkbox1);
            ptmt.setString(4, checkbox2);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public int getRowAminByContent3CheckboxTitle(String content, String checkbox1, String checkbox2, String checkbox3, String title) throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article where contentOfBlog LIKE ? and title LIKE ? and (status = ? or status = ? or status = ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, checkbox1);
            ptmt.setString(4, checkbox2);
            ptmt.setString(5, checkbox3);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }

    public int getAllRow() throws NamingException, SQLException {
        int row = 0;
        try {
            con = Utilities.makeConnection();
            String sql = "SELECT COUNT(title) FROM Article";
            ptmt = con.prepareStatement(sql);
            rs = ptmt.executeQuery();
            if (rs.next()) {
                row = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return row;
    }
    public boolean addNewBlog(String title, String shortDescription, String author, Timestamp date, String content, String status) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Insert into Article(title, shortDescription, author, date, contentOfBlog, status) values(?, ?, ?, ?, ?, ?)";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, title);
            ptmt.setString(2, shortDescription);
            ptmt.setString(3, author);
            ptmt.setTimestamp(4, date);
            ptmt.setString(5, content);
            ptmt.setString(6, status);
            int result = ptmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return false;
    }

    public ArrayList<ArticleDTO> getArtitleForAdmin(int numberPage, int amountOfArticle) throws NamingException, SQLException{
        ArrayList<ArticleDTO> articles = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article order by date DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setInt(1, numberPage);
            ptmt.setInt(2, amountOfArticle);
            rs = ptmt.executeQuery();
            articles = new ArrayList<>();
            while(rs.next()){
                articles.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally{
            closeConnection();
        }
        return articles;
    }
    public ArrayList<ArticleDTO> getArtitle(int numberPage, int amountOfArticle, String status) throws NamingException, SQLException{
        ArrayList<ArticleDTO> articles = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date "
                    + "from Article where status = ? order by date DESC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setInt(2, numberPage);
            ptmt.setInt(3, amountOfArticle);
            rs = ptmt.executeQuery();
            articles = new ArrayList<>();
            while(rs.next()){
                articles.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4)));
            }
        } finally{
            closeConnection();
        }
        return articles;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent(String content, int numberPage, int amountOfArtitcle) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setInt(2, numberPage);
            ptmt.setInt(3, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContentTitle(String content, int numberPage, int amountOfArtitcle, String title) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and title LIKE ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setInt(3, numberPage);
            ptmt.setInt(4, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent1Checkbox(String content, int numberPage, int amountOfArtitcle, String status) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and status = ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, status);
            ptmt.setInt(3, numberPage);
            ptmt.setInt(4, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent2Checkbox(String content, int numberPage, int amountOfArtitcle, String status1, String status2) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and (status = ? or status = ?) order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, status1);
            ptmt.setString(3, status2);
            ptmt.setInt(4, numberPage);
            ptmt.setInt(5, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent3Checkbox(String content, int numberPage, int amountOfArtitcle, String status1, String status2, String status3) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and (status = ? or status = ? or status = ?) order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, status1);
            ptmt.setString(3, status2);
            ptmt.setString(4, status3);
            ptmt.setInt(5, numberPage);
            ptmt.setInt(6, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent1CheckboxTitle(String content, int numberPage, int amountOfArtitcle, String status, String title) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and title LIKE ? and status = ? order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, status);
            ptmt.setInt(4, numberPage);
            ptmt.setInt(5, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent2CheckboxTitle(String content, int numberPage, int amountOfArtitcle, String status1, String status2, String title) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and title LIKE ? and (status = ? or status = ?) order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, status1);
            ptmt.setString(4, status2);
            ptmt.setInt(5, numberPage);
            ptmt.setInt(6, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public ArrayList<ArticleDTO> searchArticleAdminByContent3CheckboxTitle(String content, int numberPage, int amountOfArtitcle, String status1, String status2, String status3, String title) throws SQLException, NamingException {
        ArrayList<ArticleDTO> listArticle = null;
        try {
            con = Utilities.makeConnection();
            String sql = "Select title, shortDescription, author, date, status "
                    + "from Article "
                    + "where contentOfBlog LIKE ? and title LIKE ? and (status = ? or status = ? or status = ?) order by DATE DESC "
                    + "OFFSET  ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, "%" + content + "%");
            ptmt.setString(2, "%" + title + "%");
            ptmt.setString(3, status1);
            ptmt.setString(4, status2);
            ptmt.setString(5, status3);
            ptmt.setInt(6, numberPage);
            ptmt.setInt(7, amountOfArtitcle);
            rs = ptmt.executeQuery();
            if (listArticle == null) {
                listArticle = new ArrayList<ArticleDTO>();
            }
            while (rs.next()) {
                listArticle.add(new ArticleDTO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getTimestamp(4), rs.getString(5)));
            }
        } finally {
            closeConnection();
        }
        return listArticle;
    }
    public boolean activeStatusBlog(String title, String status) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Update Article set status = ? where title = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setString(2, title);
            int result = ptmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return false;
    }
    public boolean deleteStatusBlog(String title, String status) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Update Article set oldStatus = status, status = ? where title = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, status);
            ptmt.setString(2, title);
            int result = ptmt.executeUpdate();
            if(result > 0){
                return true;
            }
        } finally{
            closeConnection();
        }
        return false;
    }
    public boolean restoreStatusBlog(String title) throws NamingException, SQLException{
        try {
            con = Utilities.makeConnection();
            String sql = "Update Article set status = oldStatus, oldStatus = null where title = ?";
            ptmt = con.prepareStatement(sql);
            ptmt.setString(1, title);
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
