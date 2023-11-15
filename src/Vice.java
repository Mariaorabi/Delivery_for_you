import java.util.ArrayList;

public class Vice extends Manger {
	
	private Integer numOfDeliveryHeCan;
	private ArrayList<ShortDelivery> shortDelivery;
	public Vice(String firstName, String lastName, String username, String password, String location,
			Integer numOfDeliveryHeCan) {
		super(firstName, lastName, username, password, location);
		this.numOfDeliveryHeCan = numOfDeliveryHeCan;
		this.shortDelivery = new ArrayList<ShortDelivery>();
	}
	public Vice(String username, String password)
	{
		super(username,password);
	}
	public Integer getNumOfDeliveryHeCan() {
		return numOfDeliveryHeCan;
	}
	public void setNumOfDeliveryHeCan(Integer numOfDeliveryHeCan) {
		this.numOfDeliveryHeCan = numOfDeliveryHeCan;
	}
	public ArrayList<ShortDelivery> getShortDelivery() {
		return shortDelivery;
	}
	public void setShortDelivery(ArrayList<ShortDelivery> shortDelivery) {
		this.shortDelivery = shortDelivery;
	}
	@Override
	public String toString() {
		return "Vice [ "+super.toString2()+" ,numOfDeliveryHeCan=" + numOfDeliveryHeCan + ", shortDelivery=" + shortDelivery + "]";
	}
}	