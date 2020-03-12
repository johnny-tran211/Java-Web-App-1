/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hoangtd.templates;

import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Dell
 */
public class EncodingTemplate implements Serializable{

    public EncodingTemplate() {
    }
    public static String getSHA256EncodingString(String text) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] sha256Hashing = md.digest(text.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, sha256Hashing);
        StringBuilder hexStr = new StringBuilder(number.toString(16));
        while(hexStr.length() < 32){
            hexStr.insert(0, '0');
        }
        return hexStr.toString();
    }
}
