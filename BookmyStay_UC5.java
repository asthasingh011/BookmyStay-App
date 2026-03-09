/**
 * Book My Stay App
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * Demonstrates fair booking request handling using a FIFO queue.
 *
 * @author Student
 * @version 5.1
 */

import java.util.LinkedList;
import java.util.Queue;

// Simple Reservation class
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

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room Type: " + roomType + "]";
    }
}

// BookingRequestQueue class
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        requestQueue = new LinkedList<>();
    }

    // Add a new booking request to the queue
    public void addRequest(Reservation reservation) {
        requestQueue.offer(reservation);
        System.out.println("Booking request added for " + reservation.getGuestName() + " (" + reservation.getRoomType() + ")");
    }

    // Process requests in order (just for demonstration)
    public void processRequests() {
        System.out.println("\nProcessing booking requests (FIFO):");
        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.poll();
            System.out.println("Processing " + reservation);
        }
        System.out.println("All requests processed.\n");
    }

    // Show current queued requests without removing
    public void showQueuedRequests() {
        System.out.println("\nCurrent Booking Queue:");
        if (requestQueue.isEmpty()) {
            System.out.println("No pending requests.");
        } else {
            for (Reservation r : requestQueue) {
                System.out.println(r);
            }
        }
    }
}

// Main class
public class BookmyStay_UC5{

    public static void main(String[] args) {
        System.out.println("=================================");
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v5.1");
        System.out.println("=================================");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        // Show queue before processing
        bookingQueue.showQueuedRequests();

        // Process requests in FIFO order
        bookingQueue.processRequests();

        // Show queue after processing
        bookingQueue.showQueuedRequests();
    }
}