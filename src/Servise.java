import java.util.Map;
import java.util.Scanner;

public class Servise {//old
	
	/*This function to signup a manger to the program*/
	public static void signupManger()
	{
		Scanner input=new Scanner(System.in);
		String firstName,lastName,username,password,location;
		System.out.println("Please put your first name: ");
		firstName=input.next();
		System.out.println("Please put your last name: ");
		lastName=input.next();
		System.out.println("Please put an username: ");
		username=input.next();
		int help=0;
		while(help==0 && !DataBase.mangers.isEmpty()){
			while(DataBase.checkManger(username)==false){//if there is another manger in the same username
				System.out.println("Sorry but this username already used!");
				System.out.println("You may try again");
				System.out.println("Please put an username: ");
				username=input.next();
			}
			help=1;
		}
		System.out.println("Please put a password that you want to use: ");
		password=input.next();
		System.out.println("Please put your location(north or center or west): ");
		location=input.next();
		location=printCheckLocation(location);
		Manger m=new Manger(firstName,lastName,username,password,location);
		//DataBase.addManger(m);
		System.out.println("So you have signed up and become one of our company family,so welcome any time! ");
	}
	/*This function to login the manger to the program just if the username and the password are currect else return null (manger)*/
	public static Manger loginManger()
	{
		String username,password;
		Scanner input=new Scanner(System.in);
		System.out.println("Please enter your username:");
		username=input.next();
		System.out.println("Please enter your password");
		password=input.next();
		Manger manager = DataBase.login(username, password);
		return manager;
	}
	public static void menu(Manger manger,String location) {
		int checker=1,numOfItems;
		String firstName,lastName,id,phoneNumber,area,memberLocation,input1,input2;
		Scanner input=new Scanner(System.in);
		System.out.println("Hello " + manger.getFirstName() + " " + manger.getLastName() + "!");
		while(checker!=0)//In case 7 the menu will close
		{
			printMenu();
			switch(input.nextInt()) 
			{
			/*In case 1 : add new member to the chosen manger's hashMap*/
			case (1):
				System.out.print("Please enter the member's first name:");
			firstName=input.next();
			System.out.print("Please enter the member's last name:");
			lastName=input.next();
			System.out.print("Please enter the member's id:");
			id=input.next();
			while(!DataBase.checkMemberId(id))//Check if there is another member with the same id in all the program 
			{
				System.out.println("Sorry this id already exists!\nTry again");
				System.out.print("Please enter the member's id:");
				id=input.next();
			}
			System.out.print("Please enter the member's phone number:");
			phoneNumber=input.next();
			System.out.print("Please enter the member's location (north or center or west):");
			memberLocation=input.next();
			memberLocation=printCheckLocation(memberLocation);
			Members member= new Members(firstName,lastName,id, phoneNumber, memberLocation);
			if(!manger.addMembers(member))//Check if the member is in the same area as the manger
			{
				System.out.println("Sorry you can't add this member because he isn't in this manager's area");
				System.out.println("Back to the menu:");
			}
			break;
			/*In case 2: delete a member from the manger's members hashMap and if if thier have a delivery delete it from the 3 hash trees*/
			case (2):
				System.out.println("Please enter the member's id:");
			id=input.next();
			if(manger.getMembers().containsKey(id))
			{
				manger.deleteMembers(id);
			}
			else// if the manger doesn't have them we can't delete it
			{
				System.out.println("Sorry you can't delete this member,because you don't have them!");
				System.out.println("Back to the menu:");
			}
			break;
			/*In case 3 adding a delivery to a choosen member*/
			case (3):
				System.out.println("First,please enter the member id that you want to add to the delivery:");
			System.out.print("The member id: ");
			id=input.next();
			if(manger.getMembers().get(id) == null)//check if the member exist in the manger's members hashmap
			{
				System.out.println("Sorry add delivery failed, because you don't have this member.");
				System.out.println("Back to the menu:");
			}
			else 
			{
				System.out.println("Firstly,please enter the member's city /village:");
				area=input.next();
				System.out.println("Secondly,please enter the number of items in this delivery:");
				numOfItems=input.nextInt();
				printDeliveryTypes();
				input1=input.next();
				while(!input1.equals("Express") && !input1.equals("Normal") && !input1.equals("Business"))//Check if the string doesn't neither Normal or Express or Busniess
				{
					System.out.println("Error!\nYou have to enter Express or Normal or Business");
					System.out.println("You may try again");
					printDeliveryTypes();
					input1=input.next();
				}
				for(Manger m: DataBase.mangers)//adding the delivery
				{
					/*if(m.equals(manger))
						m.addDelivery(id, area, numOfItems,input1);*/
				}
			}
			break;
			/*In case print the last delivery to the choosen member*/ 
			case(4):
				if(manger.getMembers()==null)//if the manger don't have any members
				{
					System.out.println("Sorry but you don't have any member!");
					break;
				}
			while(!manger.memberDelivery())//if the manger doesn't have the member
			{
				System.out.println("Sorry you don't have this member!");
				System.out.println("You may try again");
			}
			break;
			/*In case 5 prints all the dliverie in the choosen area*/
			case(5):
				System.out.println("Please enter location(north, center or west):");
			input2=input.next();
			input2=printCheckLocation(input2);//check the input
			DataBase.printourMembersAndDeluveries(input2);
			break;
			/*In case 6 prints all the members and thier delivers ,if someone doesn't have any delivers print a approprite massage*/
			case(6):
				Integer counter=0;
				for(Manger m:DataBase.mangers)
				{
					for (Map.Entry<String, Members> m1 :m.getMembers().entrySet())
					{
						System.out.println(m1.getValue().toString1());//print just the member
						if (m1.getValue().getDelivery().isEmpty())
						{
							System.out.println("This member don't has any deliveries");
							counter++;
						}
						else
						{
							System.out.println("His deliveries"+m1.getValue().getDelivery());//print all the member's delivery
							counter++;
						}
					}
				}
			if(counter==0)
				System.out.println("There is no members in the company!");
			break;
			case(7):
				System.out.println("Have a nice day and good bye!");
			checker=0;
			break;
			default:
				System.out.println("You have to enter a number between 1-4");//if the user put number out of the range 1-4
				System.out.println("Back to the menu:");
			}
		}
	}
	private static void printMenu()
	{
		System.out.println("Please enter your option:");
		System.out.println("To add a member please put 1");
		System.out.println("To delete a member please put 2");
		System.out.println("To add a new delivey please put 3 ");
		System.out.println("To see a specific member's last delivery and it's details put 4");
		System.out.println("To see all the members in a specific area put 5");
		System.out.println("To see all the members in the company and thier deliveries put 6");
		System.out.println("To close the menu please put 7");
	}
	private static String printCheckLocation(String memberLocation)
	{
		Scanner input=new Scanner(System.in);
		while((!(memberLocation.equals("north"))) && (!(memberLocation.equals("center"))) && (!(memberLocation.equals("west"))))//Check if the string doesn't neither north or center or west 
		{
			System.out.println("Sorry,you should enter either north or west or center!");
			System.out.println("You may try again");
			System.out.print("Please enter the member's location (north or center or west):");
			memberLocation=input.next();
		}
		return memberLocation;
	}
	private static void printDeliveryTypes() {
		System.out.println("Please choose the type of the delivery");
		System.out.println("Express,Normal,Business");
	}

}



