package com.souche.rules.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.JavaFileScanner;
import org.sonar.plugins.java.api.JavaFileScannerContext;
import org.sonar.plugins.java.api.tree.BaseTreeVisitor;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.VariableTree;

/**
 * created by linyuesheng on 2018/1/30 下午12:52
 *
 * @desc 方法名、参数名、成员变量、局部变量都统一使用 lowerCamelCase 风格，必须遵从 驼峰形式
 */
@Rule(key = "SaasLowerCameCaseCheck", name = "方法名、参数名、成员变量、局部变量首字母小写", description = "方法名、参数名、成员变量、局部变量首字母小写，遵从驼峰形式")
public class SaasLowerCameCaseCheck extends BaseTreeVisitor implements JavaFileScanner {

    private static final Logger logger = LoggerFactory.getLogger(SaasLowerCameCaseCheck.class);

    private JavaFileScannerContext context;


    @Override
    public void scanFile(JavaFileScannerContext context) {
        this.context = context;
        scan(context.getTree());
    }

    /**
     * 方法内检查驼峰命名 构造函数除外
     */
    @Override
    public void visitMethod(MethodTree tree) {
        //判断是否为构造函数，构造函数返回类型为null
        if (tree.symbol().returnType() != null) {
            String methodName = tree.simpleName().name();
            //判断方法名称首字符是否小写
            char methodChar = methodName.charAt(0);
            if (Character.isUpperCase(methodChar)) {
                logger.info(">>判断方法名称首字符是否小写>>" + methodName);
                context.reportIssue(this, tree,
                    "The first Character Of Method Name should not UpperCase");
            }
        }
        super.visitMethod(tree);
    }

    /**
     * 判断变量命名是否符合规范(方法参数变量和成员变量)
     */
    @Override
    public void visitVariable(VariableTree tree) {
        String variableName = tree.simpleName().name();
        char cVariableName = variableName.charAt(0);

        if (!tree.symbol().isFinal() && !tree.symbol().isStatic()) {
            if (Character.isUpperCase(cVariableName)) {
                logger.info(">>判断变量命名是否符合规范>>" + tree.simpleName().name());
                context.reportIssue(this, tree,
                    "The first character of Member Variable should not be UpperCase");
            }
        }
        super.visitVariable(tree);
    }
}
