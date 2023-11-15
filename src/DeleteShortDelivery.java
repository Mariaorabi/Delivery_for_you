import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class DeleteShortDelivery extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnDelete;
	private Vice vice;
	private MenuForVice menuVice;
	private JComboBox<String> comboBox;
	private JLabel lblPleaseChooseThe; 
	private JLabel lblPleaseChooseThe_2;
	private JComboBox<String> comboBox_1;
	private JButton btnNewButton_1;
	private JComboBox<Integer> comboBox_2;
	private JLabel lblPleaseChooseThe_1;
	private Manger manager;
	private MenuForManager menuForManager;
	private DefaultComboBoxModel<String> model;
	private JButton btnNewButton_2;
	public DeleteShortDelivery(Vice vice,MenuForVice menuVice, Manger manager, MenuForManager menuForManager) {
		super("Delete short delivery");
		this.vice=vice;
		this.menuVice=menuVice;
		this.manager=manager;
		this.menuForManager=menuForManager;
		if (vice!=null)
			menuVice.setVisible(false);
		else 
			menuForManager.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 986, 387);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 69, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		model = new DefaultComboBoxModel<String>();
		comboBox  = new JComboBox<String>(model);
		comboBox.setBounds(235, 70, 85, 21);
		contentPane.add(comboBox);
		Boolean check=true;

		if(vice!=null)
		{
			for(ShortDelivery shortD:vice.getShortDelivery())
			{
				if(model.getIndexOf(shortD.getMember().getId()) == -1 ) {
					model.addElement(shortD.getMember().getId());
				}				
			}
		}
		else //manager
		{
			for(Members m:manager.getMembers().values())//all the members that the 
				//manager have and that have short delivries
			{
				check=true;
				for(int i=0;i<m.getDelivery().size() && check!=false;i++)
				{
					if(m.getDelivery().get(i) instanceof ShortDelivery )
					{
						check=false;//if the member have short delivery then add them
						comboBox.addItem(m.getId());
					}
				}
			}
		}
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(748, 12, 162, 29);
		contentPane.add(btnNewButton);

		btnDelete = new JButton("");
		btnDelete.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\x.jpg"));
		btnDelete.addActionListener(this);
		btnDelete.setBounds(362, 195, 85, 78);
		contentPane.add(btnDelete);

		lblPleaseChooseThe = new JLabel("Please choose the member id:");
		lblPleaseChooseThe.setForeground(new Color(0, 0, 128));
		lblPleaseChooseThe.setBounds(10, 70, 208, 21);
		contentPane.add(lblPleaseChooseThe);

		lblPleaseChooseThe_2 = new JLabel("Please choose the short delivery:");
		lblPleaseChooseThe_2.setBackground(Color.BLUE);
		lblPleaseChooseThe_2.setForeground(new Color(128, 0, 0));
		lblPleaseChooseThe_2.setBounds(10, 116, 227, 42);
		contentPane.add(lblPleaseChooseThe_2);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(196, 101, 766, 57);
		contentPane.add(comboBox_1);

		btnNewButton_1 = new JButton("Choose");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(349, 70, 85, 21);
		contentPane.add(btnNewButton_1);

		comboBox_2 = new JComboBox<Integer>();
		comboBox_2.setBounds(238, 195, 93, 21);
		contentPane.add(comboBox_2);

		lblPleaseChooseThe_1 = new JLabel("Delete delivery code:");
		lblPleaseChooseThe_1.setBackground(new Color(0, 0, 0));
		lblPleaseChooseThe_1.setForeground(new Color(128, 0, 128));
		lblPleaseChooseThe_1.setBounds(10, 195, 208, 21);
		contentPane.add(lblPleaseChooseThe_1);
		btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(455, 70, 85, 21);
		contentPane.add(btnNewButton_2);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("cancel.png")));
		lIcon.setBounds(419,12,287,222);
		getContentPane().add(lIcon);




	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id=(String) comboBox.getSelectedItem();
		Integer code=(Integer) comboBox_2.getSelectedItem();
		Integer counter=0;
		if(e.getSource()==btnNewButton_1)//Choose
		{
			if(vice==null)//manager
			{
				try {
					for(Delivery d:manager.getMembers().get(id).getDelivery())//add all the short deliveries for the choosen member id
					{
						if(d instanceof ShortDelivery)
						{
							comboBox_1.addItem(d.toString());//add short delivery to string
							comboBox_2.addItem(d.getCode());//add short delivery code
							counter++;
						}
					}
					if(counter==0)//if all the short deliveries have been deleted for this member id
						throw new ArrayIsEmptyException();
				}catch (ArrayIsEmptyException e1) {
					JOptionPane.showMessageDialog(null, "All the short deliveries have been delete for this member!!");
				}
			} 

			if(manager==null)//vice
			{
				try {
					if(vice.getShortDelivery().size()==0)
						throw new ArrayIsEmptyException();
					for(ShortDelivery sh:vice.getShortDelivery())//Add all the short deliveries for the choosen member id
					{
						if(sh.getMember().equals(new Members(id)))
						{
							comboBox_1.addItem(sh.toString());
							comboBox_2.addItem(sh.getCode());
						}
					}
				}catch(ArrayIsEmptyException t)
				{
					JOptionPane.showMessageDialog(contentPane, "This vice alreaday deleted all the short deliveries that he manage!!");
					this.setVisible(false);
					menuVice.setVisible(true);
				}
			}
		}
		else if(e.getSource()==btnDelete)//Delete
		{
			try{

				if(comboBox_2.getItemCount()==0 && comboBox_1.getItemCount()==0 )
					throw new NullPointerException();//if click on delete button before  click on the choose button*/
				if(comboBox_2.getItemCount() == 0)
					throw new ArrayIsEmptyException();
				if(!(DataBase.members.get(id).getDelivery().contains(new ShortDelivery(code))))//check if the choosen id suitable with the code delivery
					throw new AllException();
				if(vice!=null)//vice
				{
					counter=0;
					try{
						if (vice.getShortDelivery().size()==0)
							throw new NullPointerException();
						deleteFromVice(code);
						for(ShortDelivery shortD:vice.getShortDelivery())
						{
							if(shortD.getMember().equals(new Members(id)))
								counter++;
						}
						if(counter==0)
						{
							comboBox.removeItem(id);//if there is no short delivery that the vice added for this id then remove the id
						}
						for(Manger m1:DataBase.mangers)//Remove this delivery from the member that have it in the arraylist 
							//of the manager that have this member 
						{
							if(m1.getMembers().containsKey(id))
								m1.getMembers().get(id).getDelivery().remove(new ShortDelivery(code));
						}
						delete(id,code);
					}catch(NullPointerException r)
					{
						JOptionPane.showMessageDialog(null, "This vice can't delete more deliveries beacuse he hasn't !!");
						this.setVisible(false);
						menuVice.setVisible(true);
					}
				}
				else//manager!=null
				{
					for(Vice v:DataBase.vice)
					{
						v.getShortDelivery().remove(new ShortDelivery(code));//remove: if it dosen't contain this short delivery any thing will not change
						//else remove it
					}
					comboBox_1.removeAllItems();
					comboBox_2.removeAllItems();
					manager.getMembers().get(id).getDelivery().remove(new ShortDelivery(code));
					delete(id, code);
				}
			} catch (ArrayIsEmptyException e1) {
				comboBox.removeItem(id);
				JOptionPane.showMessageDialog(contentPane, "All the short deliveries for this member already deleted!");
			}catch(NullPointerException r) {
				JOptionPane.showMessageDialog(contentPane, "You have to click on the choose button ");
			}catch (AllException e1) {
				JOptionPane.showMessageDialog(contentPane, "You have to choose the suitable id member with the suitable code delivery ,so we will clear and you may choose again!");
				comboBox_1.removeAllItems();
				comboBox_2.removeAllItems();
			}
		}
		else if(e.getSource()==btnNewButton)//Close
		{
			if(manager!=null)
			{
				this.setVisible(false);
				menuForManager.setVisible(true);
			}
			else
			{
				this.setVisible(false);
				menuVice.setVisible(true);
			}
		}
		else if(e.getSource()==btnNewButton_2)
		{
			comboBox_1.removeAllItems();
			comboBox_2.removeAllItems();
		}
	}
	/*Delete the delivery from the dataBase*/
	public void delete(String id,Integer code)
	{
		if((DataBase.ourLastDeliveries.get(new Members(id))) instanceof ShortDelivery)//if the last delivery is this short delivery for this member 

		{
			if(((ShortDelivery) DataBase.ourLastDeliveries.get(new Members(id))).getCode().equals(code))
				DataBase.ourLastDeliveries.remove(new Members(id));
		}
		DataBase.ourMembersAndDeluveries1.remove(code);
		DataBase.ourMembersAndDeluveries2.remove(code);
		DataBase.members.get(id).getDelivery().remove(new ShortDelivery(code));
		for(Manger m:DataBase.mangers)
		{
			if(m.getMembers().containsKey(id) && m.getMembers().get(id).getDelivery()!=null)
				m.getMembers().get(id).getDelivery().remove(new Delivery(code));
		}
		try{
			String  line;
			File tempDB = new File("temp.txt");
			File db = new File("src/ShortDeliveriesFile.txt");//delete from ShortDeliveriesFile.txt
			BufferedReader br = new BufferedReader( new FileReader( db ) );
			BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
			while( ( line = br.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					st.nextToken();//skip vice
					String memberId=st.nextToken();
					st.nextToken();st.nextToken();//skip fromcity+targetcity
					Integer code0=Integer.parseInt(st.nextToken()); 
					if(code0.equals(code) && memberId.equals(id))
						continue;
					bw.write(line);
					bw.flush();
					bw.newLine();
				}
			}
			br.close();
			bw.close();
			db.delete();
			tempDB.renameTo(db);
			File tempDB2 = new File("temp.txt");
			File db2 = new File("src/AllDeliveriesFile.txt");//delete from AllDeliveriesFile.txt
			BufferedReader br1 = new BufferedReader( new FileReader( db2 ) );
			BufferedWriter bw1 = new BufferedWriter( new FileWriter( tempDB2 ) );
			while( ( line = br1.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String type=st.nextToken();
					st.nextToken();//skip vice
					String id1=st.nextToken();//member id
					st.nextToken() ;st.nextToken();  //skip fromcity +targetcity
					Integer code1=Integer.parseInt(st.nextToken()); 
					if(type.equals("Short"))
					{
						if(id1.equals(id) &&code1.equals(code) )
							continue;
					}
					bw1.write(line);
					bw1.flush();
					bw1.newLine();
				}}
			br1.close();
			bw1.close();
			db2.delete();
			tempDB2.renameTo(db2);
			JOptionPane.showMessageDialog(null, "The short delivery deleted successfully");
		}catch(IOException | java.util.NoSuchElementException e) {}
	}
	/*This function delete the short delivery according to a code from the shortDeliveries arraylist that the vice has*/
	public void deleteFromVice(Integer code)
	{
		vice.getShortDelivery().remove(new ShortDelivery(code));
		comboBox_1.removeAllItems();
		comboBox_2.removeAllItems();
	}
}