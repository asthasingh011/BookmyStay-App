import java.util.*;

/**
 * ==========================================================
 * CLASS - Reservation
 * ==========================================================
 * Represents a confirmed reservation.
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
 * ==========================================================
 * CLASS - BookingHistory
 * ==========================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * Description:
 * This class maintains a record of confirmed reservations.
 * It provides ordered storage for historical reporting.
 */

class BookingHistory {

    /**
     * List that stores confirmed reservations.
     */
    private List<Reservation> confirmedReservations;

    /**
     * Initializes an empty booking history.
     */
    public BookingHistory() {
        confirmedReservations = new ArrayList<>();
    }

    /**
     * Adds a confirmed reservation to booking history.
     */
    public void addReservation(Reservation reservation) {
        confirmedReservations.add(reservation);
    }

    /**
     * Returns all confirmed reservations.
     */
    public List<Reservation> getConfirmedReservations() {
        return confirmedReservations;
    }
}


/**
 * ==========================================================
 * CLASS - BookingReportService
 * ==========================================================
 *
 * Generates reports from booking history.
 */

class BookingReportService {

    /**
     * Displays summary report of confirmed bookings.
     */
    public void generateReport(BookingHistory history) {

        System.out.println("\nBooking History Report");

        List<Reservation> reservations = history.getConfirmedReservations();

        for (Reservation r : reservations) {
            System.out.println(
                    "Guest: " + r.getGuestName() +
                            ", Room Type: " + r.getRoomType()
            );
        }
    }
}


/**
 * ==========================================================
 * MAIN CLASS - UseCase8BookingHistoryReport
 * ==========================================================
 */

public class BookmyStay_UC8 {

    public static void main(String[] args) {

        System.out.println("Booking History and Reporting\n");

        BookingHistory history = new BookingHistory();

        // Adding confirmed reservations
        history.addReservation(new Reservation("Abhi", "Single"));
        history.addReservation(new Reservation("Subha", "Double"));
        history.addReservation(new Reservation("Vanmathi", "Suite"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history);
    }
}