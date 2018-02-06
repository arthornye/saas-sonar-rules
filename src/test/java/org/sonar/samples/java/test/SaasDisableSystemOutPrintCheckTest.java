/**  
* 创建时间：2017年8月16日 上午11:22:31   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasDisableSystemOutPrintCheck;

public class SaasDisableSystemOutPrintCheckTest {
    @Test
    public void test(){
        String file = "src/test/files/SaasDisableSystemOutPrintCheckCase.java";
        JavaCheckVerifier.verify(file, new SaasDisableSystemOutPrintCheck());
    }
}
