import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddMember extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private JButton btnClose;
	private Manger manger;
	private JComboBox<String> comboBox;
	private MenuForManager menuForManager;
	public AddMember(Manger manger,MenuForManager menuForManager) {
		super("Add member");
		this.manger=manger;
		this.menuForManager=menuForManager;
		menuForManager.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 341, 377);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(30, 144, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Area:");
		lblNewLabel.setBounds(2, 193, 69, 19);
		contentPane.add(lblNewLabel);

		JLabel lblFirstname = new JLabel("Firstname:");
		lblFirstname.setBounds(71, 83, 69, 13);
		contentPane.add(lblFirstname);

		JLabel lblLatname = new JLabel("Lastname:");
		lblLatname.setBounds(161, 83, 69, 13);
		contentPane.add(lblLatname);

		JLabel lblId = new JLabel("id:");
		lblId.setBounds(9, 131, 62, 13);
		contentPane.add(lblId);

		JLabel lblPhonenumber = new JLabel("Phone:");
		lblPhonenumber.setBounds(2, 154, 69, 24);
		contentPane.add(lblPhonenumber);

		textField = new JTextField();
		textField.setBounds(71, 97, 82, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(161, 97, 82, 24);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyChar()>='0'&& evt.getKeyChar()<='9') {//Check if the input include only numbers
					textField_2.setEditable(true);
				}else{ 
					if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE ||evt.getExtendedKeyCode()==KeyEvent.VK_DELETE ) {
						textField_2.setEditable(true);
					}
					else{
						textField_2.setEditable(false);
					}
				}}}
				);
		textField_2.setBounds(71, 126, 171, 24);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(71, 154, 171, 24);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(71, 186, 172, 32);
		contentPane.add(comboBox);
		comboBox.addItem("north");
		comboBox.addItem("center");
		comboBox.addItem("west");

		btnNewButton = new JButton("Add new member");
		btnNewButton.setBackground(new Color(0, 255, 255));
		btnNewButton.addActionListener(this); 

		btnNewButton.setBounds(76, 228, 154, 32);
		contentPane.add(btnNewButton);

		btnClose = new JButton("");
		btnClose.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnClose.setBackground(new Color(128, 0, 128));
		btnClose.addActionListener(this);
		btnClose.setBounds(161, 10, 156, 24);
		contentPane.add(btnClose);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("memberss.jpg")));
		lIcon.setBounds(-11,-63,892,474);
		getContentPane().add(lIcon);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton) {//Delete button
			try {
				if(textField.getText().isEmpty() || textField_1.getText().isEmpty() || textField_2.getText().isEmpty()  || textField_3.getText().isEmpty())
				{
					throw new NullPointerException();
				}
				if(DataBase.members.containsKey(textField_2.getText()))//Check if the id is exist
				{
					textField_2.setText("");
					throw new AllException(textField_2.getText());
				}
				Members member= new Members(textField.getText(),textField_1.getText(),textField_2.getText(), textField_3.getText(), (String)comboBox.getSelectedItem());
				if(!manger.addMembers(member))//Check if the member is in the same area as the manager
				{
					throw new AllException((String)comboBox.getSelectedItem(),manger.getLocation());
				}
				DataBase.members.put(textField_2.getText(),member);
				DataBase.addMember_ToFiles(manger,member);//add to suitable file
				JOptionPane.showMessageDialog(null, "The member was added successfully");
				textField.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
			}catch (AllException b) {
				JOptionPane.showMessageDialog(null,b.getMessage());
			}catch (NullPointerException e2) {
				JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
			}
		}
		if(e.getSource()==btnClose)//Close and back to the manager menu
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}

	}

}
