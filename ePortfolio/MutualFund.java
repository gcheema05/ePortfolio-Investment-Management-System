package ePortfolio;

import java.text.DecimalFormat;

/**
 * The MutualFund class inherits attributes such as symbol, name, quantity, price, and bookValue from the Investment class.
 * It also includes additional attributes and methods specific to mutual funds.
 * This class handles operations like calculating gains, payment received, and updating book values for mutual fund investments.
 */
public class MutualFund extends Investment {
    // Declare constant for convenience 
    public static final double REDEMPTION_FEE = 45.00;

    /**
     * Default constructor for creating a MutualFund object with no initial values.
     * The object will be initialized with default values for its attributes.
     */
    public MutualFund() {
        super(); // Call investment's constructor
    }

    /**
     * Constructor to initialize the MutualFund object with the specified symbol, name, quantity, and price.
     * 
     * @param symbol The symbol of the mutual fund.
     * @param name The name of the mutual fund.
     * @param quantity The quantity of shares in the mutual fund.
     * @param price The price of the mutual fund per share.
     * @throws Exception If an error occurs during initialization, such as invalid input values.
     */
    public MutualFund(String symbol, String name, int quantity, double price) throws Exception {
        super(symbol, name, quantity, price); // Call investment's constructor
        this.setBookValue(quantity, price,true);
    }

    /**
     * Copy constructor that creates a deep copy of a given MutualFund object.
     * Initializes the new MutualFund instance with the same values as the provided object.
     *
     * @param other The MutualFund object to be copied.
     * @throws Exception If an error occurs during the copy process, such as invalid data or unable to copy attributes.
     */
    public MutualFund(MutualFund other) throws Exception {
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
     * Setter method for setting the book value of an investment, based on the quantity, price, 
     * and whether the action is a buy or sell.
     * 
     * @param quantity The number of units involved in the transaction.
     * @param price The price per unit of the investment.
     * @param sellOrBuy A boolean value indicating whether the action is a buy (true) or sell (false).
     * @throws Exception If an error occurs during the calculation or if invalid input is provided.
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

        // If buying a mutual fund
        if (sellOrBuy == true) {
            this.setBookValue(this.getBookValue() + ((quantity * price)));
        }
        // Selling a mutual fund 
        else {
            this.setBookValue(this.getBookValue() * ((this.getQuantity()-quantity)/(double)this.getQuantity()));
        }
    }

    /**
     * Method to calculate the payment received when selling a mutual fund.
     * 
     * @param price The price at which the mutual fund is being sold.
     * @param quantity The number of units of the mutual fund being sold.
     * @return The total payment received from the sale of the mutual fund.
     * @throws Exception If there is an error in the calculation or invalid input.
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
        return (price * quantity) - REDEMPTION_FEE;
    }

    /**
     * Method to calculate the gain of an investment when selling a mutual fund,
     * using the payment received from the sale and the new book value.
     * 
     * @param price The price at which the mutual fund is being sold.
     * @param quantity The number of units of the mutual fund being sold.
     * @param newBookValue The new book value after the transaction.
     * @return The calculated gain from the investment.
     * @throws Exception If there is an error in the calculation or invalid input.
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
            MutualFund otherFund = (MutualFund)other;
            return (this.getSymbol().equals(otherFund.getSymbol()) && this.getName().equals(otherFund.getName()) 
                    && this.getPrice() == otherFund.getPrice() && this.getQuantity() == otherFund.getQuantity() 
                    && this.getBookValue() == otherFund.getBookValue());
        }
    }

    /**
     * Method to convert all attributes of the object into a string representation.
     * 
     * @return A string containing the values of all attributes of the object.
     */
    public String toString () {
        // Allows us to format numbers to 2 decimal places
        DecimalFormat df = new DecimalFormat("#.##");

        return "\nSymbol: " + this.getSymbol() + "\nName: " + this.getName() + "\nQuantity: " + this.getQuantity() + "\nPrice: $" + this.getPrice()
        + "\nBook Value: $" + df.format(this.getBookValue()) + "\n";
    }
}