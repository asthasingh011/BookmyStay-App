/**
 * RoomInventory manages centralized availability of all room types.
 * It uses a HashMap to store room type and available room count.
 *
 * @author Student
 * @version 3.0
 */

import java.util.HashMap;
import java.util.Map;

public class RoomInventory {

    private HashMap<String, Integer> inventory;

    /**
     * Constructor initializes room inventory
     */
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initial room availability
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    /**
     * Returns available rooms for a given type
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates room availability
     */
    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Displays the entire inventory
     */
    public void displayInventory() {
        System.out.println("\nCurrent Room Inventory:");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " rooms available");
        }
    }
}