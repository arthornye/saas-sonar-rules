/**  
* 创建时间：2017年7月17日 上午11:27:56   
* @author zt  
* 类说明：  
*/

public class SaasConstantNameCheckCase {
	private final static String name;// Noncompliant
	private final static String NAME;
	private final static String _NAME;// Noncompliant
	private final static String $NAME;// Noncompliant
	private final static String NA8ME;// Noncompliant
}
