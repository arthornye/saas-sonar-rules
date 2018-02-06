/**  
* 创建时间：2017年8月29日 下午3:11:18   
* @author zt  
* 类说明：  
*/
package org.sonar.samples.java.test;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

import com.souche.rules.rule.SaasDisableNewIntegerCheck;

public class SaasDisableNewIntegerTest {
    @Test
    public void test(){
        String file = "src/test/files/SaasDisableNewIntegerCase.java";
        JavaCheckVerifier.verify(file, new SaasDisableNewIntegerCheck());
    }
}
