import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

abstract class Item {

	private JButton addItemButton;
	
	private String barcode;
	private String deviceType;
	private String style;
	private String brand;
	private String colour;
	private String connectivity;
	private int quantity;
	private float originalCost;
	private float retailPrice;
	public Item(String barcode, String deviceType, String style, String brand, String colour, String connectivity, int quantity, float originalCost, float retailPrice) {
		this.setBarcode(barcode);
		this.setStyle(style);
		this.setDeviceType(deviceType);
		this.setBrand(brand);
		this.setColour(colour);
		this.setConnectivity(connectivity);
		this.setQuantity(quantity);
		this.setOriginalCost(originalCost);
		this.setRetailPrice(retailPrice);
		
		
		
		if (quantity == 0) {
			setAddItemButton(new JButton("Out of stock"));
		} else {
			setAddItemButton(new JButton("Purchase"));
		}
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public float getOriginalCost() {
		return originalCost;
	}
	public void setOriginalCost(float originalCost) {
		this.originalCost = originalCost;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getConnectivity() {
		return connectivity;
	}
	public void setConnectivity(String connectivity) {
		this.connectivity = connectivity;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getRetailPrice() {
		return retailPrice;
	}
	public void setRetailPrice(float retailPrice) {
		this.retailPrice = retailPrice;
	}
	
	public JButton getAddItemButton() {
		return addItemButton;
	}
	public void setAddItemButton(JButton addItemButton) {
		this.addItemButton = addItemButton;
	}
	public void checkQuantity(int quantityLeft) { //quantity left represents the amount of stock minus the amount thats in the basket
		if (quantityLeft == 0) {
			addItemButton.setText("Out of stock");
			addItemButton.setEnabled(false);
		}
	}

}
