/**  
* 创建时间：2017年8月30日 下午4:27:08   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import java.util.List;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.Tree;

import com.google.common.collect.ImmutableList;

public class SaasObjectEquelsCheck extends IssuableSubscriptionVisitor {
    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.MEMBER_SELECT);
    }

    @Override
    public void visitNode(Tree tree) {
        MemberSelectExpressionTree mset = (MemberSelectExpressionTree) tree;
        String metodName = mset.identifier().name();
        System.out.println("metodName:"+metodName);
        reportIssue(tree, "msg");
    }
}