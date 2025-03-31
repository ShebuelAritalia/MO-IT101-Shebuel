package main;
import java.util.Scanner;

public class EmployeeManage {

	public static void main(String[] args) {
	    Scanner scanner = new Scanner(System.in);

        double hourlyRate = 100.0; // Salary per hour
        double allowance = 500.0; // Weekly allowance
        double sssDeduction = 200.0; // SSS
        double philHealthDeduction = 100.0; // PhilHealth
        double pagIbigDeduction = 50.0; // Pag-IBIG
        double latePenaltyPerMinute = 2.0; // Deduction per minute late

        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        int[][] workHours = new int[5][2]; 
        
        System.out.println("Enter Employee Name:");
        String employeeName = scanner.nextLine();

        for (int i = 0; i < 5; i++) {
            System.out.print(days[i] + " Time-In (HH:MM): ");
            String timeIn = scanner.next();
            System.out.print(days[i] + " Time-Out (HH:MM): ");
            String timeOut = scanner.next();

            workHours[i][0] = convertToMinutes(timeIn);  // Time-In
            workHours[i][1] = convertToMinutes(timeOut); // Time-Out
        }

        double totalHours = 0;
        double totalLatePenalty = 0;
        int standardTimeIn = convertToMinutes("08:00"); 

        for (int i = 0; i < 5; i++) {
            int timeIn = workHours[i][0];
            int timeOut = workHours[i][1];

            // Late penalty calculation
            if (timeIn > standardTimeIn) {
                int lateMinutes = timeIn - standardTimeIn;
                totalLatePenalty += lateMinutes * latePenaltyPerMinute;
            }

            // Work hours calculation
            double hoursWorked = (timeOut - timeIn) / 60.0;
            totalHours += hoursWorked;
        }

        double weeklySalary = totalHours * hourlyRate;
        double totalDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction + totalLatePenalty;
        double takeHomePay = weeklySalary + allowance - totalDeductions;

        // Display results
        System.out.println("\n==== Employee Salary Summary ====");
        System.out.println("Employee: " + employeeName);
        System.out.println("Total Hours Worked: " + totalHours + " hours");
        System.out.println("Weekly Salary: PHP " + weeklySalary);
        System.out.println("Allowance: PHP " + allowance);
        System.out.println("SSS Deduction: PHP " + sssDeduction);
        System.out.println("PhilHealth Deduction: PHP " + philHealthDeduction);
        System.out.println("Pag-IBIG Deduction: PHP " + pagIbigDeduction);
        System.out.println("Late Penalty: PHP " + totalLatePenalty);
        System.out.println("Take-Home Pay: PHP " + takeHomePay);
        
        scanner.close();
    }

    private static int convertToMinutes(String time) {
        String[] parts = time.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return (hours * 60) + minutes;
    }

}
