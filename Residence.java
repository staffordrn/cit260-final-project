/**
 * Simple model of a Residence
 */
public class Residence {

    //VARIABLES
	//Street address of residence
    private String address;
	//Number of bedrooms in residence
    private int bedrooms;
	//Number of bathrooms in residence
    private double bathrooms;
	//Size of residence in square feet
    private int sqfeet;
	//Listing purchase price of residence
    private double purchasePrice;
	//Yearly homeowner taxes of residence
    private double taxes;
	//Loan interest rate
    protected static double interestRate = 4.375;
	//Loan time period in years
    protected static int loanPeriod = 30;
	//Required percentage down for investment property
    protected final static double PERCENT_DOWN = 0.2;
	//Number to multiply square footage by to calculate rental income
    protected final static double RENT_PER_SQFT = 0.7;

    //CONSTRUCTORS

    /**
     * Default Constructor
     */
    Residence() {
		//default values for bedrooms, bathrooms, sqfeet, purchasePrice, taxes, address, interestRate, and loanPeriod.
        bedrooms = 0;
        bathrooms = 0;
        sqfeet = 0;
        purchasePrice = 0;
        taxes = 0;
        address = null;
    }

    /**
     * Constructor
     * @param address
     * @param bedrooms
     * @param bathrooms
     * @param sqfeet
     * @param purchasePrice
     * @param taxes
     */
    Residence(String address, int bedrooms, double bathrooms, int sqfeet, double purchasePrice, double taxes) {
        this.address = address;
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.sqfeet = sqfeet;
        this.purchasePrice = purchasePrice;
        this.taxes = taxes;
    }

    //Methods
    /**
     * The downPayment method
     * The method calculates the down payment by multiplying the purchase price by the required percentage down.
     * @param purchasePrice
     * @param PERCENT_DOWN
     * @return purchasePrice
     */
    public double downPayment(double purchasePrice, double PERCENT_DOWN) {
		//Calculate and return down payment
        return purchasePrice * PERCENT_DOWN;
    }

    /**
     * The pricePerSqFt method
     * This method calculates the price per square foot of the residence by dividing purchasePrice by sqfeet.
     * @param purchasePrice
     * @param sqfeet
     * @return pricePerSqFt
     */
    public double pricePerSqFt(double purchasePrice, int sqfeet) {
		//Calculate and return pricePerSqFt
        return purchasePrice / sqfeet;
    }

    /**
     * The monthlyPayment method
     * This method calculates the monthly payment based on loan length, purchase price, and interest rate.
     * @param purchasePrice
     * @param interestRate
     * @param loanPeriod
     * @return monthlyPayment
     */
    public double monthlyPayment(double purchasePrice, double interestRate, int loanPeriod) {
        //Multiply years by 12 to get total months
		double termInMonths = loanPeriod * 12;
		//Divide interest rate by hundred to get fraction. 
		//Divide by 12 to get monthly interest rate
        double monthlyRate = (interestRate / 100) / 12;
		//Calculate and return  monthly payment by multiplying purchasePrice and monthlyRate
		//Divide by (1 - Math.pow(1 + monthlyRate, -termInMonths)
        return (purchasePrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -termInMonths));
    }

    /**
     * The rentalIncome method
     * This method calculates the possible rental income by multiplying square feet by RENT_PER_SQFT
     * rent per square foot unit of .70.
     * @param sqfeet
     * @param RENT_PER_SQFT
     * @return
     */
    public double rentalIncome(int sqfeet, double RENT_PER_SQFT) {
		//Calculate and return  possible rental income
        return sqfeet * RENT_PER_SQFT;
    }

    /**
     * The monthlyNetProfit method
     * The method calculates the net profit by subtracting (taxes / 12) and mortgage payment from the rental income.
     * @param monthlyPayment
     * @param taxes
     * @param rentalIncome
     * @return
     */
    public double monthlyNetProfit(double monthlyPayment, double taxes, double rentalIncome) {
		//Calculate and return rentalIncome
        return rentalIncome - (monthlyPayment + (taxes / 12));
    }
	
	/**
	* The tableHeader Method
	* This method returns the header for a property display table
     * @return
	*/
	public String tableHeader() {
		//Return string representation of table header
		return String.format(" ");
	}

    /**
     * The String method
     * This method returns the String representation of a residence.
	 * @return
     */
    @Override
    public String toString() {
        return String.format(" ");
    }

    //GETTERS AND SETTERS

    /**
     * Return address
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     * Set Address
     * @param address
     */
    public void setAddress(String address) { this.address = address; }

    /**
     * Return bedrooms
     * @return
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * Set bedrooms
     * @param bedrooms
     */
    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    /**
     * Return bathrooms
     * @return
     */
    public double getBathrooms() {
        return bathrooms;
    }

    /**
     * Set bathrooms
     * @param bathrooms
     */
    public void setBathrooms(double bathrooms) {
        this.bathrooms = bathrooms;
    }

    /**
     * Return sqft
     * @return
     */
    public int getSqfeet() { return sqfeet; }

    /**
     * Set sqfeet
     * @param
     */
    public void setSqfeet(int sqfeet) {
        this.sqfeet = sqfeet;
    }

    /**
     * Return purchasePrice
     * @return
     */
    public double getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Set purchasePrice
     * @param purchasePrice
     */
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    /**
     * Return taxes
     * @return
     */
    public double getTaxes() {
        return taxes;
    }

    /**
     * Set taxes
     * @param taxes
     */
    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    /**
     * Return interestRate
     * @return
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Set interestRate
     * @param interestRate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    /**
     * Return loanPeriod
     * @return
     */
    public int getLoanPeriod() {
        return loanPeriod;
    }

    /**
     * Set loanPeriod
     * @param loanPeriod
     */
    public void setLoanPeriod(int loanPeriod) {
        this.loanPeriod = loanPeriod;
    }

    public String getStringValue() {
        return " address:"+address +
        " bedrooms:"+bedrooms +
        " bathrooms:"+bathrooms +
        " sqfeet:"+sqfeet +
        " purchasePrice:"+purchasePrice +
        " taxes:"+taxes;

    }
}