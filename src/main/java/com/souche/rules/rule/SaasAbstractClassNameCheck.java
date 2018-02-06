/**  
* 创建时间：2017年7月14日 下午4:37:59   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.ClassTree;

@Rule(key="SaasAbstractClassNameCheck", name="抽象类必须以Abstract或Base开头", description="抽象类必须以Abstract或Base开头")
public class SaasAbstractClassNameCheck extends BaseTreeVisitor implements JavaFileScanner {
	private static final String RAISE_MESSAGE="抽象类必须以Abstract或Base开头";
	private JavaFileScannerContext context;
	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
        scan(context.getTree());
	}
	
	@Override
	public void visitClass(ClassTree tree){
		//1.获取类名
		//2.根据语义判断是否是抽象类
		//3.抽象类是否以Abstract或Base开头
		if (tree == null || tree.simpleName() == null || tree.simpleName().name() == null) {
			return;
		}
		String className = tree.simpleName().name();
		if (tree.symbol().isAbstract()) {
			if (!nameStartWithAbstract(className) && !nameStartWithBase(className)) {
				context.reportIssue(this, tree, RAISE_MESSAGE);
			}
		}
		super.visitClass(tree);
	}
	
	private Boolean nameStartWithAbstract(String name){
		String prefixAbstract = "Abstract";
		if (name.length() > prefixAbstract.length() && name.startsWith(prefixAbstract)) {
			return true;
		}
		return false;
	}
	
	private Boolean nameStartWithBase(String name){
		String prefixBase = "Base";
		if (name.length()>prefixBase.length() && name.startsWith(prefixBase)) {
			return true;
		}
		return false;
	}
}
