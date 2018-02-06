package org.finger.java.rule.checks.namerules;

/**
 * created by linyuesheng on 2018/1/30 下午12:52
 *
 * @desc 方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格，举例说明
 */
public class SaasLowerCameCaseCheckCase {
    private String ID;// Noncompliant
    private String UserName;// Noncompliant
    private String password;// Compliant

    private static final String NO_BB_CC_DD_CC_SSS = "2212313213";

    public String getUserInfo(String info) {
        String Yes = "Yes";// Noncompliant
        return info+Yes;
    }

    public void GetPassword(){} // Noncompliant

    public void getUser(String UserInfo){} // Noncompliant
}
