package ePortfolio;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

/**
 * The TotalGainInterface class provides a user interface for displaying the total gain or loss of an investment portfolio.
 * 
 * @see JPanel
 */
public class TotalGainInterface extends JPanel {
    // Declare and initialize constant
    public static final int NUM_OF_CHAR = 10;

    // Declare and initialize all needed variables
    private JTextField gainField;
    private JTextArea message;
    private JScrollPane scrollableMessage;
    private double totalGains = 0;
    private boolean exceptionFound = false;

    /**
     * Constructs a TotalGainInterface with the specified portfolio to calculate and display the total gain or loss of the investments.
     * 
     * @param portfolio the list of investments used to calculate the total gain or loss
     */
    public TotalGainInterface(ArrayList<Investment> portfolio) {
        // Use DecimalFormat to format output such that it displays only 2 decimal points for double values
        DecimalFormat df = new DecimalFormat("#.##");
        
        // Set the layout for the main panel
        this.setLayout(new GridLayout(1, 2));

        // Create needed JPanels and set up layout for each
        JPanel investmentInfo = new JPanel();
        investmentInfo.setLayout(new BoxLayout(investmentInfo, BoxLayout.Y_AXIS));
        JPanel messageField = new JPanel();
        messageField.setLayout(new BoxLayout(messageField, BoxLayout.Y_AXIS));

        // Setup a default font
        Font defaultFont = new Font("Arial", Font.BOLD, 20);

        // To display JLabel on the main JPanel
        JLabel getGainLabel = new JLabel("Getting Total Gains");
        getGainLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 75, 0)); // Padding
        getGainLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        getGainLabel.setFont(defaultFont); // Set font
        investmentInfo.add(getGainLabel); // Add the label

        // To display JLabel and JTextField on the main JPanel
        JLabel gainLabel = new JLabel("Total Gain");
        gainLabel.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20)); // Padding
        gainLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        gainLabel.setFont(defaultFont); // Set font
        gainField = new JTextField(NUM_OF_CHAR); // Set the size of the JTextField
        gainField.setMaximumSize(new Dimension(300, 40)); // Set the max size of the JTextField
        gainField.setFont(defaultFont); // Set font
        gainField.setEditable(false); // Uneditable
        investmentInfo.add(gainLabel); // Add label
        investmentInfo.add(gainField); // Add symbol

        // To setup a JLabel and JTextArea (display a message board)
        JLabel messageLabel = new JLabel("Individual Gains:");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding
        messageLabel.setFont(defaultFont); // Set font
        messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center algin the text
        message = new JTextArea(30,40); // Set the size of the JTextArea
        message.setEditable(false); // Make the area uneditable
        message.setLineWrap(true); // Set lineWrap to true so that extra words will go onto the next line
        messageField.add(messageLabel); // Add the label

        // Create a JScrollPane that will allow vertical and horizontal scrolling
        scrollableMessage = new JScrollPane(message);
        scrollableMessage.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollableMessage.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        messageField.add(scrollableMessage); // Add the area

        // Display total gain and individual gains if there exist a investment in the system
        if (portfolio != null && portfolio.size() > 0) {
            for (int i = 0 ; i < portfolio.size(); i++) {
                // Get the current values for price, quantity, and symbol
                double tempPrice = portfolio.get(i).getPrice();
                int tempQuantity = portfolio.get(i).getQuantity();
                String tempSymbol = portfolio.get(i).getSymbol();
                double tempGains = 0; // Declare a variable to hold tempGains

                // Catch any exception thrown by gainOfInvestment
                try {
                    // Call the gainOfInvestment and store in tempGains
                    tempGains = portfolio.get(i).gainOfInvestment(tempPrice, tempQuantity, 0);
                    totalGains += tempGains; // Add the tempGains into totalGains

                    // Print the symbol and total gain of the investment
                    message.append("Symbol: " + tempSymbol + "\nGains: $" + df.format(tempGains) + "\n");

                    exceptionFound = false; // Set to false
                }
                catch (IllegalArgumentException g) {
                    message.setText(g.getLocalizedMessage() + "\n");
                    exceptionFound = true; // Set to true
                }
                catch (Exception k) {
                    message.setText("ERROR!\n");
                    exceptionFound = true; // Set to true
                }
            }
            
            // Check if no exceptions found, if so, print the total into total gain textfield
            if (exceptionFound == false) {
                gainField.setText(df.format(totalGains));
            }
        }
        else {
            message.setText("Please add an investment!"); // Inform the user to add an investment
        }

        investmentInfo.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for userPost JPanel
        messageField.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Padding for messageField JPanel

        // Add the userPost and messageField to the main JPanel
        this.add(investmentInfo);
        this.add(messageField);
    }
}
