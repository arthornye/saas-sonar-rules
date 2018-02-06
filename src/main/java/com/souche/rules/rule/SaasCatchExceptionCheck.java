/**  
* 创建时间：2017年8月31日 下午2:02:34   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import java.util.List;

import org.sonar.plugins.java.api.tree.CatchTree;
import org.sonar.plugins.java.api.tree.StatementTree;

public class SaasCatchExceptionCheck extends BaseCheck {
    @Override
    public void visitCatch(CatchTree tree) {
        List<StatementTree> statementList = tree.block().body();
        if (statementList.size() == 0) {
            //context.reportIssue(this, tree, "msg");    
        }
        
        super.visitCatch(tree);
    }
}