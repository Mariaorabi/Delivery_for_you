import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.Date;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;

public class CitiesWithShortDeliveries extends JFrame implements ActionListener {

	private JPanel contentPane;
	private MenuForManager menuForManager;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private AbstractButton btnNewButton_1;
	public CitiesWithShortDeliveries(MenuForManager menuForManager) {
		super("All the cities that have send to them deliveries in the last 30 day");
		this.menuForManager=menuForManager;
		menuForManager.setVisible(false);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 312);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(305, 10, 148, 21);
		contentPane.add(btnNewButton);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(149, 94, 279, 122);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(new Color(221, 160, 221));


		btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		btnNewButton_1.setForeground(Color.ORANGE);
		btnNewButton_1.setBounds(246, 63, 42, 21);
		contentPane.add(btnNewButton_1);
		JLabel lblNewLabel = new JLabel("Show");
		lblNewLabel.setBounds(253, 47, 79, 13);
		contentPane.add(lblNewLabel);

		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("word.jpg")));
		lIcon.setBounds(-410,-177,931,480);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton_1)//Show the city 
		{
			Date now=java.util.Calendar.getInstance().getTime();
			Integer daysdiff = 0;
			for(Vice v:DataBase.vice)
			{
				for(ShortDelivery shortDelivery:v.getShortDelivery())
				{
					long res = (now.getTime()) -(shortDelivery.getDate().getTime());
					long diffDays = res / (24 * 60 * 60 * 1000);
					daysdiff = (int) diffDays;
					if(daysdiff>0 && daysdiff<=30)
					{
						textArea.append(shortDelivery.getTargetCity()+"\n");
					}
				}
			}
			if(textArea.getText().trim().equals(""))//If there isn't city like this
			{
				JOptionPane.showMessageDialog(null, "There is no cities in the last 30 days with short deliveries!");
				this.setVisible(false);
				menuForManager.setVisible(true);
			}
		}else if(e.getSource()==btnNewButton)//Close
		{
			this.setVisible(false);
			menuForManager.setVisible(true);
		}
	}
}

