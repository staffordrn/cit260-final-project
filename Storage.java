import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Provides methods to store and load data from files
 */
public class Storage {
    /**
     * The storeData method
     * Store the data in the specified file
     * @param filename The name of the file where the data are to be stored.
     * @param data     The data to be stored
     * @throws IOException
     */
    public static void storeData(String filename, ArrayList<Residence> data) throws IOException {

        //Check if file exists. If not, create file.
        if (filename == null || filename.trim().length() == 0) {
            throw new IllegalArgumentException("Inputs must not be null");
        }

        // File format will be:
        // One object per line of the file
        // type parentField child-field
        try (PrintWriter out = new PrintWriter(filename)) {
            for (Residence obj : data) {

                //This will make sure each object type is getting saved with same order before it adds class variables
                if (obj instanceof House) {
                    //Info stored for House objects-same as Residence + acreage
                    House h = (House) obj;
                    out.format("%s|%s|%d|%f|%d|%f|%f|%f|\n", "House", obj.getAddress(),
                            obj.getBedrooms(), obj.getBathrooms(), obj.getSqfeet(), obj.getPurchasePrice(),
                            obj.getTaxes(), h.getAcreage());
                } else if (obj instanceof Condo) {
                    //Info stored for Condo objects-same as Residence + hoaFee & amenities
                    Condo c = (Condo) obj;
                    out.format("%s|%s|%d|%f|%d|%f|%f|%f|%s|\n", "Condo", obj.getAddress(),
                            obj.getBedrooms(), obj.getBathrooms(), obj.getSqfeet(), obj.getPurchasePrice(),
                            obj.getTaxes(), c.getHoaFee(), c.getAmenities());
                } else if (obj instanceof Multiplex) {
                    //Info saved for Multiplex-same as Residence + units & utilities
                    Multiplex m = (Multiplex) obj;
                    out.format("%s|%s|%d|%f|%d|%f|%f|%d|%f|\n", "Multiplex", obj.getAddress(),
                            obj.getBedrooms(), obj.getBathrooms(), obj.getSqfeet(), obj.getPurchasePrice(),
                            obj.getTaxes(), m.getUnits(), m.getUtilities());
                }
            }
        } catch (IOException exception) {
            throw new IOException("Couldn't write file", exception);
        }
    }

    /**
     * Load the data from the specified file
     *
     * @param filename The name of the file containing the data.
     * @return The list of objects loaded from the file.
     * @throws IOException
     */
    public static ArrayList<Residence> loadData(String filename) throws IOException, FileIsEmptyException {

        if (filename == null || filename.trim().length() == 0) {
            throw new IllegalArgumentException("Inputs must not be null");
        }

        //Check if file exists and if it is readable
        File file = new File(filename);
        if(file.exists() == false || file.canRead() == false) {
            throw new IOException("Cannot find or read file");
        }

        ArrayList<Residence> newData = new ArrayList<>();

        //Initialize line number
        int lineNumber = 0;

        try (Scanner input = new Scanner(file)) {
            while (input.hasNextLine()) {
                String line = input.nextLine().trim();
                lineNumber += 1;

                //Splits the data per line after the | symbol
                //if the first field has the parent class, adds it to its own array
                String[] fields = line.split("\\|");
                if (fields.length <= 0) {
                    throw new FileIsEmptyException("File is empty." + lineNumber);
                }
                if (fields[0].equals("House")) {
                    // House|first child value|1
                    //Should have 9 fields, including [0] for "House" --plus 5 more for method calculations
                    if (fields.length < 8) {
                        throw new IOException("Invalid record format on line." + lineNumber);
                    }
                    House h = new House();
                    //This is the correct order--Address, Bedrooms, Bathrooms, Sqfeet, PurchasePrice, Taxes,
                    //InterestRate, Acreage;
                    //Load the address from the file --already a String, doesn't need parsing
                    h.setAddress(fields[1]);
                    //Load the bedrooms from the file
                    h.setBedrooms(Integer.parseInt(fields[2]));
                    //Load the bathrooms from the file
                    h.setBathrooms(Double.parseDouble(fields[3]));
                    //Load sqfeet from the file
                    h.setSqfeet(Integer.parseInt(fields[4]));
                    //Load purchasePrice from the file
                    h.setPurchasePrice(Double.parseDouble(fields[5]));
                    //Load taxes from the file
                    h.setTaxes(Double.parseDouble(fields[6]));
                    //Load acreage from the file
                    h.setAcreage(Double.parseDouble(fields[7]));

                    newData.add(h);

                    //if the first field has the Condo class, adds it to its own array
                } else if (fields[0].equals("Condo")) {
                    //Should have 10 fields, including [0] for "Condo"
                    if (fields.length < 9) {
                        throw new IOException("Invalid record format on line " + lineNumber);
                    }
                    Condo c = new Condo();
                    //This is the correct order--Address, Bedrooms, Bathrooms, Sqfeet, PurchasePrice, Taxes,
                    //InterestRate, HoaFee, Amenities;
                    //Load the address from the file --already a String, doesn't need parsing
                    c.setAddress(fields[1]);
                    //Load the bedrooms from the file
                    c.setBedrooms(Integer.parseInt(fields[2]));
                    //Load the bathrooms from the file
                    c.setBathrooms(Double.parseDouble(fields[3]));
                    //Load sqfeet from the file
                    c.setSqfeet(Integer.parseInt(fields[4]));
                    //Load purchasePrice from the file
                    c.setPurchasePrice(Double.parseDouble(fields[5]));
                    //Load taxes from the file
                    c.setTaxes(Double.parseDouble(fields[6]));
                    //Load HoaFee from the file
                    c.setHoaFee(Double.parseDouble(fields[7]));
                    //Load Amenities from the file
                    c.setAmenities(fields[8]);

                    newData.add(c);

                    //if the first field has the Multiplex class, adds it to its own array
                } else if (fields[0].equals("Multiplex")) {
                    //Should have 10 fields, including [0] for "Multiplex"
                    if (fields.length < 9) {
                        throw new IOException("Invalid record format on line " + lineNumber);
                    }
                    Multiplex m = new Multiplex();
                    //This is the correct order--Address, Bedrooms, Bathrooms, Sqfeet, PurchasePrice, Taxes,
                    //InterestRate, Units, Utilities
                    //Load the address from the file --already a String, doesn't need parsing
                    m.setAddress(fields[1]);
                    //Load the bedrooms from the file
                    m.setBedrooms(Integer.parseInt(fields[2]));
                    //Load the bathrooms from the file
                    m.setBathrooms(Double.parseDouble(fields[3]));
                    //Load sqfeet from the file
                    m.setSqfeet(Integer.parseInt(fields[4]));
                    //Load purchasePrice from the file
                    m.setPurchasePrice(Double.parseDouble(fields[5]));
                    //Load taxes from the file
                    m.setTaxes(Double.parseDouble(fields[6]));
                    //Load units from the file
                    m.setUnits(Integer.parseInt(fields[7]));
                    //Load utilities from the file
                    m.setUtilities(Double.parseDouble(fields[8]));

                    newData.add(m);

                } else {
                    throw new IOException(String.format("Invalid record type '%s' on line %d", fields[0], lineNumber));
                }
            }
        } catch (NumberFormatException ex) {
            throw new IOException("Invalid number format on line " + lineNumber);
        }

        return newData;
    }
}