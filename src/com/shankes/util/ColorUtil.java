package com.shankes.util;

import java.util.Random;

import android.graphics.Color;

public class ColorUtil {

	private static Random random = new Random();

	public static ColorRGB getRandomColorRGB() {
		ColorRGB colorRGB = new ColorRGB();
		int max = 256;
		colorRGB.setR(random.nextInt(max));
		colorRGB.setG(random.nextInt(max));
		colorRGB.setB(random.nextInt(max));
		return colorRGB; // 随机生成颜色
	}

	public static int getColorFromColorRGB(ColorRGB colorRGB) {
		return Color.rgb(colorRGB.getR(), colorRGB.getG(), colorRGB.getB());
	}

	public static int getColorReverseFromColorRGB(ColorRGB colorRGB) {
		return Color.rgb(255 - colorRGB.getR(), 255 - colorRGB.getG(), 255 - colorRGB.getB());
	}

	/**
	 * 16进制颜色转换成RGB三原色
	 * 
	 * @param colorHexStr
	 * @return
	 */
	public ColorRGB ColorHex2RGB(String colorHexStr) {
		ColorRGB colorRGB = null;
		if (!(colorHexStr.length() == 7) || !colorHexStr.startsWith("#")) {
			LogUtil.e("格式不正确！必须为六位十六进制数");
			return colorRGB;
		} else {
			String str2 = colorHexStr.substring(1, 3);
			String str3 = colorHexStr.substring(3, 5);
			String str4 = colorHexStr.substring(5, 7);
			colorRGB = new ColorRGB();
			colorRGB.setR(Integer.parseInt(str2, 16));
			colorRGB.setG(Integer.parseInt(str3, 16));
			colorRGB.setB(Integer.parseInt(str4, 16));
			return colorRGB;
		}
	}

	/**
	 * RGB三原色转换成16进制颜色
	 * 
	 * @param colorRGB
	 *            自定义三原色对象
	 * @return
	 */
	public String RGB2ColorHex(ColorRGB colorRGB) {
		return RGB2ColorHex(colorRGB.getR(), colorRGB.getG(), colorRGB.getB());
	}

	/**
	 * RGB三原色转换成16进制颜色
	 * 
	 * @param r
	 *            red
	 * @param g
	 *            green
	 * @param b
	 *            blue
	 * @return
	 */
	public String RGB2ColorHex(int r, int g, int b) {
		String colorHex = null;
		if ((0 <= r && r <= 255) && (0 <= g && g <= 255) && (0 <= b && b <= 255)) {
			colorHex = new String("#");
			colorHex = colorHex + Integer.toHexString(r) + Integer.toHexString(g) + Integer.toHexString(b);
			return colorHex;
		} else {
			LogUtil.e("RGB只能为0-255之间的整数");
			return colorHex;
		}
	}
}
