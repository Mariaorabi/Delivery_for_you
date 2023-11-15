import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Manger {
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected HashMap<String, Members> members;
	protected String location;
	public Manger(String firstName,String lastName,String username, String password, String location) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.location = location;
		this.members = new HashMap<String, Members>();
	}
	public Manger(String username)
	{
		this.username=username;
	}
	public Manger(String username, String password) {
		this.username = username;
		this.password = password;
	}
	/*This function add new member to the manger's members arrayList and return true ,if it doesn't suitable the area of the member and the manger 
	 * it doesn't add the member and return false.*/
	public boolean addMembers(Members m)
	{
		if(m==null)
			return false;
		if(!(m.getLocation().equals(this.getLocation())))//Check if the location match
			return false;
		else
		{
			members.put(m.getId(),m);
		}
		return true;

	}
	/*This function delete member from the manger's members array and tree maps-delivers*/
	public void deleteMembers(String id) 
	{
		if(members.get(id).getDelivery()!=null)
			if(!(members.get(id).getDelivery().isEmpty()))
				DataBase.deleteMember(id);
		members.remove(id);
		DataBase.members.remove(id);
	}
	/*This function print the choosen member's last delivery*/
	public boolean memberDelivery()
	{
		System.out.println("Please enter the member's id:");
		Scanner input=new Scanner(System.in);
		String id=input.next();
		for(Map.Entry<String, Members> entry: members.entrySet())
		{
			if(entry.getKey().equals(id))
			{
				DataBase.printLastDelivery(entry.getValue());
				return true;
			}
		}
		return false;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Manger other = (Manger) obj;
		return Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	public String getFirstName() 
	{
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public HashMap<String, Members> getMembers() {
		return members;
	}
	public void setMembers(HashMap<String, Members> members) {
		this.members = members;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Manger [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password + ", members=" + members + ", location=" + location + "]";
	}
	public String toString2() {
		return "firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password  + ", location=" + location ;
	}
	public String toString1() {
		return "Manger [firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", password="
				+ password +  ", location=" + location + "]";
	}


}


