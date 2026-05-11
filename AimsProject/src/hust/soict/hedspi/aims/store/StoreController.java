package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.MenuView;
import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;

import java.awt.*;
import java.util.Scanner;

public class StoreController {
    private Store store;
    private Cart cart;
    private Scanner sc;

    public StoreController(Store store, Cart cart, Scanner sc) {
        this.store = store;
        this.cart = cart;
        this.sc = sc;
    }

    public void updateStore(){
        System.out.println("\n1. Add media | 2. Remove media | 0. Back");
        System.out.print("Choose option: ");
        int choice = Integer.parseInt(sc.nextLine());

        if(choice == 1){
            System.out.print("Enter title to add : ");
            String title = sc.nextLine();
            store.addMedia(new DigitalVideoDisc(title, "Unknow", 0.0f));
        } else if(choice == 2){
            System.out.print("Enter title to remove: ");
            String title = sc.nextLine();
            Media m = store.searchByTitle(title);
            if(m != null) store.removeMedia(m);
            else System.out.println("Not found !");
        }
    }

    public void viewStore(){
        store.printStore();
        while(true){
            MenuView.showStoreMenu();
            int choice = Integer.parseInt(sc.nextLine());

            if(choice == 0) break;

            switch(choice){
                case 1:
                    System.out.print("Enter title: ");
                    Media m = store.searchByTitle(sc.nextLine());
                    if(m != null) {
                        System.out.print(m.toString());
                        handleMediaDetails(m);
                    } else System.out.println("Not found !");
                    break;
                case 2:
                    System.out.print("Enter title to add to cart: ");
                    Media toAdd = store.searchByTitle(sc.nextLine());
                    if(toAdd != null) cart.addMedia(toAdd);
                    break;
                case 3:
                    System.out.print("Enter title to play: ");
                    Media toPlay = store.searchByTitle(sc.nextLine());
                    playMedia(toPlay);
                    break;
                case 4:
                    return;
            }
        }
    }

    public void handleMediaDetails(Media m){
        while(true){
            MenuView.showMediaDetailsMenu();
            int choice = Integer.parseInt(sc.nextLine());

            if(choice == 0) break;

            if(choice == 1) cart.addMedia(m);
            else if(choice == 2) playMedia(m);

        }
    }

    private void playMedia(Media m){
        if(m instanceof DigitalVideoDisc){
            ((Playable) m).play();
        }
        else System.out.println("This media cannot be played.");
    }


}
