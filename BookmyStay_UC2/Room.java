/**
 * Abstract Room class representing common properties of all room types.
 * This class provides a template for different room categories in the system.
 *
 * @author Student
 * @version 2.0
 */
public abstract class Room {

    protected String roomType;
    protected int numberOfBeds;
    protected double pricePerNight;

    /**
     * Constructor to initialize room properties
     */
    public Room(String roomType, int numberOfBeds, double pricePerNight) {
        this.roomType = roomType;
        this.numberOfBeds = numberOfBeds;
        this.pricePerNight = pricePerNight;
    }

    /**
     * Method to display room details
     */
    public void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + numberOfBeds);
        System.out.println("Price per Night: $" + pricePerNight);
    }
}