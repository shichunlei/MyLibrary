package chingtech.library.utils;

import java.math.BigInteger;

/**
 * HealthPadApp-v2
 * Package chingtech.library.utils
 * Description:
 * Created by 师春雷
 * Created at 17/6/15 下午4:56
 */
public class ConversionUtils {

    private static final String hexStr = "0123456789ABCDEF";

    private static String[] binaryArray =
            {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010",
                    "1011", "1100", "1101", "1110", "1111"};

    private ConversionUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 十进制转十六进制
     *
     * @param i
     * @return
     */
    public static String intToHex(int i) {
        return Integer.toHexString(i);
    }

    /**
     * 十进制转二进制
     *
     * @param i
     * @return
     */
    public static String intToBinary(int i) {
        return Integer.toBinaryString(i);
    }

    /**
     * 十进制转八进制
     *
     * @param i
     * @return
     */
    public static String intToOctal(int i) {
        return Integer.toOctalString(i);
    }

    /**
     * 十进制转十六进制
     *
     * @param iStr
     * @return
     */
    public static String intStrToHex(String iStr) {
        return intToHex(Integer.parseInt(iStr));
    }

    /**
     * 十进制转二进制
     *
     * @param iStr
     * @return
     */
    public static String intStrToBinary(String iStr) {
        return intToBinary(Integer.parseInt(iStr));
    }

    /**
     * 十进制转八进制
     *
     * @param iStr
     * @return
     */
    public static String intStrToOctal(String iStr) {
        return intToOctal(Integer.parseInt(iStr));
    }

    /**
     * 十六进制转十进制
     *
     * @param hexStr
     * @return
     */
    public static String hexToIntStr(String hexStr) {
        BigInteger srch = new BigInteger(hexStr.replaceAll("\\s*", ""), 16);
        return srch.toString();
    }

    /**
     * 二进制转十进制
     *
     * @param binaryStr
     * @return
     */
    public static String binaryToIntStr(String binaryStr) {
        BigInteger srcb = new BigInteger(binaryStr.replaceAll("\\s*", ""), 2);//转换为BigInteger类型
        return srcb.toString();
    }

    /**
     * 八进制转十进制
     *
     * @param octalStr
     * @return
     */
    public static String octalToIntStr(String octalStr) {
        BigInteger srcb = new BigInteger(octalStr.replaceAll("\\s*", ""), 8);
        return srcb.toString();
    }

    /**
     * 字节数组转 16进制字符串
     *
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {

        String result = "";
        String hex;
        for (int i = 0; i < bytes.length; i++) {
            //字节高4位
            hex = String.valueOf(hexStr.charAt((bytes[i] & 0xF0) >> 4));
            //字节低4位
            hex += String.valueOf(hexStr.charAt(bytes[i] & 0x0F));
            result += hex + " ";  //这里可以去掉空格，或者添加0x标识符。
        }
        return result;
    }

    /**
     * 16进制字符串转 字节数组
     *
     * @param str
     * @return
     */
    public static byte[] hexToBytes(String str) {
        //如果字符串长度不为偶数，则追加0
        if (str.length() % 2 != 0) {
            str = "0" + str;
        }

        byte[] b = new byte[str.length() / 2];
        byte   c1, c2;
        for (int y = 0, x = 0; x < str.length(); ++y, ++x) {
            c1 = (byte) str.charAt(x);
            if (c1 > 0x60) {
                c1 -= 0x57;
            } else if (c1 > 0x40) {
                c1 -= 0x37;
            } else {
                c1 -= 0x30;
            }
            c2 = (byte) str.charAt(++x);
            if (c2 > 0x60) {
                c2 -= 0x57;
            } else if (c2 > 0x40) {
                c2 -= 0x37;
            } else {
                c2 -= 0x30;
            }
            b[y] = (byte) ((c1 << 4) + c2);
        }
        return b;
    }

    /**
     * byte数组转换为二进制字符串,每个字节以","隔开
     *
     * @param bArray
     * @return
     */
    public static String bytesToBinary(byte[] bArray) {

        String outStr = "";
        int    pos;
        for (byte b : bArray) {
            //高四位
            pos = (b & 0xF0) >> 4;
            outStr += binaryArray[pos];
            //低四位
            pos = b & 0x0F;
            outStr += binaryArray[pos] + " ";
        }
        return outStr.toString().substring(0, outStr.length() - 1);
    }

    /**
     * 二进制字符串转换为byte数组,每个字节以","隔开
     *
     * @param binaryStr
     * @return
     */
    public static byte[] binaryToByte(String binaryStr) {
        String[] temp = binaryStr.split(",");
        byte[]   b    = new byte[temp.length];
        for (int i = 0; i < b.length; i++) {
            b[i] = Long.valueOf(temp[i], 2).byteValue();
        }
        return b;
    }
}
