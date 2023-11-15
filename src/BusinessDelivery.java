
public class BusinessDelivery extends Delivery {
	private final Double fee;
	private Integer numberOfItems;
	public BusinessDelivery(Members member, String fromCity, String targetCity, Integer code, Integer numberOfItems) {
		super(member, fromCity, targetCity, code);
		this.fee=100.00;
		this.numberOfItems = numberOfItems;
	}
	public Integer getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}
	public Double getFee() {
		return fee;
	}
	@Override
	public String toString() {
		return "BusinessDelivery "+super.toString()+" ,fee=" + fee + ", numberOfItems=" + numberOfItems + "]";
	}

}