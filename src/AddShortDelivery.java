import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddShortDelivery extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnNewButton;
	private Vice vice;
	private JDateChooser dateChooser;
	private JButton btnNewButton_1;
	private MenuForVice menuForVice;
	private JLabel lblMemberId;
	private JComboBox<String> comboBox;
	private JTextField textField;
	private JLabel lblFromCity;
	private JLabel lblTargetCity;
	private JTextField textField_1;
	public AddShortDelivery(Vice vice, MenuForVice menuForVice) {
		super("Add short delivery");
		this.vice=vice;
		this.menuForVice=menuForVice;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDeliveryDate = new JLabel("Delivery date :");
		lblDeliveryDate.setBounds(10, 139, 113, 21);
		contentPane.add(lblDeliveryDate);

		JLabel lblDistancekm = new JLabel("Distance:");
		lblDistancekm.setBounds(10, 170, 113, 21);
		contentPane.add(lblDistancekm);

		textField_3 = new JTextField();
		textField_3.setBackground(new Color(255, 140, 0));
		textField_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyChar()>='0'&& evt.getKeyChar()<='9') {//Check if tge input include number
					textField_3.setEditable(true);
				}else{ 
					if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ) {
						textField_3.setEditable(true);
					}
					else{
						textField_3.setEditable(false);
					}
				}}}
				);
		textField_3.setColumns(10);
		textField_3.setBounds(133, 171, 96, 19);
		contentPane.add(textField_3);

		btnNewButton_1 = new JButton("Close");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton_1.setBackground(new Color(30, 144, 255));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(299, 10, 127, 21);
		contentPane.add(btnNewButton_1);

		JLabel lblPrice = new JLabel("Price per km:");
		lblPrice.setBounds(10, 201, 113, 21);
		contentPane.add(lblPrice);

		textField_4 = new JTextField();
		textField_4.setBackground(new Color(255, 165, 0));
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyChar()>='0'&& evt.getKeyChar()<='9') {
					textField_4.setEditable(true);
				}else{ 
					if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ) {
						textField_4.setEditable(true);
					}
					else{
						textField_4.setEditable(false);
					}
				}}}
				);
		textField_4.setColumns(10);
		textField_4.setBounds(133, 202, 96, 19);
		contentPane.add(textField_4);
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\+.png"));
		btnNewButton.setBackground(new Color(46, 139, 87));
		btnNewButton.addActionListener(this); 
		btnNewButton.setBounds(273, 221, 52, 32);
		contentPane.add(btnNewButton);

		dateChooser = new JDateChooser();//actionlisener
		dateChooser.setBackground(new Color(218, 165, 32));
		dateChooser.setForeground(new Color(238, 232, 170));
		dateChooser.getCalendarButton().addActionListener(this);
		dateChooser.setBounds(133, 139, 96, 21);
		contentPane.add(dateChooser);

		lblMemberId = new JLabel("Member id:");
		lblMemberId.setBounds(10, 44, 113, 21);
		contentPane.add(lblMemberId);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(240, 230, 140));
		comboBox.setBounds(133, 44, 96, 21);
		contentPane.add(comboBox);

		textField = new JTextField();
		textField.setBackground(new Color(255, 140, 0));
		textField.setColumns(10);
		textField.setBounds(133, 75, 96, 19);
		contentPane.add(textField);

		lblFromCity = new JLabel("From city:");
		lblFromCity.setBounds(10, 74, 113, 21);
		contentPane.add(lblFromCity);

		lblTargetCity = new JLabel("Target city:");
		lblTargetCity.setBounds(10, 105, 113, 21);
		contentPane.add(lblTargetCity);

		textField_1 = new JTextField();
		textField_1.setBackground(new Color(255, 215, 0));
		textField_1.setColumns(10);
		textField_1.setBounds(133, 104, 96, 19);
		contentPane.add(textField_1);
		for(Members m:DataBase.members.values())
		{
			comboBox.addItem(m.getId());
		}
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("parcel-delivery-company-workers-preparing-deliver-36633293.jpg")));
		lIcon.setBounds(-222,-49,680,312);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)
		{
			try{
				if(textField.getText().isEmpty() ||textField_1.getText().isEmpty() ||textField_3.getText().isEmpty() || textField_4.getText().isEmpty() ||dateChooser.getDate()==null)
					throw new NullPointerException();
				if((Double.parseDouble(textField_3.getText()))>30)//If the distance is more than 30km
				{
					throw new AllException((Integer.parseInt(textField_3.getText())));
				}
				if(vice.getNumOfDeliveryHeCan()==vice.getShortDelivery().size())//If the vice reach the maximum number of short deliveries that he can manage
					throw new ArrayIsEmptyException();
				Members member=null;
				member=DataBase.members.get((String)comboBox.getSelectedItem());//return the selected member according to id
				String fromCity=textField.getText();
				String targetCity=textField_1.getText();
				Date date= dateChooser.getDate();
				Integer distane=Integer.parseInt(textField_3.getText());
				Double price=Double.parseDouble(textField_4.getText());
				ShortDelivery shortD=new ShortDelivery(member,fromCity,targetCity,DataBase.countTree,date,distane,price);//counterTree code for this type of delivery
				vice.getShortDelivery().add(shortD);
				DataBase.ourLastDeliveries.put(member, shortD);
				DataBase.ourMembersAndDeluveries1.put(DataBase.countTree, member);
				DataBase.ourMembersAndDeluveries2.put(DataBase.countTree, shortD);
				DataBase.members.get(member.getId()).getDelivery().add(shortD);
				DataBase.countTree+=5;
				DataBase.addAlldeliveries_ToFiles(vice,member,shortD);//add to suitable file
				DataBase.addShortDelivery_ToFiles(vice,member,shortD);//add to suitable file
				JOptionPane.showMessageDialog(null,"The short delivery added successfully");
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				textField_4.setText("");
			}catch(AllException m){
				JOptionPane.showMessageDialog(null,m.getMessage());
				textField_3.setText("");
			}catch(NullPointerException m)
			{
				JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
			} catch (ArrayIsEmptyException e1) {
				this.setVisible(false);
				menuForVice.setVisible(true);
				JOptionPane.showMessageDialog(null,"This vice reached the maximum number of short deliveries "+"!!"
						+ "So we can't add this delivery or more.");
			}
		}
		else if(e.getSource()==btnNewButton_1)//Close and back to the vice menu
		{
			this.setVisible(false);
			menuForVice.setVisible(true);
		}

	}
}
