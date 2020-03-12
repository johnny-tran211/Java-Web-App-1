/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.article;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class ArticleErrorObject implements Serializable{
    private String titleEmptyError;
    private String titleDuplicateError;
    private String shortDescriptionEmptyError;
    private String contentEmptyError;

    public ArticleErrorObject() {
    }

    public String getTitleDuplicateError() {
        return titleDuplicateError;
    }

    public void setTitleDuplicateError(String titleDuplicateError) {
        this.titleDuplicateError = titleDuplicateError;
    }

    public String getTitleEmptyError() {
        return titleEmptyError;
    }

    public void setTitleEmptyError(String titleEmptyError) {
        this.titleEmptyError = titleEmptyError;
    }

    public String getShortDescriptionEmptyError() {
        return shortDescriptionEmptyError;
    }

    public void setShortDescriptionEmptyError(String shortDescriptionEmptyError) {
        this.shortDescriptionEmptyError = shortDescriptionEmptyError;
    }

    public String getContentEmptyError() {
        return contentEmptyError;
    }

    public void setContentEmptyError(String contentEmptyError) {
        this.contentEmptyError = contentEmptyError;
    }
    
}
