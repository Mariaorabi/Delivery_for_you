
public class ExpressDelivery extends Delivery{
	private final Double fee;
	private Integer numberOfItems;
	public ExpressDelivery(Members member, String fromCity, String targetCity, Integer code, Integer numberOfItems) {
		super(member, fromCity, targetCity, code);
		this.fee = null;;
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
		return "ExpressDelivery "+super.toString()+" ,fee=" + fee + ", numberOfItems=" + numberOfItems + "]";
	}

}
