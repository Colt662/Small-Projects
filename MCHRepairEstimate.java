import java.util.Scanner;
/*
Group #4
Colt Youngblood
Marcus Toles
Hector Rivera (absent)
4/9/25
Group 4 Repair Estimate
SLO #4&8
 */


public class MCHRepairEstimate {

    final static int TRAVEL_TIME_LIMIT = 90; // in minutes
    final static int TRAVEL_TIME_MINIMUM_FOR_FEE = 30; // in minutes
    final static double LABOR_RATE = 40.50; //in dollars/hr
    final static double TRAVEL_RATE = 5.75; //dollars/hr
    final static double MINUTES_TO_HOURS = 1.0 / 60.0;
    final static double TAX_RATE = 0.0825;         //8.25%
    final static double VETERAN_DISCOUNT = 0.1025; //10.25%
    final static double SENIOR_DISCOUNT = 0.095;   //9.5%
    final static String COMPANY_NAME = "Henderson's Repair Service";

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        //kept across jobs
        String inputting_jobs = "Y";
        double discount = 0.0;
        double total_cost = 0.0;
        int number_of_jobs = 0;

        while(inputting_jobs.toLowerCase().contains("y")){
            //only needed for individual jobs
            String job_name;
            int travel_time; //in minutes
            int job_hours;
            String inputting_materials;
            double total_material_cost = 0.0;
            double cost_before_discount;
            double cost_before_tax;
            double final_cost;

            System.out.print("What is the name of the job? ");
            job_name = scan.nextLine();
            System.out.println();

            System.out.print("What is the estimated travel time to the job in minutes (eg. 90 minutes): ");
            travel_time = scan.nextInt();
            scan.nextLine();//absorb newline

            if(travel_time > TRAVEL_TIME_LIMIT) {
                System.out.printf("Sorry, but %s does not take jobs more than %d minutes away. Please enter another job.\n", COMPANY_NAME, TRAVEL_TIME_LIMIT);
                System.out.println();
                continue;
            }

            System.out.print("What is the estimated number of working hours the job will take: ");
            job_hours = scan.nextInt();
            scan.nextLine();//absorb newline

            System.out.print("Does this job require " + COMPANY_NAME + " to purchase materials? Y/N: ");
            inputting_materials = scan.nextLine();

            while(inputting_materials.toLowerCase().contains("y")){
                String material_name;
                double material_price;
                int material_quantity;
                double material_cost;

                System.out.print("What is the name of the material? ");
                material_name = scan.nextLine();

                System.out.print("What is the cost of each unit of the material? ");
                material_price = scan.nextDouble();

                System.out.print("How many units of the material will be needed? ");
                material_quantity = scan.nextInt();
                scan.nextLine(); //Because of how nextLine works, the next input would be skipped otherwise

                material_cost = material_quantity * material_price;
                total_material_cost += material_cost;

                System.out.printf("%s   $%.2f X %d  =  $%.2f\n",material_name, material_price, material_quantity, material_cost);

                System.out.println();
                System.out.print("Does this job require any more materials? Y/N: ");
                inputting_materials = scan.nextLine();
            }

            cost_before_discount = compute(total_material_cost, travel_time, job_hours);

            //Discount status will only be updated during first job, kept for jobs after
            if(number_of_jobs < 1){
                System.out.println();
                System.out.print("Are you a senior? Y/N: ");
                if(scan.nextLine().toLowerCase().contains("y")){
                    discount = SENIOR_DISCOUNT;
                }
                System.out.print("Are you a veteran? Y/N: ");
                if(scan.nextLine().toLowerCase().contains("y")){
                    discount = VETERAN_DISCOUNT;
                }
                System.out.println();
            }

            cost_before_tax = cost_before_discount / (1.0 + TAX_RATE);
            final_cost = cost_before_discount * (1 - discount);
            total_cost += final_cost;
            number_of_jobs++;

            System.out.printf("\n\n%s\n", COMPANY_NAME);
            System.out.printf("Estimate for %s\n\n", job_name);
            System.out.printf("Cost of materials         $%8.2f\n", total_material_cost);
            System.out.printf("Cost of labor             $%8.2f\n", job_hours * LABOR_RATE);
            if(travel_time > TRAVEL_TIME_MINIMUM_FOR_FEE) {
                System.out.printf("Travel fee                $%8.2f\n", travel_time * TRAVEL_RATE * MINUTES_TO_HOURS);
            }
            System.out.printf("Cost Before Tax           $%8.2f\n", cost_before_tax);
            System.out.printf("Tax Rate                  %8.2f%%\n", TAX_RATE * 100);
            System.out.printf("Discounts                 %8.2f%%\n", discount * 100);
            System.out.printf("Final Price of Job        $%8.2f\n", final_cost);
            if(number_of_jobs > 1){
                System.out.printf("Total Price of all jobs   $%8.2f\n", total_cost);
            }

            System.out.println();
            System.out.print("Do you have any more jobs? Y/N: ");
            inputting_jobs = scan.nextLine();

        }

        scan.close();
    }

    public static double compute(double material_cost, double travel_time, double job_hours){
        double result = material_cost;

        if(travel_time > TRAVEL_TIME_MINIMUM_FOR_FEE) {
            result += travel_time * TRAVEL_RATE * MINUTES_TO_HOURS;
        }
        result += job_hours * LABOR_RATE;
        result *= (1 + TAX_RATE);

        return result;
    }
}