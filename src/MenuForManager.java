import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class MenuForManager extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenuItem addMember;
	private JMenuItem deleteMember;
	private JMenu member;
	private JMenuItem show;
	private JMenu else3;
	private JMenuItem shortDeliveries;
	private JMenuItem membersHaveShortDeliveries;
	private JMenuItem cities30;
	private JMenu lastDeliveries;
	private JButton btnNewButton;
	private Main main;
	private Manger manager;
	private JMenu mnDelivery;
	private JMenuItem addDelivery;
	private JMenuItem mntmRegularDelivery;
	private JMenuItem mntmShowAllMembers;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JMenu mnNewMenu_1;
	private JMenuItem mntmNewMenuItem_2;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	public MenuForManager(Manger manager, Main main) {
		super("Mangers's menu");
		this.manager=manager;
		this.main=main;
		main.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 906, 511);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.BLUE);
		menuBar.setBounds(0, 0, 892, 41);
		contentPane.add(menuBar);
		member = new JMenu("Member");
		member.setBackground(new Color(153, 204, 153));
		addMember=new JMenuItem("Add");
		addMember.setForeground(new Color(128, 0, 0));
		addMember.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		deleteMember=new JMenuItem("Delete");
		deleteMember.setForeground(Color.ORANGE);
		deleteMember.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\--.png"));
		deleteMember.addActionListener(this);
		member.add(addMember);
		addMember.addActionListener(this);
		member.add(deleteMember);
		menuBar.add(member);

		mntmShowAllMembers = new JMenuItem("Show all members and thier deliveries in the company");
		mntmShowAllMembers.setForeground(new Color(128, 128, 0));
		mntmShowAllMembers.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		member.add(mntmShowAllMembers);
		mntmShowAllMembers.addActionListener(this);
		member.addActionListener(this);

		lastDeliveries = new JMenu("Last deliveries in all the company");
		lastDeliveries.setBackground(new Color(153, 204, 102));
		show=new JMenuItem("Show");
		show.setForeground(Color.RED);
		show.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		lastDeliveries.add(show);
		show.addActionListener(this);

		mnNewMenu = new JMenu("Vice");
		mnNewMenu.setBackground(new Color(204, 204, 102));
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Add");
		mntmNewMenuItem.setForeground(Color.BLUE);
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		mnNewMenu.add(mntmNewMenuItem);
		menuBar.add(lastDeliveries);

		mnDelivery = new JMenu("Delivery");
		mnDelivery.setBackground(new Color(204, 204, 153));
		menuBar.add(mnDelivery);
		mntmNewMenuItem.addActionListener(this);

		addDelivery = new JMenuItem("Add");
		addDelivery.setForeground(new Color(255, 182, 193));
		addDelivery.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		mnDelivery.add(addDelivery);
		addDelivery.addActionListener(this);

		mntmRegularDelivery = new JMenuItem("Show all deliveries in some area");
		mntmRegularDelivery.setForeground(new Color(153, 50, 204));
		mntmRegularDelivery.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		mnDelivery.add(mntmRegularDelivery);

		mntmNewMenuItem_1 = new JMenuItem("Delete short delivery");
		mntmNewMenuItem_1.setForeground(new Color(154, 205, 50));
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\--.png"));
		mnDelivery.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
		mntmRegularDelivery.addActionListener(this);

		mnNewMenu_1 = new JMenu("Manager");
		mnNewMenu_1.setBackground(new Color(102, 102, 51));
		menuBar.add(mnNewMenu_1);

		mntmNewMenuItem_2 = new JMenuItem("Add");
		mntmNewMenuItem_2.setForeground(new Color(102, 153, 0));
		mntmNewMenuItem_2.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		mnNewMenu_1.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.addActionListener(this);

		else3 =new JMenu("else");
		else3.setBackground(new Color(51, 0, 255));

		JMenu mnNewMenu_2 = new JMenu("Sub menu");
		else3.add(mnNewMenu_2);
		membersHaveShortDeliveries=new JMenuItem("All member that have short deliveries in the company");
		mnNewMenu_2.add(membersHaveShortDeliveries);
		membersHaveShortDeliveries.setForeground(new Color(255, 102, 51));
		membersHaveShortDeliveries.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		shortDeliveries=new JMenuItem("All short deliveries in the company");
		mnNewMenu_2.add(shortDeliveries);
		shortDeliveries.setForeground(new Color(102, 51, 255));
		shortDeliveries.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		cities30=new JMenuItem("Cities that have sent to them short delievries in the last 30 days in the company ");
		mnNewMenu_2.add(cities30);
		cities30.setForeground(new Color(0, 102, 51));
		cities30.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		cities30.addActionListener(this);
		shortDeliveries.addActionListener(this);
		membersHaveShortDeliveries.addActionListener(this);
		menuBar.add(else3);


		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\lol.png"));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 415, 54, 41);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Logout");
		lblNewLabel.setBackground(new Color(0, 0, 205));
		lblNewLabel.setForeground(new Color(128, 0, 0));
		lblNewLabel.setBounds(10, 372, 65, 33);
		contentPane.add(lblNewLabel);


		btnNewButton_1 = new JButton("All short deliveries in specific date");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBounds(569, 77, 290, 41);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("All deliveries for specific member");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBounds(569, 132, 290, 41);
		contentPane.add(btnNewButton_2);
		btnNewButton_3 = new JButton("All the general deliveries in this manager area ");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setForeground(Color.BLUE);
		btnNewButton_3.setBounds(569, 183, 290, 41);
		contentPane.add(btnNewButton_3);
		JLabel lIcon =new JLabel("");
		lIcon.setBounds(-192, -30, 1095, 843);
		contentPane.add(lIcon);
		lIcon.setIcon(new ImageIcon(Main.class.getResource("ph.jpg")));

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Logout and back to the main menu
		{
			this.setVisible(false);
			main.setVisible(true);
		}
		else if(e.getSource()==addMember)//add member
		{
			this.setVisible(false);
			new AddMember(manager, this).setVisible(true);
		}
		if(e.getSource()==deleteMember)//delete member
		{
			if(manager.getMembers().isEmpty())//If the manager doesn't has any member can't delete
				JOptionPane.showMessageDialog(contentPane, "Sorry!,This manager hasn't any members yet!");
			else{
				this.setVisible(false);
				new DeleteMember(manager, this).setVisible(true);
			}
		}
		else if(e.getSource()==mntmNewMenuItem)//Add vice
		{
			this.setVisible(false);
			new AddVice(this).setVisible(true);
		}
		else if(e.getSource()==show)//Show all the last deliveries in all the company
		{
			if(DataBase.ourLastDeliveries.isEmpty())//If thier isn't any last deliveries
				JOptionPane.showMessageDialog(contentPane, "Sorry!,their isn't any last deliveries yet!");
			else
			{
				this.setVisible(false);
				new LastDeliveries(this).setVisible(true);
			}
		}
		else if (e.getSource()==shortDeliveries)//Show all the short deliveries in the company
		{
			if(!checkVicesShortDeliveries())
			{
				this.setVisible(false);
				new Table(this);
			}

		}
		else if(e.getSource()==membersHaveShortDeliveries)//Show all the members in the company that have short deliveries
		{
			if(!checkVicesShortDeliveries()){
				this.setVisible(false);
				new MemberWithShortDeliveries(this).setVisible(true);}

		}
		else if(e.getSource()==addDelivery)//Add delivery express/normal/business
		{
			if(manager.getMembers().isEmpty())//If the manager doesn't has any member can't add deliveries
			{
				this.setVisible(false);
				JOptionPane.showMessageDialog(contentPane, "Sorry this manger doesn't has any members yet!");
				setVisible(true);
			}
			else
			{this.setVisible(false);
			new addDelivery(manager,this).setVisible(true);
			}
		}
		else if(e.getSource()==mntmShowAllMembers)//Show all the members and thier deliveries in all the company
		{
			this.setVisible(false);
			new AllMembersAndDeliveries(this).setVisible(true);
		}
		else if(e.getSource()==mntmRegularDelivery)//Show all the deliveries in choosen area
		{
			this.setVisible(false);
			new AllDeliveriesInArea(this).setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem_1)
		{
			Boolean check=false;
			for(Members m :manager.getMembers().values())//Check if the manager the members that have short delivries
			{
				for(Delivery d:m.getDelivery())
				{
					if(d instanceof ShortDelivery)
						check=true;
				}
			}
			if(check==false)
				JOptionPane.showMessageDialog(contentPane, "There is no short delivireies for this manager's members yet!");
			else{
				this.setVisible(false);
				new DeleteShortDelivery(null,null,manager,this).setVisible(true);
			}
		}
		else if(e.getSource().equals(mntmNewMenuItem_2))//Add new manager
		{
			this.setVisible(false);
			new SignUp(this);
		}
		else if(e.getSource()==cities30)//See the cities that have short deliveries in the last 30 days,in all the program
		{	
			if(!checkVicesShortDeliveries()){
				this.setVisible(false);
				new CitiesWithShortDeliveries(this).setVisible(true);
			}
		}
		else if(e.getSource()==btnNewButton_1)//show shorts deliveries in specific date
		{
			if(!checkVicesShortDeliveries())
			{
				this.setVisible(false);
				new AllShortDeliveriesInDate(this,null);
			}
		}
		else if(e.getSource()==btnNewButton_2)//see all short and general deliveries for specific member
		{
			if(DataBase.members.size()==0)
			{
				JOptionPane.showMessageDialog(contentPane, "Sorry!,There is no members in the company yet!");
			}
			else if(!checkAllDForOneMember())
			{
				JOptionPane.showMessageDialog(contentPane, "Sorry!,There is no members have deliveries yet !");
			}
			else {
				this.setVisible(false);
				new AllDeliveriesForOneMember(this);
			}
		}
		else if(e.getSource()==btnNewButton_3)//show all general deliveries in the manager area
		{
			if(!checkGeneralDeliveries())
			{
				JOptionPane.showMessageDialog(contentPane, "There is no general deliveries for any member in this manager area!");
			}
			else
			{
				this.setVisible(false);
				new AllDeliveriesInManagerArea(this, manager);
			}
		}
	}
	/*This function check if there is any short deliveries in the company*/
	public boolean checkVicesShortDeliveries() {
		int counter=0;
		for(Vice v:DataBase.vice)
		{
			if(v.getShortDelivery().isEmpty())//counter help to check if all the vices doen't have any short deliveries
				counter++;
		}
		if(counter==DataBase.vice.size())//If there is no short deliveries
		{
			JOptionPane.showMessageDialog(contentPane,"Sorry but there is no short deliveries yet!");
			return true;
		}
		return false;
	}
	/*This function check if there is general deliveries in the company*/
	public Boolean checkGeneralDeliveries()
	{
		boolean check=false;
		for(Members m: DataBase.members.values())
		{
			if(m.getLocation().equals(manager.getLocation()))
			{for(Delivery d:m.getDelivery())
			{
				if(!(d instanceof ShortDelivery))
				{
					check=true;
					break;
				}
			}
			}
		}
		return check;
	}
	/*This function check if there is deliveries in the company*/
	public Boolean checkAllDForOneMember()
	{
		boolean check=false;
		for(Members m: DataBase.members.values())
		{
			if(m.getDelivery().size()>0)
				check=true;
		}
		return check;
	}
}
