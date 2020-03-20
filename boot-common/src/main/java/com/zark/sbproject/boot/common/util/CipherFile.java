package com.zark.sbproject.boot.common.util;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;

/**
 * @author Zark
 */
public class CipherFile {

    // 加密类型，支持这三种DESede,Blowfish,AES
    private static final String ENCRYPT_TYPE = "AES";
    // 加密秘钥，长度为24字节
    private static final String ENCRYPT_KEY = "mQbJILokBccRHUkS+XBk7A==";


    private Cipher initAESCipher(int cipherMode) throws Exception {
//        KeyGenerator generator = KeyGenerator.getInstance(ENCRYPT_TYPE);
//        generator.init(128, new SecureRandom(ENCRYPT_KEY.getBytes()));
//
//        SecretKey secretKey = generator.generateKey();
//        byte[] codeFormat = secretKey.getEncoded();
//
//        SecretKeySpec keySpec = new SecretKeySpec(codeFormat, ENCRYPT_TYPE);
//        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
//        cipher.init(cipherMode, keySpec);
//

        SecretKey secretKey = new SecretKeySpec(ENCRYPT_KEY.getBytes(), ENCRYPT_TYPE);
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);
        cipher.init(cipherMode, secretKey);

        return cipher;

    }


    /**
     * 加密文件
     *
     * @param srcFileName  要加密的文件
     * @param destFileName 加密后存放的文件名
     */
    public void encryptFile(String srcFileName, String destFileName) throws Exception {
        Cipher cipher = initAESCipher(Cipher.ENCRYPT_MODE);

        try (InputStream is = new FileInputStream(srcFileName);
             OutputStream out = new FileOutputStream(destFileName);
             CipherInputStream cis = new CipherInputStream(is, cipher)) {

            writeToOutStream(cis, out);
        }
    }


    private void writeToOutStream(InputStream inputStream, OutputStream outputStream) throws Exception {
        byte[] buffer = new byte[1024];
        int r;
        while ((r = inputStream.read(buffer)) > 0) {
            outputStream.write(buffer, 0, r);
        }
    }


    /**
     * 解密文件
     *
     * @param srcFileName  要解密的文件
     * @param destFileName 解密后存放的文件名
     */
    public void decryptFile(String srcFileName, String destFileName) throws Exception {
        Cipher cipher = initAESCipher(Cipher.DECRYPT_MODE);

        try (InputStream is = new FileInputStream(srcFileName);
             OutputStream out = new FileOutputStream(destFileName);
             CipherOutputStream cos = new CipherOutputStream(out, cipher)) {

            writeToOutStream(is, cos);
        }
    }

    /***
     * 测试加密解密
     * @param args
     */
    public static void main(String[] args) throws Exception {
        CipherFile deEncrypt = new CipherFile();
        // 加密
        deEncrypt.encryptFile("/Users/zark/tempfile/sbdemo.sql", "/Users/zark/tempfile/sbdemo_encrypt.sql");
        // 解密
        deEncrypt.decryptFile("/Users/zark/tempfile/sbdemo_encrypt.sql", "/Users/zark/tempfile/sbdemo_decrypt.sql");
    }
}