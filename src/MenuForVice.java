import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class MenuForVice extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private Vice vice;
	private Main main;
	private JMenuItem mntmNewMenuItem;
	private JMenuItem mntmNewMenuItem_1;
	private JLabel lblNewLabel;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	public MenuForVice(Vice vice, Main main) {
		super("Menu for vices");
		this.main=main;
		this.vice=vice;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 388);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(139, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 520, 34);
		contentPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Add short delivery");
		mnNewMenu.setBackground(Color.ORANGE);
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Add");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		mntmNewMenuItem.setBackground(Color.BLUE);
		mntmNewMenuItem.addActionListener(this);
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Delete short delivery");
		mnNewMenu_1.setBackground(Color.ORANGE);
		menuBar.add(mnNewMenu_1);

		mntmNewMenuItem_1 = new JMenuItem("Delete");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\--.png"));
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.addActionListener(this);
		mnNewMenu_1.addActionListener(this);

		btnNewButton = new JButton("Logout");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\lol.png"));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(10, 282, 52, 59);
		contentPane.add(btnNewButton);
		lblNewLabel = new JLabel("Logout");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setBounds(10, 244, 68, 28);
		contentPane.add(lblNewLabel);

		btnNewButton_1 = new JButton("All short deliveries in specific date");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setForeground(Color.BLUE);
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setBounds(174, 151, 336, 34);
		contentPane.add(btnNewButton_1);

		btnNewButton_2 = new JButton("Save all members with thier shorts deliveries");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setForeground(Color.BLUE);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.setBounds(174, 195, 336, 34);
		contentPane.add(btnNewButton_2);
		btnNewButton_3 = new JButton("Show all members with more than 3 short delivries");
		btnNewButton_3.addActionListener(this);
		btnNewButton_3.setForeground(Color.BLUE);
		btnNewButton_3.setBackground(Color.WHITE);
		btnNewButton_3.setBounds(174, 241, 336, 31);
		contentPane.add(btnNewButton_3);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("area.jpg")));
		lIcon.setBounds(-25,0,653,369);
		getContentPane().add(lIcon);

	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			main.setVisible(true);
		}
		else if(e.getSource()==mntmNewMenuItem)//Add short deliveries
		{
			try {
				if(DataBase.members.size()==0)//if there isn't members in the company
					throw new ArrayIsEmptyException();
				this.setVisible(false);
				new AddShortDelivery(vice,this).setVisible(true);
			}catch(ArrayIsEmptyException e1)
			{
				JOptionPane.showMessageDialog(null,"There is no members in the company yet!");
			}

		}
		else if(e.getSource()==mntmNewMenuItem_1)//Delete short deliveries
		{
			if(this.vice.getShortDelivery().size()==0)//If the vice doesn't add any short deliveries 
				//yet so he doesn't has any to delete
				JOptionPane.showMessageDialog(null,"This vice doesn't has any deliveries to delete!");
			else
			{
				this.setVisible(false);
				new DeleteShortDelivery(vice,this,null,null).setVisible(true);;
			}
		}else if(e.getSource()==btnNewButton_1)//show shorts deliveries in specific date
		{
			if(!checkVicesShortDeliveries())
			{
				this.setVisible(false);
				new AllShortDeliveriesInDate(null, this);
			}	
		}else if(e.getSource()==btnNewButton_2)//save button
		{
			Integer counter=0;
			if(!checkVicesShortDeliveries())
			{
				try{
					Writer w = new FileWriter("src/SaveMembersWithShort.txt"); 
					PrintWriter writer = new PrintWriter(w);
					writer.print("");//clear all and then resave/update
					writer.close();
					SaveFiles data_addShortDelivery =new SaveFiles("src/SaveMembersWithShort.txt");
					for(Members member :DataBase.members.values())//save all members with their shorts deliveries in file regadless if this vice add this short deliveries or not
					{
						counter=0;
						for(Delivery delivery:member.getDelivery())
						{
							if(delivery instanceof ShortDelivery)
							{
								counter++;
								if(counter==1)
								{
									data_addShortDelivery.writer.write(member.getFirstName()+" "+member.getLastName()+" "+member.getId()+" "
											+member.getPhoneNumber()+" "+member.getLocation()+"\n");//add the member
								}
								data_addShortDelivery.writer.write(member.getId()+" "+delivery.getFromCity()+" "+delivery.getTargetCity()
								+" "+delivery.getCode()+" "+((ShortDelivery) delivery).getDate()+" "+((ShortDelivery) delivery).getDistane()+" "+((ShortDelivery) delivery).getPrice()+"\n");//add short delivery
							}
						}
					}
					JOptionPane.showMessageDialog(contentPane,"Saved successfully");
					data_addShortDelivery.writer.close();
					data_addShortDelivery.reader.close();
				}catch(IOException z){}
			}
		}else if(e.getSource()==btnNewButton_3)//see members with 2 or more short deliveries in all the company
		{
			if(!checkVicesShortDeliveries())
			{
				if(new File("src/SaveMembersWithShort.txt").length()!=0)
				{
					this.setVisible(false);
					new MembersWith3OrMoreShort(this);
				}
				else
					JOptionPane.showMessageDialog(contentPane,"You have to save all members with thier shorts deliveries before");//if the SaveMembersWithShort.txt is still empty
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
			JOptionPane.showMessageDialog(contentPane,"Sorry but there is no short deliveries in the company yet!");
			return true;
		}
		return false;
	}


}
