package com.dj.cn.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 
 * StringUtils
 * 
 * @author: yanghq2
 * @date: 2018-12-27 11:16:07
 * @Copyright: 2018 www.lenovo.com Inc. All rights reserved.
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {
	private final static String FRACTION = "E";

	/**
	 * 格式化科学计数字符串小数点与E之间为2位小数 注意：0.003656E-6 will return 0
	 * @param value
	 * @return
	 */
	public static String formatFraction(String value) {
		if (isBlank(value)) {
			return value;
		}

		if (value.contains(FRACTION)) {
			String[] array = value.split(FRACTION);
            int size=2;
			if (array.length > size) {
				return value;
			}

			StringBuilder stringBuilder = new StringBuilder();
			BigDecimal prefix = new BigDecimal(array[0]);
			prefix = prefix.setScale(2, RoundingMode.HALF_UP);

			if (prefix.compareTo(BigDecimal.ZERO) <= 0) {
				return BigDecimal.ZERO.toPlainString();
			}

			stringBuilder.append(prefix.toPlainString()).append(FRACTION).append(array[1]);
			return stringBuilder.toString();
		}

		BigDecimal temp = new BigDecimal(value);
		temp = temp.setScale(2, RoundingMode.HALF_UP);
		return temp.toPlainString();
	}
}