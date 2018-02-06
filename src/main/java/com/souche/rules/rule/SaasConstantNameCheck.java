/**  
* 创建时间：2017年7月17日 上午10:54:49   
* @author zt  
* 类说明：  命名风格,【强制】常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长。
*/
package com.souche.rules.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.semantic.Symbol.VariableSymbol;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.VariableTree;

import com.souche.rules.tools.SaasStringCheck;

@Rule(key="SaasConstantNameCheck", name="常量名检查", description="常量命名全部大写，单词间用下划线隔开，力求语义表达完整清楚，不要嫌名字长",tags = {"bug"})
public class SaasConstantNameCheck extends BaseTreeVisitor implements JavaFileScanner {
	private static final String RAISE_MESSAGE = "常量命名全部大写，单词间用下划线隔开";
	private JavaFileScannerContext context;
	
	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
	    scan(context.getTree());
	}
	
	@Override
	public void visitVariable(VariableTree tree) {
		VariableSymbol symbol = (VariableSymbol)tree.symbol();
		// 判断是否为常量
		if (symbol.isFinal()) {
			String constantName = tree.simpleName().name();
			// 是否符合普通变量命名规范
			if (SaasStringCheck.startWithDollarOrUnderLine(constantName)) {
				context.reportIssue(this, tree, RAISE_MESSAGE);
				return;
			}
			if (!SaasStringCheck.containUpperCaseAndUnderLine(constantName)) {
				context.reportIssue(this, tree, RAISE_MESSAGE);
			}
		}
		super.visitVariable(tree);
	}
}
