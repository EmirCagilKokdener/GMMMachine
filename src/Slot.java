public class Slot {
    public static final int maxCapacity =10;
    private Product product;
    private int quantity;

    /**
     * Constructs a new empty slot.
     */
    public Slot() {
        this.product = null;
        this.quantity = 0;
    }

    /**
     * Gets the product in the slot.
     *
     * @return The product in the slot.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Gets the quantity of the product in the slot.
     *
     * @return The quantity of the product in the slot.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Adds a product to the slot.
     *
     * @param product The product to add.
     */
    public void addProduct(Product product){
        this.product = product;
        this.quantity++;
    }

    /**
     * Removes a product from the slot.
     *
     * @param product The product to remove.
     */
    public void removeProduct(Product product){
        this.product = product;
        this.quantity--;
    }

    /**
     * Checks if the slot is empty.
     *
     * @return True if the slot is empty.
     */
    public boolean isEmpty(){
        return quantity==0;
    }
    /**
     * Checks if the slot is full.
     *
     * @return True if slot is full.
     */
    public boolean isFull(){
        return quantity==maxCapacity;
    }
    /**
     * Checks if the product in the slot and the given product is same.
     *
     * @param otherProduct The given product.
     * @return True if the slot contains the same product as the given product.
     */
    public boolean hasSameProduct(Product otherProduct){
        return product.getName().equals(otherProduct.getName());
    }
}
