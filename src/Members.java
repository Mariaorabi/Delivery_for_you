import java.util.ArrayList;
import java.util.Objects;

public class Members implements Comparable<Members>{
	private String firstName;
	private String lastName;
	private String id;
	private String phoneNumber;
	private String location;
	private ArrayList<Delivery> delivery;
	public Members(String firstName, String lastName, String id, String phoneNumber, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.location = location;
		delivery=new ArrayList<Delivery>();
	}
	public Members(String id) {
		this.id = id;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Members other = (Members) obj;
		return Objects.equals(id, other.id);
	}
	public int compareTo(Members member)
	{
		if(this.lastName==null)
			return this.id.compareTo(member.id);
		if(this.lastName.compareTo(member.getLastName())==0)//if two members have the same last name
			return this.id.compareTo(member.id);
		return this.lastName.compareTo(member.getLastName());

	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public ArrayList<Delivery> getDelivery() {
		return delivery;
	}
	public void setDelivery(ArrayList<Delivery> delivery) {
		this.delivery = delivery;
	}
	@Override
	public String toString() {
		return "Member [firstName=" + firstName + ", lastName= " + lastName + ", id=" + id + ", phoneNumber="
				+ phoneNumber + ", location=" + location + "]";
	}
	public String toString1() {
		return "Member [firstName= " + firstName + ", lastName= " + lastName + ", id=" + id + ", phoneNumber="
				+ phoneNumber + ", location=" + location + "]";
	}

}
