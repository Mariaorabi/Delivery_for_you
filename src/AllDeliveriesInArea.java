import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;

public class AllDeliveriesInArea extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JComboBox<String> comboBox;
	private JButton btnNewButton;
	private JButton btnNewButton_1;
	private JTextArea textArea;
	private MenuForManager menuForManager;
	private JScrollPane scrollPane;
	private JButton btnNewButton_2;
	public AllDeliveriesInArea(MenuForManager menuForManager) {
		super("All the deliveries in a choosen area");
		this.menuForManager=menuForManager;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 585, 375);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(106, 90, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Choose area:");
		lblNewLabel.setBounds(10, 85, 118, 27);
		contentPane.add(lblNewLabel);
		comboBox = new JComboBox<String>();
		comboBox.setBounds(116, 86, 103, 24);
		comboBox.addItem("north");
		comboBox.addItem("center");
		comboBox.addItem("west");
		contentPane.add(comboBox);

		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.setBounds(408, 25, 153, 27);
		btnNewButton.addActionListener(this);
		contentPane.add(btnNewButton);

		btnNewButton_1 = new JButton("Show");
		btnNewButton_1.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\eyes.png"));
		btnNewButton_1.setBounds(263, 85, 126, 27);
		btnNewButton_1.addActionListener(this);
		contentPane.add(btnNewButton_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(130, 157, 315, 147);
		contentPane.add(scrollPane);

		btnNewButton_2 = new JButton("Clear");
		btnNewButton_2.addActionListener(this);
		btnNewButton_2.setBounds(214, 120, 103, 27);
		contentPane.add(btnNewButton_2);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		JLabel lIcon =new JLabel("");
		lIcon.setIcon(new ImageIcon(Main.class.getResource("effel.jpg")));
		lIcon.setBounds(-772,10,1378,619);
		getContentPane().add(lIcon);


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNewButton_1)//Show button
		{
			try{
				int counter = 0;
				for(Map.Entry<Integer,Delivery> entry1:DataBase.ourMembersAndDeluveries2.entrySet())
				{
					if(entry1.getValue().getMember().getLocation().equals(comboBox.getSelectedItem()))
					{
						textArea.append(entry1.getValue().toString()+"\n");//Print the to string of the delivery if it is in the choosen area
						counter++;
					}
				}
				if(counter == 0)
					throw new ArrayIsEmptyException();
			}catch (ArrayIsEmptyException m) {
				JOptionPane.showMessageDialog(null, "No deliveries!");
			}
		}
		else if(e.getSource()==btnNewButton_2)//Clear
		{
			if(textArea.getLineCount()>0)
			{
				textArea.setText("");
			}
		}
		else if(e.getSource()==btnNewButton)//Close button
		{
			setVisible(false);
			menuForManager.setVisible(true);

		}
	}

}
