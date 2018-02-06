/**  
* 创建时间：2017年8月31日 下午2:15:57   
* @author zt  
* 类说明：  
*/

public class SaasCatchExceptionCase {
    void test(){
        try {

        } catch (Exception e) {
            System.out.println("aaa");
        }
        
        try {

        } catch (Exception e) {
         // Noncompliant
        }
    }
}
