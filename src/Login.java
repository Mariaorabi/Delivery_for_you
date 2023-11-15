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
import java.awt.Color;
public class Login extends JFrame implements ActionListener {
	private JPanel contentPane;
	private JTextField textField;
	private Main main;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnLogin;
	private JLabel lblNewLabel_1;
	public Login(Main main) {
		super("Login");
		this.main=main;
		main.setVisible(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 538, 418);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Username:");
		lblNewLabel.setBounds(76, 149, 138, 45);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(182, 109, 258, 61);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(78, 301, 110, 26);
		contentPane.add(lblPassword);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.setBackground(new Color(255, 228, 181));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(363, 24, 138, 21);
		contentPane.add(btnNewButton);

		btnLogin = new JButton("");
		btnLogin.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\logh.png"));
		btnLogin.setBackground(new Color(255, 105, 180));
		btnLogin.addActionListener(this) ;
		btnLogin.setBounds(450, 348, 40, 31);
		contentPane.add(btnLogin);

		passwordField = new JPasswordField();
		passwordField.setBounds(182, 255, 258, 61);
		contentPane.add(passwordField);


		lblNewLabel_1 = new JLabel("Login");
		lblNewLabel_1.setBounds(452, 326, 63, 21);
		contentPane.add(lblNewLabel_1);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("signin.jpg")));
		lIcon.setBounds(-692,-13,1334,646);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnLogin)
		{
			boolean check=false;
			try{
				if(DataBase.mangers.isEmpty())//If there isn't managers yet (if we don't put manager in the beginning)
				{
					throw new ArrayIsEmptyException();
				}
				if(textField.getText().isEmpty() ||  passwordField.getText().isEmpty())
				{
					throw new NullPointerException();
				}
				Manger manager = new Manger(textField.getText(), passwordField.getText());
				if(DataBase.mangers.contains(manager))//Check according to username and password
					check=true;
				if(check==false)
				{
					throw new AllException();
				}
				for(Manger m: DataBase.mangers)
				{
					if(m.equals(manager))
						manager=m;
				}
				JOptionPane.showMessageDialog(null,"Hello "+manager.getFirstName()+" "+manager.getLastName()+" manger!");
				main.setVisible(false);
				this.setVisible(false);
				new MenuForManager(manager,main).setVisible(true);
			}catch(NullPointerException a) {
				JOptionPane.showMessageDialog(null,"Sorry ,but you should fill all the fields!");
			}catch (ArrayIsEmptyException e2) {
				JOptionPane.showMessageDialog(null,"Sorry,but we haven't mangers yet! ");
			} catch (AllException e1) {
				JOptionPane.showMessageDialog(null,"Sorry, either the username or the password (or two of them) is/are uncorrect,you may try again");
				textField.setText("");
				passwordField.setText("");
			}
		}else if(e.getSource()==btnNewButton)//Close
		{
			setVisible(false);
			main.setVisible(true);
		}
	}		
}


