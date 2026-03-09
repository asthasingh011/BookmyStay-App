/**
 * Book My Stay App
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Confirms booking requests by assigning unique room IDs while
 * updating inventory and preventing double-booking.
 *
 * @author Student
 * @version 6.1
 */

import java.util.*;

// Simple Reservation class
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + "]";
    }
}

// Centralized Inventory
class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public boolean decrementAvailability(String roomType) {
        int available = inventory.getOrDefault(roomType, 0);
        if (available > 0) {
            inventory.put(roomType, available - 1);
            return true;
        } else {
            return false;
        }
    }

    public void displayInventory() {
        System.out.println("\nCurrent Inventory:");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " rooms available");
        }
    }
}

// Booking request queue
class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName() +
                " (" + reservation.getRoomType() + ")");
    }

    public Reservation pollRequest() {
        return queue.poll();
    }

    public boolean hasRequests() {
        return !queue.isEmpty();
    }
}

// Room allocation service
class RoomAllocationService {
    private RoomInventory inventory;
    private Map<String, Set<String>> allocatedRooms;

    public RoomAllocationService(RoomInventory inventory) {
        this.inventory = inventory;
        allocatedRooms = new HashMap<>();
    }

    // Generate unique room ID
    private String generateRoomID(String roomType) {
        int id = allocatedRooms.getOrDefault(roomType, new HashSet<>()).size() + 1;
        return roomType.substring(0, 2).toUpperCase() + "-" + id;
    }

    public void allocateRoom(Reservation reservation) {
        String type = reservation.getRoomType();

        if (inventory.getAvailability(type) > 0) {
            // Decrement inventory
            inventory.decrementAvailability(type);

            // Allocate room
            allocatedRooms.putIfAbsent(type, new HashSet<>());
            String roomID = generateRoomID(type);
            allocatedRooms.get(type).add(roomID);

            System.out.println("Reservation confirmed for " + reservation.getGuestName() +
                    " -> Room Assigned: " + roomID);
        } else {
            System.out.println("Reservation for " + reservation.getGuestName() +
                    " cannot be confirmed. No " + type + " available.");
        }
    }

    public void displayAllocatedRooms() {
        System.out.println("\nAllocated Rooms:");
        for (Map.Entry<String, Set<String>> entry : allocatedRooms.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}

// Main class
public class BookmyStay_UC6{

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v6.1");
        System.out.println("=================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));
        bookingQueue.addRequest(new Reservation("Ethan", "Single Room"));

        // Allocate rooms
        RoomAllocationService allocationService = new RoomAllocationService(inventory);

        System.out.println("\nProcessing booking requests...");
        while (bookingQueue.hasRequests()) {
            Reservation request = bookingQueue.pollRequest();
            allocationService.allocateRoom(request);
        }

        // Show final allocation
        allocationService.displayAllocatedRooms();
        inventory.displayInventory();

        System.out.println("\nAll reservations processed. No double-booking occurred.");
    }
}