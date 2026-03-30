package L1;

import java.util.Scanner;

public class EquationSolver {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.println("------------------");
        System.out.println("1. Giai pt bac nhat 1 an (ax + b = 0)");
        System.out.println("2. Giai hpt bac nhat 2 an");
        System.out.println("3. Giai pt bac hai 1 an (ax^2 + bx + c = 0)");
        System.out.println("Chon chuc nang (1-3): ");
        int choice = Integer.parseInt(sc.nextLine());

        switch(choice){
            case 1:
                solveFirstDegree(sc);
                break;
            case 2:
                solveSystem(sc);
                break;
            case 3:
                solveSecondDegree(sc);
                break;
            default:
                System.out.println("Lua chon khong hop le");
        }
        sc.close();
    }

    public static void solveFirstDegree(Scanner sc){
        System.out.print("Nhap a: "); double a = Double.parseDouble(sc.nextLine());
        System.out.print("Nhap b: "); double b = Double.parseDouble(sc.nextLine());

        if(a == 0){
            if(b == 0){
                System.out.println("Phuong trinh co vo so nghiem");
            }else{
                System.out.println("Phuong trinh vo nghiem");
            }
        }else{
            double x = -b/a;
            System.out.println("Nghiem pt x = " + x);
        }
    }

    public static void solveSystem(Scanner sc){
        System.out.println("Nhap cac he so: ");
        System.out.print("a11: "); double a11 = sc.nextDouble();
        System.out.print("a12: "); double a12 = sc.nextDouble();
        System.out.print("b1: "); double b1 = sc.nextDouble();
        System.out.print("a21: "); double a21 = sc.nextDouble();
        System.out.print("a22: "); double a22 = sc.nextDouble();
        System.out.print("b2: "); double b2 = sc.nextDouble();

        double D = a11 * a22 - a21 * a12;
        double D1 = b1 * a22 - b2 * a12;
        double D2 = a11 * b2 - a21 * b1;

        if(D != 0){
            System.out.println("He co nghiem duy nhat: x1 = " + (D1 / D) + ", x2" + (D2 / D));
        }else{
            if(D1 == 0 || D2 == 0){
                System.out.println("He co vo so nghiem");
            }else{
                System.out.println("He vo nghiem");
            }
        }
    }

    public static void solveSecondDegree(Scanner sc){
        System.out.println("Nhap a : "); double a = sc.nextDouble();
        System.out.println("Nhap b : "); double b = sc.nextDouble();
        System.out.println("Nhap c : "); double c = sc.nextDouble();

        if(a == 0){
            if(b == 0){
                System.out.println(c == 0 ? "Vo so nghiem" : "Vo nghiem");
            }else{
                System.out.println("x = " + (-c/b));
            }
        }else{
            double delta = b * b - 4 * a * c;
            if(delta > 0){
                double x1 = (-b + Math.sqrt(delta)) / (2 * a);
                double x2 = (-b - Math.sqrt(delta)) / (2 * a);
                System.out.println("Phuong trinh co 2 nghiem phan biet: x1 = " + x1 + ", x2 = " + x2);
            }else if(delta == 0){
                System.out.println("Phuong trinh co nghiem kep x = " + (-b / (2 * a)));
            }else{
                System.out.println("Phuong trinh vo nghiem thuc");
            }
        }
    }
}
