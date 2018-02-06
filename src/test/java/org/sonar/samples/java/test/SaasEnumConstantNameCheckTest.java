/**  
* 创建时间：2017年7月21日 上午11:10:03   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasEnumConstantNameCheck;

public class SaasEnumConstantNameCheckTest {
	@Test
	public void test(){
		String file = "src/test/files/SaasEnumConstantNameCheckCase.java";
		JavaCheckVerifier.verify(file, new SaasEnumConstantNameCheck());
	}
}
