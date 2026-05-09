package L1;

import javax.swing.*;
import java.util.Scanner;

public class Ex6_1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int option = JOptionPane.showConfirmDialog(null,
                "Do you want to change to the first class ticket?");
        JOptionPane.showMessageDialog(null, "You've chosen: "
        + (option == JOptionPane.YES_OPTION ? "YES" : "NO"));
        System.exit(0);
    }
}
