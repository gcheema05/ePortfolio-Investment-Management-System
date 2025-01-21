package ePortfolio;

import java.text.DecimalFormat;

/**
 * The Stock class inherits symbol, name, quantity, price, and bookValue from the Investment class,
 * and introduces its own unique attributes and methods specific to stocks.
 * 
 * @see Investment
 */
public class Stock extends Investment {
    // Declare constant for convenience
    public static final double STOCK_COMMISSION = 9.99; 

    /**
     * Default constructor for the Stock class.
     */
    public Stock() {
        super(); // Call investment's constructor
    }

    /**
     * Constructor to initialize the Stock object with the given values.
     * 
     * @param symbol The symbol of the stock.
     * @param name The name of the stock.
     * @param quantity The quantity of the stock.
     * @param price The price of the stock.
     * @throws Exception If an error occurs while initializing the stock.
     */
    public Stock(String symbol, String name, int quantity, double price) throws Exception {
        super(symbol, name, quantity, price); // Call investment's constructor
        this.setBookValue(quantity, price,true);
    }

    /**
     * Copy constructor that creates a deep copy of the given Stock object.
     * 
     * @param other The Stock object to copy from.
     * @throws Exception If an error occurs while copying the stock.
     */
    public Stock(Stock other) throws Exception {
        // Check if other is null, throw IllegalArgumentException with a message
        if (other == null) {
            throw new IllegalArgumentException("Cannot copy from a null Stock object.");
        }
        // Copy symbol, name, quantity, price, and book value
        this.setSymbol(other.getSymbol());
        this.setName(other.getName());
        this.setQuantity(other.getQuantity());
        this.setPrice(other.getPrice());
        this.setBookValue(other.getBookValue());
    }
    
    /**
     * Setter method for book value. Calculates the book value based on quantity, price, and the transaction type (buy or sell).
     * 
     * @param quantity The number of units for the investment.
     * @param price The price per unit of the investment.
     * @param sellOrBuy A boolean indicating the transaction type. If true, it represents a buy, and if false, it represents a sell.
     * @throws Exception If an error occurs while setting the book value.
     */
    public void setBookValue(int quantity, double price, boolean sellOrBuy) throws Exception {
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or 0.");
        }

        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        }

        // If buying a stock
        if (sellOrBuy == true) {
            this.setBookValue(this.getBookValue() + ((quantity * price) + STOCK_COMMISSION));
        }
        // Selling a stock
        else {
            this.setBookValue(this.getBookValue() * ((this.getQuantity()-quantity)/(double)this.getQuantity()));
        }
    }

    /**
     * Method to calculate the payment received when selling a stock based on the sale price and quantity.
     * 
     * @param price The price per unit of the stock being sold.
     * @param quantity The number of units of the stock being sold.
     * @return The total payment received from the sale.
     * @throws Exception If an error occurs during the calculation.
     */
    public double paymentReceived(double price, int quantity) throws Exception {
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or 0.");
        }

        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        }
        return (price * quantity) - STOCK_COMMISSION;
    }

    /**
     * Method to calculate the gain of investment when selling a stock, using the paymentReceived method.
     * 
     * @param price The price per unit of the stock being sold.
     * @param quantity The number of units of the stock being sold.
     * @param newBookValue The new book value after the sale of the stock.
     * @return The gain from the investment after the sale.
     * @throws Exception If an error occurs during the calculation.
     */
    public double gainOfInvestment(double price, int quantity, double newBookValue) throws Exception {
        // Validate quantity
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be negative or 0.");
        }

        // Validate price
        if (price <= 0) {
            throw new IllegalArgumentException("Price cannot be negative or 0.");
        }

        // Validate bookValue
        if (newBookValue < 0) {
            throw new IllegalArgumentException("Book Value cannot be negative.");
        }
        return paymentReceived(price, quantity) - (this.getBookValue() - newBookValue);
    }

    /**
     * Method to compare two stock objects, compares each attribute one by one.
     * 
     * @param other The object to compare this stock object with.
     * @return true if the two stock objects are considered equal (i.e., all attributes are the same), false otherwise.
     */
    public boolean equals(Object other) {
        // Check if other object is null, return false if so
        if (other == null) {
            return false;
        }
        // Check if other object is of the same class, if not return false
        else if (this.getClass() != other.getClass()) {
            return false;
        }
        // Compare each attribute and return the result
        else {
            Stock otherStock = (Stock)other;
            return (this.getSymbol().equals(otherStock.getSymbol()) && this.getName().equals(otherStock.getName()) 
                    && this.getPrice() == otherStock.getPrice() && this.getQuantity() == otherStock.getQuantity() 
                    && this.getBookValue() == otherStock.getBookValue());
        }
    }

    /**
     * Method to convert all attributes of the object into a string representation.
     * 
     * @return A string containing the values of all attributes of the object.
     */
    public String toString() {
        // Allows us to format numbers to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");

        return "\nSymbol: " + this.getSymbol() + "\nName: " + this.getName() + "\nQuantity: " + this.getQuantity() + "\nPrice: $" + this.getPrice()
        + "\nBook Value: $" + df.format(this.getBookValue()) + "\n";
    }
}