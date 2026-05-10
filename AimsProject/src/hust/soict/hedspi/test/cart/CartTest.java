package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args){
        Cart cart = new Cart();
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        cart.addDigitalVideoDisc(dvd1);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);
        cart.addDigitalVideoDisc(dvd2);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        cart.addDigitalVideoDisc(dvd3);

        cart.viewCart2();

        //Search by ID
        System.out.println("\\n--- Search by ID ---");
        cart.searchByID(2);
        cart.searchByID(99);

        //Search by Title
        System.out.println("\\n--- Search by Title ---");
        cart.searchByTitle("Star Wars");
        cart.searchByTitle("Lion King");
        cart.searchByTitle("LION dog");
        cart.searchByTitle("Avatar");
    }
}
