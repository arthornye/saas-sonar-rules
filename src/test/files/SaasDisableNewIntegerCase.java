/**  
* 创建时间：2017年8月29日 下午3:11:32   
* @author zt  
* 类说明：  
*/

public class SaasDisableNewIntegerCase {
    public void test(){
        System.out.println("syntax:");
        Integer integer = new Integer(5);// Noncompliant 
        Integer.valueOf(5);
        
    }
}

public enum MyEnumA {
    MEDIA_CLUES_IS_REPEAT_YES("1","是"),
    MEDIA_CLUES_IS_REPEAT_YES_aaaa("2","是"),
    MEDIA_CLUES_IS_REPEAT_NO("0","否");
    
    private String code;
    private String desplayName;
    private MyEnumA(String code, String desplayName) {
        this.code = code;
        this.desplayName = desplayName;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getDesplayName() {
        return desplayName;
    }
    public void setDesplayName(String desplayName) {
        this.desplayName = desplayName;
    }
}