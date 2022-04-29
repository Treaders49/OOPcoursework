import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class user extends JFrame{
	private String name;
	private String houseNo;
	private String postcode;
	private String city;
	private JPanel contentPane;
	
	public user(String name, String houseNo, String postcode, String city) {
		this.name = name;
		this.houseNo = houseNo;
		this.postcode = postcode;
		this.city = city;

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1050, 600);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
}
