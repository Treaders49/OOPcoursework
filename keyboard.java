
public class keyboard extends Item { //is a subclass of Item object
	private String layout;
	public keyboard(String barcode, String deviceType, String style, String brand, String colour, String connectivity, int quantity, float originalCost, float retailPrice, String layout) {
		super(barcode, deviceType, style, brand, colour, connectivity, quantity, originalCost, retailPrice);
		this.setLayout(layout);
	}
	public String getLayout() { //has a unique attribute layout
		return layout;
	}
	public void setLayout(String layout) {
		this.layout = layout;
	}
	

}
