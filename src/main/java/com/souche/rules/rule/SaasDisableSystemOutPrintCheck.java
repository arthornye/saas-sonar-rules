/**  
* 创建时间：2017年8月16日 上午11:05:56   
* @author zt  
* 类说明：  
*/
package com.souche.rules.rule;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.ExpressionTree;
import org.sonar.plugins.java.api.tree.IdentifierTree;
import org.sonar.plugins.java.api.tree.MemberSelectExpressionTree;
import org.sonar.plugins.java.api.tree.MethodInvocationTree;
import org.sonar.plugins.java.api.tree.Tree;

import com.google.common.collect.ImmutableList;

@Rule(key = "SaasDisableSystemOutPrintCheck", name = "禁止使用System.out.print()；e.printStackTrace()", description = "不要使用system.out.print；e.printStackTrace()", tags = {
        "bug" })
public class SaasDisableSystemOutPrintCheck extends IssuableSubscriptionVisitor {
    private final Logger logger = LoggerFactory.getLogger(SaasDisableSystemOutPrintCheck.class);

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.MEMBER_SELECT);
    }

    @Override
    public void visitNode(Tree tree) {
        MemberSelectExpressionTree mset = (MemberSelectExpressionTree) tree;
        if (isOutOrErr(mset) && isSystem(mset.expression())) {
            reportIssue(tree, "用logger代替System.out or System.err");
        }
        if (isEPrintStackTrace(mset)) {
            reportIssue(tree, "禁止使用e.printStackTrace()");
        }
    }

    private static boolean isSystem(ExpressionTree expression) {
        IdentifierTree identifierTree = null;
        if (expression.is(Tree.Kind.IDENTIFIER)) {
            identifierTree = (IdentifierTree) expression;
        } else if (expression.is(Tree.Kind.MEMBER_SELECT)) {
            identifierTree = ((MemberSelectExpressionTree) expression).identifier();
        }
        return identifierTree != null && "System".equals(identifierTree.name());
    }

    private static boolean isOutOrErr(MemberSelectExpressionTree mset) {
        String metodName = mset.identifier().name();
        return "out".equals(metodName) || "err".equals(metodName);
    }
    
    private static boolean isEPrintStackTrace(MemberSelectExpressionTree mset){
        Tree parentTree = mset.parent();
        String parentMethodName = "";
        if(parentTree.is(Tree.Kind.METHOD_INVOCATION)){
            MethodInvocationTree methodInvocationTree = ((MethodInvocationTree) parentTree);
            parentMethodName = methodInvocationTree.methodSelect().firstToken().text();
        }
        
        String metodName = mset.identifier().name();
        if (("e".equals(parentMethodName) && "printStackTrace".equals(metodName))) {
            return true;    
        }
        return false;
    }
}
