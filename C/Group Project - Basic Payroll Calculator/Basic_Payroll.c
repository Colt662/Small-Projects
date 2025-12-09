                        //Group 3
//Dalton Drews
//Colt Youngblood
//Landen Summers
//Christopher Johnson

#include <stdio.h>

int main(void) {

	int num_employees, id_num, i; 
	float hours, pay_rate, overtime_pay, gross_pay, net_pay, taxes, tax_rate = .03525;
	
	printf("Enter Number of Employees: ");
	scanf("%d", &num_employees);
	
	for(i = 0; i < num_employees; i++) {
		
		printf("\nEnter employee's ID number: ");
		scanf("%d", &id_num);
		
		printf("Enter employee's number of hours worked: ");
		scanf("%f", &hours);
		
		printf("Enter employee's hourly payrate: ");
		scanf("%f", &pay_rate);
		
		if (hours <= 40){
			gross_pay = hours * pay_rate;
		} else {
			overtime_pay = (hours - 40) * pay_rate * 1.5;
			gross_pay = (pay_rate * 40) + overtime_pay;	
		}
		
		taxes = gross_pay * tax_rate;
		
		net_pay = gross_pay - taxes;
		
		printf("\n");
		printf("Employee ID:.......%d\n", id_num);
		printf("Hours worked:......%.2f\n", hours);
		printf("Gross Pay:........$%.2f\n", gross_pay);
		printf("Taxes deducted:...$%.2f\n", taxes);
		printf("Net Pay:..........$%.2f\n", net_pay);
	
	}	
	  
    return 0;
}

