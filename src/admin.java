import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class admin extends user {

	private JPanel contentPane;

	
	public admin(String name) {
		super(name);
		setTitle("Admin Menu - User: " + name);
		
	}

}
