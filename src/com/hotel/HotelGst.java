package com.hotel;

public class HotelGst implements Billable {
	 @Override
	    public float calculateGST(float amount) {
	        return amount * 0.18f;
	    }
}
