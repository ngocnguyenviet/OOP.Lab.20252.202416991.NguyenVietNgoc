package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.MenuView;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import java.util.Scanner;

public class CartController {
    private Cart cart;
    private Scanner sc;

    public CartController(Cart cart, Scanner sc) {
        this.cart = cart;
        this.sc = sc;
    }

    public void viewCart() {
        cart.print();
        while(true){
            MenuView.showCartMenu();
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 0) break;

            switch(choice){
                case 1:
                    System.out.print("Filter by (1) ID or (2) Title? ");
                    if(sc.nextInt() == 1){
                        System.out.print("Enter ID: ");
                        cart.searchByID(sc.nextInt());
                    } else {
                        sc.nextLine();
                        System.out.print("Enter Title: ");
                        cart.searchByTitle(sc.nextLine());
                    }
                    sc.nextLine();
                    break;
                case 2:
                    System.out.print("Sort by (1) Title or (2) Cost? ");
                    if(sc.nextInt() == 1) cart.sortByCost();
                    else cart.sortByTitle();
                    sc.nextLine();
                    cart.print();
                    break;
                case 3:
                    System.out.print("Enter title to remove: ");
                    Media mRemove = cart.searchByTitle(sc.nextLine());
                    if(mRemove != null){
                        cart.removeMedia(mRemove);
                    }
                    break;
                case 4:
                    System.out.print("Enter title to play : ");
                    Media mPlay = cart.searchByTitle(sc.nextLine());
                    if(mPlay instanceof Playable) ((Playable) mPlay).play();
                    else System.out.println("Cannot play this media.");
                    break;
                case 5:
                    System.out.println("Order created. Thank you !");
                    cart.emptyCart();
                    return;
            }
        }
    }
}
