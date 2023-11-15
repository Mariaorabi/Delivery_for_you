import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.calendar.JDateChooser;

public class AllShortDeliveriesInDate extends JFrame implements ActionListener{
	private JPanel contentPane;
	private MenuForManager menuForManager;
	private MenuForVice menuForVice;
	private JButton btnNewButton;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JDateChooser dateChooser;
	private JButton btnNewButton_1;
	private JTable jtable;
	public AllShortDeliveriesInDate(MenuForManager menuForManager,MenuForVice menuForVice) {
		super("All short deliveries in specific date");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 775, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		panel = new JPanel();
		panel.setBounds(35, 161, 680, 294);
		contentPane.add(panel);
		if(menuForManager!=null)
			{
			menuForManager.setVisible(false);
			this.menuForManager=menuForManager;
			}
		else 
			{
			menuForVice.setVisible(false);
			this.menuForVice=menuForVice;
			}
		dateChooser = new JDateChooser();
		dateChooser.setBounds(161, 34, 129, 28);
		contentPane.add(dateChooser);
		btnNewButton = new JButton("");
		btnNewButton.setBounds(597, 9, 138, 21);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		panel.setLayout(null);
		JLabel lblNewLabel = new JLabel("Choose date:");
		lblNewLabel.setBounds(35, 34, 116, 28);
		contentPane.add(lblNewLabel);
		
		btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(259, 90, 110, 21);
		contentPane.add(btnNewButton_1);
		
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Close
		{
			if(menuForManager!=null)
			{
				this.setVisible(false);
				menuForManager.setVisible(true);
			}
			else
			{
				this.setVisible(false);
				menuForVice.setVisible(true);
			}
		}
		else if(e.getSource()==btnNewButton_1)
		{
			String line = new String();
			StringTokenizer st;
			int i=0;
			Boolean check=false;
			Integer size=0;
			for(Vice vice:DataBase.vice)//the max size of the short deliveries according to the vices short deliveries sizes
			{
				size+=vice.getShortDelivery().size();
			}
			Object[][] data =new Object[size][6];
			String column[]={"date","fromCity","targetCity","distane","price","member id"};
			try{
				SaveFiles data_AddManagers =new SaveFiles("src/ShortDeliveriesFile.txt");
				while((line = data_AddManagers.reader.readLine()) != null){
					st = new StringTokenizer(line);
					if((st.hasMoreTokens()) != false) {
					st.nextToken();//skip vice
					Members member=DataBase.members.get(st.nextToken());
					String fromCity=st.nextToken();
					String targetCity=st.nextToken();
					st.nextToken();//skip code
					String dateStr =st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken()+" "+st.nextToken() ;
					Date date=DataBase.convertStringToDate(dateStr);
					Integer distane=Integer.parseInt(st.nextToken());
					Double price=Double.parseDouble(st.nextToken());
					Calendar calendar1 = Calendar.getInstance();
				    Calendar calendar2 = Calendar.getInstance();
				    calendar2.setTime(dateChooser.getDate());
				    calendar1.setTime(date);
					if(calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
						      && calendar1.get(Calendar.MONTH) == calendar2.get(Calendar.MONTH)
						      && calendar1.get(Calendar.DAY_OF_MONTH) == calendar2.get(Calendar.DAY_OF_MONTH))
					{
						int j=0;
						data[i][j]=date;
						data[i][++j]=fromCity; 
						data[i][++j]=targetCity;
						data[i][++j]=distane;
						data[i][++j]=price;
						data[i][++j]=member.getId();
						i++;
						check=true;
					}
				}
			}
				if(check)
				{
					jtable=new JTable(data,column);
					scrollPane_1 = new JScrollPane();
					scrollPane_1.setBounds(0, 0, 680, 294);
					panel.add(scrollPane_1);
					scrollPane = new JScrollPane();
					scrollPane_1.setViewportView(scrollPane);
					scrollPane.setViewportView(jtable);
				}
				else
					JOptionPane.showMessageDialog(contentPane,"There is no short deliveries in this date!!");
			data_AddManagers.reader.close();
			data_AddManagers.writer.close();
			}catch(IOException | ParseException r ){}
		catch(java.lang.NullPointerException m)
			{
			JOptionPane.showMessageDialog(contentPane,"You must fill the date");
			}
		}
	}
}
