import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class AddVice extends JFrame implements ActionListener{
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_3;
	private JPasswordField passwordField;
	private JButton btnNewButton ;
	private JButton btnNewButton_1 ;
	private JLabel lblNewLabel_4_2;
	private JTextField textField_4;
	private MenuForManager menuForManager;
	private JComboBox<String> comboBox;
	public AddVice(MenuForManager menuForManager) 
	{
		super("Add new vice to the company");
		this.menuForManager=menuForManager;
		menuForManager.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 271);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);


		btnNewButton= new JButton("Add");
		btnNewButton.setBackground(new Color(255, 255, 255));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(268, 28, 83, 21);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(20, 43, 83, 13);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Last name");
		lblNewLabel_2.setBounds(20, 66, 83, 21);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Area");
		lblNewLabel_3.setBounds(20, 97, 45, 13);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Username");
		lblNewLabel_4.setBounds(20, 120, 71, 21);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_4_1 = new JLabel("Password");
		lblNewLabel_4_1.setBounds(20, 151, 71, 21);
		contentPane.add(lblNewLabel_4_1);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(102, 97, 96, 20);
		contentPane.add(comboBox);
		comboBox.addItem("north");
		comboBox.addItem("center");
		comboBox.addItem("west");
		textField = new JTextField();
		textField.setBounds(102, 40, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(102, 67, 96, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(102, 126, 96, 19);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(102, 155, 96, 17);
		contentPane.add(passwordField);
		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(20, 10, 139, 23);
		contentPane.add(btnNewButton_1);

		lblNewLabel_4_2 = new JLabel("How many delivery he can add?");
		lblNewLabel_4_2.setBounds(20, 182, 225, 21);
		contentPane.add(lblNewLabel_4_2);

		textField_4 = new JTextField();
		textField_4.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyChar()>='0'&& evt.getKeyChar()<='9') {//Check if number
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
		textField_4.setColumns(10);
		textField_4.setBounds(255, 183, 96, 19);
		contentPane.add(textField_4);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("phj.jpg")));
		lIcon.setBounds(0,-33,544,327);
		getContentPane().add(lIcon);

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton)//Add vice
		{
			try {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_3.getText().isEmpty() ||passwordField.getText().isEmpty() || textField_4.getText().isEmpty())
				{
					throw new NullPointerException();
				}

				Vice vice=new Vice(textField.getText(),textField_1.getText(),textField_3.getText(),passwordField.getText(),(String)comboBox.getSelectedItem(),Integer.parseInt(textField_4.getText()));
				if(!DataBase.vice.isEmpty()){
					if(DataBase.vice.contains(vice)){//if there is another vice in the same username
						{
							textField_3.setText("");
							throw new AllException();
						}
					}
				}
				DataBase.vice.add(vice);
				DataBase.addVice_ToFiles(vice);//add to suitable file
				JOptionPane.showMessageDialog(null,"Vice added successfully");
				textField.setText("");
				textField_1.setText("");
				textField_3.setText("");
				passwordField.setText("");
				textField_4.setText("");
			} catch (AllException e1) {
				JOptionPane.showMessageDialog(getContentPane(),"Sorry but this username already used!,You may try again");
			}catch(NullPointerException a) {
				JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
			}catch(java.lang.NumberFormatException s)
			{
				JOptionPane.showMessageDialog(getContentPane(),"Sorry but you should enter a number in ->How many delivery he can add? ");
				textField_4.setText(" ");
			}
		}
		else if(e.getSource()==btnNewButton_1)//Close button
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
	}

}

