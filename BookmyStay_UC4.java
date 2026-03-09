/**
 * Book My Stay App
 * Use Case 4: Room Search & Availability Check
 *
 * Demonstrates read-only room search using centralized inventory
 * without modifying system state.
 *
 * @author Student
 * @version 4.1
 */

import java.util.HashMap;
import java.util.Map;

// Abstract Room class (version 2.0)
abstract class Room {
    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;

    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Price per Night: $" + pricePerNight);
    }

    public String getRoomType() {
        return roomType;
    }
}

// Concrete room classes
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 1, 1000); }
}
class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 2, 1800); }
}
class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 3, 3500); }
}

// Centralized inventory (version 3.0)
class RoomInventory {
    private HashMap<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    // Read-only method for availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Display inventory (read-only)
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}

// Search service for read-only search
class RoomSearchService {

    private RoomInventory inventory;
    private Room[] rooms;

    public RoomSearchService(RoomInventory inventory, Room[] rooms) {
        this.inventory = inventory;
        this.rooms = rooms;
    }

    // Display only rooms with availability > 0
    public void searchAvailableRooms() {
        System.out.println("\nAvailable Rooms for Guests:");
        boolean anyAvailable = false;

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.getRoomType());
            if (available > 0) {
                room.displayRoomDetails();
                System.out.println("Available Rooms: " + available + "\n");
                anyAvailable = true;
            }
        }

        if (!anyAvailable) {
            System.out.println("No rooms available at the moment.");
        }
    }
}

// Main class
public class BookmyStay_UC4{

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v4.1");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Create room objects
        Room[] rooms = { new SingleRoom(), new DoubleRoom(), new SuiteRoom() };

        // Initialize search service
        RoomSearchService searchService = new RoomSearchService(inventory, rooms);

        // Perform read-only search
        searchService.searchAvailableRooms();

        System.out.println("Room search complete. Inventory remains unchanged.");
    }
}