/**
 * Book My Stay Application
 * Use Case 2: Basic Room Types & Static Availability
 *
 * Demonstrates object modeling using abstraction, inheritance,
 * and static availability representation.
 *
 * @author Student
 * @version 2.1
 */

public class UseCase2RoomInitialization {

    public static void main(String[] args) {

        System.out.println("===================================");
        System.out.println("Welcome to Book My Stay App");
        System.out.println("Hotel Booking System v2.1");
        System.out.println("===================================");

        // Create room objects
        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        // Static availability variables
        int singleRoomAvailable = 5;
        int doubleRoomAvailable = 3;
        int suiteRoomAvailable = 2;

        // Display room details and availability
        System.out.println("\nSingle Room Details:");
        singleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + singleRoomAvailable);

        System.out.println("\nDouble Room Details:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + doubleRoomAvailable);

        System.out.println("\nSuite Room Details:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available Rooms: " + suiteRoomAvailable);

        System.out.println("\nThank you for using Book My Stay!");
    }
}