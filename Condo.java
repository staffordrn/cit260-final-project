/**
 * Simple model of a Condo
 */
public class Condo extends Residence {

	//Yearly home owner association fee for investment property
    private double hoaFee;
	//List of investment property amenities. Pool, tennis courts, etc.
    private String amenities;

    /**
     * Default constructor
     */
    Condo() {
        //default values for hoaFee and amenities
        hoaFee = 0;
        amenities = "null";
    }

    /**
     * Constructor
     * @param hoaFee
     * @param amenities
     * @param bedrooms
     * @param bathrooms
     * @param sqfeet
     * @param purchasePrice
     * @param taxes
     * @param address
     */
    Condo(String address, int bedrooms, double bathrooms, int sqfeet, double purchasePrice, double taxes, double hoaFee,
          String amenities) {
        super(address, bedrooms, bathrooms, sqfeet, purchasePrice, taxes);
        this.hoaFee = hoaFee;
        this.amenities = amenities;
    }

    /**
     * Return hoaFee
     * @return
     */
    public double getHoaFee() { return hoaFee; }

    /**
     * Set hoaFee
     * @param hoaFee
     */
    public void setHoaFee(double hoaFee) { this.hoaFee = hoaFee; }

    /**
     * Return amenities
     * @return
     */
    public String getAmenities() { return amenities; }

    /**
     * Set amenities
     * @param amenities
     */
    public void setAmenities(String amenities) { this.amenities = amenities; }

    /**
     * The monthlyNetProfit method
     * This method calculates the expected monthly net profit after subtracting the monthly mortgage payment, taxes,
     * and hoaFee from the rental income.
     * @param monthlyPayment
     * @param taxes
     * @param rentalIncome
     * @param hoaFee
     * @return monthlyNetProfit
     */
    public double monthlyNetProfit(double monthlyPayment, double taxes, double rentalIncome, double hoaFee) {
        //Equation for calculating monthly net profit
		double monthlyNetProfit = rentalIncome - (monthlyPayment + (taxes / 12) + hoaFee);
		//Return net profit value
        return monthlyNetProfit;
    }

	/**
	* The header Method
	* This method returns the header for the Condo table
	*/
	@Override
	public String tableHeader() {
		//Return string representation of the Multiplex table header
		//TODO change table header from House format to Condo
		return String.format("\nCondo Properties List%n%-50s   %-4s   %-5s   %-6s   %-11s   %-9s   %-10s    %-7s   " +
                        "%-9s   %-9s   %-8s   %-7s   %-30s%n--------------------------------------------------   " +
                        "----   -----   ------   -----------   ---------   -----------   -------   ---------   " +
                        "---------   --------   -------   ------------------------------",
                "Address", "Beds", "Baths", "SqFt", "Price", "Taxes", "$ Down", "$/SqFt", "Payment", "Income",
                "Profit", "HOA Fee", "Amenities");
	}
	
    /**
     * The String method
     * This method display the data about each property as a string in a table
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
        return String.format("%-50s   %-4d   %-5.2f   %-,6d   $%-,11.2f  $%-,9.2f  $%-,10.2f   $%-,7.2f  $%-,9.2f  " +
                        "$%-,9.2f  $%-,7.2f   $%-7.2f   %-30s", this.getAddress(), this.getBedrooms(),
                this.getBathrooms(), this.getSqfeet(), this.getPurchasePrice(), this.getTaxes(),
                downPayment, pricePerSqFoot, monthlyPayment, rentalIncome, netProfit, this.getHoaFee(),
                this.getAmenities());
    }
}