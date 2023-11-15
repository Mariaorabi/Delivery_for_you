import java.awt.event.ActionListener;
import java.util.Map;
import java.util.Map.Entry;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.Color;

public class LastDeliveries extends JFrame implements ActionListener{

	private JPanel contentPane;
	private JButton btnNewButton;
	private JButton btnChoose;
	private JComboBox<String> comboBox;
	private JTextArea textArea;
	private MenuForManager menuForManger;
	private JLabel lblDeliveryCode;
	private JComboBox<Integer> comboBox_1;
	private JScrollPane scrollPane;
	public LastDeliveries(MenuForManager menuForManger) {
		super("Show all last deliveries");
		this.menuForManger=menuForManger;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 963, 508);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("All last deliveries");
		lblNewLabel.setBounds(10, 54, 112, 32);
		contentPane.add(lblNewLabel);

		comboBox = new JComboBox<String>();
		comboBox.setBackground(new Color(160, 82, 45));
		comboBox.setBounds(124, 48, 800, 65);
		contentPane.add(comboBox);
		for(Entry<Integer, Delivery> entry1:DataBase.ourMembersAndDeluveries2.entrySet())
		{
			if(DataBase.ourLastDeliveries.containsValue(entry1.getValue()))
			{
				comboBox.addItem("Code: "+entry1.getKey()+" "+entry1.getValue().toString());//Show all the last deliveries and there codes
			}
		}
		btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this) ;
		btnNewButton.setBounds(512, 10, 150, 28);
		contentPane.add(btnNewButton);

		btnChoose = new JButton("Choose");
		btnChoose.addActionListener(this);
		btnChoose.setBounds(254, 123, 85, 21);
		contentPane.add(btnChoose);

		lblDeliveryCode = new JLabel("Delivery code:");
		lblDeliveryCode.setBounds(10, 120, 118, 27);
		contentPane.add(lblDeliveryCode);

		comboBox_1 = new JComboBox<Integer>();
		comboBox_1.setBackground(new Color(160, 82, 45));
		comboBox_1.setBounds(124, 123, 102, 21);
		contentPane.add(comboBox_1);
		for(Entry<Integer, Delivery> entry1:DataBase.ourMembersAndDeluveries2.entrySet())
		{
			if(DataBase.ourLastDeliveries.containsValue(entry1.getValue()))
			{
				comboBox_1.addItem(entry1.getKey());//Add the codes
			}
		}
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 176, 443, 204);
		contentPane.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		JLabel lIcon =new JLabel("");
		lIcon.setBounds(-58, -71, 1459, 595);
		contentPane.add(lIcon);
		lIcon.setIcon(new ImageIcon(Main.class.getResource("last.jpg")));

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Integer code=(Integer) comboBox_1.getSelectedItem();//get the choosen code
		if(e.getSource()==btnChoose)//Choose button
		{
			textArea.setText(" ");
			for(Map.Entry<Integer,Members> entry1:DataBase.ourMembersAndDeluveries1.entrySet())
			{
				if(entry1.getKey().equals(code))
				{
					textArea.append("Delivery code: "+Integer.toString(code)+"\n");
					textArea.append(" "+entry1.getValue().toString1()+"\n");//member
					textArea.append(DataBase.ourLastDeliveries.get(entry1.getValue()).toString()+"\n");//lastdelivery
					if(DataBase.ourLastDeliveries.get(entry1.getValue()) instanceof ShortDelivery)
					{
						for(Vice vice:DataBase.vice)
						{
							if(vice.getShortDelivery().contains(DataBase.ourLastDeliveries.get(entry1.getValue())))
								textArea.append(vice.toString()+"\n");//vice
						}
					}
					else
					{
						for(Manger m:DataBase.mangers)
						{
							if(m.getMembers().containsKey(entry1.getValue().getId()))
								textArea.append(m.toString1()+"\n");//manger
						}
					}

				}
			}
		}
		if(e.getSource()==btnNewButton)//close
		{
			this.setVisible(false);
			menuForManger.setVisible(true);
		}

	}	
}
