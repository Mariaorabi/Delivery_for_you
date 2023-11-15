import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.TreeSet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
public class MembersWith3OrMoreShort extends JFrame implements ActionListener{
	private JPanel contentPane;
	private MenuForVice menuVice;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JPanel panel_1;
	private JScrollPane scrollPane_1;
	public MembersWith3OrMoreShort(MenuForVice menuVice) {
		super("All the members with 3 or more short deliveries");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 613, 545);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.menuVice=menuVice;
		setVisible(true);
		contentPane.setVisible(true);
		menuVice.setVisible(false);
		int counter=0;
		int countWords=0;;
		int i=0;
		boolean check =true;
		JOptionPane.showMessageDialog(panel_1, "This table update to the last time this vice have saved members and their short deliveries in the file(SaveMembersWithShort.txt)");
		TreeSet<Members> members=new TreeSet<Members>();//tree set because there is no member that can show more than once
		Integer max_size=0;
		max_size=DataBase.members.size();//the max size of all members in the company 
		Object[][] data=new Object[max_size][5];
		String column[]={"firstName","lastName","id","phoneNumber","location"};
		try {
			String line = new String();
			StringTokenizer st;
			SaveFiles data1 =new SaveFiles("src/SaveMembersWithShort.txt");
			if((line = data1.reader.readLine()) != null){
				st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String words[] = line.split(" ");  
					countWords += words.length;
					while(countWords==5)
					{
						if(line==null)
						{
							countWords=0;
							break;
						}
						check=true;
						st = new StringTokenizer(line);
						if((st.hasMoreTokens()) != false) {
							st.nextToken();st.nextToken();//skip firstname+lastname
							String id=st.nextToken();
							st.nextToken();st.nextToken();//skip phone+location
							counter=0;
							while( check==true &&((line = data1.reader.readLine())!= null) )
							{
								countWords=0;
								String words1[] = line.split(" ");  
								countWords += words1.length;
								if(countWords!=5)
									counter++;
								if(counter>=3)
								{
									members.add(DataBase.members.get(id));//add the member to treeset if he has 3 or more short deliveries
								}
								if(countWords==5)
								{
									check=false;
								}
							}

						}}
				}}
			if(members.size()==0)
			{
				JOptionPane.showMessageDialog(null, "There isn't any members with 3 or more short deliveries ,so we will close this window");
				this.setVisible(false);
				menuVice.setVisible(true);
			}
			data1.writer.close();
			data1.reader.close();

		}catch(IOException r) {}
		for(Members m:members)
		{
			int j=0;
			data[i][j]=m.getFirstName();
			data[i][++j]=m.getLastName(); 
			data[i][++j]=m.getId();
			data[i][++j]=m.getPhoneNumber();
			data[i][++j]=m.getLocation();
			i++;
		}


		panel_1 = new JPanel();
		panel_1.setBounds(10, 10, 579, 473);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		btnNewButton = new JButton("");
		btnNewButton.setBounds(410, 441, 138, 21);
		panel_1.add(btnNewButton);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 527, 431);
		panel_1.add(scrollPane);
		JTable jtable=new JTable(data,column);
		scrollPane.setViewportView(jtable);

		scrollPane_1 = new JScrollPane();
		scrollPane.setRowHeaderView(scrollPane_1);
		btnNewButton.addActionListener(this);
		contentPane.setLayout(null);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			menuVice.setVisible(true);
		}		
	}
}
