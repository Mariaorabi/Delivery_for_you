/*Maria Orabi 212394134*/
import java.util.Scanner;

public class OldMain {

	public static void main(String[] args) {
		Scanner input=new Scanner(System.in);
		int input1=0,input2;
		System.out.println("Hello,you are now visiting ----> Deliveries For You company ");
		while(input1==0)//if input==1 so close the project
		{
			System.out.println("To sign up please put 1");
			System.out.println("To login please put 2");
			System.out.println("To exit please put 3");
			input2=input.nextInt();
			switch(input2)
			{
			/*Case 1 tp signup */  
			case(1):
			if(DataBase.mangers.size()<3)
			{
				System.out.println("    Signup   ");
				Servise.signupManger();
			}
			else
				System.out.println("Sorry but all the 3 mangers have alraedy signup");
			break;
			/*Case 2 to login */  
			case(2):
				System.out.println("     Login   ");
			Manger manger = null;
			manger = Servise.loginManger();
			while(manger==null)//if the password or/and the username uncorrect
				manger = Servise.loginManger();
			Servise.menu(manger,manger.getLocation());
			break;
			/*Case 1 tp exit all the program */  
			case(3):
				System.out.println("You choose to exit the program so thank you,and good bye!");
			input1=1;
			break;
			default:
				System.out.println("You have to enter a number between 1-3");//if the user put number out of the range 1-3
				System.out.println("Back to the login menu:");
			}
		}
		input.close();

	}
}
	