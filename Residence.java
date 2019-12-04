public class Residence {

    //VARIABLES
    protected int bedrooms;
    protected double bathrooms;
    protected int sqft;
    protected double purchasePrice;
    protected double taxes;
    protected String address;
    protected double interestRate;
    protected double loanPeriod;
    protected double monthlyPayment;
    protected double rentalIncome;
    protected final double PERCENT_DOWN = 0.2;
    protected final double RENT_PER_SQFT = 0.7;

    //CONSTRUCTORS
    Residence() {
    bedrooms = 0;
    bathrooms = 0;
    sqft = 0;
    purchasePrice = 0;
    taxes = 0;
    address = null;
    interestRate = 0;
    }

    Residence(int bedrooms, int bathrooms, int sqft, double purchasePrice, double taxes, String address, double interestRate) {
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.sqft = sqft;
        this.purchasePrice = purchasePrice;
        this.taxes = taxes;
        this.address = address;
        this.interestRate = interestRate;
    }

    //METHODS
    public double downPayment(double purchasePrice) {
        return purchasePrice * PERCENT_DOWN;
    }
    public double pricePerSqFt(double purchasePrice, int sqft) {
        return purchasePrice / sqft;
    }
    public double monthlyPayment(double purchasePrice, double interestRate, int loanPeriod) {
        double termInMonths = loanPeriod / 12;
        double monthlyRate = interestRate / 12;
        return (purchasePrice * monthlyRate) / (1-Math.pow(1+monthlyRate, - termInMonths));
    }
    public double rentalIncome(int sqft) {
        return RENT_PER_SQFT * sqft;
    }
    public double monthlyNetProfit(double monthlyPayment, double taxes, double rentalIncome) {
        return rentalIncome - (monthlyPayment + taxes);
    }
    public String toString() {
        return String.format(" ");
    }

    //GETTERS AND SETTERS
    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public double getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSqft() { return sqft; }

    public void setSqft(int sqft) {
        this.sqft = sqft;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) { this.address = address; }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getLoanPeriod() {  return loanPeriod;  }

    public void setLoanPeriod(double loanPeriod) { this.loanPeriod = loanPeriod; }

    public double getMonthlyPayment() { return monthlyPayment; }

    public void setMonthlyPayment(double monthlyPayment) { this.monthlyPayment = monthlyPayment; }

    public double getRentalIncome() { return rentalIncome; }

    public void setRentalIncome(double rentalIncome) { this.rentalIncome = rentalIncome; }
}
