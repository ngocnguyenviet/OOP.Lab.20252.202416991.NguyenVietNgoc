package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }
    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");

        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");

        addBook.addActionListener(e -> {
            this.dispose();
            new AddBookToStoreScreen(store, cart);
        });

        addCD.addActionListener(e -> {
            this.dispose();
            new AddCompactDiscToStoreScreen(store, cart);
        });

        addDVD.addActionListener(e -> {
            this.dispose();
            new AddDigitalVideoDiscToStoreScreen(store, cart);
        });

        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);

        menu.add(smUpdateStore);
        JMenuItem viewStore = new JMenuItem("View store");

        viewStore.addActionListener(e -> {
            this.dispose();
            new StoreScreen(this.store, this.cart);
        });

        menu.add(viewStore);

        JMenuItem viewCart = new JMenuItem("View cart");

        viewCart.addActionListener(e -> {
            this.dispose();
            new CartScreen(this.cart, this.store);
        });

        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton btnViewCart = new JButton("View cart");
        btnViewCart.setPreferredSize(new Dimension(100, 50));
        btnViewCart.setMaximumSize(new Dimension(100, 50));

        btnViewCart.addActionListener(e -> {
            this.dispose();
            new CartScreen(this.cart, this.store);
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(btnViewCart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }
    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(0, 3, 2, 2));

        for (Media media : store.getItemsInStore()) {
            MediaStore cell = new MediaStore(media, cart);
            center.add(cell);
        }

        return center;
    }

    public StoreScreen(Store store, Cart cart) {
        this.store = store;
        this.cart = cart;
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        // DVDs
        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King",
                "Animation",
                "Roger Allers",
                87,
                19.95f
        );

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars",
                "Science Fiction",
                "George Lucas",
                124,
                24.95f
        );

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Aladdin",
                "Animation",
                "John Musker",
                90,
                18.99f
        );

// Books
        Book book1 = new Book(
                "Java Programming",
                "Education",
                15.50f
        );

        Book book2 = new Book(
                "Clean Code",
                "Programming",
                21.75f
        );

        Book book3 = new Book(
                "Design Patterns",
                "Software Engineering",
                29.99f
        );

// CDs
        CompactDisc cd1 = new CompactDisc(
                "Greatest Hits",
                "Music",
                12.99f,
                "Queen"
        );

        CompactDisc cd2 = new CompactDisc(
                "Thriller",
                "Pop",
                14.99f,
                "Michael Jackson"
        );

        CompactDisc cd3 = new CompactDisc(
                "Abbey Road",
                "Rock",
                13.99f,
                "The Beatles"
        );

// Add all items to store
        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(book3);

        store.addMedia(cd1);
        store.addMedia(cd2);
        store.addMedia(cd3);
        new StoreScreen(store, cart);
    }
}
