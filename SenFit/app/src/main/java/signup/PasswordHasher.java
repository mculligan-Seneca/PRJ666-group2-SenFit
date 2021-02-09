/*
PRJ666 Sen-Fit
init date: January 31st 2021
Author Mitchell Culligan
Version 1.0
PasswordHashwer Class
This class generates a salt and hashes passwords for member class.
 This class can also be used to compare hashed passwords
 */

package signup;



import android.util.Log;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;


public class PasswordHasher {


    private static final int ITERATION_COUNT=65536;//number of iterations the algorithm performs
    private static final int KEY_LENGTH=16;//defualt key length
    private static final String DEFAULT_ALGORITHM="PBKDF2WithHmacSHA1";//the algorithm to be performed during the hash
    private  byte[] salt;
    private int keyLength;
    PasswordHasher(){
        SecureRandom random = new SecureRandom();
        this.salt = new byte[KEY_LENGTH];
        this.keyLength= KEY_LENGTH;
        random.nextBytes(this.salt);
    }
    PasswordHasher(byte[] salt,int key_length){
        this.salt = new byte[key_length];
        this.keyLength=key_length;
        System.arraycopy(salt,0,this.salt,0,this.salt.length);
    }

    public byte[] hashPassword(String password)throws GeneralSecurityException {
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(),this.salt,
              ITERATION_COUNT,this.keyLength );//Password based encryption key
        byte[] hash=null;
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance(DEFAULT_ALGORITHM);
            //throws no such algorithm exception
            //client should be notified and member should not be stored in database
            hash = factory.generateSecret(spec).getEncoded();
        }catch(GeneralSecurityException ge){
            Log.e("Passwrd hasher",ge.getMessage());
            throw ge;

        }


        return hash;

    }

    public byte[] getSalt(){
        return this.salt;
    }


    //TODO: add compare function to check hased passwords

}
