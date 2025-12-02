public class Apartment implements Comparable<Apartment>{
    public String apartmentAddress;
    public int apartmentNumber;
    public int rentAmount;
    public int numberOfBedrooms;

    public Apartment(int apartmentNumber, String apartmentAddress, int rentAmount, int numberOfBedrooms){
        this.apartmentNumber = apartmentNumber;
        this.apartmentAddress = apartmentAddress;
        this.rentAmount = rentAmount;
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public String toString(){
        return "Apartment Number: " + apartmentNumber + ", Street Address: " + apartmentAddress + ", Rent Amount: " + rentAmount + ", Number of Bedrooms: " + numberOfBedrooms;
    }

    public int compareTo(Apartment other){
        return (this.rentAmount - other.rentAmount);
    }
}
