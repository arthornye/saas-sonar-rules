/**  
* 创建时间：2017年7月11日 上午10:44:36   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasVariableNotStartWithSpecialCharRule;

public class SaasVariableNotStartWithSpecialCharRuleTest {
	@Test
	public void test(){
		String file = "src/test/files/SaasVariableNotStartWithSpecialCharRuleCase.java";
		JavaCheckVerifier.verify(file, new SaasVariableNotStartWithSpecialCharRule());
	}
}
