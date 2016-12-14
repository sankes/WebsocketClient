package com.shankes.volley.encode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Unicode编码字符和中文字符互转工具类
 * 
 * @author shankesmile
 */
public class UnicodeUtil {
	/**
	 * description：把中文字符串转换为十六进制Unicode编码字符串(assic码字符不转换)
	 * 
	 * @param string
	 *            中文字符
	 * @return Unicode编码字符
	 */
	public static String stringToUnicode(String string) {
		String str = "";

		for (int i = 0; i < string.length(); i++) {
			int ch = (int) string.charAt(i);

			if (ch > 255) {
				str += "\\u" + Integer.toHexString(ch);
			} else {
				// str += "\\" + Integer.toHexString(ch);
				str += string.substring(i, i + 1);
			}
		}
		return str;
	}

	/**
	 * 把十六进制Unicode编码字符串转换为中文字符串(assic码字符不转换)
	 * 
	 * @param unicodeString
	 *            Unicode编码字符
	 * @return
	 */
	public static String unicodeToString(String unicodeString) {
		Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
		Matcher matcher = pattern.matcher(unicodeString);
		char ch;
		while (matcher.find()) {
			ch = (char) Integer.parseInt(matcher.group(2), 16);
			unicodeString = unicodeString.replace(matcher.group(1), ch + "");
		}
		return unicodeString;
	}
}
