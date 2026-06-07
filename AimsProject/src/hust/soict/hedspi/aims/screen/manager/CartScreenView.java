package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.SwingUtilities;

public class CartScreenView {

    public static void main(String[] args) {
        Cart cart = new Cart();
        Store store = new Store();

        cart.addMedia(new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        ));

        cart.addMedia(new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                124,
                24.95f
        ));

        cart.addMedia(new Book(
                "Java Programming",
                "Education",
                15.50f
        ));

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CartScreen(cart, store);
            }
        });
    }
}