//imports
import menu.Menu;
import menu.MenuItem;
import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Method creates Main Menu to display to user
 */
public class MainMenu extends Menu {

	/**
	* Returns the title "Main Menu" to user
	* @return string
	*/
    @Override
    protected String getTitle() {
        return "Main Menu";
    }

	/**
	* Returns null since there is no description for this menu
	* @return null
	*/
    @Override
    protected String getDescription() {
        return null;
    }

	/**
     * The getMenuItems method
	* Method returns an array of Menu Items
	* @return array
	*/
    @Override
    protected MenuItem[] getMenuItems() {
		//Array of main menu items
        MenuItem[] menuItems = new MenuItem[]{
                new MenuItem('1', "Change interest rate and loan period"),
                new MenuItem('2', "View all properties"),
                new MenuItem('3', "Add property"),
                new MenuItem('4', "Remove Property"),
                new MenuItem('X', "Exit"),
        };
        return menuItems;
    }

	/**
	* Return boolean value to decide whether menu runs again or not
	* @return boolean
	*/
    @Override
    protected boolean handleMenuSelection(char key) {
		//If user enters X, this block runs and menu quits
        if (key == 'X' || key == 'x') {
            //Exits program
            return false;
		//Else if user enters 1, this block runs
        } else if (key == '1') {
            //Call a method to change rate and period
            changeRateAndPeriod();
		//If user enters 2, this block runs
        } else if (key == '2') {
            displayAll();
            return true;
            //If user enters 3, this block runs
        } else if (key == '3') {
            // Display the Add Menu description and options
           AddMenu menu =  new AddMenu();
           return menu.display();
		//If user enters 4, this block runs
        } else if (key == '4') {
            // Display the Remove Menu title and options
            RemoveMenu rmenu = new RemoveMenu();
            return rmenu.display();
		//Else user enters anything other than above options, main menu prints again
        } else {
            System.out.println("Enter valid selection for Main Menu.");
            return true;
        }
        return true;
    }

    public static void displayAll() {
        //Load data from file and create ArrayList
        ArrayList<Residence> newData = new ArrayList<>();
        try {
            newData = Storage.loadData("data.txt");
            //DebugUtils.Write(newData);
        } catch (FileIsEmptyException fiee) {
            //Do nothing - Catch if file is empty
        } catch (Exception ex) {
            System.err.println("Error loading file: " + ex.getMessage());
            System.exit(1);
        }

        //Create instance for each subclass type of property
        House newHouse = new House();
        Condo newCondo = new Condo();
        Multiplex newMultiplex = new Multiplex();

        //Call tableHeader method to display table header and loop printing list of type of property.
        //House
        System.out.println(newHouse.tableHeader());
        for (Residence r : newData) {
            if (r instanceof House) {
                House h = (House) r;
                System.out.println(h.toString());
            }
        }

        //Condo
        System.out.println(newCondo.tableHeader());
        for (Residence r : newData) {
            if (r instanceof Condo) {
                Condo c = (Condo) r;
                System.out.println(c.toString());
            }
        }

        //Multiplex
        System.out.println(newMultiplex.tableHeader());
        for (Residence r : newData) {
            if (r instanceof Multiplex) {
                Multiplex m = (Multiplex) r;
                System.out.println(m.toString());
            }
        }
    }
    private boolean changeRateAndPeriod() {
        //Create a new Residence object to get interest rate
        Residence obj = new Residence();
        //Needed a boolean condition for the while loop
        boolean valid = false;

        //Keeps current interest rate and loan period display out of the loop so it doesn't display if user has to re-enter
        //Also pulls the interestRate from the new Residence object & displays it
        System.out.println();
        System.out.format("Current loan interest rate is: %5.3f%%\n", (obj.getInterestRate()));
        System.out.println("Current loan period in years is: " + obj.getLoanPeriod());

        //Sets up a try-catch in a while loop, so it will loop again if it catches InputMismatchException
        while (!valid) {
            try {
                //Initial prompt for new interest rate
                String newRate = prompt("Enter a new interest rate percent (example: 5.25): ",
                        true);
                //Passes newRate into the object's InterestRate setter
                //obj.setInterestRate(Double.parseDouble(newRate));
                Residence.interestRate = (Double.parseDouble(newRate));

                //Make sure the rate is not less than 1%, or not greater than or equal to 100%
                if (obj.getInterestRate() < 1 || obj.getInterestRate() >= 100) {
                    System.out.println("The rate must be a positive value that is between 1 and 100.\n");
                }
                //If the input fits within the requested values
                else {
                    //This line ends the loop
                    valid = true;
                    //This display will return the new rate that was passed in
                    System.out.format("Your new interest rate is: %5.3f%%\n", (obj.getInterestRate()));
                }

                //Prompt to enter loan period
                String loanPeriod = prompt("Enter the loan period in years (ex. 30): ", true);
                obj.setLoanPeriod(Integer.parseInt(loanPeriod));

                if (obj.getLoanPeriod() < 1 || obj.getLoanPeriod() > 30) {
                    System.out.println("The rate must be a positive value that is between 1 and 30.\n");
                }
                //If the input fits within the requested values
                else {
                    //This line ends the loop
                    valid = true;
                    //This display will return the new rate that was passed in
                    System.out.println("Your new loan period is : " + obj.getLoanPeriod());

                    //Let user know main menu will reload
                    System.out.println("Returning to the Main Menu...");

                    //Delay printing main menu
                    delay(2000);
                }
                //This will make sure that it will not accept non-numbers as input, and will loop
            } catch (InputMismatchException | NumberFormatException ex) {
                System.out.println("Your input is not valid. It must be a number.");
            }
        }
        return true;
    }
}