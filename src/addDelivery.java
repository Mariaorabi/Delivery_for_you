import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class addDelivery extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox_1;
	private JComboBox<String> comboBox;
	private Manger manger;
	private JButton btnNewButton;
	private JButton btnClose;
	private MenuForManager menuForManager;
	public addDelivery(Manger manger,MenuForManager menuForManager) {
		super("Add delivery"); 
		this.manger=manger;
		this.menuForManager=menuForManager;
		menuForManager.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 380, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(155, 95, 96, 19);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(155, 135, 96, 19);
		contentPane.add(textField_2);

		JLabel lblNewLabel = new JLabel("Member id:");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setBounds(10, 52, 64, 19);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("From city:");
		lblNewLabel_1.setForeground(new Color(218, 112, 214));
		lblNewLabel_1.setBounds(10, 95, 135, 19);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("To city:");
		lblNewLabel_2.setForeground(new Color(75, 0, 130));
		lblNewLabel_2.setBounds(10, 135, 118, 19);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Type:");
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setBounds(10, 180, 106, 19);
		contentPane.add(lblNewLabel_3);

		comboBox = new JComboBox<String>();
		comboBox.setBounds(155, 51, 96, 21);
		contentPane.add(comboBox);
		for(Members member:manger.getMembers().values())//Add all the manager's id members  
		{
			comboBox.addItem(member.getId());
		}

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setBounds(155, 176, 96, 21);
		comboBox_1.addItem("Normal");
		comboBox_1.addItem("Express");
		comboBox_1.addItem("Busniess");
		contentPane.add(comboBox_1);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\++4.png"));
		btnNewButton.setBackground(new Color(255, 182, 193));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(169, 223, 31, 21);
		contentPane.add(btnNewButton);

		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnClose.setBackground(new Color(0, 0, 128));
		btnClose.addActionListener(this);
		btnClose.setBounds(208, 10, 148, 19);
		contentPane.add(btnClose);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("photo1.jpg")));
		lIcon.setBounds(0,-57,425,397);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Add button
		{
			String id=(String)comboBox.getSelectedItem();//Get the id
			String type=(String)comboBox_1.getItemAt(comboBox_1.getSelectedIndex());//Get the type
			try{
				if(textField_2.getText().isEmpty() || textField_1.getText().isEmpty())
					throw new NullPointerException();
				Members member=manger.getMembers().get(id);
				if(type.equals("Normal"))
				{
					String number=JOptionPane.showInputDialog(getContentPane(),"Enter the number of items");
					NormalDelivery normal=new NormalDelivery(member, textField_1.getText(), textField_2.getText(),DataBase.countTree, Integer.parseInt(number));
					addD(normal, member,this.manger);
				}
				else if(type.equals("Express"))
				{
					String number=JOptionPane.showInputDialog(getContentPane(),"Enter the number of items");
					ExpressDelivery express=new ExpressDelivery(member, textField_1.getText(), textField_2.getText(),DataBase.countTree, Integer.parseInt(number));
					addD(express, member,this.manger);
				}
				else if(type.equals("Busniess"))
				{
					String number=JOptionPane.showInputDialog(getContentPane(),"Enter the number of items");
					BusinessDelivery busniess=new BusinessDelivery(member, textField_1.getText(), textField_2.getText(),DataBase.countTree,Integer.parseInt(number) );
					addD(busniess,member,this.manger);
				}
				textField_1.setText("");
				textField_2.setText("");
			}catch(java.lang.NumberFormatException r)//If put String not number
			{
				JOptionPane.showMessageDialog(getContentPane(),"Sorry,You have to enter a number!!");
			}catch(NullPointerException a) {
				JOptionPane.showMessageDialog(getContentPane(),"Sorry ,but you should fill all the fields!");
			}
		}
		if(e.getSource()==btnClose)
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
	}
	/*This function add the delivery the dataBase*/
	public void addD(Delivery d,Members member ,Manger manger)
	{
		DataBase.members.get(member.getId()).getDelivery().add(d);
		DataBase.ourLastDeliveries.put(member, d);
		DataBase.ourMembersAndDeluveries1.put(DataBase.countTree, member);
		DataBase.ourMembersAndDeluveries2.put(DataBase.countTree,d);
		DataBase.countTree+=5;
		DataBase.addGeneralDelivery_ToFiles(manger,d);//add to suitable file
		DataBase.addAlldeliveries_ToFiles(null,member,d);//add to suitable file
		JOptionPane.showMessageDialog(null,"The delivery was added successfully");

	}
}

