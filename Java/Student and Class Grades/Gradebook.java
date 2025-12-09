import java.util.Scanner;

public class Gradebook {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter the number of courses: ");
        int num_courses = scan.nextInt();
        scan.nextLine(); //absorb newline

        String[] course_name_array = new String[num_courses];
        String[] course_section_array = new String[num_courses];
        int[] course_average_array = new int[num_courses];
        int[] highest_grade_array = new int[num_courses];
        int[] lowest_grade_array = new int[num_courses];
        int[] above_80_array = new int[num_courses];
        int[] below_70_array = new int[num_courses];
        int[] scholarship_array = new int[num_courses];


        //this loop inputs/outputs information for each course
        for(int i = 0; i < num_courses; i++) {
            System.out.print("Enter the course name: ");
            course_name_array[i] = scan.nextLine();

            System.out.print("Enter the course section: ");
            course_section_array[i] = scan.nextLine();

            System.out.printf("Enter the number of students in %s - %s: ", course_name_array[i], course_section_array[i]);
            int num_students = scan.nextInt();
            scan.nextLine(); //absorb newline


            String[] first_name_array = new String[num_students];
            String[] last_name_array = new String[num_students];
            int[] ID_array = new int[num_students];
            int[] student_averages_array = new int[num_students];
            int[] extra_credit_array = new int[num_students];
            int[] student_scholarship_array = new int[num_students];
            char[] letter_grades_array = new char[num_students];


            //this loop inputs information for each student
            for(int j = 0; j < num_students; j++){
                double full_average = 0; //used to avoid double->int loss between grade categories

                System.out.printf("\nEntering information for student %d/%d\n", j + 1, num_students);
                System.out.print("Enter the students first name: ");
                first_name_array[j] = scan.nextLine();

                System.out.print("Enter the students last name: ");
                last_name_array[j] = scan.nextLine();

                System.out.print("Enter the students ID: ");
                ID_array[j] = scan.nextInt();
                scan.nextLine(); //absorb newline

                full_average += input_assignment_type("Quiz/discussion", "Exam", .15, scan);
                full_average += input_assignment_type("Exam", "Program", .2, scan);
                full_average += input_assignment_type("Program", "SLO", .3, scan);
                full_average += input_assignment_type("SLO", "Extra Credit", .35, scan);

                System.out.print("Enter the extra credit for this student: ");
                extra_credit_array[j] = scan.nextInt();
                scan.nextLine(); //absorb newline

                while(extra_credit_array[j] > 5 || extra_credit_array[j] < 0){
                    System.out.println("A student can only have 0-5 points of extra credit");
                    System.out.print("Enter the extra credit for this student: ");
                    extra_credit_array[j] = scan.nextInt();
                    scan.nextLine(); //absorb newline
                }

                student_averages_array[j] = (int)Math.round(full_average + extra_credit_array[j]);
                student_scholarship_array[j] =calculate_scholarship(student_averages_array[j]);
                letter_grades_array[j] = calculate_letter_grade(student_averages_array[j]);
            }

            course_average_array[i] = (int)Math.round((calculate_array_total(student_averages_array) * 1.0)/ num_students);
            highest_grade_array[i] = find_highest_grade(student_averages_array);
            lowest_grade_array[i] = find_lowest_grade(student_averages_array);
            above_80_array[i] = find_number_above_80(student_averages_array);
            below_70_array[i] = find_number_below_70(student_averages_array);
            scholarship_array[i] = calculate_array_total(student_scholarship_array);


            System.out.println();
            System.out.printf("\n%s - %s\n", course_name_array[i], course_section_array[i]);

            System.out.printf("Course average: %d\n",course_average_array[i]);
            System.out.printf("Highest grade: %d\n",highest_grade_array[i]);
            System.out.printf("Lowest grade: %d\n",lowest_grade_array[i]);
            System.out.printf("Students who made above 80 (B or A): %d\n",above_80_array[i]);
            System.out.printf("Students who made below 70 (D or F): %d\n",below_70_array[i]);
            System.out.printf("Total scholarships awarded to class: $%,d.00\n",scholarship_array[i]);

            System.out.println("+--------+-----------------------+----------------------+---------+--------------+--------+-------------+");
            System.out.println("|   ID   |         First         |         Last         | Average | Extra Credit | Letter | Scholarship |");
            System.out.println("+--------+-----------------------+----------------------+---------+--------------+--------+-------------+");

            for(int j = 0; j < num_students; j++){
                System.out.printf("| %-6d | %-21s | %-20s | %-7d | %-12d | %-6c | $%,7d.00 |\n",ID_array[j], first_name_array[j], last_name_array[j], student_averages_array[j], extra_credit_array[j], letter_grades_array[j], student_scholarship_array[j]);
                System.out.println("+--------+-----------------------+----------------------+---------+--------------+--------+-------------+");
            }

            System.out.println();
        }


        System.out.println();
        System.out.println("+---------+----------------------+--------------+---------------+---------+---------+---------+--------------+");
        System.out.println("| Section |         Name         | Lowest Grade | Highest Grade | Average | As & Bs | Fs & Ds | Scholarships |");

        for(int i = 0; i < num_courses; i++) {
            System.out.println("+---------+----------------------+--------------+---------------+---------+---------+---------+--------------+");
            System.out.printf("| %-7s | %-20s | %-12d | %-13d | %-7d | %-7d | %-7d | $%,8d.00 |\n", course_section_array[i], course_name_array[i], lowest_grade_array[i], highest_grade_array[i], course_average_array[i], above_80_array[i], below_70_array[i], scholarship_array[i]);
        }

        System.out.println("**************************************************************************************************************");
        System.out.printf("| %-30s | %-12d | %-13d | %-7d | %-7d | %-7d | $%,8d.00 |\n", "Total", find_lowest_grade(lowest_grade_array), find_highest_grade(highest_grade_array), Math.round(calculate_array_total(course_average_array) * 1.0 / num_courses), calculate_array_total(above_80_array), calculate_array_total(below_70_array), calculate_array_total(scholarship_array));
        System.out.println("+--------------------------------+--------------+---------------+---------+---------+---------+--------------+");


    }

    public static double input_assignment_type(String assignment_type, String next_assignment_type, double assignment_type_weight, Scanner scan){
        int grade_input;
        int grade_point_total = 0;
        int num_grades = 0;

        System.out.printf("Enter a grade for a %s: ", assignment_type);
        grade_input = scan.nextInt();
        scan.nextLine(); //absorb newline

        do {
            if(grade_input <= 100 && grade_input >= 0){
                num_grades++;
                grade_point_total += grade_input;

            }else{
                System.out.printf("%d is not a valid grade\n", grade_input);
                System.out.printf("Enter a grade for a %s: ", assignment_type);
                grade_input = scan.nextInt();
                scan.nextLine(); //absorb newline
                continue;
            }

            System.out.printf("Enter a grade for a %s OR -1 to start entering %ss: ", assignment_type, next_assignment_type);
            grade_input = scan.nextInt();
            scan.nextLine(); //absorb newline
        } while(grade_input != -1 || num_grades == 0);

        System.out.println();
        return (assignment_type_weight * ((grade_point_total * 1.0 ) / (num_grades * 1.0)));
    }

    public static int calculate_scholarship(int average){
        int scholarship = 0;

        if(average >= 95){
            scholarship = 750;

        } else if(average >= 90){
            scholarship = 500;

        } else if(average >= 85){
            scholarship = 250;
        }

        return scholarship;
    }

    public static char calculate_letter_grade(int average){
        return switch (average / 10) { //intentionally does integer division
            case 10, 9 -> 'A';
            case 8 -> 'B';
            case 7 -> 'C';
            case 6 -> 'D';
            default -> 'F';
        };
    }

    public static int calculate_array_total(int[] array){
        int sum = 0;

        for(int element : array){
            sum += element;
        }

        return sum;
    }

    public static int find_highest_grade(int[] array){
        int highest = 0;

        for(int element : array){
            if(element > highest){
                highest = element;
            }
        }

        return  highest;
    }

    public static int find_lowest_grade(int[] array){
        int lowest = 100;

        for(int element : array){
            if(element < lowest){
                lowest = element;
            }
        }

        return  lowest;
    }

    public static int find_number_above_80(int[] array){
        int result = 0;

        for(int element : array){
            if(element >= 80){
                result++;
            }
        }

        return result;
    }

    public static int find_number_below_70(int[] array){
        int result = 0;

        for(int element : array){
            if(element < 70){
                result++;
            }
        }

        return result;
    }
}
