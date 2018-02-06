/**  
* 创建时间：2017年7月11日 上午9:38:41   
* @author zt  
* 类说明：  命名风格，【强制】 代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束
*/
package com.souche.rules.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.VariableTree;

import com.souche.rules.tools.SaasStringCheck;

@Rule(
		key="SaasVariableNotStartWithSpecialCharRule",
		name="代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束",
		description="代码中的命名均不能以下划线或美元符号开始，也不能以下划线或美元符号结束",
		tags = {"bug"}
)
public class SaasVariableNotStartWithSpecialCharRule extends BaseTreeVisitor implements JavaFileScanner {
	private JavaFileScannerContext context;
	
	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
	    scan(context.getTree());
	}

	@Override
	public void visitMethod(MethodTree tree){
		String methodName = tree.simpleName().name();
		if(SaasStringCheck.startWithDollarOrUnderLine(methodName)){
			context.reportIssue(this, tree, "方法名不能以特殊字符($和_)开头或结束");
			System.out.println("命名错误："+"方法名不能以特殊字符($和_)开头或结束");
		}
		super.visitMethod(tree);
	}
	
	@Override
	public void visitVariable(VariableTree tree) {
		String vName = tree.simpleName().name();
		if (SaasStringCheck.startWithDollarOrUnderLine(vName)) {
			context.reportIssue(this, tree, "变量名不能以特殊字符($和_)开头或结束");
		}
		super.visitVariable(tree);
	}
	
	@Override
	public void visitClass(ClassTree tree) {
		if (tree == null || tree.simpleName() == null || tree.simpleName().name() == null) {
			return;
		}
		String className = tree.simpleName().name();
		if (SaasStringCheck.startWithDollarOrUnderLine(className)) {
			context.reportIssue(this, tree, "类名不能以特殊字符($和_)开头或结束");
		}
		super.visitClass(tree);
	}
	
}
