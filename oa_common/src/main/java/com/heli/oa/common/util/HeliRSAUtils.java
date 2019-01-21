package com.heli.oa.common.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import org.apache.commons.codec.binary.Base64;

public class HeliRSAUtils {
    private static final String ALGORITHM = "RSA";
    private static final String PUBLICK_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9LKJAxjR5690/9CDBSuvmAZ2e23Q6GI64Q2y9CsXRvIVcd+K6b9qbjuDpZB2/A1LpTIHGYYg5672eKbC7+OZQjJUPBSaZMoEXYHsEN1tbxTG239LZM1xGUGwpTGE4p0gJxBSyS0GcRr0cxV4CCG+hG+S36677qxoQ36WudrFw6wIDAQAB";
    private static final String PRIVATE_KEY = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL0sokDGNHnr3T/0IMFK6+YBnZ7bdDoYjrhDbL0KxdG8hVx34rpv2puO4OlkHb8DUulMgcZhiDnrvZ4psLv45lCMlQ8FJpkygRdgewQ3W1vFMbbf0tkzXEZQbClMYTinSAnEFLJLQZxGvRzFXgIIb6Eb5LfrrvurGhDfpa52sXDrAgMBAAECgYAlw7JEUg0Jxc8uRDMBw04iZXIs00x5iIt9N2COwIbLHuJUmk41v7yZT0WQ8f6KMkSr9Cz4CGJkha4d097HN6HqW9XfYkr2eYQMtHTBTF6OGJs0cWDRN8+pRE+i5c2yQCkPdIPpMQiW56ABCkr0yT8cghTKWf6eCW0N2Azyt/XnGQJBAP2l5IwvTjwlp7iUTQySRE+8sAFs+XDs9Ep1jntKyuy8bguOO8D0R1yv3O7BUcsTsZbc2fqutSvsk5eTO8Dej20CQQC+7bGuC6W+35cD+e96x6JxV5O0DI6P6guj1bU5Xr+j+mMA1s/72xk6xNtLmwEMil+yFqvGXUdMVwGOEZJzSFK3AkBnHs6v/3t7EIQDkqdQeRa0pC2aJseylWMr8mce0OV3IZRLtmOsqqNol0bm+klamoMUpm0ocWbOmLvYX89nNhRxAkBdD+6zLuozBzjMr16TMgtzw4QzFovGigXybZBPzmcKvljTZ+EombrKcnmReJQiULAkVfiUT5MZGq+fV+FSX8YxAkBQNdAqVMSg5CI0Bumn/2ovAjHVh1uwLxlW7FCbMuEvxpJ/4w4F1mmJKoDYg8ePEeG6TPpUjuuYWU+/tJAUTHxe";
    private static final String CIPHER_DE = "RSA";
    private static final String CIPHER_EN = "RSA";
    private static final int MAX_ENCRYPT_BLOCK = 117;
    private static final int MAX_DECRYPT_BLOCK = 128;

    public HeliRSAUtils() {
    }

    public static String encryptByPublicKey(String sourceStr) throws Exception {
        byte[] data = sourceStr.getBytes();
        byte[] keyBytes = Base64.decodeBase64("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC9LKJAxjR5690/9CDBSuvmAZ2e23Q6GI64Q2y9CsXRvIVcd+K6b9qbjuDpZB2/A1LpTIHGYYg5672eKbC7+OZQjJUPBSaZMoEXYHsEN1tbxTG239LZM1xGUGwpTGE4p0gJxBSyS0GcRr0cxV4CCG+hG+S36677qxoQ36WudrFw6wIDAQAB".getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePublic(x509EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(1, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;

        for(int i = 0; inputLength - offset > 0; offset = i * 117) {
            byte[] cache;
            if (inputLength - offset > 117) {
                cache = cipher.doFinal(data, offset, 117);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }

            out.write(cache, 0, cache.length);
            ++i;
        }

        byte[] encryptedData = out.toByteArray();
        out.close();
        byte[] btt = Base64.encodeBase64(encryptedData);
        String encryptStr = new String(btt);
        return encryptStr;
    }

    public static String decryptByPrivateKey(String sourceStr) throws Exception {
        byte[] data = Base64.decodeBase64(Base64.encodeBase64(Base64.decodeBase64(sourceStr.getBytes())));
        byte[] keyBytes = Base64.decodeBase64("MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL0sokDGNHnr3T/0IMFK6+YBnZ7bdDoYjrhDbL0KxdG8hVx34rpv2puO4OlkHb8DUulMgcZhiDnrvZ4psLv45lCMlQ8FJpkygRdgewQ3W1vFMbbf0tkzXEZQbClMYTinSAnEFLJLQZxGvRzFXgIIb6Eb5LfrrvurGhDfpa52sXDrAgMBAAECgYAlw7JEUg0Jxc8uRDMBw04iZXIs00x5iIt9N2COwIbLHuJUmk41v7yZT0WQ8f6KMkSr9Cz4CGJkha4d097HN6HqW9XfYkr2eYQMtHTBTF6OGJs0cWDRN8+pRE+i5c2yQCkPdIPpMQiW56ABCkr0yT8cghTKWf6eCW0N2Azyt/XnGQJBAP2l5IwvTjwlp7iUTQySRE+8sAFs+XDs9Ep1jntKyuy8bguOO8D0R1yv3O7BUcsTsZbc2fqutSvsk5eTO8Dej20CQQC+7bGuC6W+35cD+e96x6JxV5O0DI6P6guj1bU5Xr+j+mMA1s/72xk6xNtLmwEMil+yFqvGXUdMVwGOEZJzSFK3AkBnHs6v/3t7EIQDkqdQeRa0pC2aJseylWMr8mce0OV3IZRLtmOsqqNol0bm+klamoMUpm0ocWbOmLvYX89nNhRxAkBdD+6zLuozBzjMr16TMgtzw4QzFovGigXybZBPzmcKvljTZ+EombrKcnmReJQiULAkVfiUT5MZGq+fV+FSX8YxAkBQNdAqVMSg5CI0Bumn/2ovAjHVh1uwLxlW7FCbMuEvxpJ/4w4F1mmJKoDYg8ePEeG6TPpUjuuYWU+/tJAUTHxe".getBytes());
        PKCS8EncodedKeySpec pKCS8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        Key key = keyFactory.generatePrivate(pKCS8EncodedKeySpec);
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(2, key);
        int inputLength = data.length;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int offset = 0;

        for(int i = 0; inputLength - offset > 0; offset = i * 128) {
            byte[] cache;
            if (inputLength - offset > 128) {
                cache = cipher.doFinal(data, offset, 128);
            } else {
                cache = cipher.doFinal(data, offset, inputLength - offset);
            }

            out.write(cache);
            ++i;
        }

        byte[] decryptedData = out.toByteArray();
        out.close();
        return new String(decryptedData);
    }
}