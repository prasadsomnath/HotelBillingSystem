package com.hotel;

public class OrderItem extends MenuItem{
	 private int quantity;
	    private int subtotal;

	    public OrderItem(int itemId, String itemName, int itemPrice, int quantity) {
	        super(itemId, itemName, itemPrice);
	        this.quantity = quantity;
	        this.subtotal = calculateSubtotal(quantity);
	    }

	    @Override
	    public int calculateSubtotal(int quantity) {
	        return this.itemPrice * quantity;
	    }

	    public int getSubtotal() {
	        return subtotal;
	    }

	    @Override
	    public String toString() {
	        return String.format("   %-5d %-10d %-20s %-10d %-10d", itemId, quantity, itemName, itemPrice, subtotal);
	    }

}
