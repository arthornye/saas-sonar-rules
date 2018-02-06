/**  
* 创建时间：2017年7月14日 上午10:57:22   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasAbstractClassNameCheck;

public class SaasAbstractClassNameRuleTest {
	@Test
	public void test(){
		String file = "src/test/files/SaasAbstractClassNameRuleCase.java";
		JavaCheckVerifier.verify(file, new SaasAbstractClassNameCheck());
	}
}
