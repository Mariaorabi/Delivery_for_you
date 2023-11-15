import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Color;

public class SignUp extends JFrame implements ActionListener{

	private JPanel contentPane; 
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JButton btnNewButton ;
	private JButton btnNewButton_1 ;
	private MenuForManager menuManger;
	private JComboBox<String> comboBox;
	public SignUp(MenuForManager menuManger) 
	{
		super("Signup for manager");
		this.menuManger=menuManger;
		setVisible(true);
		menuManger.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 680, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnNewButton = new JButton("Signup ");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\now.png"));
		btnNewButton.setForeground(new Color(250, 128, 114));
		btnNewButton.setBackground(new Color(128, 0, 128));


		btnNewButton.setBounds(510, 21, 108, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setForeground(new Color(255, 192, 203));
		lblNewLabel_1.setBounds(112, 91, 83, 28);
		contentPane.add(lblNewLabel_1);
		JLabel lblNewLabel_2 = new JLabel("Last name");
		lblNewLabel_2.setForeground(new Color(220, 20, 60));
		lblNewLabel_2.setBounds(112, 181, 83, 31);
		contentPane.add(lblNewLabel_2);
		btnNewButton_1 = new JButton(">Back");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton_1.setBackground(new Color(238, 130, 238));

		JLabel lblNewLabel_3 = new JLabel("Area");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setBounds(112, 254, 83, 25);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Username");
		lblNewLabel_4.setForeground(new Color(255, 165, 0));
		lblNewLabel_4.setBounds(112, 342, 83, 21);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Password");
		lblNewLabel_4_1.setForeground(new Color(255, 255, 0));
		lblNewLabel_4_1.setBounds(112, 419, 83, 21);
		contentPane.add(lblNewLabel_4_1);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(205, 248, 219, 42);
		contentPane.add(comboBox);
		comboBox.addItem("north");
		comboBox.addItem("center");
		comboBox.addItem("west");

		textField = new JTextField();
		textField.setBounds(205, 89, 219, 42);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(205, 172, 219, 42);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(205, 336, 219, 43);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(205, 405, 219, 49);
		contentPane.add(passwordField);
		btnNewButton.addActionListener(this);
		btnNewButton_1.addActionListener(this);

		btnNewButton_1.setBounds(10, 21, 133, 21);
		contentPane.add(btnNewButton_1);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("si.jpg")));
		lIcon.setBounds(-21,-35,687,659);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Signup
		{
			try {
				if(textField.getText().isEmpty() || passwordField.getText().isEmpty())
				{
					throw new NullPointerException();
				}
				if(!DataBase.mangers.isEmpty()){
					if(DataBase.checkManger(textField_3.getText())==false){//if there is another manger in the same username
						textField_3.setText("");
						throw new AllException();
					}
				}
				Manger m=new Manger(textField.getText(),textField_1.getText(),textField_3.getText(),passwordField.getText(),(String)comboBox.getSelectedItem());
				DataBase.mangers.add(m);
				DataBase.addManager_ToFiles(m);
				JOptionPane.showMessageDialog(null,"So you have signed up and become one of our company family,So welcome any time!");
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				passwordField.setText("");
			}catch(NullPointerException a) {
				JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
			}catch (AllException e2) {
				JOptionPane.showMessageDialog(null,"Sorry but this username already used!,You may try again");
			}
		}
		else if(e.getSource()==btnNewButton_1)//Close
		{
			setVisible(false);
			menuManger.setVisible(true);

		}
	}
}
