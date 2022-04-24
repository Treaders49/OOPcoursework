import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class customer extends user {

	private JPanel contentPane;
	private JTable productTable;
	private DefaultTableModel dtmItems;
	private HashMap<Item, Integer> basket;
	
	public customer(String name) {
		super(name);
		setTitle("Customer Menu - User: " + name);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1014, 539);
		getContentPane().add(tabbedPane);
		
		JPanel productPanel = new JPanel();
		tabbedPane.addTab("View Items", null, productPanel, null);
		productPanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 850, 495);
		productPanel.add(scrollPane);
		
		productTable = new JTable();
		scrollPane.setViewportView(productTable);
		
		populateTable(productPanel);
		
		JPanel basketPanel = new JPanel();
		tabbedPane.addTab("View Basket", null, basketPanel, null);
		
		JPanel paymentPanel = new JPanel();
		tabbedPane.addTab("Payment", null, paymentPanel, null);
		
		basket = new HashMap<Item, Integer>();
		
	}


	private void populateTable(JPanel productPanel) {
		ArrayList<Item> tableArrayList = new ArrayList<Item>();
		try {
		      File f = new File("Stock.txt");
		      Scanner myReader = new Scanner(f);
		      while (myReader.hasNextLine()) {
		        String row = myReader.nextLine();
		        String[] rowData = row.split(", ");
		        System.out.println(rowData[1]);
		        if (rowData[1].equals("mouse")) {
		        	mouse m = new mouse(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), Integer.parseInt(rowData[9]));
		        	tableArrayList.add(m);
		        } else {
		        	keyboard k = new keyboard(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), rowData[9]);
		        	tableArrayList.add(k);
		        }
		      }
		      System.out.println(tableArrayList);
		      myReader.close();
		    } catch (FileNotFoundException e) {
			   System.out.println("An error occurred.");
			   e.printStackTrace();
			}
		dtmItems = new DefaultTableModel();
		dtmItems.setColumnIdentifiers(new Object[] {"Barcode" , "Device Type", "Style", "Brand", "Colour", "Connectivity", "Layout", "No. Buttons", "Quantity", "Price"});
		productTable.setModel(dtmItems);
		int x = 25; //counting the x axis for the value of the button placement incrementing by 18 each time
		for (Item i : tableArrayList) {
			if (i.getDeviceType().equals("mouse")) {
				mouse m = (mouse) i;
				dtmItems.addRow(new Object[] {m.getBarcode(), m.getDeviceType(), m.getStyle(), m.getBrand(), m.getColour(), m.getConnectivity(), "N/A", m.getNumberOfButtons(), m.getQuantity(), String.format("%.2f",m.getRetailPrice())});
				addButton(productPanel, m, x);
			
			} else {
				keyboard k = (keyboard) i;
				dtmItems.addRow(new Object[] {k.getBarcode(), k.getDeviceType(), k.getStyle(), k.getBrand(), k.getColour(), k.getConnectivity(), k.getLayout(), "N/A", k.getQuantity(), String.format("%.2f",k.getRetailPrice())});
				addButton(productPanel, k, x);
			}
			x+= 16;
		}
	}
	
	private void addButton(JPanel productPanel, Item i, int x) {
		JButton purchaseButton = i.getAddItemButton();
		purchaseButton.setBounds(860, x, 125, 18);
		purchaseButton.setVisible(true);
		productPanel.add(purchaseButton);
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter how many of this product you would like to purchase:", null));
					if (quantity < 0) {
						JOptionPane.showMessageDialog(null, "Please enter a value which is more than zero");
					
					}else if (quantity > i.getQuantity()) {
						JOptionPane.showMessageDialog(null, "The number you have entered exceeds the stock amount");
					} else {
						basket.put(i, quantity);
					}
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter a value which is a number");
				}
			}
		});
	}
}
