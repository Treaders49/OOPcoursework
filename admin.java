import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class admin extends user {

	private JPanel contentPane;

	
	public admin(String name, String houseNum, String postcode, String city) {
		super(name, houseNum, postcode, city);
		setTitle("Admin Menu - User: " + name);
		
	}

}
