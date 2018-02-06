/**  
* 创建时间：2017年7月21日 上午10:13:49   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasEnumClassNameCheck;

public class SaasEnumClassNameCheckTest {
	@Test
	public void test(){
		String file = "src/test/files/SaasEnumClassNameCheckCase.java";
		JavaCheckVerifier.verify(file, new SaasEnumClassNameCheck());
	}
}
