import java.io.*;
import java.util.*;

/**
 * ==========================================================
 * CLASS - RoomInventory
 * Maintains available rooms.
 * ==========================================================
 */

class RoomInventory {

    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single", 5);
        inventory.put("Double", 3);
        inventory.put("Suite", 2);
    }

    public Map<String, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Integer> inventory) {
        this.inventory = inventory;
    }

    public void printInventory() {

        System.out.println("\nCurrent Inventory:");

        for (String type : inventory.keySet()) {
            System.out.println(type + ": " + inventory.get(type));
        }
    }
}


/**
 * ==========================================================
 * CLASS - FilePersistenceService
 * ==========================================================
 * Saves and loads inventory data from a text file.
 */

class FilePersistenceService {

    /**
     * Saves room inventory state to file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {

            for (Map.Entry<String, Integer> entry :
                    inventory.getInventory().entrySet()) {

                writer.println(entry.getKey() + "=" + entry.getValue());
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    /**
     * Loads inventory state from file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        Map<String, Integer> loadedInventory = new HashMap<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {

                    String roomType = parts[0];
                    int count = Integer.parseInt(parts[1]);

                    loadedInventory.put(roomType, count);
                }
            }

            inventory.setInventory(loadedInventory);

        } catch (Exception e) {
            System.out.println("Error loading inventory data.");
        }
    }
}


/**
 * ==========================================================
 * MAIN CLASS
 * Use Case 12 - Data Persistence & System Recovery
 * ==========================================================
 */

public class BookmyStay_UC12 {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService =
                new FilePersistenceService();

        String filePath = "inventory.txt";

        // Load inventory on startup
        persistenceService.loadInventory(inventory, filePath);

        // Show current inventory
        inventory.printInventory();

        // Save inventory before shutdown
        persistenceService.saveInventory(inventory, filePath);
    }
}