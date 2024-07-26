import java.util.ArrayList;
import java.util.List;
public class FileHelper {
    private String[] productInput;
    private String[] purchaseInput;
    private GMMMachine gmmMachine;
    private List<String> finalLogs;

    /**
     * Constructs a FileHelper object with the given product and purchase inputs.
     *
     * @param productInput An array of strings representing product information.
     * @param purchaseInput An array of strings representing purchase information.
     */
    public FileHelper (String[] productInput,String[]purchaseInput){
        this.productInput = productInput;
        this.purchaseInput = purchaseInput;
        this.gmmMachine = new GMMMachine();
        this.finalLogs = new ArrayList<>();
    }

    /**
     * Processes the product input by creating Product objects and loading them into the GMMMachine.
     *
     * @return 0 if the product input is processed successfully, -1 if machine is full.
     */
    public int processProductInput() {

        for (String line : productInput) {
            String[] items = line.split("\t");
            String name = items[0];
            int price = Integer.parseInt(items[1]);
            String[] nutrientValues = items[2].split(" ");
            double protein = Double.parseDouble(nutrientValues[0]);
            double carbohydrate = Double.parseDouble(nutrientValues[1]);
            double fat = Double.parseDouble(nutrientValues[2]);
            Product product = new Product(name, price, protein, carbohydrate, fat);
            if (gmmMachine.getTotalQuantity() >= GMMMachine.getMaxCapacity()) { // This is for stopping after machine is full.
                finalLogs.add("INFO: There is no available place to put "+product.getName()+"\n");
                finalLogs.add("INFO: The machine is full!\n");
                return -1; // Stop trying to load products and exit the method with failure.
            }
            gmmMachine.Loader(product);
            finalLogs.addAll(gmmMachine.getLogs());
            gmmMachine.clearLogs();
        }
        return 0; // Exit the method with success.
    }

    /**
     * Write the content of the GMMMachine to the logs.
     */
    public void gmmMachineWriter(){
        gmmMachine.contentWriter();
        finalLogs.addAll(gmmMachine.getLogs());
        gmmMachine.clearLogs();
    }

    /**
     * Processes the Purchase input and it runs the purchaser.
     */
    public void processPurchaseInput(){
        for (String line : purchaseInput){
            String[] items =line.split("\t");
            finalLogs.add("INPUT: "+line+"\n");
            String[] money = items[1].split(" ");
            String choice = items[2];
            int value = Integer.parseInt(items[3]);
            gmmMachine.Purchaser(money,choice,value);
            finalLogs.addAll(gmmMachine.getLogs());
            gmmMachine.clearLogs();
        }

    }

    /**
     * Gets the final log.
     *
     * @return The list of logs.
     */
    public List<String> getFinalLogs() {
        return finalLogs;
    }
}
