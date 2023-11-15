import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
public class LoginVice extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField textField;
	private Main main;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	public LoginVice(Main main) {
		super("Login");
		this.main=main;
		main.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 539, 416);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(55, 161, 69, 19);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(177, 106, 248, 63);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(51, 303, 85, 18);
		contentPane.add(lblPassword);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(375, 10, 140, 29);
		contentPane.add(btnNewButton);

		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\logh.png"));
		btnLogin.addActionListener(this) ;
		btnLogin.setBounds(450, 338, 38, 31);
		contentPane.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(177, 258, 248, 63);
		contentPane.add(passwordField);

		lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(452, 319, 63, 21);
		contentPane.add(lblNewLabel_1);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("signin.jpg")));
		lIcon.setBounds(-714,-15,1370,646);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin)
		{boolean check=false;
		try{
			if(DataBase.vice.isEmpty())//if there isn't vices yet
			{
				throw new ArrayIsEmptyException();
			}
			if(textField.getText().isEmpty() || passwordField.getText().isEmpty())
			{
				throw new NullPointerException();
			}
			Vice vice = new Vice(textField.getText(),passwordField.getText());
			if(DataBase.vice.contains(vice))//Check according to username and password
				check=true;
			if(check==false)
			{
				textField.setText("");
				passwordField.setText("");
				throw new AllException();
			}
			for(Vice v: DataBase.vice)
			{
				if(v.equals(vice))
					vice=v;
			}
			JOptionPane.showMessageDialog(null,"Hello "+vice.getFirstName()+" "+vice.getLastName()+" vice!");
			setVisible(false);
			main.setVisible(false);
			new MenuForVice(vice,main).setVisible(true);
		}catch(NullPointerException a) {
			JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
		}catch (ArrayIsEmptyException e2) {
			JOptionPane.showMessageDialog(null,"Sorry,but we haven't vices yet! ");
		} catch (AllException e1) {
			JOptionPane.showMessageDialog(null,"Sorry, either the username or the password (or two of them) is/are uncorrect,you may try again");
		}
		}else if(e.getSource()==btnNewButton)//Close
		{
			setVisible(false);
			main.setVisible(true);
		}
	}		
}

