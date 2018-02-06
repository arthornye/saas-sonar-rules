/**  
* 创建时间：2017年7月21日 上午9:52:01   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.ClassTree;

@Rule(key = "SaasEnumClassNameCheck", description = "枚举类命名应当有'Enum'后缀", name = "枚举类命名应当有'Enum'后缀")
public class SaasEnumClassNameCheck extends BaseCheck {
	private final String SUFFIX_ENUM = "Enum";
	private final String ERROR_MESSAGE = "枚举类命名应当有'Enum'后缀";
	private Boolean isEnum = false;

	@Override
	public void visitClass(ClassTree tree) {
		// 是枚举类
		isEnum = tree.symbol().isEnum();
		if (isEnum) {
			String className = tree.simpleName().name();
			// 类名包含Enum后缀
			if (!className.endsWith(SUFFIX_ENUM)) {
				context.reportIssue(this, tree, ERROR_MESSAGE);
			}
		}
	}

//	@Override
//	public void visitEnumConstant(EnumConstantTree tree) {
//		if (isEnum) {
//			String constantName = tree.simpleName().name();
//			System.out.print("枚举类中常量名称：" + constantName);	
//		}
//	}
//	
//	@Override
//	 public void visitVariable(VariableTree tree){
//		String constantName = tree.simpleName().name();
//		System.out.print("枚举类中变量名称：" + constantName);
//	}
//
//	@Override
//	  public void visitMethod(MethodTree tree) {
//		System.out.print("method名称：" + tree.simpleName().name());
//	}
}
