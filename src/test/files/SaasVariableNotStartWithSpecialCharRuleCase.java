/**  
* 创建时间：2017年7月11日 上午10:42:11   
* @author zt  
* 类说明：  
*/

public class SaasVariableNotStartWithSpecialCharRuleCase {
	void testFunction(){
		String name;
		String age_; // Noncompliant
		String $age; // Noncompliant
	}
	void _testB(){} // Noncompliant
	void $testB(){} // Noncompliant
	void testB_(){} // Noncompliant
	void testB$(){} // Noncompliant
	void test_bbb(){} 
}

public class TestClassName${} // Noncompliant
public class $TestClassName{} // Noncompliant
public class $TestClassName${} // Noncompliant
public class TestClassName_{} // Noncompliant
public class _TestClassName{} // Noncompliant
public class _TestClassName_{} // Noncompliant

