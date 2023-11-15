
public class AllException extends Exception {
	public AllException()
	{
		super();
	}
	public AllException(String id)
	{
		super("Sorry "+id+" id is already exists!you may try again");
	}
	public AllException(String area1,String area2)
	{
		super("Sorry you can't add this member because the member is in the "+area1+
				" and the manger is in the "+area2+" you may try again");
	}
	public AllException(Integer x)
	{
		super("The number "+x+" of distance is bigger than 30!You may try again");
	}
	public AllException(Members m)
	{
		super("You choose member with id "+m.getId()+" you should before delete all the member's short deliveries");
	}
	
}
