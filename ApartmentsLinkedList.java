import java.util.LinkedList;
import java.util.Scanner;

public class ApartmentsLinkedList {
    public static void main(String[] args) {
        LinkedList<Apartment> apartmentList = new LinkedList<>();
        Scanner scan = new Scanner(System.in);


        System.out.print("Enter an apartment number: ");
        int apartmentNumber = scan.nextInt();
        scan.nextLine();//absorb newLine

        while(apartmentNumber != -1){
            System.out.printf("Enter the address of apartment #%d: ", apartmentNumber);
            String address = scan.nextLine();

            System.out.printf("Enter the rent amount (to the nearest dollar) of apartment #%d: ", apartmentNumber);
            int rent = scan.nextInt();
            scan.nextLine();//absorb newLine

            System.out.printf("Enter the number bedrooms in apartment #%d: ", apartmentNumber);
            int bedrooms = scan.nextInt();
            scan.nextLine();//absorb newLine

            apartmentList.add(new Apartment(apartmentNumber, address, rent, bedrooms));

            System.out.print("Enter an apartment number or -1 to stop: ");
            apartmentNumber = scan.nextInt();
            scan.nextLine();//absorb newLine
        }
        scan.close();

        apartmentList.sort(null);

        for(Apartment room : apartmentList){
            System.out.println(room);
        }
    }
}