/**
 * 创建时间：2017年8月16日 上午11:23:35
 * 
 * @author zt 类说明：
 */

public class SaasDisableSystemOutPrintCheckCase {
    public void name() {
        new Integer(5);
        System.out.println("My Message");// Noncompliant
        
        try {
            
        } catch (Exception e) {
            e.printStackTrace();// Noncompliant
        }
    }

    public void myFunction(){} 
}
