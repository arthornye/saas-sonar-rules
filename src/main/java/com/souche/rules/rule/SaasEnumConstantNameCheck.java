/**  
* 创建时间：2017年7月21日 上午11:07:28   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.EnumConstantTree;

import com.souche.rules.tools.SaasStringCheck;

@Rule(key="SaasEnumConstantNameCheck", name="枚举成员名称需要全大写，单词间用下划线隔开", description="枚举成员名称需要全大写，单词间用下划线隔开")
public class SaasEnumConstantNameCheck extends BaseCheck {
	private final String ERROR_MESSAGE="枚举成员名称需要全大写，单词间用下划线隔开";
	@Override
	public void visitEnumConstant(EnumConstantTree tree) {
		String constantName = tree.simpleName().name();
		if (!SaasStringCheck.containUpperCaseAndUnderLine(constantName)) {
			context.reportIssue(this, tree, ERROR_MESSAGE);
		}				
	}
}
