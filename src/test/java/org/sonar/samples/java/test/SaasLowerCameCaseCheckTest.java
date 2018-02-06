package org.sonar.samples.java.test;

import com.souche.rules.rule.SaasLowerCameCaseCheck;
import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * created by linyuesheng on 2018/1/30 下午1:07
 *
 * @desc TODO
 */
public class SaasLowerCameCaseCheckTest {
    @Test
    public void test() {
        String file = "src/test/files/SaasLowerCameCaseCheckCase.java";
        JavaCheckVerifier.verify(file, new SaasLowerCameCaseCheck());
    }
}
