import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;

public class admin extends user { //admin is a subclass of user and therefore shares all of the same attributes as customer

	private JPanel contentPane;
	private JTextField txtBarcode;
	private JTextField txtType;
	private JTextField txtBrand;
	private JTextField txtQuantity;
	private JTextField txtConnectivity;
	private JTextField txtColour;
	private JTextField txtOriginalCost;
	private JTextField txtRetailPrice;
	private JTextField txtNumButtons;
	private JComboBox layoutBox;
	private JRadioButton addMouseButton;
	private JRadioButton addKeyboardButton;
	private ArrayList<JTextField> fieldArray; //an arraylist of all text fields which makes clearing all of them more efficient
	
	public admin(String name, String houseNum, String postcode, String city) {
		super(name, houseNum, postcode, city); //initialising superclass attributes that are shared with customer
		super.setBounds(100, 100, 930, 600);
		
		
		
		setTitle("Admin Menu - User: " + name);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 878, 539);
		getContentPane().add(tabbedPane);
		
		productPanel = new JPanel();
		tabbedPane.addTab("View Stock", null, productPanel, null);
		productPanel.setLayout(null);
		
		JScrollPane inventoryScrollPane = new JScrollPane();
		
		inventoryScrollPane.setBounds(0,0,870,500);
		productPanel.add(inventoryScrollPane);
		
		
		JPanel addItemPanel = new JPanel();
		tabbedPane.addTab("Add Item", null, addItemPanel, null);
		addItemPanel.setLayout(null);
		
		
		
		
		
		
		JLabel typeLabel = new JLabel("Add Mouse");
		typeLabel.setFont(new Font("Calibri", Font.BOLD, 28));
		typeLabel.setBounds(88, 104, 267, 35);
		addItemPanel.add(typeLabel);
		
		txtBarcode = new JTextField();
		txtBarcode.setBounds(88, 164, 120, 20);
		addItemPanel.add(txtBarcode);
		txtBarcode.setColumns(10);
		
		txtType = new JTextField();
		txtType.setBounds(88, 261, 120, 20);
		addItemPanel.add(txtType);
		txtType.setColumns(10);
		
		txtBrand = new JTextField();
		txtBrand.setBounds(88, 344, 120, 20);
		addItemPanel.add(txtBrand);
		txtBrand.setColumns(10);
		
		JLabel barcodeLabel = new JLabel("Barcode");
		barcodeLabel.setBounds(88, 150, 86, 14);
		addItemPanel.add(barcodeLabel);
		
		JLabel itemTypeLabel = new JLabel("Mouse Type");
		itemTypeLabel.setBounds(88, 240, 86, 14);
		addItemPanel.add(itemTypeLabel);
		
		JLabel brandLabel = new JLabel("Brand");
		brandLabel.setBounds(88, 327, 86, 14);
		addItemPanel.add(brandLabel);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(338, 344, 120, 20);
		addItemPanel.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		txtConnectivity = new JTextField();
		txtConnectivity.setBounds(338, 261, 120, 20);
		addItemPanel.add(txtConnectivity);
		txtConnectivity.setColumns(10);
		
		txtColour = new JTextField();
		txtColour.setBounds(338, 164, 120, 20);
		addItemPanel.add(txtColour);
		txtColour.setColumns(10);
		
		JLabel colourLabel = new JLabel("Colour");
		colourLabel.setBounds(338, 150, 46, 14);
		addItemPanel.add(colourLabel);
		
		JLabel connectivityLabel = new JLabel("Connectivity");
		connectivityLabel.setBounds(338, 240, 85, 14);
		addItemPanel.add(connectivityLabel);
		
		JLabel quantityLabel = new JLabel("Quantity In Stock");
		quantityLabel.setBounds(338, 327, 97, 14);
		addItemPanel.add(quantityLabel);
		
		txtOriginalCost = new JTextField();
		txtOriginalCost.setBounds(627, 164, 120, 20);
		addItemPanel.add(txtOriginalCost);
		txtOriginalCost.setColumns(10);
		
		txtRetailPrice = new JTextField();
		txtRetailPrice.setBounds(627, 258, 120, 20);
		addItemPanel.add(txtRetailPrice);
		txtRetailPrice.setColumns(10);
		
		txtNumButtons = new JTextField();
		txtNumButtons.setBounds(627, 344, 120, 20);
		addItemPanel.add(txtNumButtons);
		txtNumButtons.setColumns(10);
		
		JLabel originalCostLabel = new JLabel("Original Cost");
		originalCostLabel.setBounds(627, 150, 97, 14);
		addItemPanel.add(originalCostLabel);
		
		JLabel retailLabel = new JLabel("Retail Price");
		retailLabel.setBounds(627, 240, 97, 14);
		addItemPanel.add(retailLabel);
		
		JLabel numButtonsLabel = new JLabel("Number of Buttons");
		numButtonsLabel.setBounds(627, 327, 117, 14);
		addItemPanel.add(numButtonsLabel);
		
		layoutBox = new JComboBox(new Object[] {"UK", "US"});
		layoutBox.setBounds(627, 344, 120, 20);
		addItemPanel.add(layoutBox);
		layoutBox.setVisible(false);
		
		
		JLabel layoutLabel = new JLabel("Keyboard Layout");
		layoutLabel.setBounds(627, 327, 120, 14);
		addItemPanel.add(layoutLabel);
		layoutLabel.setVisible(false);
		
		fieldArray = new ArrayList<JTextField>(Arrays.asList(txtBarcode, txtType, txtBrand, txtQuantity, txtConnectivity, txtColour, txtOriginalCost, txtRetailPrice, txtNumButtons));

		
		addKeyboardButton = new JRadioButton("Add Keyboard");
		addKeyboardButton.setBounds(418, 38, 109, 23);
		addItemPanel.add(addKeyboardButton);
		addKeyboardButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //changing the format of data entry to fit adding a new keyboard
				typeLabel.setText("Add Keyboard");
				itemTypeLabel.setText("Keyboard type");
				numButtonsLabel.setVisible(false);
				txtNumButtons.setVisible(false);
				layoutLabel.setVisible(true);
				layoutBox.setVisible(true);
			}
		});
		
		addMouseButton = new JRadioButton("Add Mouse");
		addMouseButton.setBounds(258, 38, 109, 23);
		addItemPanel.add(addMouseButton);
		addMouseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //changing the format of data entry to fit adding a new mouse
				typeLabel.setText("Add Mouse");
				itemTypeLabel.setText("Mouse type");
				layoutLabel.setVisible(false);
				layoutBox.setVisible(false);
				numButtonsLabel.setVisible(true);
				txtNumButtons.setVisible(true);
			}
		});
		
		ButtonGroup bg =  new ButtonGroup();
		bg.add(addKeyboardButton);bg.add(addMouseButton);
		addMouseButton.setSelected(true);
		
		productTable = new JTable();
		dtmItems = new DefaultTableModel();
		dtmItems.setColumnIdentifiers(new Object[] {"Barcode" , "Device Type", "Style", "Brand", "Colour", "Connectivity", "Layout", "No. Buttons", "Quantity", "Original Price", "Price"});
		productTable.setModel(dtmItems);
		inventoryScrollPane.setViewportView(productTable);
		
		inventory = new ArrayList<Item>();
		
		loadAdminInventory();
		
		JButton addItemButton = new JButton("Add Item");
		addItemButton.setBounds(33, 463, 89, 23);
		addItemPanel.add(addItemButton);
		
		
		addItemButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addItem();
			}
		});
	}
	
	private boolean barcodeExists(String barcode) { //function that checks to see if a barcode exists by iterating through the file and storing each barcode
		boolean exists = false;
		ArrayList<String> barcodeArray = new ArrayList<String>();
		
	    File f = new File("Stock.txt");
		Scanner myReader;
		try {
			myReader = new Scanner(f);
			while (myReader.hasNextLine()) {
				String row = myReader.nextLine();
				System.out.println(row);
				String[] rowData = row.split(", ");
				System.out.println(rowData[0]);
				barcodeArray.add(rowData[0]);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (barcodeArray.contains(barcode)) {
	    	  exists = true;
	      }
	      System.out.println(barcodeArray);
		return exists;
	}
	
	private void addItem() { //function for adding an item to the stock after checking the format for all of the entries based on their different requirements
		String barcode;
		String brand;
		String type;
		int quantity;
		String connectivity;
		String colour;
		float originalCost;
		float retailPrice;
		String layout;
		int numButtons = 0;
		if (((addKeyboardButton.isSelected()) && ((txtBarcode.getText().equals("")) || (txtBrand.getText().equals("")) || (txtType.getText().equals("")) || (txtQuantity.getText().equals("")) || (txtConnectivity.getText().equals("")) || (txtColour.getText().equals("")) || (txtOriginalCost.getText().equals(""))|| (txtRetailPrice.getText().equals(""))))  || ((addMouseButton.isSelected()) && ((txtBarcode.getText().equals("")) || (txtBrand.getText().equals("")) || (txtType.getText().equals("")) || (txtQuantity.getText().equals("")) || (txtConnectivity.getText().equals("")) || (txtColour.getText().equals("")) || (txtOriginalCost.getText().equals(""))|| (txtRetailPrice.getText().equals("")) || (txtNumButtons.getText().equals(""))))) {
			JOptionPane.showMessageDialog(null, "Some fields have been left blank"); //checking for if any inputs have been left blank
			return;
		} else {
				if (txtBarcode.getText().matches("[0-9]+") && (txtBarcode.getText().length() == 6)) { //check to see if the barcode matches the format of only numbers using the .matches function and also ensuring it is a length of 6 
					if (barcodeExists(txtBarcode.getText())) { //calls the function barcodeExists with the barcode as a parameter
						JOptionPane.showMessageDialog(null, "Barcode already exists and must be unique");
						return; 
					}
					barcode = txtBarcode.getText(); //if the requirements are satisfied, the barcode is assigned
					
				} else {
					JOptionPane.showMessageDialog(null, "Barcode should not include letters and should be 6 digits long");
					return; //if requirements are not satisfied, break from the function
				}
				
				if ((txtType.getText().matches("[a-zA-Z]+")) && (txtBrand.getText().matches("[a-zA-Z]+")) && (txtColour.getText().matches("[a-zA-Z]+")) && (txtConnectivity.getText().matches("[a-zA-Z]+")) ) { //using the regex phrases and .matches function to ensure that these 4 inputs are made of only letters
					type = txtType.getText();
					colour = txtColour.getText();
					brand = txtBrand.getText();
					connectivity = txtConnectivity.getText();
					
				} else {
					JOptionPane.showMessageDialog(null, "entries type, brand, colour and connectivity should not have numbers in");
					return;
				}
				
				if(txtQuantity.getText().matches("[0-9]+")) { //checking that quantity can only be a whole number
					quantity = Integer.parseInt(txtQuantity.getText()); 
					
				} else {
					JOptionPane.showMessageDialog(null, "quantity should not include letters");
					return;
				}
				if ((txtRetailPrice.getText().matches("[0-9]?[0-9]?[0-9]?(\\.[0-9][0-9]?)?")) && (txtOriginalCost.getText().matches("[0-9]?[0-9]?[0-9]?(\\.[0-9][0-9]?)?"))) { //making sure it matches the pattern of only whole numbers or decimals
					retailPrice = Float.parseFloat(txtRetailPrice.getText());
					originalCost = Float.parseFloat(txtOriginalCost.getText());
					System.out.println(originalCost);
				} else {
					JOptionPane.showMessageDialog(null, "retail price and original cost should be a whole number or decimal in the format X.XX");
					return;
				}
				if ((txtNumButtons.getText().matches("[0-9]+")) && (!(txtNumButtons.getText().equals("")))) {//saying if there is something entered in the numButtons box and it is in the number format, assign it to numButtons variable
					numButtons = Integer.parseInt(txtNumButtons.getText());
					
				} else {
					if (addMouseButton.isSelected()) {
						JOptionPane.showMessageDialog(null, "number of buttons should be an integer");
					}
				}
				
				if (addKeyboardButton.isSelected()) { //checking it is the keyboard option that is selected
					layout = (String) layoutBox.getSelectedItem(); //getting the layout that does not need a check
					keyboard k = new keyboard(barcode, "keyboard ", type, brand, colour, connectivity, quantity, originalCost, retailPrice, layout); //create a new keyboard object
					addToStock(k); //creating a new keyboard object and adding it to the stock
				} else {
					mouse m = new mouse(barcode, "mouse", type, brand, colour, connectivity, quantity, originalCost, retailPrice, numButtons);
					addToStock(m); //creating a new mouse object and adding it to the stock
				}
				
		}
	}
	
	private void addToStock(Item i) {
		try {
			FileWriter fw = new FileWriter("Stock.txt", true); //initialising a new filewriter with the given text file
		    BufferedWriter bw = new BufferedWriter(fw); //bufferedwriter allows the ability to append to the end of a text file
		    if (i.getDeviceType().equals("mouse")) {
		    	mouse m = (mouse) i; //if the type is mouse create a new mouse object and format the entry into the stock file so it can be read by customers and admin
		    	bw.write("\n" + m.getBarcode() + ", mouse, " + m.getStyle() + ", " + m.getBrand() + ", " + m.getColour() + ", " + m.getConnectivity() + ", " + m.getQuantity() + ", " + m.getOriginalCost() + ", " + m.getRetailPrice() + ", " + m.getNumberOfButtons());
		    	JOptionPane.showMessageDialog(null, "Mouse added successfully");
		    	clearTextFields();
		    } else { 
		    	keyboard k = (keyboard) i; //create a new keyboard object and format into stock file
		    	bw.write("\n" + k.getBarcode() + ", keyboard, " + k.getStyle() + ", " + k.getBrand() + ", " + k.getColour() + ", " + k.getConnectivity() + ", " + k.getQuantity() + ", " + k.getOriginalCost() + ", " + k.getRetailPrice() + ", " + k.getLayout());
		    	JOptionPane.showMessageDialog(null, "Keyboard added successfully");
		    	clearTextFields();
		    }
		    bw.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void clearTextFields() { //clear all entries when an item is added
		for(JTextField field: fieldArray) {
			field.setText("");
		}
	}
	
	private void loadAdminInventory() { //reads from the stock file and populates the admin table of every entry
		dtmItems.setRowCount(0);
		
		inventory.clear();
		
		
			
			
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
		        		
		        		
	
		        		
		        	} else  {
		        		keyboard k = new keyboard(rowData[0], rowData[1], rowData[2], rowData[3], rowData[4], rowData[5], Integer.parseInt(rowData[6]), Float.parseFloat(rowData[7]),Float.parseFloat(rowData[8]), rowData[9]);
		        		
		        		inventory.add(k);
		        		
		        		
		        		
		        }
		        
		        
		        
		        }
		      }
		      inventory = sortByAsc(inventory); //sorting the prices of the stock items into ascending order ready to be displayed to the screen
		        
		        updateInventoryTable(inventory); //displaying the chosen stock to the screen
		
		      myReader.close();
		      
		} catch (FileNotFoundException e) {
			   System.out.println("An error occurred.");
			   e.printStackTrace(); 
		}
	}
	private void updateInventoryTable(ArrayList<Item> itemsToDisplay) { //chooses the appropriate list to display to the screen
		
		
		
		productTable.setVisible(true);
		dtmItems.setRowCount(0);
		
		for (Item i : itemsToDisplay) { //loops through the items that have been created and displays each one to the sccreen
			if (i.getDeviceType().equals("mouse")) {
				mouse m = (mouse) i;
				dtmItems.addRow(new Object[] {m.getBarcode(), m.getDeviceType(), m.getStyle(), m.getBrand(), m.getColour(), m.getConnectivity(), "N/A", m.getNumberOfButtons(), m.getQuantity(), String.format("%.2f",m.getOriginalCost()), String.format("%.2f",m.getRetailPrice())});
				
			
			} else {
				keyboard k = (keyboard) i;
				dtmItems.addRow(new Object[] {k.getBarcode(), k.getDeviceType(), k.getStyle(), k.getBrand(), k.getColour(), k.getConnectivity(), k.getLayout(), "N/A", k.getQuantity(), String.format("%.2f",k.getOriginalCost()),  String.format("%.2f",k.getRetailPrice())});
				
			}
		}
	}
}
