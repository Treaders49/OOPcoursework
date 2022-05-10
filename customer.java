import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class customer extends user {

	private JPanel contentPane;
	private JTable productTable;
	private JTable basketTable;
	private JPanel productPanel;
	private JPanel inventoryContentPanel;
	private JPanel purchaseButtonPanel;
	private DefaultTableModel dtmItems;
	private DefaultTableModel dtmBasket;
	private HashMap<Item, Integer> basket;
	private ArrayList<Item> inventory;
	private ArrayList<JButton> buttonList;
	private float basketTotal;
	private JTextField textField;
	
	
	public customer(String name,String houseNum, String postcode, String city) {
		super(name, houseNum, postcode, city);
		setTitle("Customer Menu - User: " + name);
		getContentPane().setLayout(null);
		
		buttonList = new ArrayList<JButton>();

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1014, 539);
		getContentPane().add(tabbedPane);
		
		productPanel = new JPanel();
		tabbedPane.addTab("View Items", null, productPanel, null);
		productPanel.setLayout(null);
		
		
		
		JScrollPane inventoryScrollPane = new JScrollPane();
		
		inventoryScrollPane.setBounds(0,0,870,500);
		productPanel.add(inventoryScrollPane);
		
		inventoryContentPanel = new JPanel();
		inventoryContentPanel.setBounds(0,0,850,495);
		GridBagLayout gbl_inventoryContentPanel = new GridBagLayout();
		gbl_inventoryContentPanel.columnWidths = new int[]{0, 106, 0};
		gbl_inventoryContentPanel.rowHeights = new int[]{228, 6};
		gbl_inventoryContentPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_inventoryContentPanel.rowWeights = new double[]{1.0, 0.0};
		inventoryContentPanel.setLayout(gbl_inventoryContentPanel);
		dtmItems = new DefaultTableModel();
		dtmItems.setColumnIdentifiers(new Object[] {"Barcode" , "Device Type", "Style", "Brand", "Colour", "Connectivity", "Layout", "No. Buttons", "Quantity", "Price"});
		inventoryScrollPane.setViewportView(inventoryContentPanel);
		
		productTable = new JTable();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(0, 0, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.BOTH;
		inventoryContentPanel.add(productTable, gbc);
		
		
		
		
		
		
		
		
		productTable.setModel(dtmItems);
		inventoryScrollPane.setColumnHeaderView(productTable.getTableHeader());
		
		purchaseButtonPanel = new JPanel();
		purchaseButtonPanel.setLayout(null);
		GridBagConstraints gbc_purchaseButtonPanel = new GridBagConstraints();
		gbc_purchaseButtonPanel.insets = new Insets(0, 0, 5, 0);
		gbc_purchaseButtonPanel.fill = GridBagConstraints.BOTH;
		gbc_purchaseButtonPanel.gridx = 1;
		gbc_purchaseButtonPanel.gridy = 0;
		inventoryContentPanel.add(purchaseButtonPanel, gbc_purchaseButtonPanel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(880, 40, 119, 20);
		productPanel.add(comboBox);
		
		textField = new JTextField();
		textField.setBounds(880, 136, 119, 20);
		productPanel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.setBounds(880, 167, 89, 23);
		productPanel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(880, 121, 46, 14);
		productPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(880, 27, 46, 14);
		productPanel.add(lblNewLabel_1);
		
		
		
		inventory = new ArrayList<Item>();
		populateTable();
		
		JPanel basketPanel = new JPanel();
		basketPanel.setLayout(null);
		tabbedPane.addTab("View Basket", null, basketPanel, null);
		JScrollPane basketScrollPane = new JScrollPane();
		basketScrollPane.setBounds(10, 5, 850, 495);
		basketPanel.add(basketScrollPane);

		basketTable = new JTable();
		basketScrollPane.setViewportView(basketTable);
		
		JButton clearBasketButton = new JButton("Clear Basket");
		clearBasketButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				basket.clear();
				basketTotal = 0;
				updateBasketTable();
				populateTable();
				
			}
		});
		
		clearBasketButton.setBounds(860,20,150,50);
		basketPanel.add(clearBasketButton);
		
		JButton payButton = new JButton("To Payment");
		payButton.setBounds(860,90,150,50);
		basketPanel.add(payButton);
		payButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (basket.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Basket is currently empty");
				} else {
					tabbedPane.setSelectedIndex(2);
				}
			}
		});
		
		
		JPanel paymentPanel = new JPanel();
		tabbedPane.addTab("Payment", null, paymentPanel, null);
		paymentPanel.setLayout(null);

		JRadioButton paypalButton = new JRadioButton("Pay with Paypal");
		paypalButton.setBounds(10, 10, 300, 100);
		paypalButton.setSelected(true);


		JTextField emailAddressBox = new JTextField();
		JLabel emailLabel = new JLabel("Email Address:");
		emailLabel.setBounds((paypalButton.getX()+ 50), (paypalButton.getY() + 150), 250, 25);
		emailAddressBox.setBounds((paypalButton.getX()+ 150), (emailLabel.getY()), 200, 25);
		

		JLabel cardNumberLabel = new JLabel("Card Number:");
		JLabel securityNumberLabel = new JLabel("Security number:");
		JTextField cardNumber = new JTextField();
		JTextField securityNumber = new JTextField();

		JButton confirmPayButton = new JButton("Confirm Payment");
		confirmPayButton.setBounds((paypalButton.getX()+ 50), (paypalButton.getY() + 400), 175, 25);
		confirmPayButton.setVisible(true);
		confirmPayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			String paymentMethod;
			boolean validationIssues = false;
			if (paypalButton.isSelected()) {
				paymentMethod = "Paypal";
				String email = emailAddressBox.getText();
				if (!(email.contains("@"))) {
					JOptionPane.showMessageDialog(null, "email must include an @");
					validationIssues = true;
					emailAddressBox.setText("");
				}
			} else {
				paymentMethod = "Credit Card";
				String cardno = cardNumber.getText();
				String securityno = securityNumber.getText();
				if ((cardno.length() != 6) || (securityno.length() != 3)) {
					validationIssues = true;
					JOptionPane.showMessageDialog(null, "Card number should be 6 digits and security number should be 3");
					cardNumber.setText("");
					securityNumber.setText("");
				}
				
			}
			if (!(validationIssues)) {
				JOptionPane.showMessageDialog(null, "£" + String.format("%.2f", basketTotal) + " paid using " + paymentMethod + ", and the delivery address is number " + houseNum + " " + postcode + ", " + city );
				basketTotal = 0;
				basket.clear();
				updateBasketTable();
				updatestock();
				populateTable();
				tabbedPane.setSelectedIndex(0);
			}
			
			}
		});

		cardNumberLabel.setBounds((paypalButton.getX()+ 50), (paypalButton.getY() + 150), 200, 25);
		cardNumber.setBounds((paypalButton.getX()+ 200), (paypalButton.getY() + 150), 200, 25);
		
		securityNumberLabel.setBounds((paypalButton.getX()+ 50), (paypalButton.getY() + 225), 200, 25);
		securityNumber.setBounds((paypalButton.getX()+ 200), (paypalButton.getY() + 225), 200, 25);

		
		cardNumberLabel.setVisible(false);cardNumber.setVisible(false);
		securityNumber.setVisible(false);securityNumberLabel.setVisible(false);
		JRadioButton creditButton = new JRadioButton("Pay with Credit Card");
		creditButton.setBounds(320, 10, 300, 100);
		paymentPanel.add(paypalButton);paymentPanel.add(creditButton);
		paymentPanel.add(emailAddressBox);paymentPanel.add(emailLabel);paymentPanel.add(cardNumber);paymentPanel.add(cardNumberLabel);paymentPanel.add(securityNumber);paymentPanel.add(securityNumberLabel);paymentPanel.add(confirmPayButton);
		ButtonGroup bg =  new ButtonGroup();
		bg.add(paypalButton);bg.add(creditButton);
		paypalButton.setSelected(true);
		paypalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNumber.setVisible(false);cardNumberLabel.setVisible(false);securityNumber.setVisible(false);securityNumberLabel.setVisible(false);
				emailAddressBox.setVisible(true);emailLabel.setVisible(true);


			}
		});
		creditButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNumber.setVisible(true);cardNumberLabel.setVisible(true);securityNumber.setVisible(true);securityNumberLabel.setVisible(true);
				emailAddressBox.setVisible(false);emailLabel.setVisible(false);

			}
		});

		basket = new HashMap<Item, Integer>();
		
		tabbedPane.setEnabledAt(2, false);
		
		
	}


	private void populateTable() {
		for (JButton button : buttonList ) {
			button.setVisible(false);
		}
		buttonList.clear();
		dtmItems.setRowCount(0);
		inventory.clear();
		int gridyIndex = 0;
		int y = 0; //counting the y axis for the value of the button placement incrementing by 16 each time
		
		try {
		      File f = new File("Stock.txt");
		      Scanner myReader = new Scanner(f);
		      while (myReader.hasNextLine()) {
		        String row = myReader.nextLine();
		        if (row.contains("mouse") || (row.contains("keyboard"))) {
		        	System.out.println(row);
		        	String[] rowData = row.split(", ");
		        	
		        	if (rowData[1].equals("mouse")) {
		        		mouse m = new mouse(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), Integer.parseInt(rowData[9]));
		        		System.out.println(m);
		        		inventory.add(m);
		        		addButton(productPanel, m, y);
		        	} else  {
		        		keyboard k = new keyboard(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), rowData[9]);
		        		inventory.add(k);
		        		addButton(productPanel, k, y);
		        }
		        y+= 16;
		        
		        
		      
		      System.out.println(inventory);
		      
		    
			}
		updateInventoryTable();
		      }myReader.close();
		      
		} catch (FileNotFoundException e) {
			   System.out.println("An error occurred.");
			   e.printStackTrace(); 
	}
		        
	}
	private void updateInventoryTable() {
		
		System.out.println(productPanel.getComponents());
		productTable.setVisible(true);
		dtmItems.setRowCount(0);
		
		for (Item i : inventory) {
			if (i.getDeviceType().equals("mouse")) {
				mouse m = (mouse) i;
				dtmItems.addRow(new Object[] {m.getBarcode(), m.getDeviceType(), m.getStyle(), m.getBrand(), m.getColour(), m.getConnectivity(), "N/A", m.getNumberOfButtons(), m.getQuantity(), String.format("%.2f",m.getRetailPrice())});
				
			
			} else {
				keyboard k = (keyboard) i;
				dtmItems.addRow(new Object[] {k.getBarcode(), k.getDeviceType(), k.getStyle(), k.getBrand(), k.getColour(), k.getConnectivity(), k.getLayout(), "N/A", k.getQuantity(), String.format("%.2f",k.getRetailPrice())});
				
			}
			
			
			
		}
		
	}
	
	private void addButton(JPanel productPanel, Item i, int y) {
		
		
		JButton purchaseButton = i.getAddItemButton();
		buttonList.add(purchaseButton);
		purchaseButton.setBounds(0, y, 100, 16);
		purchaseButton.setVisible(true);
		purchaseButtonPanel.add(purchaseButton);
		
		purchaseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int row = getRow(i); //the row which the item is on, used to manipulate stock etc
				try {
					int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter how many of this product you would like to purchase:", null));
					if (quantity < 0) {
						JOptionPane.showMessageDialog(null, "Please enter a value which is more than zero");
					
					}else if (quantity > (int)dtmItems.getValueAt(row, 8)) {
						JOptionPane.showMessageDialog(null, "The number you have entered exceeds the stock amount");
						
					} else {
						if (basket.containsKey(i)) {
							basket.put(i, ((int)basket.get(i) + quantity));
							
						} else {
							basket.put(i, quantity);
						}
						
						System.out.println(row);
						int newValue = ((int)dtmItems.getValueAt(row, 8)) - quantity;
						dtmItems.setValueAt(newValue, row, 8);
						updateBasketTable();
						i.checkQuantity((int)dtmItems.getValueAt(row, 8));
						
					}
				}catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "Please enter a value which is a number");
				}
				
			}
		});
	}
	private void updateBasketTable() {
		basketTotal = 0;
		dtmBasket = new DefaultTableModel();
		basketTable.setModel(dtmBasket);
		dtmBasket.setColumnIdentifiers(new Object[] {"Barcode", "Brand", "Type", "quantity purchased", "cost of item", "total cost"});
		
		for (HashMap.Entry<Item, Integer> entry : basket.entrySet()) {
			System.out.println(entry.getKey());
			System.out.println(entry.getValue());
			Item i = entry.getKey();
			int quantity = entry.getValue();
			dtmBasket.addRow(new Object[] {i.getBarcode(), i.getBrand(), i.getDeviceType(), quantity, String.format("%.2f", i.getRetailPrice()), String.format("%.2f",(quantity * i.getRetailPrice())) });
			basketTotal+= (quantity * i.getRetailPrice());
		}
		dtmBasket.addRow(new Object[] {"","","","","TOTAL:",String.format("%.2f", basketTotal) });
	}

	private int getRow(Item i) {
		int index = 0;
		while (index < inventory.size() - 1) {
			if (i.getBarcode().equals(dtmItems.getValueAt(index, 0)) ) {
				break;
			}
			index++;
		}
		return index;
	}

private void updatestock() {
	try {
		FileWriter myWriter = new FileWriter("Stock.txt");
		for (Item i : inventory) {
			if (basket.containsKey(i)) {
				int newQuantity = (int) dtmItems.getValueAt(getRow(i), 8);
				if (i.getDeviceType().equals("mouse")) {
					mouse m = (mouse) i;
					myWriter.write(m.getBarcode() + ", " + m.getDeviceType() + ", " + m.getStyle() + ", " + m.getBrand() + ", " + m.getColour() + ", " + m.getStyle() + ", " + m.getConnectivity() + ", " + m.getStyle() + ", " + Integer.toString(newQuantity) + ", " + Float.toString(m.getOriginalCost()) + ", " + Float.toString(m.getRetailPrice()) + Integer.toString(m.getNumberOfButtons()));
				} else {
					keyboard k = (keyboard) i;
					myWriter.write(k.getBarcode() + ", " + k.getDeviceType() + ", " + k.getStyle() + ", " + k.getBrand() + ", " + k.getColour() + ", " + k.getStyle() + ", " + k.getConnectivity() + ", " + k.getStyle() + ", " + Integer.toString(newQuantity) + ", " + Float.toString(k.getOriginalCost()) + ", " + Float.toString(k.getRetailPrice()) + (k.getLayout()));
				}
				
			} else {
				myWriter.write(m.getBarcode() + ", " + m.getDeviceType() + ", " + m.getStyle() + ", " + m.getBrand() + ", " + m.getColour() + ", " + m.getStyle() + ", " + m.getConnectivity() + ", " + m.getStyle() + ", " + Integer.toString(newQuantity) + ", " + Float.toString(m.getOriginalCost()) + ", " + Float.toString(m.getRetailPrice()) + Integer.toString(m.getNumberOfButtons()));
			}
		}

		myWriter.close();
	  } catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	  }
}
}



