/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.templates;

import hoangtd.article.ArticleDAO;
import java.io.Serializable;
import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author Dell
 */
public class PagingTemplate implements Serializable {

    public PagingTemplate() {
    }
    

    public int getQuantityOfAdminPage(int amountOfArticle) throws NamingException, SQLException{
        ArticleDAO dao = new ArticleDAO();
        int row = dao.getAllRow();
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
        
    }
    public int getQuantityOfAdminPageByContent(int amountOfArticle, String content) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent(content);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContentTitle(int amountOfArticle, String content, String title) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContentTitle(content, title);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent1CheckBox(int amountOfArticle, String content, String checkbox) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent1Checkbox(content, checkbox);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent2CheckBox(int amountOfArticle, String content, String checkbox1, String checkbox2) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent2Checkbox(content, checkbox1, checkbox2);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent3CheckBox(int amountOfArticle, String content, String checkbox1, String checkbox2, String checkbox3) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent3Checkbox(content, checkbox1, checkbox2, checkbox3);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent1CheckBoxTitle(int amountOfArticle, String content, String checkbox, String title) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent1CheckboxTitle(content, checkbox, title);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent2CheckBoxTitle(int amountOfArticle, String content, String checkbox1, String checkbox2, String title) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent2CheckboxTitle(content, checkbox1, checkbox2, title);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityOfAdminPageByContent3CheckBoxTitle(int amountOfArticle, String content, String checkbox1, String checkbox2, String checkbox3, String title) throws NamingException, SQLException{
         ArticleDAO dao = new ArticleDAO();
        int row = dao.getRowAminByContent3CheckboxTitle(content, checkbox1, checkbox2, checkbox3, title);
        int totalOfPage = row /amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if(overbalanceOfRow == 0){
            return totalOfPage;
        }else{
            return totalOfPage + 1;
        }
    }
    public int getQuantityTitleOfPage(int amountOfArticle) throws NamingException, SQLException {
        ArticleDAO dao = new ArticleDAO();
        int row = dao.getRow("Active");
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }

    public int getQuantityOfPage(int amountOfArticle, int row) throws NamingException, SQLException {
        int totalOfPage = row / amountOfArticle;
        int overbalanceOfRow = row % amountOfArticle;
        if (overbalanceOfRow == 0) {
            return totalOfPage;
        } else {
            return totalOfPage + 1;
        }

    }
    public boolean  checkNumber(String numberStr){
        try {
            int number = Integer.parseInt(numberStr);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
