package com.shankes.util;

import java.util.Random;

public class RandomUtil {

	private static Random random = new Random();

	/**
	 * 获取随机数,最大值不超过max(不包括最大值max)
	 * 
	 * @param max
	 * @return
	 */
	public static int getRandom(int max) {
		return random.nextInt(max);
	}

	/**
	 * 随机生成（from至to之间的数,包括起始值from,不包括末尾值to）
	 * 
	 * @param from
	 *            起始值
	 * @param to
	 *            末尾值
	 * @return
	 */
	public static int getRandomBetween(int from, int to) {
		// (10 + random.nextInt(100-10)); // 随机生成（10至100之间的数,包括10,不包括100）
		return from + random.nextInt(to - from);
	}
}
