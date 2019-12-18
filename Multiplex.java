/**
 * Simple Model of a Multiplex
 */
public class Multiplex extends Residence {

    //Number of units in Multiplex
    private int units;
    //Amount of utilities landlord would pay - can be 0
    private double utilities;

    /**
     * Default constructor
     */
    public Multiplex() {
        //default values for units and utilities
        units = 1;
        utilities = 0;
    }

    /**
     * Constructor
     * @param address
     * @param units
     * @param bedrooms
     * @param bathrooms
     * @param sqfeet
     * @param purchasePrice
     * @param taxes
     * @param units
     * @param utilities
     */
    public Multiplex(String address, int bedrooms, double bathrooms, int sqfeet, double purchasePrice,
                     double taxes, int units, double utilities) {
        super(address, bedrooms, bathrooms, sqfeet, purchasePrice, taxes);
        this.units = units;
        this.utilities = utilities;
    }

    /**
     * Return units
     * @return
     */
    public int getUnits() { return units; }

    /**
     * Set units
     * @param units
     */
    public void setUnits(int units) { this.units = units; }

    /**
     * Return utilities
     * @return
     */
    public double getUtilities() { return utilities; }

    /**
     * Set utilities
     * @param utilities
     */
    public void setUtilities(double utilities) { this.utilities = utilities; }

    /**
     * The monthlyNetProfit method
     * This method calculates the net profit after subtracting utilities, taxes, and mortgage from rental income
     * @param monthlyPayment
     * @param taxes
     * @param rentalIncome
     * @param utilities
     * @return netProfit
     */
    public double monthlyNetProfit(double monthlyPayment, double taxes, double rentalIncome, double utilities) {
        //Equation for calculating monthly net profit
        double monthlyNetProfit = rentalIncome - (monthlyPayment + (taxes / 12) + utilities);
        //Return net profit value
        return monthlyNetProfit;
    }
	
	/**
	* The header Method
	* This method returns the header for the Multiplex table
	*/
	@Override
	public String tableHeader() {
		//Return string representation of the Multiplex table header
        return String.format("\nMultiplex Properties List%n%-50s   %-4s   %-5s   %-6s   %-11s   %-9s   %-10s    %-7s" +
                        "   %-9s    %-9s    %-8s    %-5s   %-9s%n--------------------------------------------------   " +
                        "----   -----   ------   -----------   ---------   -----------   -------   ---------    " +
                        "---------    --------    -----   ---------", "Address", "Beds", "Baths", "SqFt", "Price",
                "Taxes", "$ Down", "$/SqFt", "Payment", "Income", "Profit", "Units", "Utilities");
	}

    /**
     * The String method
     * Return a string representation of the Multiplex
     * @return String
     */
    @Override
    public String toString() {

        Double downPayment = super.downPayment(this.getPurchasePrice(), PERCENT_DOWN);
        Double pricePerSqFoot = super.pricePerSqFt(this.getPurchasePrice(), this.getSqfeet());
        Double monthlyPayment = super.monthlyPayment(this.getPurchasePrice(), this.getInterestRate(),
                this.getLoanPeriod());
        Double rentalIncome = this.rentalIncome(this.getSqfeet(), RENT_PER_SQFT);
        Double netProfit = this.monthlyNetProfit(monthlyPayment, rentalIncome, this.getTaxes());

		//Returns the string for each object of a Multiplex to the table
		return String.format("%-50s   %-4d   %-5.2f   %-,6d   $%-,11.2f  $%-,9.2f  $%-,10.2f   $%-,7.2f  $%-,9.2f" +
                        "   $%-,9.2f   $%-,8.2f   %-5d   $%-9.2f", this.getAddress(), this.getBedrooms(),
                this.getBathrooms(), this.getSqfeet(), this.getPurchasePrice(), this.getTaxes(),
                downPayment, pricePerSqFoot, monthlyPayment, rentalIncome, netProfit, this.getUnits(),
                this.getUtilities());
    }
}