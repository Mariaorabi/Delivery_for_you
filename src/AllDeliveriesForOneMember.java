import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;

public class AllDeliveriesForOneMember extends JFrame implements ActionListener{
	private JPanel contentPane;
	private MenuForManager menuForManager;
	private JButton btnNewButton;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JComboBox<String> comboBox;
	private Object[][] data;
	private JTable jtable;
	private JButton btnNewButton_1;
	private JScrollPane scrollPane_2;
	public AllDeliveriesForOneMember(MenuForManager menuForManager) {
		super("All the deliveries for a specific member");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		this.menuForManager=menuForManager; 
		contentPane.setLayout(null);
		menuForManager.setVisible(false);
		btnNewButton = new JButton("");
		btnNewButton.setBounds(630, 10, 138, 21);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(136, 29, 88, 26);
		contentPane.add(comboBox);
		for(Members m:DataBase.members.values())//all members that have deliveries 
		{
			if(m.getDelivery().size()>0)
			{
				comboBox.addItem(m.getId());
			}
		}
		comboBox.addActionListener(this);

		JLabel lblNewLabel = new JLabel("Choose id:");
		lblNewLabel.setBounds(23, 29, 103, 26);
		contentPane.add(lblNewLabel);

		btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setBounds(269, 32, 110, 21);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);
		panel = new JPanel();
		panel.setBounds(20, 114, 703, 338);
		contentPane.add(panel);
		panel.setLayout(null);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 705, 340);
		panel.add(scrollPane_2);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
		else if(e.getSource()==btnNewButton_1)//after click on choose button(this button)the table will apear according to the choosen id
		{
			String id=(String) comboBox.getSelectedItem();
			String line = new String();
			int i=0;
			Object column[]={"type","numOfItems","date","fromCity","targetCity","distane","price","member id"};	
			Integer size=DataBase.members.get(id).getDelivery().size();//the size of the deliveries according to the member deliveries sizes
			data=new Object[size][8];
			StringTokenizer st;
			try{
				SaveFiles data_AddShort =new SaveFiles("src/ShortDeliveriesFile.txt");
				while((line = data_AddShort.reader.readLine()) != null){
					st = new StringTokenizer(line);
					if((st.hasMoreTokens()) != false) {
					st.nextToken();//skip vice
					Members member=DataBase.members.get(st.nextToken());
					String fromCity=st.nextToken();
					String targetCity=st.nextToken();
					st.nextToken();//skip code
					String dateStr =st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken() ;
					Date date= DataBase.convertStringToDate(dateStr);
					Integer distane=Integer.parseInt(st.nextToken());
					Double price=Double.parseDouble(st.nextToken());
					if(member.equals(new Members(id)))
					{
						int j=0;
						data[i][j]="Short";
						data[i][++j]=null; //short deliveries don't have numberOfItems
						data[i][++j]=date;
						data[i][++j]=fromCity;
						data[i][++j]=targetCity;
						data[i][++j]=distane;
						data[i][++j]=price;
						data[i][++j]=id;
						i++;
					}
				}
				}
				data_AddShort.reader.close();
				data_AddShort.writer.close();
				SaveFiles data_AddGeneral =new SaveFiles("src/GeneralDeliveriesFile.txt");
				while((line = data_AddGeneral.reader.readLine()) != null){
					st = new StringTokenizer(line);
					if((st.hasMoreTokens()) != false) {
					String type=st.nextToken();
					st.nextToken();//skip manager username;
					String id1=st.nextToken();
					String fromCity=st.nextToken();
					String targetCity=st.nextToken();
					st.nextToken();//skip code
					Integer numberOfItems=Integer.parseInt(st.nextToken());
					if(id1.equals(id))
					{
						int j=0;
						data[i][j]=type;
						data[i][++j]=numberOfItems; 
						data[i][++j]=null;//general deliveries don't have date
						data[i][++j]=fromCity;
						data[i][++j]=targetCity;
						data[i][++j]=null;//general deliveries don't have distance
						data[i][++j]=null;//general deliveries don't have price
						data[i][++j]=id;
						i++;
					}
				}}

				jtable=new JTable(data,column);
				scrollPane_1 = new JScrollPane();
				scrollPane_1.setBounds(0, 0, 680, 294);
				panel.add(scrollPane_1);
				scrollPane = new JScrollPane();
				scrollPane_1.setViewportView(scrollPane);
				scrollPane.setViewportView(jtable);

				
				data_AddGeneral.reader.close();
				data_AddGeneral.writer.close();
			}catch(IOException | ParseException r){}
		}
	}
}
