package chingtech.library.utils;

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
		return str == null || "".equals(str.trim());
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
			Pattern pattern = Pattern
					.compile("^([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)*@([a-zA-Z0-9]*[-_]?[a-zA-Z0-9]+)+[\\.][A-Za-z]{2,3}([\\.][A-Za-z]{2})?$");
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
	 * 将double型的金钱数转换成两位小数的String型
	 * 
	 * @param money
	 * @return
	 */
	public static String doubleToString(double money) {
		DecimalFormat df = new DecimalFormat("######0.00");
		return df.format(money);
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
	 * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
	 * 
	 * @param str
	 *            无逗号的数字
	 * 
	 * @return 加上逗号的数字
	 */
	public static String addComma(String str) {

		String beforeStr;
		String afterStr = "";

		if (isEmpty(str)) {
			return "0";
		}

		if (str.contains(".")) {// 判断传过来的字符串是否含有小数点‘.’,即数字是否为小数
			// 截取小数点以及之后的字符串
			afterStr = str.substring(str.indexOf("."), str.length());
			// 截取小数点之前的字符串
			beforeStr = str.substring(0, str.indexOf("."));
		} else {
			beforeStr = str;
		}

		// 将传进数字反转
		String reverseStr = new StringBuilder(beforeStr).reverse().toString();

		String strTemp = "";

		for (int i = 0; i < reverseStr.length(); i++) {
			if (i * 3 + 3 > reverseStr.length()) {
				strTemp += reverseStr.substring(i * 3, reverseStr.length());
				break;
			}

			strTemp += reverseStr.substring(i * 3, i * 3 + 3) + ",";
		}

		// 将[789,456,] 中最后一个[,]去除
		if (strTemp.endsWith(",")) {
			strTemp = strTemp.substring(0, strTemp.length() - 1);
		}

		// 将数字重新反转
		String resultStr = new StringBuilder(strTemp).reverse().toString();
		return resultStr + afterStr;
	}

	/**
	 * 将时间秒数格式化为 HH:mm:ss格式
	 *
	 * @param seconds
	 * @return
	 */
	public static String formatTimeLength(int seconds) {

		String formatLength;
		if(seconds == 0) {
			formatLength = UNKNOWN_SIZE;
		} else if(seconds < 60) {//小于1分钟
			formatLength = "00:" + (seconds < 10 ? "0" + seconds : seconds);
		} else if(seconds < 60 * 60) {//小于1小时
			long sec = seconds % 60;
			long min = seconds / 60;
			formatLength = (min < 10 ? "0" + min : String.valueOf(min)) + ":" +
					(sec < 10 ? "0" + sec : String.valueOf(sec));
		} else {
			long hour = seconds / 3600;
			long min = seconds % 3600 / 60;
			long sec = seconds % 3600 % 60;
			formatLength = (hour < 10 ? "0" + hour : String.valueOf(hour)) + ":" +
					(min < 10 ? "0" + min : String.valueOf(min)) + ":" +
					(sec < 10 ? "0" + sec : String.valueOf(sec));
		}
		return formatLength;
	}

	/**
	 * 判断一个数字是奇数还是偶数
	 *
	 * @param number
	 * @return
     */
	public static boolean isOdd(int number){
		if(number % 2 == 1){   //是奇数
			return true;
		}
		return false;
	}
}
