package hust.soict.hedspi.aims;

public class MenuView {
    public static void showMainMenu() {
        System.out.println("\n--- AIMS MAIN MENU ---");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.print("Please choose a number (0-3): ");
    }

    public static void showStoreMenu(){
        System.out.println("\n--- STORE MENU ---");
        System.out.println("1. See a media's details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.print("Please choose a number (0-4): ");
    }

    public static void showMediaDetailsMenu(){
        System.out.println("\n--- MEDIA DETAILS ---");
        System.out.println("1. Add to cart");
        System.out.println("2. Play");
        System.out.println("0. Back");
        System.out.print("Please choose a number (0-2): ");
    }

    public static void showCartMenu(){
        System.out.println("\n--- CART MENU ---");
        System.out.println("1. Filter media in cart");
        System.out.println("2. Sort media in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.print("Please choose a number (0-5): ");
    }
}
