package chingtech.library.utils;

import android.support.annotation.NonNull;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加密解密
 *
 * Created by CodingQiang on 2016/11/16.
 */
public class DESHelper {

    private static final String KEY = "@njytyl&";

    private static final String DES_CBC = "DES/CBC/PKCS5Padding";

    // 解密数据
    public static String decrypt(String message, String key) throws Exception {
        byte[]           bytesrc    = convertHexString(message);
        Cipher           cipher     = Cipher.getInstance(DES_CBC);
        DESKeySpec       desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey        secretKey  = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec  iv         = new IvParameterSpec(key.getBytes("UTF-8"));

        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);

        byte[] retByte = cipher.doFinal(bytesrc);
        return new String(retByte);
    }

    // 加密
    @NonNull
    public static String encrypt(String message, String key) throws Exception {
        Cipher cipher = Cipher.getInstance(DES_CBC);

        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey        secretKey  = keyFactory.generateSecret(desKeySpec);
        IvParameterSpec  iv         = new IvParameterSpec(key.getBytes("UTF-8"));
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);

        return toHexString(cipher.doFinal(message.getBytes("UTF-8")));
    }

    public static byte[] convertHexString(String ss) {
        byte digest[] = new byte[ss.length() / 2];
        for (int i = 0; i < digest.length; i++) {
            String byteString = ss.substring(2 * i, 2 * i + 2);
            int    byteValue  = Integer.parseInt(byteString, 16);
            digest[i] = (byte) byteValue;
        }

        return digest;
    }

    @NonNull
    public static String toHexString(byte b[]) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            String plainText = Integer.toHexString(0xff & b[i]);
            if (plainText.length() < 2) {
                plainText = "0" + plainText;
            }
            hexString.append(plainText);
        }

        return hexString.toString();
    }

    public static void main(String[] args) throws Exception {
        String testStr = "123";
        System.out.println(encrypt(testStr, KEY));
        System.out.println(decrypt(encrypt(testStr, KEY), KEY));
    }
}
