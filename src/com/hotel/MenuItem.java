package com.hotel;

public abstract class MenuItem {
	 protected int itemId;
	    protected String itemName;
	    protected int itemPrice;

	    public MenuItem(int itemId, String itemName, int itemPrice) {
	        this.itemId = itemId;
	        this.itemName = itemName;
	        this.itemPrice = itemPrice;
	    }
	    // i am crating one abstract method , in children class can be provided body for the method
	    public abstract int calculateSubtotal(int quantity);
	

}
