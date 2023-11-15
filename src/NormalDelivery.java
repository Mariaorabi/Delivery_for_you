
public class NormalDelivery extends Delivery{
	private Integer numberOfItems;
	public NormalDelivery(Members member, String fromCity, String targetCity, Integer code, Integer numberOfItems) {
		super(member, fromCity, targetCity, code);
		this.numberOfItems = numberOfItems;
	}
	public Integer getNumberOfItems() {
		return numberOfItems;
	}
	public void setNumberOfItems(Integer numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

	@Override
	public String toString() {
		return "NormalDelivery "+super.toString()+" numberOfItems=" + numberOfItems + "]";
	}
}