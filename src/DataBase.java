import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JOptionPane;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DataBase {
	public static Integer countTree=100;
	public static ArrayList<Manger> mangers=new ArrayList<Manger>();
	public static TreeMap<Members,Delivery> ourLastDeliveries=new TreeMap<Members,Delivery>();
	public static TreeMap<Integer, Members> ourMembersAndDeluveries1=new TreeMap<Integer, Members >();
	public static TreeMap<Integer, Delivery> ourMembersAndDeluveries2=new TreeMap<Integer, Delivery >();
	public static ArrayList<Vice> vice=new ArrayList<Vice>();
	public static HashMap<String,Members> members=new HashMap<String,Members> ();
	/*This function add manger to the suitable file*/
	public static void addManager_ToFiles(Manger manager) {
		SaveFiles data_AddManagers =new SaveFiles("src/ManagersFile.txt");
		try{
			data_AddManagers.writer.write(manager.getFirstName()+" "+manager.getLastName()+" "+
			manager.getPassword()+" "+manager.getUsername()+" "+manager.getLocation()+"\n");
			data_AddManagers.writer.close();
			data_AddManagers.reader.close();
		}
		catch(IOException e){}
	}
	/*This function add vice to the suitable file*/
	public static void addVice_ToFiles(Vice vice) {
		SaveFiles data_AddManagers =new SaveFiles("VicesFile.txt");
		try{
			data_AddManagers.writer.write(vice.getFirstName()+" "+vice.getLastName()+" "+vice.getUsername()
			+" "+vice.getPassword()+" "+vice.getLocation()+" "+vice.getNumOfDeliveryHeCan()+"\n");
			data_AddManagers.writer.close();
			data_AddManagers.reader.close();
		}
		catch(IOException e){}
	}
	/*This function add member to the suitable file*/
	public static void addMember_ToFiles(Manger manager,Members member) {
		SaveFiles data_AddManagers =new SaveFiles("src/AllMembersFile.txt");
		try{
			data_AddManagers.writer.write(member.getFirstName()+" "+member.getLastName()+" "+member.getId()
			+" "+member.getPhoneNumber()+" "+member.getLocation()+" "+manager.getUsername()+"\n");
			data_AddManagers.writer.close();
			data_AddManagers.reader.close();
		}
		catch(IOException e){}
	}
	/*This function add general delivery to the suitable file*/
	public static void addGeneralDelivery_ToFiles(Manger manager,Delivery delivery) {
		SaveFiles data_GeneralDelivery =new SaveFiles("src/GeneralDeliveriesFile.txt");
		try{
			if(delivery instanceof NormalDelivery) {
				data_GeneralDelivery.writer.write("Normal"+" "+manager.getUsername()+" "+delivery.getMember().getId()+" "+delivery.getFromCity()+" "+delivery.getTargetCity()+" "+delivery.getCode()+" "+((NormalDelivery) delivery).getNumberOfItems()+"\n");
			}
			else if(delivery instanceof ExpressDelivery)
			{
				data_GeneralDelivery.writer.write("Express"+" "+manager.getUsername()+" "+delivery.getMember().getId()+" "+delivery.getFromCity()+" "+delivery.getTargetCity()+" "+delivery.getCode()+" "+((ExpressDelivery) delivery).getNumberOfItems()+"\n");
			}
			else if(delivery instanceof BusinessDelivery)
			{
				data_GeneralDelivery.writer.write("Business"+" "+manager.getUsername()+" "+delivery.getMember().getId()+" "+delivery.getFromCity()+" "+delivery.getTargetCity()+" "+delivery.getCode()+" "+((BusinessDelivery) delivery).getNumberOfItems()+"\n");
			}
			data_GeneralDelivery.writer.close();
			data_GeneralDelivery.reader.close();
		}
		catch(IOException e){}
	}
	/*This function add short delivery to the suitable file*/
	public static void addShortDelivery_ToFiles(Vice v,Members member,ShortDelivery shortD) {
		SaveFiles data_addShortDelivery =new SaveFiles("src/ShortDeliveriesFile.txt");
		try{
			data_addShortDelivery.writer.write(v.getUsername()+" "+member.getId()+" "+shortD.getFromCity()+" "+shortD.getTargetCity()
			+" "+shortD.getCode()+" "+shortD.getDate()+" "+shortD.getDistane()+" "+shortD.getPrice()+"\n");
			data_addShortDelivery.writer.close();
			data_addShortDelivery.reader.close();
		}
		catch(IOException e){}
	}
	/*This function add all the deliveries to the suitable file*/
	public static void addAlldeliveries_ToFiles(Vice v,Members member,Delivery d) {
		SaveFiles data_addDelivery =new SaveFiles("src/AllDeliveriesFile.txt");
		try{
			if(d instanceof ShortDelivery)
			{
				data_addDelivery.writer.write("Short"+" "+v.getUsername()+" "+member.getId()+" "+d.getFromCity()+" "+d.getTargetCity()
				+" "+d.getCode()+" "+((ShortDelivery) d).getDate()+" "+((ShortDelivery) d).getDistane()+" "+((ShortDelivery) d).getPrice()+"\n");
			}
			else
			{
				if(d instanceof NormalDelivery) {
					data_addDelivery.writer.write("Normal"+" "+d.getMember().getId()+" "+d.getFromCity()+" "+d.getTargetCity()+" "+d.getCode()+" "+((NormalDelivery) d).getNumberOfItems()+"\n");
				}
				else if(d instanceof ExpressDelivery)
				{
					data_addDelivery.writer.write("Express"+" "+d.getMember().getId()+" "+d.getFromCity()+" "+d.getTargetCity()+" "+d.getCode()+" "+((ExpressDelivery) d).getNumberOfItems()+"\n");
				}
				else if(d instanceof BusinessDelivery)
				{
					data_addDelivery.writer.write("Business"+" "+d.getMember().getId()+" "+d.getFromCity()+" "+d.getTargetCity()+" "+d.getCode()+" "+((BusinessDelivery) d).getNumberOfItems()+"\n");
				}
			}
			data_addDelivery.writer.close();
			data_addDelivery.reader.close();
		}
		catch(IOException e){}
	}
	/*This function get all the data from the suitables files*/
	public static void getAllInFiles()
	{
		String line = new String();
		StringTokenizer st = null;
		try{
			SaveFiles data_AddManagers =new SaveFiles("src/ManagersFile.txt");
			while((line = data_AddManagers.reader.readLine()) != null){
				st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					mangers.add(new Manger(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken()));
				}}
			data_AddManagers.reader.close();
			data_AddManagers.writer.close();
			SaveFiles data_AddVices =new SaveFiles("VicesFile.txt");
			while((line = data_AddVices.reader.readLine()) != null){
				st = new StringTokenizer(line);
				vice.add(new Vice(st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(), st.nextToken(),Integer.parseInt(st.nextToken())));
			}
			data_AddVices.reader.close();
			data_AddVices.writer.close();
			SaveFiles data_AddMembers =new SaveFiles("src/AllMembersFile.txt");
			while((line = data_AddMembers.reader.readLine()) != null){
				st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String firstName=st.nextToken();
					String lastName=st.nextToken();
					String id=st.nextToken();
					String phoneNumber=st.nextToken();
					String location=st.nextToken();
					String managerUsername=st.nextToken();
					Members member=new Members(firstName, lastName, id, phoneNumber, location);
					members.put(id, member);
					for(Manger manager:mangers)
					{
						if(manager.getUsername().equals(managerUsername))
							manager.getMembers().put(id,member);
					}
				}}
			data_AddMembers.reader.close();
			data_AddMembers.writer.close();
			SaveFiles data_AddDeliveries=new SaveFiles("src/AllDeliveriesFile.txt");
			while((line = data_AddDeliveries.reader.readLine()) != null){
				st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String type=st.nextToken();
					if(type.equals("Short"))
					{
						String username=st.nextToken();//vice
						Vice vice1=null;
						for(Vice v:vice)
						{
							if(v.getUsername().equals(username))
							{
								vice1=v;
							}
						}
						Members member=DataBase.members.get(st.nextToken());
						String fromCity=st.nextToken();
						String targetCity=st.nextToken();
						Integer code=Integer.parseInt(st.nextToken());
						String dateStr =st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken() ;
						Date date= DataBase.convertStringToDate(dateStr);
						Integer distane=Integer.parseInt(st.nextToken());
						Double price=Double.parseDouble(st.nextToken());
						ShortDelivery shortD=new ShortDelivery(member,fromCity,targetCity,code,date,distane,price);						
						addD(shortD,member);
						vice1.getShortDelivery().add(shortD);
					}
					else if(type.equals("Normal"))
					{
						Members member=DataBase.members.get(st.nextToken());
						NormalDelivery normal=new NormalDelivery(member, st.nextToken(), st.nextToken(),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
						addD(normal,member);
					}
					else if(type.equals("Express"))
					{
						Members member=DataBase.members.get(st.nextToken());
						ExpressDelivery express=new ExpressDelivery(member, st.nextToken(), st.nextToken(),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
						addD(express,member);
					}
					else if(type.equals("Business"))
					{
						Members member=DataBase.members.get(st.nextToken());
						BusinessDelivery business=new BusinessDelivery(member, st.nextToken(), st.nextToken(),Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
						addD(business,member);
					}
				}}
			data_AddDeliveries.writer.close();
			data_AddDeliveries.reader.close();
		}catch (IOException | ParseException e) {
		}
	}
	/*This function add the delivery to the dataBase*/
	public static void addD(Delivery d,Members member)
	{
		for(Manger m:mangers) {
			if(m.getMembers().containsKey(member.getId()))
			{
				m.getMembers().get(member.getId()).getDelivery().add(d);
			}
		}
		ourMembersAndDeluveries1.put(d.getCode(),member);
		ourMembersAndDeluveries2.put(d.getCode(),d);
		countTree=d.getCode()+5;
		ourLastDeliveries.put(member, d);
	}
	/*This function convert the string that have taken from the files into date*/
	public static Date convertStringToDate(String s) throws ParseException
	{
		Date date= new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy" ,Locale.ENGLISH).parse(s);
		return date;
	}
	/*This function check if there is another manger with the same username*/
	public static boolean checkManger(String username)
	{
		for(Manger m:mangers)
		{
			if(m.getUsername().equals(username))
				return false;
		}
		return true;
	}
	/*This function check in all the program if there is members in the same id*/
	public static boolean checkMemberId(String id) {
		for(Manger manger :mangers)
		{
			if(manger.getMembers().containsKey(id))
				return false;
		}
		return true;
	}
	/*This function check the usrename and password if they are currect return the manger else return null*/
	public static Manger login(String username,String password){
		if(mangers.isEmpty())
		{
			System.out.println("Sorry we don't have any mangers yet!");
			return null;
		}
		Manger manager = new Manger(username, password);
		for(Manger m2: mangers)
		{
			if(m2.equals(manager))
				return m2;
		}
		System.out.println("Sorry, either the username or the password (or two of them) is/are uncorrect,you may try again");
		return null;
	}
	/*This function delete the member according to the id */
	public static void deleteMember(String id) {
		if(!ourMembersAndDeluveries1.isEmpty() &&ourMembersAndDeluveries1.containsValue(new Members(id)))
		{
			for(Delivery d:members.get(id).getDelivery())
			{
				ourMembersAndDeluveries1.remove(d.getCode());
				ourMembersAndDeluveries2.remove(d.getCode());
			}
			ourLastDeliveries.remove(new Members(id));
			members.remove(id);
		}
	}
	/*This function print the last delivery of the choosen member if he dosen't have any deliveries print a sutible massage*/
	public static void printLastDelivery(Members member)
	{
		if(ourLastDeliveries.containsKey(member) && !member.getDelivery().isEmpty())
			System.out.println(("The last delivery of this member: "+ourLastDeliveries.get(member)));
		else
			System.out.println("This member doesn't have a delivery yet!");
	}
	/*This function print the deliveries of the choosen area*/
	public static void printourMembersAndDeluveries(String input2){
		int counter = 0;
		System.out.println("The details of the deliveries in the "+input2+":");
		for(Map.Entry<Integer,Delivery> entry1:ourMembersAndDeluveries2.entrySet())
		{
			for(Map.Entry<Integer, Members> entry2:ourMembersAndDeluveries1.entrySet())

			{
				if(entry1.getKey().equals(entry2.getKey()) && entry2.getValue().getLocation().equals(input2))
				{
					System.out.println(entry1.getValue());
					counter++;
				}
			}
		}
		if(counter == 0)
			System.out.println("No deliveries!");
	}


}
