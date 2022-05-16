
public class mouse extends Item{ //a subclass of Item class
	private int numberOfButtons;
	public mouse(String barcode, String deviceType, String style, String brand, String colour, String connectivity, int quantity, float originalCost, float retailPrice,int numberOfButtons) {
		super(barcode, deviceType, style, brand, colour, connectivity, quantity, originalCost, retailPrice);
		this.setNumberOfButtons(numberOfButtons);
	}
	public int getNumberOfButtons() { //has a unique attribute numberOfButtons
		return numberOfButtons;
	}
	public void setNumberOfButtons(int numberOfButtons) {
		this.numberOfButtons = numberOfButtons;
	}

}
