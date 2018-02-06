/**  
* 创建时间：2017年7月17日 上午11:12:15   
* @author zt  
* 类说明：  字符串检查工具
*/
package com.souche.rules.tools;

public class SaasStringCheck {
	/**
	 * 字符串以下划线，美元符开头或结束
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean startWithDollarOrUnderLine(String str) {
		char startChar = str.charAt(0);
		char endChar = str.charAt(str.length() - 1);
		// ASIC十进制映射值 $=36，_=95,
		if (startChar == 36 || startChar == 95 || endChar == 36 || endChar == 95) {
			System.out.println("命名错误：" + str);
			return true;
		}
		return false;
	}

	/**
	 * 字符串必须是字符或下划线，并且字符都为大写,不能以_开头或结尾
	 * @param str
	 * @return
	 */
	public static Boolean containUpperCaseAndUnderLine(String str) {
		Integer nameLength = str.length();
		Integer charIndex = 0;
		for (char ch : str.toCharArray()) {
			if(charIndex == 0 || charIndex == nameLength-1){
				if (ch == 95) {
					return false;
				}
			}
			// 大写字母A-Z 65-90,_=95
			if ((ch < 65 || ch > 90) && ch != 95) {
				return false;
			}
			charIndex++;
		}
		return true;
	}
}
