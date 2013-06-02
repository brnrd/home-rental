package web.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * @author Romain <ro.foncier@gmail.com>
 */
public class PasswordHasher {

    private final static int ITERATION_NUMBER = 10; // Normally, It should be about 1000 ! ;-)

    public PasswordHasher() {
    }

    public String MakePassword(String password) {
        //try {

            ShaPasswordEncoder encoder = new ShaPasswordEncoder(256);
            return encoder.encodePassword(password, "");
            
            //MessageDigest md = MessageDigest.getInstance("SHA-256");
            //md.update(password.getBytes()); // Change this to "UTF-16" if needed
            //byte[] digest = md.digest();

            // Digest computation
            //byte[] bDigest = getHash(ITERATION_NUMBER, password);
            //String sDigest = byteToBase64(digest);
            //return byteToBase64(digest);
        //} catch (NoSuchAlgorithmException e) {}
        //} catch (UnsupportedEncodingException e) {}
        //return null;
    }

    /**
     * From a password, a number of iterations and a salt, returns the
     * corresponding digest
     *
     * @param iterationNb int The number of iterations of the algorithm
     * @param password String The password to encrypt
     * @param salt byte[] The salt
     * @return byte[] The digested password
     * @throws NoSuchAlgorithmException If the algorithm doesn't exist
     */
    public byte[] getHash(int iterationNb, String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();
        //digest.update(salt);
        try {
            byte[] input = digest.digest(password.getBytes("UTF-8"));
            /*
             for (int i = 0; i < iterationNb; i++) {
             digest.reset();
             input = digest.digest(input);
             }*/
            return input;
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * From a base 64 representation, returns the corresponding byte[]
     *
     * @param data String The base64 representation
     * @return byte[]
     * @throws IOException
     */
    public byte[] base64ToByte(String data) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(data);
    }

    /**
     * From a byte[] returns a base 64 representation
     *
     * @param data byte[]
     * @return String
     * @throws IOException
     */
    public String byteToBase64(byte[] data) {
        BASE64Encoder endecoder = new BASE64Encoder();
        return endecoder.encode(data);
    }
}