package ePortfolio;

/**
 * Parent class for MutualFund and Stock classes, containing common attributes and methods for investments.
 */
public abstract class Investment {
    // Declare object attributes
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;

    /**
     * Default constructor to initialize an Investment object with default values.
     */
    public Investment() {
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0;
        this.bookValue = 0;
    }

    /**
     * Constructor to initialize the object attributes based on the given values.
     * 
     * @param symbol The symbol of the investment.
     * @param name The name of the investment.
     * @param quantity The quantity of the investment.
     * @param price The price of the investment.
     * @throws Exception If any of the values provided are invalid.
     */
    public Investment(String symbol, String name, int quantity, double price) throws Exception {
        // Validate symbol
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty.");
        }
        this.symbol = symbol;

        // Validate name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;

        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or 0.");
        }
        this.quantity = quantity;

        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        }
        this.price = price;

        this.bookValue = 0; // Set to default value
    }

    /**
     * Getter method for symbol, which returns the symbol of the investment.
     * 
     * @return The symbol of the investment.
     */
    public String getSymbol() {
        return this.symbol;
    }

    /**
     * Getter method for name, which returns the name of the investment.
     * 
     * @return The name of the investment.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter method for quantity, which returns the current quantity of the investment.
     * 
     * @return The current quantity of the investment.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Getter method for price, which returns the current price of the investment.
     * 
     * @return The current price of the investment.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Getter method for bookValue, which returns the current book value of the investment.
     * 
     * @return The current book value of the investment.
     */
    public double getBookValue() {
        return this.bookValue;
    }
    
    /**
     * Setter method for symbol, which sets the symbol to the given value.
     * 
     * @param symbol The symbol to set.
     * @throws Exception If the provided symbol is invalid or cannot be set.
     */
    public void setSymbol(String symbol) throws Exception {
        // Validate symbol
        if (symbol == null || symbol.isEmpty()) {
            throw new IllegalArgumentException("Symbol cannot be empty.");
        }
        this.symbol = symbol;
    }

    /**
     * Setter method for name, which sets the name to the given value.
     * 
     * @param name The name to set.
     * @throws Exception If the provided name is invalid or cannot be set.
     */
    public void setName(String name) throws Exception {
        // Validate name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        this.name = name;
    }

    /**
     * Setter method for quantity, which sets the quantity to the given value.
     * 
     * @param quantity The quantity to set.
     * @throws Exception If the provided quantity is invalid or cannot be set.
     */
    public void setQuantity(int quantity) throws Exception {
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or 0.");
        }
        this.quantity = quantity;
    }

    /**
     * Setter method for price, which sets the price to the given value.
     * 
     * @param price The price to set.
     * @throws Exception If the provided price is invalid or cannot be set.
     */
    public void setPrice(double price) throws Exception {
        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        }
        this.price = price;
    }

    /**
     * Setter method for book value, which sets the book value to the given amount.
     * 
     * @param bookValue The value to set for the book value.
     * @throws Exception If the provided book value is invalid or cannot be set.
     */
    public void setBookValue(double bookValue) throws Exception {
        // Validate bookValue
        if (bookValue < 0) {
            throw new IllegalArgumentException("Book Value cannot be negative.");
        }
        this.bookValue = bookValue;
    }

    /**
     * Abstract setter method for book value, which updates the book value based on the quantity, price, 
     * and whether the operation is a buy or sell.
     * 
     * @param quantity The number of units to buy or sell.
     * @param price The price per unit of the stock or mutual fund.
     * @param sellOrBuy A boolean value indicating whether the operation is a buy (true) or sell (false).
     * @throws Exception If there is an error during the update process.
     */
    public abstract void setBookValue(int quantity, double price, boolean sellOrBuy) throws Exception;

    /**
     * Abstract method to calculate the payment received when selling a stock.
     * 
     * @param price The price per unit of the stock being sold.
     * @param quantity The number of units of the stock being sold.
     * @return The total payment received from selling the stock.
     * @throws Exception If there is an error during the calculation.
     */
    public abstract double paymentReceived(double price, int quantity) throws Exception;

    /**
     * Abstract method to calculate the gain of an investment when selling a stock. 
     * The calculation uses the payment received for the stock and the new book value.
     * 
     * @param price The price per unit of the stock being sold.
     * @param quantity The number of units of the stock being sold.
     * @param newBookValue The updated book value after the transaction.
     * @return The calculated gain of the investment from the sale.
     * @throws Exception If there is an error in the calculation process.
     */
    public abstract double gainOfInvestment(double price, int quantity, double newBookValue) throws Exception;

    /**
     * Abstract method that should be implemented by subclasses to compare the current object 
     * with another object. The comparison should be done based on the attributes of the object.
     * 
     * @param other The object to be compared with the current object.
     * @return true if the objects are considered equal based on their attributes, 
     *         false otherwise.
     */
    public abstract boolean equals(Object other);

    /**
     * Abstract method that should be implemented by subclasses to convert all attributes 
     * of the object into a string representation.
     * 
     * @return A string representation of the object's attributes.
     */
    public abstract String toString();
}
