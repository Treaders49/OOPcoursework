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
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridBagLayout;
import java.awt.Insets;


public class customer extends user {

	private JPanel contentPane;
	
	private JTable basketTable;
	
	private JPanel inventoryContentPanel;
	private JPanel purchaseButtonPanel;
	
	private DefaultTableModel dtmBasket;
	private HashMap<Item, Integer> basket;
	private ArrayList<String> brandList;
	private ArrayList<JButton> buttonList;
	private float basketTotal;
	private JTextField buttonNumberField;
	private JComboBox brandComboBox;
	
	
	public customer(String name,String houseNum, String postcode, String city) {
		super(name, houseNum, postcode, city);
		super.setBounds(100, 100, 1050, 600);
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
		
		
		inventory = new ArrayList<Item>();
		basket = new HashMap<Item, Integer>();
		brandList = new ArrayList<String>();
		populateTable(false);
		brandComboBox = new JComboBox(brandList.toArray());
        
        
		
		brandComboBox.setBounds(880, 40, 119, 20);
		productPanel.add(brandComboBox);
		
		buttonNumberField = new JTextField();
		buttonNumberField.setBounds(880, 136, 119, 20);
		productPanel.add(buttonNumberField);
		buttonNumberField.setColumns(10);
		
		JButton btnNewButton = new JButton("Filter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				populateTable(true);
			}
		});
		btnNewButton.setBounds(880, 167, 89, 23);
		productPanel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Number of buttons");
		lblNewLabel.setBounds(880, 121, 119, 14);
		productPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Brand");
		lblNewLabel_1.setBounds(880, 27, 89, 14);
		productPanel.add(lblNewLabel_1);
		
		
		
		
		
		
		
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
				populateTable(false);
				
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
				updatestock();
				basketTotal = 0;
				basket.clear();
				updateBasketTable();
				populateTable(false);
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

		
		
		tabbedPane.setEnabledAt(2, false);
		
		
	}


	private void populateTable(boolean filtered) {
		
		for (JButton button : buttonList ) { //clearing the previous screen
			button.setVisible(false);
		}
		buttonList.clear();
		dtmItems.setRowCount(0);
		
		inventory.clear();
		
		
			
			
			try {
			  int y = 0; //counting the y axis for the value of the button placement incrementing by 16 each time
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
		        		if (basketQuantity(m.getBarcode()) != 0) { //function that returns 0 if item isnt in basket and quantity if it is in basket
		        			m.setQuantity(m.getQuantity() - basketQuantity(m.getBarcode()));
		        		}
		        		inventory.add(m);
		        		addButton(productPanel, m, y);
		        		y+=16;
		        		
		        		
		        	} else  {
		        		keyboard k = new keyboard(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), rowData[9]);
		        		if (basketQuantity(k.getBarcode()) != 0) { 
		        			k.setQuantity(k.getQuantity() - basketQuantity(k.getBarcode()));
		        		}
		        		inventory.add(k);
		        		addButton(productPanel, k, y);
		        		y+=16;
		        		
		        }
		        if (!(brandList.contains(rowData[3]))) { //collating a list of unique brands to insert into the brand combo box
		        	brandList.add(rowData[3]);
		        	System.out.println(brandList);
		        }
		        
		        
		        }
		        
		        
		        inventory = sortByAsc(inventory);
		        sortButtons(inventory);
		        updateInventoryTable(inventory);
		
		      }myReader.close();
		      
		} catch (FileNotFoundException e) {
			   System.out.println("An error occurred.");
			   e.printStackTrace(); 
		}
		
		if (filtered) {
			
			ArrayList<Item> filteredInventory = new ArrayList<Item>();
			int y = 0;
			String numButtons = buttonNumberField.getText();
			String brand = (String) brandComboBox.getSelectedItem();
			switch (numButtons) {
			case "":
				
				for (Item i: inventory) {
					if (!(brand.equals(i.getBrand()))) {
						purchaseButtonPanel.remove(i.getAddItemButton());
					} else {
						i.getAddItemButton().setBounds(0, y, 105, 16);
						filteredInventory.add(i);
						y+=16;
					}
					
				}
				
				filteredInventory = sortByAsc(filteredInventory);
				sortButtons(filteredInventory);
				updateInventoryTable(filteredInventory);
				break;
			default:
				for (Item i: inventory) {
					if (i.getDeviceType().equals("mouse")) {
						mouse m = (mouse) i;
						if ((!(brand.equals(m.getBrand()))) || (!(m.getNumberOfButtons() == Integer.parseInt(numButtons)))) {
						purchaseButtonPanel.remove(i.getAddItemButton());
						} else {
						i.getAddItemButton().setBounds(0, y, 105, 16);
						filteredInventory.add(i);
						y+=16;
					}
					
					
					
					} else {
						purchaseButtonPanel.remove(i.getAddItemButton());
					}
				}
				filteredInventory = sortByAsc(filteredInventory);
				sortButtons(filteredInventory);
				updateInventoryTable(filteredInventory);
				break;
			}
			
				
			
			
			
		}
		
		
		        
	}
	private void updateInventoryTable(ArrayList<Item> itemsToDisplay) { //chooses the appropriate list to display to the screen
		
		productTable.setVisible(true);
		dtmItems.setRowCount(0);
		
		for (Item i : itemsToDisplay) {
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
		
		int row = getRow(i); //the row which the item is on, used to manipulate stock etc
		JButton purchaseButton = i.getAddItemButton();
		System.out.println("button");
		buttonList.add(purchaseButton);
		
		purchaseButton.setBounds(0, y, 105, 16);
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
						i.checkQuantity((int)dtmItems.getValueAt(row, 8));
						
					} else {
						System.out.println(containsBarcode(i.getBarcode()));
						if (containsBarcode(i.getBarcode())) {//the process of adding to quantity of a bought item rather than making it a seperate entity
							Item duplicateItem = getItemFromBasket(i.getBarcode());
							basket.put(duplicateItem, ((int)basket.get(duplicateItem) + quantity));
							
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
		
		while (index < dtmItems.getRowCount() - 1) {
			System.out.println("barcode:" + i.getBarcode());
			System.out.println(dtmItems.getValueAt(index, 0));
			if (i.getBarcode().equals(dtmItems.getValueAt(index, 0)) ) {
				break;
				
			}
			index++;
		}
		System.out.println("index = " + index);
		
		return index;
		
		
		
	}

private void updatestock() {
	
	try {
		FileWriter myWriter = new FileWriter("Stock.txt");
		for (Item i : inventory) {
			System.out.println(basket);
			if (basket.containsKey(i)) {
				int newQuantity = (int) dtmItems.getValueAt(getRow(i), 8);
				if (i.getDeviceType().equals("mouse")) {
					mouse m = (mouse) i;
					myWriter.write(m.getBarcode() + ", " + m.getDeviceType() + ", " + m.getStyle() + ", " + m.getBrand() + ", " + m.getColour() + ", "  + m.getConnectivity() + ", " + Integer.toString(newQuantity) + ", " + Float.toString(m.getOriginalCost()) + ", " + Float.toString(m.getRetailPrice()) + ", " + Integer.toString(m.getNumberOfButtons()) + "\n");
				} else {
					keyboard k = (keyboard) i;
					myWriter.write(k.getBarcode() + ", " + k.getDeviceType() + ", " + k.getStyle() + ", " + k.getBrand() + ", " + k.getColour() + ", "  + k.getConnectivity() + ", "  + Integer.toString(newQuantity) + ", " + Float.toString(k.getOriginalCost()) + ", " + Float.toString(k.getRetailPrice()) + ", " + (k.getLayout()) + "\n");
				}
				
			} else {
				if (i.getDeviceType().equals("mouse")) {
					mouse m = (mouse) i;
					myWriter.write(m.getBarcode() + ", " + m.getDeviceType() + ", " + m.getStyle() + ", " + m.getBrand() + ", " + m.getColour() + ", " +  m.getConnectivity() +  ", " + m.getQuantity() + ", " + Float.toString(m.getOriginalCost()) + ", " + Float.toString(m.getRetailPrice()) + ", " + Integer.toString(m.getNumberOfButtons()) + "\n");
				} else {
					keyboard k = (keyboard) i;
					myWriter.write(k.getBarcode() + ", " + k.getDeviceType() + ", " + k.getStyle() + ", " + k.getBrand() + ", " + k.getColour() + ", "  + k.getConnectivity() + ", " + k.getQuantity() + ", " + Float.toString(k.getOriginalCost()) + ", " + Float.toString(k.getRetailPrice()) + ", " + (k.getLayout()) + "\n");
				}
			}
		}

		myWriter.close();
	  } catch (IOException e) {
		System.out.println("An error occurred.");
		e.printStackTrace();
	  }
}

private int basketQuantity(String itemCode) {
	int quantity = 0;
	
	if (!(basket.isEmpty())) {
		for (Item key : basket.keySet()) {
			if (key.getBarcode().equals(itemCode)) {
				quantity = basket.get(key);
			}
		
		}
	}
	return quantity;
}

private boolean containsBarcode(String barcode) {
	boolean containsBarcode = false;
	if (!(basket.isEmpty())) {
		for (Item key : basket.keySet()) {
			if (key.getBarcode().equals(barcode)) {
				containsBarcode = true;
			}
		
		}
	}
	return containsBarcode;
}

private Item getItemFromBasket(String barcode) {
	Item i = null;
	for (Item key : basket.keySet()) {
		if (key.getBarcode().equals(barcode)) {
			i = key;
		}
	}
	return i;
}

private void sortButtons(ArrayList<Item> inventory) {
	for(Item i: inventory) {
		i.getAddItemButton().setBounds(0, (16 * inventory.indexOf(i)), 105, 16);
	}
}
}



