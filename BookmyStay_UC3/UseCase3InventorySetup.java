/**
 * Book My Stay Application
 * Use Case 3: Centralized Room Inventory Management
 *
 * Demonstrates how HashMap provides a centralized structure
 * for storing and managing room availability.
 *
 * @author Student
 * @version 3.1
 */

public class UseCase3InventorySetup {

    public static void main(String[] args) {

        System.out.println("=================================");
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v3.1");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display current inventory
        inventory.displayInventory();

        // Example: Retrieve availability
        System.out.println("\nChecking availability for Single Room...");
        int singleRooms = inventory.getAvailability("Single Room");
        System.out.println("Available Single Rooms: " + singleRooms);

        // Example: Update availability
        System.out.println("\nUpdating Double Room availability...");
        inventory.updateAvailability("Double Room", 4);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("\nInventory setup complete.");
    }
}