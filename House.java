/**
 * Simple model of a House
 */
public class House extends Residence {

    //Lot size in acres of investment property
    private double acreage = 0;

    /**
     * Return acreage
     * @return
     */
    public double getAcreage() {
        return acreage;
    }

    /**
     * Set acreage
     * @param acreage
     */
    public void setAcreage(double acreage) {
        this.acreage = acreage;
    }

    /**
     * Default constructor
     */
    House() {
        //default value of acreage
        acreage = 0;
    };

    /**
     * Constructor
     * @param address
     * @param bedrooms
     * @param bathrooms
     * @param sqfeet
     * @param acreage
     * @param purchasePrice
     * @param taxes
     */
    House(String address, int bedrooms, double bathrooms, int sqfeet, double purchasePrice, double taxes,
          double acreage) {
        super(address, bedrooms, bathrooms, sqfeet, purchasePrice, taxes);
        this.acreage = acreage;
    }

    /**
     * The header Method
     * This method returns the header for the House table
     */
    @Override
    public String tableHeader() {
        //Return string representation of the House table header
        return String.format("\nHouse Properties List%n%-50s   %-4s   %-5s   %-6s   %-11s   %-9s   %-10s    %-7s   " +
                        "%-9s   %-9s   %-8s   %-7s%n--------------------------------------------------   ----   " +
                        "-----   ------   -----------   ---------   -----------   -------   ---------   ---------   " +
                        "--------   -------%n", "Address", "Beds", "Baths", "SqFt", "Price", "Taxes", "$ Down",
                "$/SqFt", "Payment", "Income", "Profit", "Acreage");
    }

    /**
     * The String method
     * This method displays the data about each property as a string in a table
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

        //String of each object in arraylist that is a house
        return String.format("%-50s   %-4d   %-5.2f   %-,6d   $%-,11.2f  $%-,9.2f  $%-,10.2f   $%-,7.2f  $%-,9.2f  " +
                        "$%-,9.2f  $%-,8.2f   %-7.2f", this.getAddress(), this.getBedrooms(), this.getBathrooms(),
                this.getSqfeet(), this.getPurchasePrice(), this.getTaxes(), downPayment, pricePerSqFoot,
                monthlyPayment, rentalIncome, netProfit, this.getAcreage());
    }
}