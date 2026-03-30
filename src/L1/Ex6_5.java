package L1;

import java.util.Scanner;
import java.util.Arrays;

public class Ex6_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        double[] arr = new double[size];

        System.out.println("Enter " + size + " numeric elements:");
        for (int i = 0; i < size; i++) {
            arr[i] = scanner.nextDouble();
        }

        Arrays.sort(arr);

        double sum = 0;
        for (double num : arr) {
            sum += num;
        }

        double average = sum / size;

        System.out.println("Sorted array: " + Arrays.toString(arr));
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);

        scanner.close();
    }
}