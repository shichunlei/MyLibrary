package chingtech.library.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    private static final String UNKNOWN_SIZE = "00:00";

    private StringUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 是否不为空
     *
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str.trim());
    }

    /**
     * 是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        if (str == null || "".equals(str) || str.length() == 0) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证两个值是否相同
     *
     * @param value1
     * @param value2
     * @return
     */
    public static boolean checkValSame(String value1, String value2) {
        return value1.equals(value2);
    }

    /**
     * 验证邮箱的合法性
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {

        if (email.contains("@")) {
            Pattern pattern = Pattern.compile(
                    "^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }

        BigDecimal b   = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 精确的小数位四舍五入处理
     *
     * @param v     需要四舍五入的数字
     * @param scale 小数点后保留几位
     * @return 四舍五入后的结果
     */
    public static String round(String v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(v);
        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 将double型转换成小数的String型
     *
     * @param value
     * @return
     */
    public static String doubleToString(double value) {
        return round(value, 2);
    }

    /**
     * 将小数转换成百分数
     *
     * @param decimal
     * @return
     */
    public static String doubleToPercentage(double decimal) {
        DecimalFormat df = new DecimalFormat("#0.0%");
        return df.format(decimal);
    }

    /**
     * 金额分割，四舍五人金额
     *
     * @param s
     * @return
     */
    public static String formatMoney(BigDecimal s) {
        String  retVal;
        String  str;
        boolean is_positive_integer;
        if (null == s) {
            return "0.00";
        }

        if (0 == s.doubleValue()) {
            return "0.00";
        }
        //判断是否正整数
        if (s.toString().indexOf("-") != -1) {
            is_positive_integer = true;
        } else {
            is_positive_integer = false;
        }
        //是负整数
        if (is_positive_integer) {
            //去掉 - 号
            s = new BigDecimal(s.toString().substring(1, s.toString().length()));
        }
        str = s.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        StringBuffer sb   = new StringBuffer();
        String[]     strs = str.split("\\.");
        int          j    = 1;
        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(strs[0].length() - i - 1);
            sb.append(a);
            if (j % 3 == 0 && i != strs[0].length() - 1) {
                sb.append(",");
            }
            j++;
        }
        String       str1 = sb.toString();
        StringBuffer sb1  = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(str1.length() - 1 - i);
            sb1.append(a);
        }
        sb1.append(".");
        sb1.append(strs[1]);
        retVal = sb1.toString();

        if (is_positive_integer) {
            retVal = "-" + retVal;
        }
        return retVal;
    }

    /**
     * 四舍五人金额
     *
     * @param value
     * @return
     */
    public static String roundMoney(BigDecimal value) {
        String  retVal;
        String  str;
        boolean is_positive_integer;
        if (null == value) {
            return "0.00";
        }

        if (0 == value.doubleValue()) {
            return "0.00";
        }
        //判断是否正整数
        if (value.toString().indexOf("-") != -1) {
            is_positive_integer = true;
        } else {
            is_positive_integer = false;
        }
        //是负整数
        if (is_positive_integer) {
            //去掉 - 号
            value = new BigDecimal(value.toString().substring(1, value.toString().length()));
        }
        str = value.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
        StringBuffer sb   = new StringBuffer();
        String[]     strs = str.split("\\.");
        int          j    = 1;
        for (int i = 0; i < strs[0].length(); i++) {
            char a = strs[0].charAt(strs[0].length() - i - 1);
            sb.append(a);
            if (j % 3 == 0 && i != strs[0].length() - 1) {
                sb.append("");
            }
            j++;
        }
        String       str1 = sb.toString();
        StringBuffer sb1  = new StringBuffer();
        for (int i = 0; i < str1.length(); i++) {
            char a = str1.charAt(str1.length() - 1 - i);
            sb1.append(a);
        }
        sb1.append(".");
        sb1.append(strs[1]);
        retVal = sb1.toString();

        if (is_positive_integer) {
            retVal = "-" + retVal;
        }
        return retVal;
    }

    /**
     * 将时间秒数格式化为 HH:mm:ss格式
     *
     * @param seconds
     * @return
     */
    public static String formatTimeLength(int seconds) {

        String formatLength;
        if (seconds == 0) {
            formatLength = UNKNOWN_SIZE;
        } else if (seconds < 60) {//小于1分钟
            formatLength = "00:" + (seconds < 10 ? "0" + seconds : seconds);
        } else if (seconds < 60 * 60) {//小于1小时
            long sec = seconds % 60;
            long min = seconds / 60;
            formatLength = (min < 10 ? "0" + min : String.valueOf(min)) + ":" + (sec < 10 ?
                    "0" + sec : String.valueOf(sec));
        } else {
            long hour = seconds / 3600;
            long min  = seconds % 3600 / 60;
            long sec  = seconds % 3600 % 60;
            formatLength = (hour < 10 ? "0" + hour : String.valueOf(hour)) + ":" + (min < 10 ?
                    "0" + min : String.valueOf(min)) + ":" + (sec < 10 ? "0" + sec :
                    String.valueOf(sec));
        }
        return formatLength;
    }

    /**
     * 分钟数转小时：分钟
     *
     * @param mins
     * @return
     */
    public static String formatTime(int mins) {

        int hour = mins / 60;
        int min  = mins % 60;

        return hour + "小时" + min + "分钟";
    }

    /**
     * 判断一个数字是奇数还是偶数
     *
     * @param number
     * @return
     */
    public static boolean isOdd(int number) {
        if (number % 2 == 1) {   //是奇数
            return true;
        }
        return false;
    }

    /**
     * 检测一个String类型的数字是否为整数
     *
     * @param number
     * @return
     */
    public static boolean checkNumInt(String number) {
        String regexInteger = "-?\\d*";
        return number.matches(regexInteger);
    }

    /**
     * 人民币转成大写
     *
     * @param value
     * @return String
     */
    public static String hangeToBig(double value) {

        char[] hunit  = {'拾', '佰', '仟'}; // 段内位置表示
        char[] vunit  = {'万', '亿'}; // 段名表示
        char[] digit  = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
        long   midVal = (long) (value * 100); // 转化成整形
        String valStr = String.valueOf(midVal); // 转化成字符串

        String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
        String rail = valStr.substring(valStr.length() - 2); // 取小数部分

        String prefix = ""; // 整数部分转化的结果
        String suffix; // 小数部分转化的结果
        // 处理小数点后面的数
        if (rail.equals("00")) { // 如果小数部分为0
            suffix = "整";
        } else {
            suffix = digit[rail.charAt(0) - '0']
                    + "角"
                    + digit[rail.charAt(1) - '0']
                    + "分"; // 否则把角分转化出来
        }
        // 处理小数点前面的数
        char[] chDig      = head.toCharArray(); // 把整数部分转化成字符数组
        char   zero       = '0'; // 标志'0'表示出现过0
        byte   zeroSerNum = 0; // 连续出现0的次数
        for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
            int idx  = (chDig.length - i - 1) % 4; // 取段内位置
            int vidx = (chDig.length - i - 1) / 4; // 取段位置
            if (chDig[i] == '0') { // 如果当前字符是0
                zeroSerNum++; // 连续0次数递增
                if (zero == '0') { // 标志
                    zero = digit[0];
                } else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
                    prefix += vunit[vidx - 1];
                    zero = '0';
                }
                continue;
            }
            zeroSerNum = 0; // 连续0次数清零
            if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
                prefix += zero;
                zero = '0';
            }
            prefix += digit[chDig[i] - '0']; // 转化该数字表示
            if (idx > 0) {
                prefix += hunit[idx - 1];
            }
            if (idx == 0 && vidx > 0) {
                prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
            }
        }

        if (prefix.length() > 0) {
            prefix += '圆'; // 如果整数部分存在,则有圆的字样
        }
        return prefix + suffix; // 返回正确表示
    }
}
