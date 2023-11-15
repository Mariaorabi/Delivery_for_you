import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

public class DeleteMember extends JFrame implements ActionListener{

	private JPanel contentPane;
	private Manger manger;
	private JComboBox<String> comboBox;
	private JButton btnClose;
	private JButton btnNewButton;
	private MenuForManager menuForManager;
	public DeleteMember(Manger manger,MenuForManager menuForManager) {
		super("Delete member");
		this.manger=manger;
		this.menuForManager=menuForManager;
		menuForManager.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 488, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel_1 = new JLabel("Member id:");
		lblNewLabel_1.setBounds(10, 54, 77, 21);
		contentPane.add(lblNewLabel_1);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(240, 128, 128));
		comboBox.setBounds(95, 54, 90, 20);
		contentPane.add(comboBox);
		for(Members m:manger.getMembers().values())//Add all the members id that the this manager have
			comboBox.addItem(m.getId());
		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnClose.addActionListener(this);
		btnClose.setBounds(310, 10, 154, 31);
		contentPane.add(btnClose);

		btnNewButton = new JButton("Delete Member");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\x.jpg"));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.setBounds(42, 122, 101, 69);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Delete");
		lblNewLabel.setForeground(new Color(255, 69, 0));
		lblNewLabel.setBounds(69, 195, 101, 21);
		contentPane.add(lblNewLabel);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("deliveryx.jpg")));
		lIcon.setBounds(-243,-24,737,521);
		getContentPane().add(lIcon);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String id=(String) comboBox.getSelectedItem();
		if(e.getSource()==btnNewButton)//Delete
		{
			try{
				if(comboBox.getItemCount() == 0)//if there is no more id(members)
					throw new NullPointerException();
				if(manger.getMembers().containsKey(id))
				{
					int count=0;
					for(Delivery d :manger.getMembers().get(id).getDelivery())
					{
						if(d instanceof ShortDelivery)
							count++;
						if(count>0)//if the member has short deliveries
							throw new AllException();
					}
				}
				delete(id);
			}catch(AllException m) {
				JOptionPane.showMessageDialog(contentPane, "This member has short deliveries !!,so we will delete the short deliveries for him and delete him ");
				for(Delivery shortD :manger.getMembers().get(id).getDelivery())//delete all the short deliveries for this member from the array list of the vice that added it
				{
					if(shortD instanceof ShortDelivery)
					{
						for(Vice vice:DataBase.vice)
						{
							vice.getShortDelivery().remove(shortD);//remove function remove it if it has it ,if not it do nothing
						}
					}
				}
				try{
					String  line;
					File tempDB = new File("temp.txt");
					File db = new File("src/ShortDeliveriesFile.txt");
					BufferedReader br = new BufferedReader( new FileReader( db ) );
					BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
					while( ( line = br.readLine() ) != null ) {
						StringTokenizer st = new StringTokenizer(line);
						if((st.hasMoreTokens()) != false) {
							st.nextToken();//skip vice
							String id1=st.nextToken(); 
							if(id1.equals(id))
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
					delete(id);
				}catch(IOException w) {}
			}catch(NullPointerException r)
			{
				JOptionPane.showMessageDialog(contentPane,"There is no members this manager can delete!");
				setVisible(false);
				menuForManager.setVisible(true);
			}
		}else if(e.getSource()==btnClose)
		{
			setVisible(false);
			menuForManager.setVisible(true);
		}
	}
	/*This function delete the member from the dataBase and the comoBox */
	public void delete(String id)
	{
		DataBase.deleteMember(id);
		manger.getMembers().remove(id);
		for(Manger m1:DataBase.mangers)
		{
			m1.getMembers().remove(id);
		}
		comboBox.removeItem(id);
		try{	 
			String  line;
			File tempDB1 = new File("temp1.txt");
			File db1 = new File("src/AllMembersFile.txt");
			BufferedReader br0 = new BufferedReader( new FileReader( db1 ) );
			BufferedWriter bw0 = new BufferedWriter( new FileWriter( tempDB1 ) );
			while( ( line = br0.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					st.nextToken();st.nextToken();//skip firstname+lastname
					String id1=st.nextToken();
					if(id1.equals(id))
						continue;
					bw0.write(line);
					bw0.flush();
					bw0.newLine();
				}}
			br0.close();
			bw0.close();
			db1.delete();
			tempDB1.renameTo(db1);
			File tempDB2 = new File("temp.txt");
			File db2 = new File("src/AllDeliveriesFile.txt");
			BufferedReader br1 = new BufferedReader( new FileReader( db2 ) );
			BufferedWriter bw1 = new BufferedWriter( new FileWriter( tempDB2 ) );
			while( ( line = br1.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String type=st.nextToken();
					if(type.equals("Short"))
					{
						st.nextToken();//skip vice
						String id1=st.nextToken(); 
						if(id1.equals(id))
							continue;
					}
					else//normal express and busniess
					{
						String id1=st.nextToken(); 
						if(id1.equals(id))
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
			File tempDB = new File("temp.txt");
			File db = new File("src/GeneralDeliveriesFile.txt");
			BufferedReader br = new BufferedReader( new FileReader( db ) );
			BufferedWriter bw = new BufferedWriter( new FileWriter( tempDB ) );
			while( ( line = br.readLine() ) != null ) {
				StringTokenizer st = new StringTokenizer(line);
				st.nextToken();st.nextToken();//skip type+managerusername
				String memberId=st.nextToken();
				if(memberId.equals(id))
					continue;
				bw.write(line);
				bw.flush();
				bw.newLine();
			}
			br.close();
			bw.close();
			db.delete();
			tempDB.renameTo(db);
			JOptionPane.showMessageDialog(contentPane,"The member deleted successfully");
		}catch(IOException e) {}
	}}

