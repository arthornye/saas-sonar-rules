package com.souche.rules.rule;

import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.tree.*;

import java.util.List;


/**
 * Created by yqz on 1/29/18.
 */

@Rule(
        key = "SaasParamStartCheck",
        name = "POJO 类中布尔类型的变量，都不要加 is",
        description = "定义为基本数据类型Boolean isSuccess;的属性，它的方法也是isSuccess()，RPC 框架在反向解析的时候，“以为”对应的属性名称是 success，导致属性获取不到，进而抛出异常。",
        priority = Priority.CRITICAL,
        tags = {"bug"})
public class SaasParamStartCheck extends BaseCheck {

    @Override
    public void visitClass(ClassTree tree){
        //1.获取类信息
        //2.获取属性元素
        List<Tree> treeList=tree.members();
        //3.判断属性命名
        for(Tree oneTree:treeList){
            if(oneTree instanceof VariableTree){
                VariableTree variableTree=(VariableTree) oneTree;
                if(variableTree.type()!=null && variableTree.type().symbolType().name().equals("boolean")){
                    String name=variableTree.simpleName().name();
                    if(checkStartWithIS(name)){
                        context.reportIssue(this,oneTree,"属性名不能以is开头");
                       // System.out.println("命名错误"+"属性名不能以is开头");
                    }
                }
            }
        }
        super.visitClass(tree);
    }
    Boolean checkStartWithIS(String name){
        if(name.startsWith("is")){
            return true;
        }
        return false;
    }
}