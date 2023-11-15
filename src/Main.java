import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.*;
public class Main extends JFrame implements ActionListener{
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JLabel lblPleaseChoose;
	private JLabel lblPleaseChoose_2;
	private JButton btnNewButton_1_1;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataBase.getAllInFiles();//get all the data that have saved in the files
					Main main=new Main();
					main.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Main() {
		super("Menu");
		JOptionPane.showMessageDialog(null, "Hello,you are now visiting ----> Deliveries For You company");
		getContentPane().setLayout(null);
		btnNewButton_1 = new JButton("Login for mangers only");
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(21, 176, 176, 21);
		getContentPane().add(btnNewButton_1);

		btnNewButton_2 = new JButton("Close all the program");
		btnNewButton_2.setBackground(Color.RED);
		btnNewButton_2.addActionListener(this) ;
		btnNewButton_2.setBounds(21, 238, 176, 21);
		getContentPane().add(btnNewButton_2);

		lblPleaseChoose = new JLabel("Please choose one");
		lblPleaseChoose.setBounds(53, 106, 120, 29);
		lblPleaseChoose.setForeground(Color.red);
		getContentPane().add(lblPleaseChoose);

		setSize(494,345);
		this.setVisible(true);

		lblPleaseChoose_2 = new JLabel("Deliveries for you company");
		lblPleaseChoose_2.setForeground(Color.red);
		lblPleaseChoose_2.setBounds(188, 10, 243, 46);
		getContentPane().add(lblPleaseChoose_2);


		btnNewButton_1_1 = new JButton("Login for vices only");
		btnNewButton_1_1.addActionListener(this);
		btnNewButton_1_1.setBackground(Color.RED);
		btnNewButton_1_1.setBounds(21, 207, 176, 21);
		getContentPane().add(btnNewButton_1_1);
		JSpinner timeSpinner = new JSpinner( new SpinnerDateModel() );//Show the time when access the program
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm:ss");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.setValue(new Date()); //Will show the current time
		timeSpinner.setBounds(378,279,102,29);
		getContentPane().add(timeSpinner);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("icon.jpg")));
		lIcon.setBounds(-72,-12,563,320);
		getContentPane().add(lIcon);

	}
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==btnNewButton_1)//Login for managers only 
		{
			this.setVisible(false);
			new Login(this).setVisible(true);
		}
		else if(e.getSource()==btnNewButton_1_1)//Login for vice managers only 
		{
			this.setVisible(false);
			new LoginVice(this).setVisible(true);
		} 
		else if(e.getSource()==btnNewButton_2)//Close all the program
		{
			this.setVisible(false);
			JOptionPane.showMessageDialog(null, "Have a good day and bye bye");

		}
	}
}
