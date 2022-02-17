package logic.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class App {
    public static void main(String[] args) throws
            NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        String verification = RSAUtil.sign("1", "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEA7BDjFJpEMBILumrf+pvbl/lY3wgJsTTn30nppSgbyctplaS9ar9Ahgc53XFdxVn0GPCeGPSDc+VW7dFHuzwcjwIDAQABAkEAsNLNZadQIDnEMzTO5OdOC34oxZc9u1EHxkeVDbMHbZTR7BjmNnu2wn7RgZERRicn8AYe1SA48u7K3Q6y1k8zoQIhAPyw115NdwoX+DFTRepmQOPgsTlA9dTMsAhBAMtyi/3HAiEA7yhPaLEyvdbWw7NtV4clbBcB6oQqNJuXO+bz19eTivkCIE0JbN9nJn+BRz1icdJAUlAm1GDsGQZDUPdzOdbMmTlJAiEAqsz6MYXj/Eq0JR4+5/UAF/syhh+P2PSKBOEDo1I18FkCIQD2uNz0Df+qg4ZUePxXbvXNWoBFqdM3PUyEYNKHEXEj3Q==");
        System.out.println(verification);
        System.out.println(RSAUtil.verify("1", verification + "dfguhaidfhgiaseufgh", "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAOwQ4xSaRDASC7pq3/qb25f5WN8ICbE0599J6aUoG8nLaZWkvWq/QIYHOd1xXcVZ9Bjwnhj0g3PlVu3RR7s8HI8CAwEAAQ=="));
    }
}
