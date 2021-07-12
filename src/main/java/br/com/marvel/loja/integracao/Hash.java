package br.com.marvel.loja.integracao;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String getHashMd5(String s) throws NoSuchAlgorithmException {
    	MessageDigest m=MessageDigest.getInstance("MD5");
         m.update(s.getBytes(),0,s.length());
            	
    	return new BigInteger(1,m.digest()).toString(16);
}
}