package L1;

import java.util.Scanner;

public class Calculate {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Nhap so thu nhat: ");
            double num1 = Double.parseDouble(sc.nextLine());

            System.out.print("Nhap so thu hai: ");
            double num2 = Double.parseDouble(sc.nextLine());

            System.out.println("\n--- Ket Qua ---");
            System.out.println("Tong   : " + (num1 + num2));
            System.out.println("Hieu   : " + (num1 - num2));
            System.out.println("Tich   : " + (num1 * num2));

            if (num2 != 0) {
                System.out.println("Thuong : " + (num1 / num2));
            } else {
                System.out.println("Thuong : Khong the chia cho 0");
            }
        } catch (NumberFormatException e) {
            System.out.println("Loi: Ban phai nhap vao mot so thuc!");
        } finally {
            sc.close();
        }
    }
}