/**  
* 创建时间：2017年7月21日 上午9:53:25   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;

public class BaseCheck extends BaseTreeVisitor implements JavaFileScanner {
	public JavaFileScannerContext context;
	
	@Override
	public void scanFile(JavaFileScannerContext context) {
		this.context = context;
	    scan(context.getTree());
	}
}
