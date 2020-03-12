/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.registration;

import java.io.Serializable;

/**
 *
 * @author Dell
 */
public class RegisterErrorObject implements Serializable{
    private String emailFormError;
    private String fullnameLengthError;
    private String passwordLengthError;
    private String confirmPasswordError;
    private String duplicateEmailError;

    public RegisterErrorObject() {
    }

    public String getDuplicateEmailError() {
        return duplicateEmailError;
    }

    public void setDuplicateEmailError(String duplicateEmailError) {
        this.duplicateEmailError = duplicateEmailError;
    }

    
    public String getEmailFormError() {
        return emailFormError;
    }

    public void setEmailFormError(String emailFormError) {
        this.emailFormError = emailFormError;
    }

    public String getFullnameLengthError() {
        return fullnameLengthError;
    }

    public void setFullnameLengthError(String fullnameLengthError) {
        this.fullnameLengthError = fullnameLengthError;
    }

    public String getPasswordLengthError() {
        return passwordLengthError;
    }

    public void setPasswordLengthError(String passwordLengthError) {
        this.passwordLengthError = passwordLengthError;
    }

    public String getConfirmPasswordError() {
        return confirmPasswordError;
    }

    public void setConfirmPasswordError(String confirmPasswordError) {
        this.confirmPasswordError = confirmPasswordError;
    }
    
}
