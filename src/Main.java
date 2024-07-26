public class Main {
    public static void main(String[] args) {

        // Reads input from file.
        String[] productInput = FileInput.readFile(args[0], true, true);
        String[] purchaseInput =FileInput.readFile(args[1],true,true);
        // Create a FileHelper object and process the input.
        FileHelper fileHelper = new FileHelper(productInput,purchaseInput);
        fileHelper.processProductInput();
        fileHelper.gmmMachineWriter();
        fileHelper.processPurchaseInput();
        fileHelper.gmmMachineWriter();
        // Write the processed logs to the specified output file.
        FileOutput.writeToFile(args[2], fileHelper.getFinalLogs(), false, false);
    }
}