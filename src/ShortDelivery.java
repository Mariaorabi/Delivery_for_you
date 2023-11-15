import java.util.Date;
import java.util.Objects;

public class ShortDelivery extends Delivery{
	private Date date;
	private Integer distane;
	private Double price;
	public ShortDelivery(Members member, String fromCity, String targetCity, Integer code, Date date,
			Integer distane, Double price) {
		super(member, fromCity, targetCity, code);
		this.date = date;
		this.distane = distane;
		this.price = price;
	}
	public ShortDelivery(Integer code)
	{
		super(code);
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getDistane() {
		return distane;
	}
	public void setDistane(Integer distane) {
		this.distane = distane;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "ShortDelivery "+super.toString2() + ", date=" + date + ", distane=" + distane + ", price=" + price + "]";
	}
}