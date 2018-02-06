/**  
* 创建时间：2017年7月21日 上午10:14:06   
* @author zt  
* 类说明：  
*/

public enum MyEnum{};
public enum MyEnummmm{}// Noncompliant
public enum MyEnumA{}// Noncompliant
public enum Enummm{}// Noncompliant


public enum DidiTGCSearchCarEnum {
	SEARCH_CAR_ONE(null,100000,null,20000),
	SEARCH_CAR_TWO(100000,200000,20000,40000),
	SEARCH_CAR_THREE(200000,null,30000,null),
	;
	private Integer priceRangeBegin;
	private Integer priceRangeEnd;
	private Integer tgcPriceRangeBegin;
	private Integer tgcPriceRangeEnd;
	
	private DidiTGCSearchCarEnum(Integer priceRangeBegin, Integer priceRangeEnd,
			Integer tgcPriceRangeBegin,Integer tgcPriceRangeEnd) {
		this.priceRangeBegin = priceRangeBegin;
		this.priceRangeEnd = priceRangeEnd;
		this.tgcPriceRangeBegin = tgcPriceRangeBegin;
		this.tgcPriceRangeEnd = tgcPriceRangeEnd;
    }

	public Integer getPriceRangeBegin() {
		return priceRangeBegin;
	}

	public void setPriceRangeBegin(Integer priceRangeBegin) {
		this.priceRangeBegin = priceRangeBegin;
	}

	public Integer getPriceRangeEnd() {
		return priceRangeEnd;
	}

	public void setPriceRangeEnd(Integer priceRangeEnd) {
		this.priceRangeEnd = priceRangeEnd;
	}

	public Integer getTgcPriceRangeBegin() {
		return tgcPriceRangeBegin;
	}

	public void setTgcPriceRangeBegin(Integer tgcPriceRangeBegin) {
		this.tgcPriceRangeBegin = tgcPriceRangeBegin;
	}

	public Integer getTgcPriceRangeEnd() {
		return tgcPriceRangeEnd;
	}

	public void setTgcPriceRangeEnd(Integer tgcPriceRangeEnd) {
		this.tgcPriceRangeEnd = tgcPriceRangeEnd;
	}
	
}