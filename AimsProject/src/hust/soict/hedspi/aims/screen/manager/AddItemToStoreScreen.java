package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.screen.CartScreen;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;

    protected JTextField tfTitle;
    protected JTextField tfCategory;
    protected JTextField tfCost;

    public AddItemToStoreScreen(Store store, Cart cart, String screenTitle) {
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());

        cp.add(createNorth(screenTitle), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);
        cp.add(createSouth(), BorderLayout.SOUTH);

        setTitle(screenTitle);
        setSize(1024, 768);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createNorth(String screenTitle) {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader(screenTitle));
        return north;
    }

    protected JMenuBar createMenuBar() {
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

        JMenuItem viewStore = new JMenuItem("View store");
        viewStore.addActionListener(e -> {
            this.dispose();
            new StoreScreen(store, cart);
        });

        JMenuItem viewCart = new JMenuItem("View cart");
        viewCart.addActionListener(e -> {
            this.dispose();
            new CartScreen(cart, store);
        });

        menu.add(smUpdateStore);
        menu.add(viewStore);
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    private JPanel createHeader(String screenTitle) {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel(screenTitle);
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 40));
        title.setForeground(Color.CYAN);

        JButton btnViewCart = new JButton("View cart");
        btnViewCart.setPreferredSize(new Dimension(100, 50));
        btnViewCart.setMaximumSize(new Dimension(100, 50));

        btnViewCart.addActionListener(e -> {
            this.dispose();
            new CartScreen(cart, store);
        });

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(btnViewCart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    private JPanel createCenter() {
        JPanel wrapper = new JPanel(new BorderLayout());

        JPanel form = new JPanel();
        form.setLayout(new GridLayout(0, 2, 10, 15));

        tfTitle = new JTextField(25);
        tfCategory = new JTextField(25);
        tfCost = new JTextField(25);

        form.add(new JLabel("Title:"));
        form.add(tfTitle);

        form.add(new JLabel("Category:"));
        form.add(tfCategory);

        form.add(new JLabel("Cost:"));
        form.add(tfCost);

        addSpecialFields(form);

        JPanel extraPanel = createExtraPanel();

        wrapper.setBorder(BorderFactory.createEmptyBorder(40, 250, 40, 250));
        wrapper.add(form, BorderLayout.NORTH);

        if (extraPanel != null) {
            wrapper.add(extraPanel, BorderLayout.CENTER);
        }

        return wrapper;
    }

    protected JPanel createExtraPanel() {
        return null;
    }

    private JPanel createSouth() {
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAdd = new JButton("Add to store");
        JButton btnCancel = new JButton("Cancel");

        btnAdd.addActionListener(e -> addItemToStore());

        btnCancel.addActionListener(e -> {
            this.dispose();
            new StoreScreen(store, cart);
        });

        south.add(btnAdd);
        south.add(btnCancel);

        return south;
    }

    protected abstract void addSpecialFields(JPanel form);

    protected abstract void addItemToStore();
}