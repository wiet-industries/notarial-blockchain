package logic.utils;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RSAUtil {
    public static String sign(String plainText, String privateKeyString) {
        try{
            PrivateKey privateKey = getPrivateKey(privateKeyString);
            Signature privateSignature = Signature.getInstance("SHA256withRSA");
            privateSignature.initSign(privateKey);
            privateSignature.update(plainText.getBytes(UTF_8));

            byte[] signature = privateSignature.sign();

            return Base64.getEncoder().encodeToString(signature);
        } catch (Exception e) {
            System.err.println("Something went wrong with signing the verification field");
        }

        return null;
    }

    public static boolean verify(String plainText, String signature, String publicKeyString) {
        try{
            PublicKey publicKey = getPublicKey(publicKeyString);
            Signature publicSignature = Signature.getInstance("SHA256withRSA");
            publicSignature.initVerify(publicKey);
            publicSignature.update(plainText.getBytes(UTF_8));

            byte[] signatureBytes = Base64.getDecoder().decode(signature);

            return publicSignature.verify(signatureBytes);
        } catch (Exception e) {
            System.err.println("Something went wrong with verifing the verification field");
            return false;
        }
    }

    private static PublicKey getPublicKey(String key){
        try{
            byte[] byteKey = Base64.getDecoder().decode(key.getBytes());
            X509EncodedKeySpec X509publicKey = new X509EncodedKeySpec(byteKey);
            KeyFactory kf = KeyFactory.getInstance("RSA");

            return kf.generatePublic(X509publicKey);
        }
        catch(Exception e){
            System.err.println("Something went wrong with creating public Key");
        }

        return null;
    }

    private static PrivateKey getPrivateKey(String key) {
        try {
            byte [] pkcs8EncodedBytes = Base64.getDecoder().decode(key);

            // extract the private key

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(pkcs8EncodedBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            PrivateKey privKey = kf.generatePrivate(keySpec);

            return privKey;
        } catch (Exception e) {
            System.err.println("Something went wrong with creating private Key");
        }

        return null;
    }
}
