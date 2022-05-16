import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class user extends JFrame{
	private String name;
	private String houseNo;
	private String postcode;
	private String city;
	private JPanel contentPane;
	protected JTable productTable;
	protected JPanel productPanel;
	protected DefaultTableModel dtmItems;
	protected  ArrayList<Item> inventory;
	public user(String name, String houseNo, String postcode, String city) {
		this.name = name;
		this.houseNo = houseNo;
		this.postcode = postcode;
		this.city = city;

		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
	}
	
	protected ArrayList<Item> sortByAsc(ArrayList<Item> inventory) { //a function that is shared by admin and customer to order items in a jtable based on their retail prices
		int x;
		int y;
		for (x = 0; x < inventory.size(); x++) { //a simple bubble sort algorithm used to compare each of the arrayList items and place them in ascending order
			for (y = x + 1; y < inventory.size(); y++) {
				if (inventory.get(y).getRetailPrice() < inventory.get(x).getRetailPrice()) {
					Item temp = inventory.get(y); //swapping of the items
					inventory.set(y, inventory.get(x));
					inventory.set(x, temp);
					
				}
			}
		}
		return inventory;
	}
	
	
			
			
			
		
		
		
	
}
