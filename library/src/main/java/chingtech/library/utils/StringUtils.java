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
	 * 分钟数转小时：分钟
	 *
	 * @param mins
	 * @return
	 */
	public static String formatTime(int mins){

		int hour = mins / 60;
		int min = mins % 60;

		return hour + "小时" + min + "分钟";
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

	/**
	 * 检测一个String类型的数字是否为整数
	 *
	 * @param number
	 * @return
     */
	public static boolean checkNumInt(String number){
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

		char[] hunit = {'拾', '佰', '仟'}; // 段内位置表示
		char[] vunit = {'万', '亿'}; // 段名表示
		char[] digit = {'零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖'}; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角" + digit[rail.charAt(1) - '0']
					+ "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
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
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}
}
