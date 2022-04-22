import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class customer extends user {

	private JPanel contentPane;
	private JTable productTable;

	
	public customer(String name) {
		super(name);
		setTitle("Customer Menu - User: " + name);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 864, 539);
		getContentPane().add(tabbedPane);
		
		JPanel productPanel = new JPanel();
		tabbedPane.addTab("View Items", null, productPanel, null);
		
		productTable = new JTable();
		productPanel.add(productTable);
		populateTable(productTable);
		
		JPanel basketPanel = new JPanel();
		tabbedPane.addTab("View Basket", null, basketPanel, null);
		
		JPanel paymentPanel = new JPanel();
		tabbedPane.addTab("Payment", null, paymentPanel, null);
		
	}


	private void populateTable(JTable table) {
		// TODO Auto-generated method stub
		
	}
}
