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
	private JTable basketTable;
	private DefaultTableModel dtmItems;
	private DefaultTableModel dtmBasket;
	private HashMap<Item, Integer> basket;
	private float basketTotal;
	
	public customer(String name,String houseNum, String postcode, String city) {
		super(name, houseNum, postcode, city);
		setTitle("Customer Menu - User: " + name);
		getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 1014, 539);
		getContentPane().add(tabbedPane);
		
		JPanel productPanel = new JPanel();
		tabbedPane.addTab("View Items", null, productPanel, null);
		productPanel.setLayout(null);
		
		JScrollPane inventoryScrollPane = new JScrollPane();
		inventoryScrollPane.setBounds(10, 5, 850, 495);
		productPanel.add(inventoryScrollPane);
		
		productTable = new JTable();
		inventoryScrollPane.setViewportView(productTable);
		
		populateTable(productPanel);
		
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
				populateTable(productPanel);
				
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
				JOptionPane.showMessageDialog(null, "£" + String.format("%2f", basketTotal) + "paid using " + paymentMethod + ", and the delivery address is number " + houseNum + " " + postcode + " ," + city );
				basket.clear();
				updateBasketTable();
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
				updateBasketTable();
			}
		});
	}
	private void updateBasketTable() {
		dtmBasket = new DefaultTableModel();
		basketTable.setModel(dtmBasket);
		dtmBasket.setColumnIdentifiers(new Object[] {"Barcode", "Brand", "Type", "quantity purchased", "cost of item", "total cost"});
		
		for (HashMap.Entry<Item, Integer> entry : basket.entrySet()) {
			Item i = entry.getKey();
			int quantity = entry.getValue();
			dtmBasket.addRow(new Object[] {i.getBarcode(), i.getBrand(), i.getDeviceType(), quantity, String.format("%.2f", i.getRetailPrice()), String.format("%.2f",(quantity * i.getRetailPrice())) });
			basketTotal+= (quantity * i.getRetailPrice());
		}
		dtmBasket.addRow(new Object[] {"","","","","TOTAL:",String.format("%.2f", basketTotal) });
	}

	
}
