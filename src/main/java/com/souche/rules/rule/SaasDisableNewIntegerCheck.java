/**  
* 创建时间：2017年8月29日 下午3:06:31   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import java.util.List;

import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.NewClassTree;
import org.sonar.plugins.java.api.tree.Tree;

import com.google.common.collect.ImmutableList;

@Rule(key="SaasDisableNewIntegerCheck")
public class SaasDisableNewIntegerCheck extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.NEW_CLASS);
    }

    @Override
    public void visitNode(Tree tree) {
        NewClassTree mset = (NewClassTree) tree;
        if (mset.symbolType().is("java.lang.Integer")) {
            reportIssue(mset, "不要使用 new Integer()，应该使用 Integer.valueOf()");
         }
    }
}
