package com.zark.sbproject.boot.common.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Base64;

/**
 * @author Zark
 */
public class CipherFile {

    private static final String ENCRYPT_TYPE = "AES/CBC/PKCS5Padding";

    private static final String ENCRYPT_KEY = "emh1YW5nY2h1YW4xMjM0NQ==";


    private Cipher initAESCipher(int cipherMode) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPT_TYPE);

        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(ENCRYPT_KEY), "AES");
        IvParameterSpec iv = new IvParameterSpec(secretKey.getEncoded());

        cipher.init(cipherMode, secretKey, iv);

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
        deEncrypt.encryptFile("D:\\file\\file.txt", "D:\\file\\file_encrypt.txt");
        // 解密
        deEncrypt.decryptFile("D:\\file\\file_encrypt.txt", "D:\\file\\file_decrypt.txt");
    }
}