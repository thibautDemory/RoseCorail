package hei.ProjetRoseCorail.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncodingPassword {

    public String encodePassword(String password){
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte byteData[] = md.digest();
        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        String mdpEncoder = sb.toString();

        return mdpEncoder;
    }
}
