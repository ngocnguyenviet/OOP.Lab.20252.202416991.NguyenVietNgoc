package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.cart.CartController;
import hust.soict.hedspi.aims.store.Store;
import hust.soict.hedspi.aims.store.StoreController;

import java.util.Scanner;

public class Aims {
    private static Store store = new Store();
    private static Cart cart = new Cart();
    private static Scanner sc = new Scanner(System.in);


    public static void main(String[] args) {
        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King", "Animation",
                "Roger Aller", 87, 19.95F);
        anOrder.addMedia(dvd1);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars", "Science Fiction",
                "George Lucas", 87, 24.95F);
        anOrder.addMedia(dvd2);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Aladin", "Animation", 18.99F);
        anOrder.addMedia(dvd3);

        StoreController storeController = new StoreController(store, cart, sc);
        CartController cartController = new CartController(cart, sc);

        while(true){
            MenuView.showMainMenu();
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice){
                case 1:
                    storeController.viewStore();
                    break;
                case 2:
                    storeController.updateStore();
                    break;
                case 3:
                    cartController.viewCart();
                    break;
                case 0:
                    System.out.println("Exiting AIMS...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
                }
        }
    }
}
