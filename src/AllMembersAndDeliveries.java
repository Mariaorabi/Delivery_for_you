import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class AllMembersAndDeliveries extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextArea textArea;
	private MenuForManager menuForManager;
	private JScrollPane scrollPane;
	public AllMembersAndDeliveries(MenuForManager menuForManager) {
		super("All the members and thier deliveries ");
		this.menuForManager=menuForManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 309);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 0, 255));
		contentPane.setForeground(new Color(128, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(320, 10, 155, 36);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		btnNewButton_1.setBackground(Color.CYAN);
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setBounds(187, 58, 64, 16);
		contentPane.add(btnNewButton_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 84, 456, 178);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton_1)
		{
			try{
				Integer counter=0;
				for(Manger m:DataBase.mangers)
				{
					for (Map.Entry<String, Members> m1 :m.getMembers().entrySet())
					{
						textArea.append(m1.getValue().toString1()+"\n");//print just the member
						counter++;
						if (m1.getValue().getDelivery().isEmpty())
						{
							textArea.append("This member doesn't has any deliveries \n");
						}
						else
						{
							textArea.append("His deliveries"+m1.getValue().getDelivery()+"\n");//print all the member's delivery
						}
					}

				}
				if(counter==0)
					throw new ArrayIsEmptyException();
			}catch (ArrayIsEmptyException e2) {
				JOptionPane.showMessageDialog(null,"Sorry, but we haven't any members yet!");

			}
		}else if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
	}
}