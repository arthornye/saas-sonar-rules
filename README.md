### 写一个自定义规则需要做的事情
<color=red>如果有时间先看一下官方的文档:https://docs.sonarqube.org/display/PLUG/Writing+Custom+Java+Rules+101</color>
1. 自定义插件的实现是基于TDD(测试驱动开发)方式来做的。所以一定要有3个文件：
    * 测试类 如 `SaasAbstractClassNameRuleTest.java`    
    * 规则检查类 如 `SaasAbstractClassNameRule.java`        
    * 测试用例类 如 `SaasAbstractClassNameRuleCase.java` 该类非常严格，必须要提供一个不通过的case，并在在其后面加上`// Noncompliant`,注意有空格,而且只有一个空格
2. 为了更好的描述规则需要一个json和html文件,名字与规则类一致，后缀为`_java.html`或`_java.json`
    * SaasAbstractClassNameCheck_java.html
    * SaasAbstractClassNameCheck_java.json
3. 注册规则：在`RulesList.java`注册新的规则
4. 运行单元测试保证没有问题
5. 打包插件：mvn clean install
6. 将插件放到SonarQube服务中，并重启
7. SonarQube服务端-代码规则-SaaS Custom Repository中找到新建的规则-激活
8. 执行客户端的sonar-scanner
9. 在服务端看到结果

### 如果服务端还没有自定义规则的配置需要新建一个
1. create a specific file
2. set rule active and under the specific name
3. choose project, set use specific 

### 自定义规则中的一下细节
* 基于TDD，测试驱动开发。用例文件（错误的用例要标记`// Noncompliant`,注意有空格），测试类，规则类
* abstract syntax tree ：抽象语法树。扫描器中读取的java文件，视为一颗树(tree)，每一个节点都有其对应的类型(kind)，每一种类型kind都有其对应的symbol
* semantic model:语义模型。
* @Rule注解：用于SonarQube服务端注册.==一定要有key，name，description三个字段==
```
@Rule(
       key = "MyFirstCustomCheck",
       name = "Return type and parameter of a method should not be the same",//代码规则列表中的简单描述
       description = "For a method having a single parameter, the types of its return value and its parameter should never be the same.",//匹配到问题后详情页面的描述
       priority = Priority.CRITICAL,
       tags = {"bug”}
     )
```

### 规则类的入口        
一般规则类型的实现可以选择继承`IssuableSubscriptionVisitor` 或 `BaseTreeVisitor`

两者区别 
    
> 一般来说，特别简单的规则定义可以使用`IssuableSubscriptionVisitor`,比如：方法名首字母必须要小写
> 复杂一些的规则使用`BaseTreeVisitor`，BaseTreeVisitor中对于想要监控的item分类更加清晰

### RulesDefinition和CheckRegistrar

> `RulesDefinition`定义了规则插件在SonarQube服务端的规则里展示的metadata，比如插件的名字，支持的语言等

> `CheckRegistrar`主要定义每个规则类的注册

### 规则描述
> 与rule类同名的.html文件作用,比如`SecurityAnnotationMandatory_java.html`：配置规则的正确用法，错误用法等

    <p>不要用$_作为命名的开头或结尾</p>
    <h2>错误的代码示范</h2>
    <pre>
        _name / __name / $Object / name_ / name$ / Object$
    </pre>
    <h2>正确的写法</h2>
    <pre>
    name
    </pre>

> 与rule类同名的.json文件作用，比如`SecurityAnnotationMandatory_java.json`:配置规则的基本信息，类型，严重性等，几个主要字段的定义如下：

    {
        "title": "html标题",
        "type": "BUG",//规则的定义，坏味道，bug，漏洞,//CODE_SMELL(1), BUG(2), VULNERABILITY(3);
        "status": "Beta",//Beta,Deprecated,Ready 参加 https://docs.sonarqube.org/display/SONAR/Rules 
        "remediation": {//技术债时间计算相关
            "func": "Constant\/Issue",//Constant/issue, Linear,Linear with offse
            "constantCost": "5min"
        },
        "tags": [// 参加这里：https://docs.sonarqube.org/display/SONAR/Built-in+Rule+Tags
            "pitfall"
        ],
        "defaultSeverity": "Minor"//安全等级，//INFO,MINOR,MAJOR,CRITICAL,BLOCKER
    }
    // Constant/issue: 修复每个问题花费的时间是一样的。总时间=issue*constantCost
    // Linear:修复的时间取决于问题的复杂程度
    // Linear with offset:这个和上面那个计算技术债时间的，参考：https://docs.sonarqube.org/display/PLUG/Rule+Remediation+Costs
    // constantCost: 修复需要花费的时间
  

### 一个完整的demo
【目标】抽象类命名使用 Abstract 或 Base 开头;异常类命名使用 Exception 结尾
`SaasAbstractClassNameRuleTest.java` 测试类

    public class SaasAbstractClassNameRuleTest {
	    @Test
	    public void test(){
		    String file = "src/test/files/SaasAbstractClassNameRuleCase.java";
		    JavaCheckVerifier.verify(file, new SaasAbstractClassNameRule());
	    }
    }
`SaasAbstractClassNameRule.java`        规则检查类

    @Rule(key="SaasAbstractClassNameRule", description="抽象类必须以Abstract或Base开头")
    public class SaasAbstractClassNameRule extends BaseTreeVisitor implements JavaFileScanner {
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
		if (name.length() > prefixAbstract.length() && name.contains(prefixAbstract) && name.indexOf(prefixAbstract)==0) {
			return true;
		}
		return false;
	}
	
	private Boolean nameStartWithBase(String name){
		String prefixBase = "Base";
		if (name.length()>prefixBase.length() && name.contains(prefixBase) && name.indexOf(prefixBase)==0) {
			return true;
		}
		return false;
	}
    }

`SaasAbstractClassNameRuleCase.java`    测试用例类,这个文件中定义了连个类名，第一个可以通过，第二个会挂起

    public class SaasVariableNotStartWithSpecialCharRuleCase {
    }

    public abstract class aaa{} // Noncompliant
    
这三个文件在项目中对应的位置如下：
![img](http://ot88dskoo.bkt.clouddn.com/17-7-17/28298525.jpg)# saas-sonar-rules
