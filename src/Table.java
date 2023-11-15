import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;

public class Table extends JFrame implements ActionListener{
	private JPanel contentPane;
	private MenuForManager menuForManager;
	private JButton btnNewButton;
	private JPanel panel;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	public Table(MenuForManager menuForManager) {
		super("All short deliveries in the company");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 741, 462);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setVisible(true);
		setContentPane(contentPane);
		this.menuForManager=menuForManager; 
		panel = new JPanel();
		panel.setBounds(37, 40, 680, 294);
		contentPane.add(panel);
		menuForManager.setVisible(false);
		Integer size=0;
		for(Vice vice:DataBase.vice)//the size of the short deliveries according to the vices short deliveries sizes
		{
			size+=vice.getShortDelivery().size();
		}
		Object[][] data=new Object[size][6];
		int i=0;
		for(Vice vice:DataBase.vice)
		{
			for(ShortDelivery d:vice.getShortDelivery()) {
				int j=0;
				data[i][j]=d.getDate();
				data[i][++j]=d.getFromCity(); 
				data[i][++j]=d.getTargetCity();
				data[i][++j]=d.getDistane();
				data[i][++j]=d.getPrice();
				data[i][++j]=d.getMember().getId();
				i++;
			}}
		String column[]={"date","fromCity","targetCity","distane","price","member id"};
		btnNewButton = new JButton("");
		btnNewButton.setBounds(22, 359, 138, 21);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Smart\\Downloads\\bt1.png"));
		btnNewButton.addActionListener(this);
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		panel.setLayout(null);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 680, 294);
		panel.add(scrollPane_1);
		
		scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		JTable jtable=new JTable(data,column);
		scrollPane.setViewportView(jtable);
		
		
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
	