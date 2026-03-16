import java.util.*;

/**
 * ==========================================================
 * CLASS - InvalidBookingException
 * ==========================================================
 * Custom exception for invalid booking scenarios.
 */

class InvalidBookingException extends Exception {

    public InvalidBookingException(String message) {
        super(message);
    }
}


/**
 * ==========================================================
 * CLASS - RoomInventory
 * ==========================================================
 * Maintains available rooms.
 */

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 5);
        inventory.put("Suite", 2);
    }

    public boolean isValidRoomType(String roomType) {
        return inventory.containsKey(roomType);
    }
}


/**
 * ==========================================================
 * CLASS - ReservationValidator
 * ==========================================================
 * Validates booking input.
 */

class ReservationValidator {

    public void validate(
            String guestName,
            String roomType,
            RoomInventory inventory
    ) throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty.");
        }

        if (!inventory.isValidRoomType(roomType)) {
            throw new InvalidBookingException("Invalid room type selected.");
        }
    }
}


/**
 * ==========================================================
 * CLASS - BookingRequestQueue
 * ==========================================================
 * Stores booking requests.
 */

class BookingRequestQueue {

    private Queue<String> queue;

    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    public void addRequest(String request) {
        queue.add(request);
    }
}


/**
 * ==========================================================
 * MAIN CLASS - UseCase9ErrorHandlingValidation
 * ==========================================================
 */

public class BookmyStay_UC9 {

    public static void main(String[] args) {

        // Display application header
        System.out.println("Booking Validation");

        Scanner scanner = new Scanner(System.in);

        // Initialize required components
        RoomInventory inventory = new RoomInventory();
        ReservationValidator validator = new ReservationValidator();
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        try {

            System.out.print("Enter guest name: ");
            String guestName = scanner.nextLine();

            System.out.print("Enter room type (Single/Double/Suite): ");
            String roomType = scanner.nextLine();

            // Validate booking
            validator.validate(guestName, roomType, inventory);

            // If valid add booking request
            bookingQueue.addRequest(guestName + " - " + roomType);

            System.out.println("Booking request accepted.");

        } catch (InvalidBookingException e) {

            // Handle validation errors
            System.out.println("Booking failed: " + e.getMessage());

        } finally {
            scanner.close();
        }
    }
}