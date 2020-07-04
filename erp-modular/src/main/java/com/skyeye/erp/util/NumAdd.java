/**
 * Copyright 卫志强 QQ：598748873@qq.com Inc. All rights reserved.
 */
package com.skyeye.erp.util;

/**
 * 大数字相加
 * @author Lenovo
 *
 */
public class NumAdd {
	public static String BigNumAdd(String s1, String s2) {// 实现两个大数相加的功能
		if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {
			return add(s1, s2);
		} else if (s1.charAt(0) != '-' && s2.charAt(0) != '-') {
			return add(s1, s2);
		} else
			return add2(s1, s2);

	}

	public static String add(String s1, String s2) {// 两个正数相加，两个负数相加
		StringBuilder b1 = null, b2 = null;
		if (s1.charAt(0) != '-' && s2.charAt(0) != '-') {// 两个正数
			b1 = new StringBuilder(s1);
			b2 = new StringBuilder(s2);
		}
		if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {// 两个负数
			b1 = new StringBuilder(s1.substring(1, s1.length()));
			b2 = new StringBuilder(s2.substring(1, s2.length()));
		}
		int big = b1.length() - b2.length();
		int takeOver = 0;// 进位
		int sum = 0;
		char[] chs = new char[Math.abs(big)];
		if (big > 0) {// 向b2的高位补0
			for (int i = 0; i < big; i++) {
				chs[i] = '0';
			}
			b2.insert(0, chs);
		} else if (big < 0) {
			for (int i = 0; i < -big; i++) {
				chs[i] = '0';
			}
			b1.insert(0, chs);
		}
		for (int i = b1.length() - 1; i >= 0; i--) {
			sum = b1.charAt(i) - '0' + b2.charAt(i) - '0' + takeOver;
			if (sum >= 10) {
				sum -= 10;
				char c = (char) (sum + '0');
				b1.setCharAt(i, c);
				takeOver = 1;
				if (i == 0) {// 向最高位进一
					b1.insert(0, '1');
				}
			} else {
				char c = (char) (sum + '0');
				b1.setCharAt(i, c);
				takeOver = 0;
			}
		}
		if (s1.charAt(0) == '-' && s2.charAt(0) == '-') {// 两个负数
			return "-" + b1.toString();
		}
		return b1.toString();

	}

	public static String add2(String s1, String s2) {// 一个为正，一个为负
		String result = null;
		StringBuilder b1 = null;
		StringBuilder b2 = null;
		char sign = '+';
		int big = 0;// 两个数字长度的差值
		int jiewei = 0;
		int sum = 0;
		if (s1.charAt(0) == '-') {// 第一个数是负数
			b1 = new StringBuilder(s1.substring(1, s1.length()));
			b2 = new StringBuilder(s2);
			// step:去除-号，判断大小，因为减法竖式只能大数减小数，设置最终的符号标志位
			if (b2.length() < b1.length()) {// 被减数的长度小，结果肯定为负
				sign = '-';
			} else if (b2.length() == b1.length()) {// 长度相同时，两个数的大小取决于高位谁大
				for (int i = b2.length() - 1; i >= 0; i--) {
					if (b2.charAt(i) > b1.charAt(i))
						sign = '+';
					else if (b2.charAt(i) < b1.charAt(i))
						sign = '-';
				}
			} else
				sign = '+';
			// step2:找到最大的length.给较小的length高位补0
			if (sign == '+') {// 给b1高位补0
				big = b2.length() - b1.length();
				for (int i = 0; i < big; i++) {
					b1.insert(0, '0');
				}
				// step3:模拟相减操作，将每次的结果替换到被减数相应的位上
				// b2-b1
				for (int i = b2.length() - 1; i >= 0; i--) {
					sum = b2.charAt(i) - b1.charAt(i) - jiewei;
					if (sum < 0) {
						jiewei = 1;
						sum += 10;
						char c = (char) (sum + '0');
						b2.setCharAt(i, c);
					} else {
						jiewei = 0;
						char c = (char) (sum + '0');
						b2.setCharAt(i, c);
					}
				}
				// step4：去除结果中高位的0
				int index = 0;// 不为0的最高位下标
				for (int i = 0; i < b2.length(); i--) {
					char n = b2.charAt(i);
					if (n != '0') {
						index = i;
						break;
					}
				}
				result = b2.substring(index, b2.length());
			} else {// b2高位补0
				big = b1.length() - b2.length();
				for (int i = 0; i < big; i++) {
					b2.insert(0, '0');
				}
				// b1-b2
				for (int i = b1.length() - 1; i >= 0; i--) {
					sum = b1.charAt(i) - b2.charAt(i) - jiewei;
					if (sum < 0) {
						jiewei = 1;
						sum += 10;
						char c = (char) (sum + '0');
						b1.setCharAt(i, c);
					} else {
						jiewei = 0;
						char c = (char) (sum + '0');
						b1.setCharAt(i, c);
					}

				}
				// step4：去除结果中高位的0
				int index = 0;// 不为0的最高位下标
				for (int i = 0; i < b1.length(); i++) {
					char n = b1.charAt(i);
					if (n != '0') {
						index = i;
						break;
					}
				}
				// step5:负数要在最前面加-
				result = sign + b1.substring(index, b1.length());
			}

		}

		if (s2.charAt(0) == '-') {// 第二个数是负数
			b2 = new StringBuilder(s2.substring(1, s2.length()));
			b1 = new StringBuilder(s1);
			// step:去除-号，判断大小，因为减法竖式只能大数减小数，设置最终的符号标志位
			if (b1.length() < b2.length()) {// 被减数的长度小，结果肯定为负
				sign = '-';
			} else if (b2.length() == b1.length()) {// 长度相同时，两个数的大小取决于高位谁大
				for (int i = b2.length() - 1; i >= 0; i--) {
					if (b1.charAt(i) > b2.charAt(i))
						sign = '+';
					else if (b1.charAt(i) < b2.charAt(i))
						sign = '-';
				}
			} else
				sign = '+';
			// step2:找到最大的length.给较小的length高位补0
			if (sign == '+') {// 给b2高位补0
				big = b1.length() - b2.length();
				for (int i = 0; i < big; i++) {
					b2.insert(0, '0');
				}
				System.out.println(b1.toString());
				System.out.println(b2.toString());
				// step3:模拟相减操作，将每次的结果替换到被减数相应的位上
				// b2-b1;
				for (int i = b1.length() - 1; i >= 0; i--) {
					sum = b1.charAt(i) - b2.charAt(i) - jiewei;
					if (sum < 0) {
						jiewei = 1;
						sum += 10;
						char c = (char) (sum + '0');
						b1.setCharAt(i, c);
					} else {
						jiewei = 0;
						char c = (char) (sum + '0');
						b1.setCharAt(i, c);
					}
				}
				// step4：去除结果中高位的0
				int index = 0;// 不为0的最高位下标
				for (int i = 0; i < b1.length(); i++) {
					char n = b1.charAt(i);
					if (n != '0') {
						index = i;
						break;
					}
				}
				result = b1.substring(index, b2.length());
			} else {// b1高位补0
				big = b2.length() - b1.length();
				for (int i = 0; i < big; i++) {
					b1.insert(0, '0');
				}
				// b2-b1
				for (int i = b1.length() - 1; i >= 0; i--) {
					sum = b2.charAt(i) - b1.charAt(i) - jiewei;
					if (sum < 0) {
						jiewei = 1;
						sum += 10;
						char c = (char) (sum + '0');
						b2.setCharAt(i, c);
					} else {
						jiewei = 0;
						char c = (char) (sum + '0');
						b2.setCharAt(i, c);
					}

				}
				// step4：去除结果中高位的0
				int index = 0;// 不为0的最高位下标
				for (int i = 0; i < b2.length(); i++) {
					char n = b2.charAt(i);
					if (n != '0') {
						index = i;
						break;
					}
				}
				// step5:负数要在最前面加-
				result = sign + b2.substring(index, b2.length());
			}

		}
		return result;

	}
}
