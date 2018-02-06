package org.sonar.samples.java.test;

import com.souche.rules.rule.SaasParamStartCheck;
import org.junit.Test;

import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * Created by yqz on 1/29/18.
 */

public class SaasParamStartCheckTest {
    @Test
    public void test() {
        JavaCheckVerifier.verify("src/test/files/SaasParamStartCheck.java", new SaasParamStartCheck());
    }
}
