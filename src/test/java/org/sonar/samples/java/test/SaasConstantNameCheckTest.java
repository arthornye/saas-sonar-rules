/**  
* 创建时间：2017年7月17日 上午11:24:49   
* @author zt  
* 类说明：  常量名检查测试类
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasConstantNameCheck;

public class SaasConstantNameCheckTest {

	@Test
	public void test(){
		String file = "src/test/files/SaasConstantNameCheckCase.java";
		JavaCheckVerifier.verify(file, new SaasConstantNameCheck());
	}
}
