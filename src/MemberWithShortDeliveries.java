import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class MemberWithShortDeliveries extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private MenuForManager menuForManager;
	private JScrollPane scrollPane;
	public MemberWithShortDeliveries(MenuForManager menuForManager) {
		super("All the members that have short deliveries");
		this.menuForManager=menuForManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 497, 365);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(58, 114, 343, 167);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(303, 28, 147, 24);
		contentPane.add(btnNewButton);
		int counter=0;
		int counterMembers=0;
		try{
			for(Members m:DataBase.members.values())//All the member in the company
			{
				counter=0;
				for(Delivery d:m.getDelivery())//check if the member has short deliveries
				{
					if(d instanceof ShortDelivery)
					{
						counter++;
					}
					if(counter==1)
					{
						textArea.append("First name: "+m.getFirstName()+"Id: "+m.getId()+"Phone number: "
								+m.getPhoneNumber()+"Area: "+m.getLocation()+"\n");//Print the member
					}
					counterMembers++;
				}

			}
			if(counterMembers==0)
				throw new ArrayIsEmptyException();
		}catch (ArrayIsEmptyException e) {
			JOptionPane.showMessageDialog(contentPane,"Sorry,but there is no member that has short delivries yet!"
					+"\n So we will close this window");
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("members.jpg")));
		lIcon.setBounds(-56,-46,600,426);
		getContentPane().add(lIcon);
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
