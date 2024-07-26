import java.util.ArrayList;
import java.util.List;

public class GMMMachine {
    private static final int rows =6;
    private static final int columns =4;
    private static final int maxCapacity =240;
    private int totalQuantity;
    private Slot[][] machine;
    private List<String> logs;

    /**
     * Constructs a new GMMMachine object.
     */
    public  GMMMachine(){
        this.logs=new ArrayList<>();
        machine = new Slot[rows][columns];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < columns;j++){
                machine[i][j] = new Slot();
            }
        }

    }

    /**
     * Loads the products into the machine if there is available slot for the product.
     * @param product Product to be loaded.
     * @return 0 if it is successfully loaded -1 if loading fails.
     */
    public int Loader(Product product) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Slot slot = machine[i][j];
                if (slot.isEmpty()) {
                        // Slot is empty, add the product
                    slot.addProduct(product);
                    totalQuantity++;
                    return 0; // Exit the method with success.
                } else {
                    if (slot.hasSameProduct(product)) {
                        // Slot has the same product
                        if (!slot.isFull()) {
                            // Slot is not full, add the product
                            slot.addProduct(product);
                            totalQuantity++;
                            return 0; // Exit the method with success.
                        }
                    }
                }
            }
        }
        // If no available slots found
        logs.add("INFO: There is no available place to put " + product.getName()+"\n");
        return -1; // Exit the method with failure.
    }

    /**
     * Writes the current content of the machine to the log
     */
    public void contentWriter() {
        logs.add("-----Gym Meal Machine-----\n");
        int slotCounter = 0; // Counter for the number of slots printed in the current row.

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Slot slot = machine[i][j];
                if (slot.isEmpty()) {
                    logs.add("___(0, 0)___");
                } else {
                    logs.add(slot.getProduct().getName() + "(" + Math.round(slot.getProduct().getCalorie()) + ", " + slot.getQuantity() + ")___");
                }
                // Increase the number of slots.
                slotCounter++;

                // Check if four slots have been printed in the current row.
                if (slotCounter % 4 == 0) {
                    logs.add("\n"); // Go to the next row.
                }
            }
        }
        logs.add("----------\n");
    }

    /**
     * Processes the purchase based on the input.
     *
     * @param money Array of the money provided by the input file.
     * @param choice The purchase choice (PROTEIN,NUMBER,FAT...).
     * @param value Value corresponding to the choice.
     */
    public void Purchaser(String[] money,String choice,Integer value) {
        int totalMoney = 0;
        boolean isInvalid = false;
        for (String m : money) {
            Integer amount = Integer.parseInt(m);
            if (amount == 1 || amount == 5 || amount == 10 || amount == 20 || amount == 50 || amount == 100 || amount == 200) {// Money amount that machine accepts.
                totalMoney = totalMoney + amount;
            } else {
                isInvalid = true;
            }
        }
        if (isInvalid) {
            logs.add("INFO: Money is invalid.");
        }
        purchaseByType(choice,value,totalMoney);
    }

    /**
     * Processes a purchase based on the specified choice and the value.
     * If the choice is "NUMBER", it attempts to purchase a product by slot number.
     * If the choice is "PROTEIN", "FAT", "CALORIE", or "CARB", it searches by its value in range of plus or negative 5
     * If it meets the requirements then after purchase it removes the purchased product from the machine.
     *
     * @param choice The purchase choice (PROTEIN, NUMBER, FAT...).
     * @param value Value corresponding to the choice.
     * @param totalMoney Total amount of valid money.
     * @return 0 if the purchase is successful -1 if it fails
     */
    public int purchaseByType(String choice,int value, int totalMoney) {
        if (choice.equals("NUMBER")) {
            // Convert the value to slot indices
            int rowIndex = value / columns;
            int columnIndex = value % columns;
            if (value>23) {
                logs.add("INFO: Number cannot be accepted. Please try again with another number.\n");
                logs.add("RETURN: Returning your change: " + totalMoney + " TL\n");
                return -1;
            }
            Slot slot = machine[rowIndex][columnIndex];
            if (!slot.isEmpty()) {
                Product product = slot.getProduct();
                if (totalMoney >= product.getPrice()) {
                    slot.removeProduct(product);
                    logs.add("PURCHASE: You have bought one " + product.getName()+"\n");
                    logs.add("RETURN: Returning your change: " + (int)(totalMoney - product.getPrice()) + " TL\n");
                    return 0;
                } else {
                    logs.add("INFO Insufficient money, try again with more money.\n");
                    logs.add("RETURN: Returning your change: " + totalMoney + " TL\n");
                    return -1;
                }
            } else {
                logs.add("INFO: This slot is empty, your money will be returned.\n");
                logs.add("RETURN: Returning your change: " + totalMoney + " TL\n");
                return -1;
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Slot slot = machine[i][j];
                if (!slot.isEmpty()) {
                    Product product = slot.getProduct();
                    double ourValue = 0;
                    switch (choice){
                        case "PROTEIN":
                            ourValue = product.getProtein();
                            break;
                        case "FAT":
                            ourValue = product.getFat();
                            break;
                        case    "CALORIE":
                            ourValue = product.getCalorie();
                            break;
                        case "CARB":
                            ourValue = product.getCarbohydrate();
                    }
                    if (ourValue >= value - 5 && ourValue <= value + 5) {
                        if (totalMoney >= product.getPrice()) {
                            slot.removeProduct(product);
                            logs.add("PURCHASE: You have bought one " + product.getName()+"\n");
                            logs.add("RETURN: Returning your change: " + (int)(totalMoney - product.getPrice()) + " TL\n");
                            return 0;
                        } else {
                            logs.add("INFO: Insufficient money, try again with more money.\n");
                            logs.add("RETURN: Returning your change: " + totalMoney + " TL\n");
                            return -1;
                        }
                    }
                }
            }
        }
        logs.add("INFO: Product not found, your money will be returned.\n");
        logs.add("RETURN: Returning your change: " + totalMoney + " TL\n");
        return -1;
    }

    /**
     * Gets the total quantity of products loaded into the machine.
     *
     * @return The total quantity of loaded products.
     */

    public int getTotalQuantity() {
        return totalQuantity;
    }
    /**
     * Gets the maximum capacity of the machine.
     *
     * @return The maximum capacity of the machine.
     */

    public static int getMaxCapacity(){
        return maxCapacity;
    }
    /**
     * Gets the logs recorded during machine operations.
     *
     * @return The list of logs.
     */

    public List<String> getLogs() {
        return logs;
    }

    /**
     * Clears the recorded logs .
     */
    public void clearLogs(){
        logs.clear();
    }
}
