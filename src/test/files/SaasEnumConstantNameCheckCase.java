
public class MyTest{
	String hello;
	
}

public enum MyConstantNameEnum {
	a(1),// Noncompliant
	bbbbb(22222),// Noncompliant
	BBB8_CCCC(333),// Noncompliant
	_TTTT(444),// Noncompliant
	TTTT_(5555),// Noncompliant
	SEARCH_CAR_ONE(null,100000,null,20000),
	SEARCH_CAR_TWO(100000,200000,20000,40000),
	SEARCH_CAR_THREE(200000,null,30000,null),
	;
	
	
	private MyConstantNameEnum() {
		
    }
}
