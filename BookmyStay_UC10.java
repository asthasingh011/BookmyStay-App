import java.util.*;

/**
 * ==========================================================
 * CLASS - RoomInventory
 * ==========================================================
 * Maintains available rooms in the system.
 */

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public void increaseRoom(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }

    public int getAvailableRooms(String roomType) {
        return inventory.get(roomType);
    }
}


/**
 * ==========================================================
 * CLASS - CancellationService
 * ==========================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 */

class CancellationService {

    /** Stack that stores recently released room IDs */
    private Stack<String> releasedRoomIds;

    /** Maps reservation ID to room type */
    private Map<String, String> reservationRoomTypeMap;

    /** Initializes cancellation tracking structures */
    public CancellationService() {
        releasedRoomIds = new Stack<>();
        reservationRoomTypeMap = new HashMap<>();
    }

    /**
     * Registers a confirmed booking
     */
    public void registerBooking(String reservationId, String roomType) {
        reservationRoomTypeMap.put(reservationId, roomType);
    }

    /**
     * Cancels a confirmed booking
     */
    public void cancelBooking(String reservationId, RoomInventory inventory) {

        if (!reservationRoomTypeMap.containsKey(reservationId)) {
            System.out.println("Invalid reservation ID.");
            return;
        }

        String roomType = reservationRoomTypeMap.get(reservationId);

        // Restore inventory
        inventory.increaseRoom(roomType);

        // Track rollback
        releasedRoomIds.push(reservationId);

        reservationRoomTypeMap.remove(reservationId);

        System.out.println("Booking cancelled successfully. Inventory restored for room type: " + roomType);
    }

    /**
     * Displays rollback history
     */
    public void showRollbackHistory() {

        System.out.println("\nRollback History (Most Recent First):");

        while (!releasedRoomIds.isEmpty()) {
            System.out.println("Released Reservation ID: " + releasedRoomIds.pop());
        }
    }
}


/**
 * ==========================================================
 * MAIN CLASS - UseCase10BookingCancellation
 * ==========================================================
 */

public class BookmyStay_UC10 {

    public static void main(String[] args) {

        System.out.println("Booking Cancellation");

        RoomInventory inventory = new RoomInventory();
        CancellationService cancellationService = new CancellationService();

        String reservationId = "Single-1";
        String roomType = "Single";

        // Simulate confirmed booking
        cancellationService.registerBooking(reservationId, roomType);

        // Cancel booking
        cancellationService.cancelBooking(reservationId, inventory);

        // Show rollback history
        cancellationService.showRollbackHistory();

        System.out.println("\nUpdated Single Room Availability: "
                + inventory.getAvailableRooms("Single"));
    }
}