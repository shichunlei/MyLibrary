package chingtech.library.utils;

import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static chingtech.library.utils.TimeUtils.isLeapYear;
import static chingtech.library.utils.ValidationUtils.is15Idcard;
import static chingtech.library.utils.ValidationUtils.is18Idcard;
import static chingtech.library.utils.ValidationUtils.isNumber;

/**
 * <p>
 * 类说明:身份证合法性校验
 * </p>
 * <p>
 * --15位身份证号码
 * </p>
 * <p>
 * 第7、8位为出生年份(两位数)，第9、10位为出生月份，第11、12位代表出生日期，第15位代表性别，奇数为男，偶数为女。
 * </p>
 * <p>
 * --18位身份证号码
 * </p>
 * <p>
 * 第7、8、9、10位为出生年份(四位数)，第11、第12位为出生月份，第13、14位代表出生日期，第17位代表性别，奇数为男，偶数为女。
 * </p>
 */
public class IdcardUtil {

    //记录错误信息
    private static String errmsg = "";

    /**
     * 省，直辖市代码表： { 11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",
     * 21:"辽宁",22:"吉林",23:"黑龙江",31:"上海",32:"江苏",
     * 33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",
     * 42:"湖北",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",
     * 51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",
     * 63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"}
     */
    private static String codeAndCity[][] = {{"11", "北京"}, {"12", "天津"}, {"13", "河北"}, {"14", "山西"},
            {"15", "内蒙古"}, {"21", "辽宁"}, {"22", "吉林"}, {"23", "黑龙江"}, {"31", "上海"}, {"32", "江苏"},
            {"33", "浙江"}, {"34", "安徽"}, {"35", "福建"}, {"36", "江西"}, {"37", "山东"}, {"41", "河南"},
            {"42", "湖北"}, {"43", "湖南"}, {"44", "广东"}, {"45", "广西"}, {"46", "海南"}, {"50", "重庆"},
            {"51", "四川"}, {"52", "贵州"}, {"53", "云南"}, {"54", "西藏"}, {"61", "陕西"}, {"62", "甘肃"},
            {"63", "青海"}, {"64", "宁夏"}, {"65", "新疆"}, {"71", "台湾"}, {"81", "香港"}, {"82", "澳门"},
            {"91", "国外"}};

    private static String cityCode[] = {"11", "12", "13", "14", "15", "21", "22", "23", "31", "32",
            "33", "34", "35", "36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52",
            "53", "54", "61", "62", "63", "64", "65", "71", "81", "82", "91"};

    // 每位加权因子
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    // 第18位校检码
    private static String verifyCode[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * <p>
     * 判断18位身份证的合法性
     * </p>
     * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
     * <p>
     * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
     * </p>
     * <p>
     * 1.前1、2位数字表示：所在省份的代码；
     * </p>
     * <p>
     * 2.第3、4位数字表示：所在城市的代码；
     * </p>
     * <p>
     * 3.第5、6位数字表示：所在区县的代码；
     * </p>
     * <p>
     * 4.第7~14位数字表示：出生年、月、日；
     * </p>
     * <p>
     * 5.第15、16位数字表示：所在地的派出所的代码；
     * </p>
     * <p>
     * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
     * </p>
     * <p>
     * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
     * </p>
     * <p>
     * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4
     * 2
     * </p>
     * <p>
     * 2.将这17位数字和系数相乘的结果相加。
     * </p>
     * <p>
     * 3.用加出来和除以11，看余数是多少？
     * </p>
     * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2
     * <p>
     * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
     * </p>
     *
     * @param idcard
     * @return
     */
    public static boolean isIdcard(String idcard) {

        // 获取前17位
        String idcard17 = "";

        //================ 身份证号码的长度 15位或18位 ================
        if (!is15Idcard(idcard) && !is18Idcard(idcard)) {
            errmsg = "身份证号码长度应该为15位或18位!";
            Log.e("Tag", errmsg);
            return false;
        }

        if (idcard.length() == 18) {
            idcard17 = idcard.substring(0, 17);
        } else if (idcard.length() == 15) {
            idcard17 = idcard.substring(0, 6) + "19" + idcard.substring(6, 15);
        }

        //================ 出生年月是否有效 ================
        String birthday = idcard17.substring(6, 14);
        int    year     = Integer.parseInt(idcard17.substring(6, 10));
        int    month    = Integer.parseInt(idcard17.substring(10, 12));
        int    day      = Integer.parseInt(idcard17.substring(12, 14));

        // 该身份证生出日期在当前日期之后时为假
        Date birthdate = null;
        try {
            birthdate = new SimpleDateFormat("yyyyMMdd").parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (birthdate == null || new Date().before(birthdate)) {
            errmsg = "身份证生日不在有效范围";
            Log.e("Tag", errmsg);
            return false;
        }

        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat  s  = new SimpleDateFormat("yyyy-MM-dd");
        s.setLenient(false);// 用于设置Calendar是否宽松解析字符串，如果为false，则严格解析；默认为true，宽松解析
        try {
            if ((gc.get(Calendar.YEAR) - year) > 150
                    || (gc.getTime().getTime() - s.parse(year + "-" + month + "-" + day).getTime())
                    < 0) {
                errmsg = "身份证生日不在有效范围";
                Log.e("Tag", errmsg);
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            Log.e("Tag", errmsg);
            return false;
        } catch (java.text.ParseException e1) {
            e1.printStackTrace();
            errmsg = "身份证生日不在有效范围";
            Log.e("Tag", errmsg);
            return false;
        }

        if (month < 1 || month > 12) {
            errmsg = "身份证月份无效";
            Log.e("Tag", errmsg);
            return false;
        }

        // 判断是否为合法的日期
        boolean mflag = false;
        switch (month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                mflag = (day >= 1 && day <= 31);
                break;
            case 2: // 公历的2月非闰年有28天,闰年的2月是29天。
                if (isLeapYear(birthday)) {
                    mflag = (day >= 1 && day <= 29);
                } else {
                    mflag = (day >= 1 && day <= 28);
                }
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                mflag = (day >= 1 && day <= 30);
                break;
        }
        if (!mflag) {
            errmsg = "身份证日期无效";
            Log.e("Tag", errmsg);
            return false;
        }

        // ================ 地区码是否有效 ================
        boolean flag = false;
        for (String id : cityCode) {
            if (id.equals(idcard17.substring(0, 2))) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            errmsg = "身份证地区编码错误";
            Log.e("Tag", errmsg);
            return false;
        }

        //================ 数字 除最后以为都为数字 ================
        if (!isNumber(idcard17)) {
            errmsg = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字";
            Log.e("Tag", errmsg);
            return false;
        }

        if (idcard.length() == 18) {
            // 获取第18位
            String idcard18Code = idcard.substring(17, 18);
            char   c[]          = idcard17.toCharArray();
            String checkCode;

            if (null != c) {
                int bit[] = converCharToInt(c);

                int sum17 = getPowerSum(bit);

                // 将和值与11取模得到余数进行校验码判断
                checkCode = getCheckCodeBySum(sum17);
                if (null == checkCode) {
                    return false;
                }
                // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
                if (!idcard18Code.equalsIgnoreCase(checkCode)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    private static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    private static String getCheckCodeBySum(int sum17) {
        return verifyCode[sum17 % 11];
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    private static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int   k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }
}