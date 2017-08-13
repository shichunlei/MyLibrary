package chingtech.library.utils;

import android.annotation.SuppressLint;

import java.io.*;
import java.util.UUID;
import java.util.regex.Pattern;

/**
 * 主要功能：用于App验证数据验证
 *
 * @Prject: CommonUtilLibrary
 * @Package: com.jingewenku.abrahamcaijin.commonutil
 * @author: AbrahamCaiJin
 * @date: 2017年05月03日 16:37
 * @Copyright: 个人版权所有
 * @Company:
 * @version: 1.0.0
 */
@SuppressLint("SimpleDateFormat")
@SuppressWarnings("rawtypes")
public class ValidationUtils {

    //邮箱表达式
    private final static Pattern email_pattern = Pattern.compile(
            "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$");

    //手机号表达式
    private final static Pattern phone_pattern = Pattern.compile(
            "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147,145))\\d{8}$");

    //银行卡号表达式
    private final static Pattern bankNo_pattern = Pattern.compile("^[0-9]{16,19}$");

    //座机号码表达式
    private final static Pattern plane_pattern = Pattern.compile(
            "^((\\(\\d{2,3}\\))|(\\d{3}\\-))?(\\(0\\d{2,3}\\)|0\\d{2,3}-)?[1-9]\\d{6,7}(\\-\\d{1,4})?$");

    //非零表达式
    private final static Pattern notZero_pattern = Pattern.compile("^\\+?[1-9][0-9]*$");

    //数字表达式
    private final static Pattern number_pattern = Pattern.compile("^[0-9]*$");

    //大写字母表达式
    private final static Pattern upChar_pattern = Pattern.compile("^[A-Z]+$");

    //小写字母表达式
    private final static Pattern lowChar_pattern = Pattern.compile("^[a-z]+$");

    //大小写字母表达式
    private final static Pattern letter_pattern = Pattern.compile("^[A-Za-z]+$");

    //中文汉字表达式
    private final static Pattern chinese_pattern = Pattern.compile("^[\u4e00-\u9fa5],{0,}$");

    //条形码表达式
    private final static Pattern onecode_pattern = Pattern.compile(
            "^(([0-9])|([0-9])|([0-9]))\\d{10}$");

    //邮政编码表达式
    private final static Pattern postalcode_pattern = Pattern.compile("([0-9]{3})+.([0-9]{4})+");

    //IP地址表达式
    private final static Pattern ipaddress_pattern = Pattern.compile(
            "[1-9](\\d{1,2})?\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))\\.(0|([1-9](\\d{1,2})?))");

    //URL地址表达式
    private final static Pattern url_pattern = Pattern.compile(
            "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?");

    //用户名表达式
    private final static Pattern username_pattern = Pattern.compile(
            "^[A-Za-z0-9_]{1}[A-Za-z0-9_.-]{3,31}");

    //真实姓名表达式
    private final static Pattern realnem_pattern = Pattern.compile(
            "[\u4E00-\u9FA5]{2,5}(?:·[\u4E00-\u9FA5]{2,5})*");

    //匹配HTML标签,通过下面的表达式可以匹配出HTML中的标签属性。
    private final static Pattern html_patter = Pattern.compile(
            "<\\\\/?\\\\w+((\\\\s+\\\\w+(\\\\s*=\\\\s*(?:\".*?\"|'.*?'|[\\\\^'\">\\\\s]+))?)+\\\\s*|\\\\s*)\\\\/?>");

    //抽取注释,如果你需要移除HMTL中的注释，可以使用如下的表达式。
    private final static Pattern notes_patter = Pattern.compile("<!--(.*?)-->");

    //查找CSS属性,通过下面的表达式，可以搜索到相匹配的CSS属性。
    private final static Pattern css_patter = Pattern.compile(
            "^\\\\s*[a-zA-Z\\\\-]+\\\\s*[:]{1}\\\\s[a-zA-Z0-9\\\\s.#]+[;]{1}");

    //提取页面超链接,提取html中的超链接。
    private final static Pattern hyperlink_patter = Pattern.compile(
            "(<a\\\\s*(?!.*\\\\brel=)[^>]*)(href=\"https?:\\\\/\\\\/)((?!(?:(?:www\\\\.)?'.implode('|(?:www\\\\.)?', $follow_list).'))[^\"]+)\"((?!.*\\\\brel=)[^>]*)(?:[^>]*)>");

    //提取网页图片,假若你想提取网页中所有图片信息，可以利用下面的表达式。
    private final static Pattern image_patter = Pattern.compile(
            "\\\\< *[img][^\\\\\\\\>]*[src] *= *[\\\\\"\\\\']{0,1}([^\\\\\"\\\\'\\\\ >]*)");

    //提取Color Hex Codes,有时需要抽取网页中的颜色代码，可以使用下面的表达式。
    private final static Pattern color_patter = Pattern.compile(
            "^#([A-Fa-f0-9]{6}|[A-Fa-f0-9]{3})$");

    //文件路径及扩展名校验,验证windows下文件路径和扩展名（下面的例子中为.txt文件）
    private final static Pattern route_patter = Pattern.compile(
            "^([a-zA-Z]\\\\:|\\\\\\\\)\\\\\\\\([^\\\\\\\\]+\\\\\\\\)*[^\\\\/:*?\"<>|]+\\\\.txt(l)?$");

    private final static Pattern idcard_15 = Pattern.compile(
            "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");

    private final static Pattern idcard_18 = Pattern.compile(
            "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([\\d|x|X]{1})$");

    //提取URL链接,下面的这个表达式可以筛选出一段文本中的URL
    // "^(f|ht){1}(tp|tps):\\/\\/([\\w-]+\\.)+[\\w-]+(\\/[\\w- ./?%&=]*)?"
    //检查URL的前缀,应用开发中很多时候需要区分请求是HTTPS还是HTTP，通过下面的表达式可以取出一个url的前缀然后再逻辑判断。
    //if (!s.match(/^[a-zA-Z]+:\\/\\//))
    //	{
    //		s = 'http://' + s;
    //	}
    //校验IP-v6地址
    //	(([0-9a-fA-F]{1,4}:){7,7}[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,7}:|([0-9a-fA-F]{1,4}:){1,6}:[0-9a-fA-F]{1,4}|([0-9a-fA-F]{1,4}:){1,5}(:[0-9a-fA-F]{1,4}){1,2}|([0-9a-fA-F]{1,4}:){1,4}(:[0-9a-fA-F]{1,4}){1,3}|([0-9a-fA-F]{1,4}:){1,3}(:[0-9a-fA-F]{1,4}){1,4}|([0-9a-fA-F]{1,4}:){1,2}(:[0-9a-fA-F]{1,4}){1,5}|[0-9a-fA-F]{1,4}:((:[0-9a-fA-F]{1,4}){1,6})|:((:[0-9a-fA-F]{1,4}){1,7}|:)|fe80:(:[0-9a-fA-F]{0,4}){0,4}%[0-9a-zA-Z]{1,}|::(ffff(:0{1,4}){0,1}:){0,1}((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])|([0-9a-fA-F]{1,4}:){1,4}:((25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9])\\.){3,3}(25[0-5]|(2[0-4]|1{0,1}[0-9]){0,1}[0-9]))
    //校验IP-v4地址
    //	\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b
    //	判断IE的版本   "^.*MSIE [5-8](?:\\.[0-9]+)?(?!.*Trident\\/[5-9]\\.0).*$"
    //	校验金额 "^[0-9]+(.[0-9]{2})?$"
    //	校验密码强度   "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"

    /**
     * 验证非零正整数
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNotZero(String str) {
        return notZero_pattern.matcher(str).matches();
    }


    /**
     * 验证是数字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isNumber(String str) {
        return number_pattern.matcher(str).matches();
    }

    /**
     * 验证是大写字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isUpChar(String str) {
        return upChar_pattern.matcher(str).matches();
    }


    /**
     * 验证是小写字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isLowChar(String str) {
        return lowChar_pattern.matcher(str).matches();
    }


    /**
     * 验证是英文字母
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isLetter(String str) {
        return letter_pattern.matcher(str).matches();
    }


    /**
     * 验证输入汉字
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isChinese(String str) {
        return chinese_pattern.matcher(str).matches();
    }


    /**
     * 验证真实姓名
     *
     * @param str 验证字符
     * @return
     */
    public static boolean isRealName(String str) {
        return realnem_pattern.matcher(str).matches();
    }


    /**
     * 验证是否是条形码
     *
     * @param oneCode 条形码
     * @return boolean
     */
    public static boolean isOneCode(String oneCode) {
        return onecode_pattern.matcher(oneCode).matches();
    }


    /**
     * 验证邮箱是否正确
     *
     * @param email 邮箱地址
     * @return boolean
     */
    public static boolean isEmail(String email) {
        return email_pattern.matcher(email).matches();
    }


    /**
     * 验证手机号是否正确
     *
     * @param phone 手机号码
     * @return boolean
     */
    public static boolean isPhone(String phone) {
        return phone_pattern.matcher(phone).matches();
    }


    /**
     * 验证座机号码是否正确
     *
     * @param plane 座机号码
     * @return boolean
     */
    public static boolean isPlane(String plane) {
        return plane_pattern.matcher(plane).matches();
    }


    /**
     * 验证邮政编码是否正确
     *
     * @param postalcode 邮政编码
     * @return boolean
     */
    public static boolean isPostalCode(String postalcode) {
        return postalcode_pattern.matcher(postalcode).matches();
    }


    /**
     * 验证IP地址是否正确
     *
     * @param ipaddress IP地址
     * @return boolean
     */
    public static boolean isIpAddress(String ipaddress) {
        return ipaddress_pattern.matcher(ipaddress).matches();
    }


    /**
     * 验证URL地址是否正确
     *
     * @param url 地址
     * @return boolean
     */
    public static boolean isURL(String url) {
        return url_pattern.matcher(url).matches();
    }


    /**
     * 验证是否是正整数
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isInteger(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * 验证是否是小数
     *
     * @param paramString 验证字符
     * @return boolean
     */
    public static boolean isPoint(String paramString) {
        if (paramString.indexOf(".") > 0) {
            if (paramString.substring(paramString.indexOf(".")).length() > 3) {
                return false;
            }
        }
        return true;
    }


    /**
     * 验证是否银行卡号
     *
     * @param bankNo 银行卡号
     * @return
     */
    public static boolean isBankNo(String bankNo) {
        //替换空格
        bankNo = bankNo.replaceAll(" ", "");
        //银行卡号可为12位数字
        if (12 == bankNo.length()) {
            return true;
        }
        //银行卡号可为16-19位数字
        return bankNo_pattern.matcher(bankNo).matches();
    }

    /**
     * 判断是否有特殊字符
     *
     * @param str 验证字符
     * @return boolean
     */
    public static boolean isPeculiarStr(String str) {
        boolean flag  = false;
        String  regEx = "[^0-9a-zA-Z\u4e00-\u9fa5]+";
        if (str.length() != (str.replaceAll(regEx, "").length())) {
            flag = true;
        }
        return flag;
    }

    /**
     * 判断是否为用户名账号(规则如下：用户名由下划线或字母开头，由数字、字母、下划线、点、减号组成的4-32位字符)
     *
     * @param username 用户名
     * @return boolean
     */
    public static boolean isUserName(String username) {
        return username_pattern.matcher(username).matches();
    }

    /**
     * 获取字符串中文字符的长度（每个中文算2个字符）.
     *
     * @param str 指定的字符串
     * @return 中文字符的长度
     */
    public static int chineseLength(String str) {
        int    valueLength = 0;
        String chinese     = "[\u0391-\uFFE5]";
        /* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
        if (!StringUtils.isEmpty(str)) {
            for (int i = 0; i < str.length(); i++) {
                /* 获取一个字符 */
                String temp = str.substring(i, i + 1);
                /* 判断是否为中文字符 */
                if (temp.matches(chinese)) {
                    valueLength += 2;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取字符串的长度.
     *
     * @param str 指定的字符串
     * @return 字符串的长度（中文字符计2个）
     */
    public static int strLength(String str) {
        int    valueLength = 0;
        String chinese     = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    // 中文字符长度为2
                    valueLength += 2;
                } else {
                    // 其他字符长度为1
                    valueLength += 1;
                }
            }
        }
        return valueLength;
    }

    /**
     * 描述：获取指定长度的字符所在位置.
     *
     * @param str  指定的字符串
     * @param maxL 要取到的长度（字符长度，中文字符计2个）
     * @return 字符的所在位置
     */
    public static int subStringLength(String str, int maxL) {
        int    currentIndex = 0;
        int    valueLength  = 0;
        String chinese      = "[\u0391-\uFFE5]";
        // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
        for (int i = 0; i < str.length(); i++) {
            // 获取一个字符
            String temp = str.substring(i, i + 1);
            // 判断是否为中文字符
            if (temp.matches(chinese)) {
                // 中文字符长度为2
                valueLength += 2;
            } else {
                // 其他字符长度为1
                valueLength += 1;
            }
            if (valueLength >= maxL) {
                currentIndex = i;
                break;
            }
        }
        return currentIndex;
    }

    /**
     * 描述：是否只是字母和数字.
     *
     * @param str 指定的字符串
     * @return 是否只是字母和数字:是为true，否则false
     */
    public static Boolean isNumberLetter(String str) {
        Boolean isNoLetter = false;
        String  expr       = "^[A-Za-z0-9]+$";
        if (str.matches(expr)) {
            isNoLetter = true;
        }
        return isNoLetter;
    }

    /**
     * 描述：是否包含中文.
     *
     * @param str 指定的字符串
     * @return 是否包含中文:是为true，否则false
     */
    public static Boolean isContainChinese(String str) {
        Boolean isChinese = false;
        String  chinese   = "[\u0391-\uFFE5]";
        if (!StringUtils.isEmpty(str)) {
            // 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1
            for (int i = 0; i < str.length(); i++) {
                // 获取一个字符
                String temp = str.substring(i, i + 1);
                // 判断是否为中文字符
                if (temp.matches(chinese)) {
                    isChinese = true;
                } else {

                }
            }
        }
        return isChinese;
    }

    /**
     * 描述：从输入流中获得String.
     *
     * @param is 输入流
     * @return 获得的String
     */
    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder  sb     = new StringBuilder();
        String         line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            // 最后一个\n删除
            if (sb.indexOf("\n") != -1 && sb.lastIndexOf("\n") == sb.length() - 1) {
                sb.delete(sb.lastIndexOf("\n"), sb.lastIndexOf("\n") + 1);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    the str
     * @param length 指定字节长度
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length) {
        return cutString(str, length, "");
    }

    /**
     * 描述：截取字符串到指定字节长度.
     *
     * @param str    文本
     * @param length 字节长度
     * @param dot    省略符号
     * @return 截取后的字符串
     */
    public static String cutString(String str, int length, String dot) {
        int strBLen = strlen(str, "GBK");
        if (strBLen <= length) {
            return str;
        }
        int          temp = 0;
        StringBuffer sb   = new StringBuffer(length);
        char[]       ch   = str.toCharArray();
        for (char c : ch) {
            sb.append(c);
            if (c > 256) {
                temp += 2;
            } else {
                temp += 1;
            }
            if (temp >= length) {
                if (dot != null) {
                    sb.append(dot);
                }
                break;
            }
        }
        return sb.toString();
    }

    /**
     * 描述：截取字符串从第一个指定字符.
     *
     * @param str1   原文本
     * @param str2   指定字符
     * @param offset 偏移的索引
     * @return 截取后的字符串
     */
    public static String cutStringFromChar(String str1, String str2, int offset) {
        if (StringUtils.isEmpty(str1)) {
            return "";
        }
        int start = str1.indexOf(str2);
        if (start != -1) {
            if (str1.length() > start + offset) {
                return str1.substring(start + offset);
            }
        }
        return "";
    }

    /**
     * 描述：获取字节长度.
     *
     * @param str     文本
     * @param charset 字符集（GBK）
     * @return the int
     */
    public static int strlen(String str, String charset) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        int length = 0;
        try {
            length = str.getBytes(charset).length;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return length;
    }

    /**
     * 获取大小的描述.
     *
     * @param size 字节个数
     * @return 大小的描述
     */
    public static String getSizeDesc(long size) {
        String suffix = "B";
        if (size >= 1024) {
            suffix = "K";
            size = size >> 10;
            if (size >= 1024) {
                suffix = "M";
                // size /= 1024;
                size = size >> 10;
                if (size >= 1024) {
                    suffix = "G";
                    size = size >> 10;
                    // size /= 1024;
                }
            }
        }
        return size + suffix;
    }

    /**
     * 描述：ip地址转换为10进制数.
     *
     * @param ip the ip
     * @return the long
     */
    public static long ip2int(String ip) {
        ip = ip.replace(".", ",");
        String[] items = ip.split(",");
        return Long.valueOf(items[0]) << 24
                | Long.valueOf(items[1]) << 16
                | Long.valueOf(items[2]) << 8
                | Long.valueOf(items[3]);
    }

    /**
     * 获取UUID
     *
     * @return 32UUID小写字符串
     */
    public static String gainUUID() {
        String strUUID = UUID.randomUUID().toString();
        strUUID = strUUID.replaceAll("-", "").toLowerCase();
        return strUUID;
    }

    /**
     * 15位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is15Idcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : idcard_15.matcher(idcard).matches();
    }

    /**
     * 18位身份证号码的基本数字和位数验校
     *
     * @param idcard
     * @return
     */
    public static boolean is18Idcard(String idcard) {
        return idcard == null || "".equals(idcard) ? false : idcard_18.matcher(idcard).matches();
    }

    /**
     * 手机号码，中间4位星号替换
     *
     * @param phone 手机号
     * @return 星号替换的手机号
     */
    public static String phoneNoHide(String phone) {
        // 括号表示组，被替换的部分$n表示第n组的内容
        // 正则表达式中，替换字符串，括号的意思是分组，在replace()方法中，
        // 参数二中可以使用$n(n为数字)来依次引用模式串中用括号定义的字串。
        // "(\d{3})\d{4}(\d{4})", "$1****$2"的这个意思就是用括号，
        // 分为(前3个数字)中间4个数字(最后4个数字)替换为(第一组数值，保持不变$1)(中间为*)(第二组数值，保持不变$2)
        return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 银行卡号，保留最后4位，其他星号替换
     *
     * @param cardId 卡号
     * @return 星号替换的银行卡号
     */
    public static String cardIdHide(String cardId) {
        return cardId.replaceAll("\\d{15}(\\d{3})", "**** **** **** **** $1");
    }

    /**
     * 身份证号，中间10位星号替换
     *
     * @param id 身份证号
     * @return 星号替换的身份证号
     */
    public static String idHide(String id) {
        return id.replaceAll("(\\d{4})\\d{10}(\\d{4})", "$1** **** ****$2");
    }

    /**
     * 是否为车牌号（沪A88888）
     *
     * @param vehicleNo 车牌号
     * @return 是否为车牌号
     */
    public static boolean checkVehicleNo(String vehicleNo) {
        Pattern pattern = Pattern.compile("^[\u4e00-\u9fa5]{1}[a-zA-Z]{1}[a-zA-Z_0-9]{5}$");
        return pattern.matcher(vehicleNo).find();
    }
}
