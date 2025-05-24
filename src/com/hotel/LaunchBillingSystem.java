package com.hotel;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LaunchBillingSystem {
    private static final Scanner s = new Scanner(System.in);
    private static final List<OrderItem> orders = new ArrayList<>();

    public static void main(String[] args) {
    	try {
        System.out.println("********** Welcome to Amantrana Pure-Veg Hotel **********");
        System.out.print("Dear Customer, kindly enter your Name: ");
        String name = s.nextLine();

        String choice;
        do {
            int category = displayMainMenu(name);
            if(category<5) {
            displayItems(category);
            processOrder(category);
            }
            else {
            	System.out.println("invalid no, please check once again");
            }
            System.out.print("Do you want to continue or order anything more (Y/N)? ");
            choice = s.next().toLowerCase();
        } while (choice.equals("y"));

        printBill();
    
    }
	catch(InputMismatchException e) {
		System.out.println("\"input missmatch Exception occure\"");
//		String a=e.getStackTrace().toString();
//		System.out.println(a);
	}
    }

    private static int displayMainMenu(String name) throws InputMismatchException  {
    
        System.out.println("\nDear " + name + ", below is the menu card:");
        System.out.println("---------------------------");
        System.out.println("Serial No.       Category");
        System.out.println("----------------------------");
        System.out.println("1                Indian Soup");
        System.out.println("2                Chinese Soup");
        System.out.println("3                Starters");
        System.out.println("4                Main Course");
        System.out.println("------------------------------");
        System.out.print("Enter your choice as a Serial Number: ");
        return s.nextInt();
    	
    }

    private static void displayItems(int category) {
        System.out.println("---------------------------------------------------");
        System.out.println("Item ID   Item Name                 Item Price");
        System.out.println("---------------------------------------------------");
        switch (category) {
            case 1 -> {
                System.out.println("1         Tamatar Shorba            80");
                System.out.println("2         Hara Shorba               80");
                System.out.println("3         Gosht Yakhani             85");
                System.out.println("4         French Onion Soup         100");
            }
            case 2 -> {
                System.out.println("1         Clear Soup                70");
                System.out.println("2         Noodles Soup              80");
                System.out.println("3         Hot & Sour Soup           130");
                System.out.println("4         Lemon Coriander Soup      90");
            }
            case 3 -> {
                System.out.println("1         Paneer Tikka              140");
                System.out.println("2         Baby Corn                 140");
                System.out.println("3         Aloo Tikka                150");
                System.out.println("4         Gobi Tikka                160");
            }
            case 4 -> {
                System.out.println("1         Paneer Palak              140");
                System.out.println("2         Jeera Fried Rice          90");
                System.out.println("3         Kaju Paneer               130");
                System.out.println("4         Tandoori Naan             20");
            }
            default -> System.out.println("Invalid choice.");
        }
        System.out.println("--------------------------------------------");
    }

    private static void processOrder(int category) {
        System.out.print("Enter your selected Item ID: ");
        int itemId = s.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = s.nextInt();

        String[][] menuItems = {
            {"Tamatar Shorba", "Hara Shorba", "Gosht Yakhani", "French Onion Soup"},
            {"Clear Soup", "Noodles Soup", "Hot & Sour Soup", "Lemon Coriander Soup"},
            {"Paneer Tikka", "Baby Corn", "Aloo Tikka", "Gobi Tikka"},
            {"Paneer Palak", "Jeera Fried Rice", "Kaju Paneer", "Tandoori Naan"}
        };

        int[][] prices = {
            {80, 80, 85, 100},
            {70, 80, 130, 90},
            {140, 140, 150, 160},
            {140, 90, 130, 20}
        };

        if (itemId >= 1 && itemId <= 4) {
            String itemName = menuItems[category - 1][itemId - 1];
            int itemPrice = prices[category - 1][itemId - 1];

            OrderItem item = new OrderItem(itemId, itemName, itemPrice, quantity);
            orders.add(item);
        } else {
            System.out.println("Invalid Item ID.");
        }
    }

    private static void printBill() {
        System.out.println("\n*************** Final Bill ***************");
        System.out.println("ItemID  Quantity   Item Name           Price      Subtotal");
        System.out.println("-----------------------------------------------------------");
        
        // i am using java 8 features, such as  stream (),map, for-each
        orders.forEach(item -> System.out.println(item));
           
        float total = (float) orders.stream().mapToInt(OrderItem::getSubtotal).sum();

        Billable gst = new HotelGst();
        // to achieve polymorphisum , i am creating interface reference  to crating object
        float gstAmount = gst.calculateGST(total);
        float finalAmount = total + gstAmount;

        System.out.println("-----------------------------------------------------------");
        System.out.printf("Subtotal: %.2f\n", total);
        System.out.printf("18%% GST: %.2f\n", gstAmount);
        System.out.printf("Total Amount (with GST): %.2f\n", finalAmount);
        System.out.println(" ");
        System.out.println("********** Thank You For Visiting Our Hotel **********");
        
    }
}