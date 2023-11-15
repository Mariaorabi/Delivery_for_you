import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

public class AllDeliveriesInManagerArea extends JFrame implements ActionListener {

	private JPanel contentPane;
	private MenuForManager menuForManager;
	private JButton btnNewButton;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private Manger manager;
	public AllDeliveriesInManagerArea(MenuForManager menuForManager,Manger manager) {
		super("All general deliveries in the manager area");
		this.manager=manager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		this.menuForManager=menuForManager; 
		panel = new JPanel();
		panel.setBounds(37, 40, 680, 294);
		contentPane.add(panel);
		menuForManager.setVisible(false);
		Integer size=0;
		for(Members m1:DataBase.members.values())
		{
			if(m1.getLocation().equals(manager.getLocation()))
			{
				for(Delivery d:m1.getDelivery())
				{
					if(!(d instanceof ShortDelivery))
						size++;
				}
			}
		}
		Object[][] data=new Object[size][5];
		int i=0;
		try {
			String line = new String();
			SaveFiles data_getD =new SaveFiles("src/GeneralDeliveriesFile.txt");
			while((line = data_getD.reader.readLine()) != null){
				StringTokenizer st = new StringTokenizer(line);
				if((st.hasMoreTokens()) != false) {
					String type=st.nextToken();
					st.nextToken();//skip manger username
					String memberId=st.nextToken();
					Members member=DataBase.members.get(memberId);
					String fromCity=st.nextToken();
					String targetCity=st.nextToken();
					st.nextToken();//skip code
					String numOfItems=st.nextToken();	
					if(this.manager.getLocation().equals(member.getLocation()))//all deliveries in this manager area
						//according if the member s in the manager area,I put all the deliveries in the manager area 
						//regardless if this manager add this member or not
					{
						int j=0;
						data[i][j]=type;
						data[i][++j]=memberId; 
						data[i][++j]=fromCity;
						data[i][++j]=targetCity;
						data[i][++j]=numOfItems;
						i++;
					}
				}}
			data_getD.reader.close();
			data_getD.writer.close();
		}catch(IOException r){}
		String column[]={"type","member id","fromCity","targetCity","numberOfIAtems"};
		btnNewButton = new JButton("");
		btnNewButton.setBounds(22, 359, 138, 21);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		panel.setLayout(null);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 680, 294);
		panel.add(scrollPane_1);

		scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		JTable jtable=new JTable(data,column);
		scrollPane.setViewportView(jtable);



	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}		
	}
}
