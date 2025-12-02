#include <stdio.h>

double calc_sal(double year_sal);
double calc_hourly(double hour_rate, double hours);
double calc_com(double com_sales);

const int com_base = 375;
const double com_rate = .072;

int main (void) {
	int num_of_employees, i, pay_type;
	double year_sal, hour_rate, hours, com_sales, total_gross = 0;
	const double tax_rate = .08625;
	
	printf("Please enter the number of employees: ");
	scanf("%d",&num_of_employees);	
	printf("\n");
	
	double gross_pay[num_of_employees], net_pay[num_of_employees], ID[num_of_employees], taxes[num_of_employees];
	
	for(i = 0;i < num_of_employees; i++){
		printf("Please enter the ID number of an employee: ");
		scanf("%lf",&ID[i]);
		printf("Please enter their pay code (1 - Salaried, 2 - Hourly, 3 - Commission): ");
		scanf("%d",&pay_type);
		
		switch(pay_type){
			case 1:
				printf("Please enter their yearly salary: ");
				scanf("%lf", &year_sal);
				gross_pay[i] = calc_sal(year_sal);
				break;
				
			case 2:
				printf("Please enter their hourly rate: ");
				scanf("%lf", &hour_rate);
				printf("Please enter their hours worked: ");
				scanf("%lf", &hours);
				gross_pay[i] = calc_hourly(hour_rate, hours);
				break;
				
			case 3:
				printf("Please enter their gross sales: ");
				scanf("%lf", &com_sales);
				gross_pay[i] = calc_com(com_sales);	
				break;	
		}
		
		net_pay[i] = gross_pay[i] * (1 - tax_rate);
		taxes[i] = gross_pay[i] * tax_rate;
		total_gross = total_gross + gross_pay[i];
		
		printf("\n");	
	}
	
	printf("\n");
	printf("\n");
	
	for(i = 0;i < num_of_employees; i++){
		printf("Employee ID: %4.0lf Gross Pay: %7.2lf Net Pay: %7.2lf Taxes Paid: %6.2lf\n", ID[i], gross_pay[i], net_pay[i], taxes[i]);
	}
	printf("Total Gross Pay: %9.2lf", total_gross);
	
	return 0;	
}

double calc_sal(double year_sal){
	return (year_sal/52);
}

double calc_hourly(double hour_rate, double hours){
	if (hours <= 40) {
        return (hours * hour_rate);
    } else {
        return ((hour_rate * 40) + ((hours - 40) * hour_rate * 1.5));
    }
}

double calc_com(double com_sales){
	return (com_base + (com_sales * com_rate));
}
