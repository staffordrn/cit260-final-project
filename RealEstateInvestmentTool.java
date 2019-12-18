import java.util.ArrayList;

/**
 * Test driver for residence, condo, house, and multiplex
 */
public class RealEstateInvestmentTool {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {

        //Welcome message
        System.out.println("Welcome to the Property Investment Tool.");

        //Tells the user what the program does.
        System.out.println("This tool compiles a list of investment properties available for purchase and also\n" +
                "maintains and display data for each. Properties can be removed or added by the user.\n");

        //Displays main menu to the user
        new MainMenu().display();

        //Displays a goodbye message.
        System.out.println("Goodbye...");
    }
}