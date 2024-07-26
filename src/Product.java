public class Product {
    private final String name;
    private final int price;
    private final double protein;
    private final double carbohydrate;
    private final double fat;
    private final double calorie;

    /**
     *Constructs a new Product with the given values.
     *
     * @param name Name of the product.
     * @param price Price of the product.
     * @param protein Protein of the product.
     * @param carbohydrate Carbohydrate of the product
     * @param fat Fat of the product.
     */
    public Product(String name, int price, double protein, double carbohydrate, double fat) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.calorie = calorieCalculator();
    }

    /**
     * Calculates the calorie of the product based on its protein, fat and carbohydrate.
     *
     * @return Calorie of the product.
     */
    private double calorieCalculator() {
        return (4 * protein) + (4 * carbohydrate) + (9 * fat);

    }

    /**
     * Gets the name of the product.
     *
     * @return Name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the product.
     *
     * @return Price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Gets the protein of the product.
     *
     * @return Protein of the product.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Gets the carbohydrate of the product.
     *
     * @return Carbohydrate of the product.
     */
    public double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Gets the fat of the product.
     *
     * @return Fat of the product.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Gets the calorie of the product.
     *
     * @return Calorie of the product.
     */
    public double getCalorie() {
        return calorie;
    }
}
