/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.article;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dell
 */
public class ArticleDTO implements Serializable {

    private String title;
    private String shortDescription;
    private String author;
    private Timestamp date;
    private String status;
    private String content;

    public ArticleDTO(String title, String shortDescription, String author, Timestamp date) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
    }

    public ArticleDTO(String title, String shortDescription, String author, Timestamp date, String status) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
        this.status = status;
    }

    public ArticleDTO(String title, String shortDescription, String content, String author, Timestamp date) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public ArticleDTO(String title, String shortDescription, String content, String author, Timestamp date, String status) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.content = content;
        this.author = author;
        this.date = date;
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
