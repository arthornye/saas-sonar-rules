package org.sonar.samples.java.rules;
/**  
* 创建时间：2017年7月14日 上午10:58:00   
* @author zt  
* 类说明：  
*/
public class SaasVariableNotStartWithSpecialCharRuleCase {
}

public abstract class aaa{} // Noncompliant

public abstract class AbstractClassName{}
public abstract class MyClass{}	// Noncompliant
public abstract class BaseMyClass{}
public abstract class MyClassBase{}// Noncompliant
