/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.comment;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class CommentDTO implements Serializable{
    private String username;
    private Timestamp date;
    private String comment;
    private String title;

    public CommentDTO(String username, Timestamp date, String comment, String title) {
        this.username = username;
        this.date = date;
        this.comment = comment;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    

    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
}
