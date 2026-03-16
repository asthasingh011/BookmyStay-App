import java.util.*;

/**
 * CLASS - Reservation
 * Represents a booking request.
 */
class Reservation {

    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }
}


/**
 * CLASS - BookingRequestQueue
 * Shared queue storing booking requests.
 */
class BookingRequestQueue {

    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation getNextRequest() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}


/**
 * CLASS - RoomInventory
 * Shared inventory of rooms.
 */
class RoomInventory {

    private Map<String, Integer> inventory = new HashMap<>();
    private Map<String, Integer> roomCounter = new HashMap<>();

    public RoomInventory() {

        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);

        roomCounter.put("Single", 0);
        roomCounter.put("Double", 0);
        roomCounter.put("Suite", 0);
    }

    public String allocateRoom(String roomType) {

        if (inventory.get(roomType) <= 0) {
            return null;
        }

        inventory.put(roomType, inventory.get(roomType) - 1);

        int number = roomCounter.get(roomType) + 1;
        roomCounter.put(roomType, number);

        return roomType + "-" + number;
    }

    public void printInventory() {

        System.out.println("\nRemaining Inventory:");
        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}


/**
 * CLASS - RoomAllocationService
 * Allocates rooms from inventory.
 */
class RoomAllocationService {

    public void allocateRoom(Reservation reservation, RoomInventory inventory) {

        String roomId = inventory.allocateRoom(reservation.getRoomType());

        if (roomId != null) {
            System.out.println(
                    "Booking confirmed for Guest: "
                            + reservation.getGuestName()
                            + ", Room ID: "
                            + roomId
            );
        } else {
            System.out.println(
                    "No rooms available for "
                            + reservation.getRoomType()
            );
        }
    }
}


/**
 * CLASS - ConcurrentBookingProcessor
 * Processes bookings in threads.
 */
class ConcurrentBookingProcessor implements Runnable {

    private BookingRequestQueue bookingQueue;
    private RoomInventory inventory;
    private RoomAllocationService allocationService;

    public ConcurrentBookingProcessor(
            BookingRequestQueue bookingQueue,
            RoomInventory inventory,
            RoomAllocationService allocationService
    ) {
        this.bookingQueue = bookingQueue;
        this.inventory = inventory;
        this.allocationService = allocationService;
    }

    @Override
    public void run() {

        while (true) {

            Reservation reservation;

            synchronized (bookingQueue) {

                if (bookingQueue.isEmpty()) {
                    break;
                }

                reservation = bookingQueue.getNextRequest();
            }

            synchronized (inventory) {
                allocationService.allocateRoom(reservation, inventory);
            }
        }
    }
}


/**
 * MAIN CLASS
 * Use Case 11 - Concurrent Booking Simulation
 */
public class BookmyStay_UC11 {

    public static void main(String[] args) {

        System.out.println("Concurrent Booking Simulation");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        RoomInventory inventory = new RoomInventory();
        RoomAllocationService allocationService = new RoomAllocationService();

        // Add booking requests
        bookingQueue.addRequest(new Reservation("Abhi", "Single"));
        bookingQueue.addRequest(new Reservation("Vanmathi", "Double"));
        bookingQueue.addRequest(new Reservation("Kural", "Suite"));
        bookingQueue.addRequest(new Reservation("Subha", "Single"));

        // Create booking processor threads
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );
        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );
        // Start concurrent processing
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }
        inventory.printInventory();
    }
}