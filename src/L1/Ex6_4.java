package L1;

import java.util.Scanner;

public class Ex6_4 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = -1;
        int monthIndex = -1;

        String[][] validMonths = {
                {"1", "january", "jan.", "jan"},
                {"2", "february", "feb.", "feb"},
                {"3", "march", "mar.", "mar"},
                {"4", "april", "apr.", "apr"},
                {"5", "may"},
                {"6", "june", "jun"},
                {"7", "july", "jul"},
                {"8", "august", "aug.", "aug"},
                {"9", "september", "sept.", "sep"},
                {"10", "october", "oct.", "oct"},
                {"11", "november", "nov.", "nov"},
                {"12", "december", "dec.", "dec"}
        };

        while (true) {
            System.out.print("Enter month: ");
            String monthInput = scanner.nextLine().trim().toLowerCase();
            monthIndex = -1;

            for (int i = 0; i < validMonths.length; i++) {
                for (String valid : validMonths[i]) {
                    if (valid.equals(monthInput)) {
                        monthIndex = i + 1;
                        break;
                    }
                }
                if (monthIndex != -1) break;
            }

            System.out.print("Enter year: ");
            String yearInput = scanner.nextLine().trim();

            try {
                year = Integer.parseInt(yearInput);
                if (year < 0) {
                    year = -1;
                }
            } catch (NumberFormatException e) {
                year = -1;
            }

            if (monthIndex != -1 && year != -1) {
                break;
            } else {
                System.out.println("Invalid month/year. Please enter again.");
            }
        }

        boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
        int days = 0;

        switch (monthIndex) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                days = 31;
                break;
            case 4: case 6: case 9: case 11:
                days = 30;
                break;
            case 2:
                days = isLeapYear ? 29 : 28;
                break;
        }

        System.out.println("Number of days: " + days);
        scanner.close();
    }
}