package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.screen.AddItemToStoreScreen;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddBookToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfAuthor;

    private DefaultListModel<String> authorListModel;
    private JList<String> authorList;

    private ArrayList<String> authors = new ArrayList<>();

    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add Book To Store");
    }

    @Override
    protected void addSpecialFields(JPanel form) {
        tfAuthor = new JTextField(25);

        form.add(new JLabel("Author:"));
        form.add(tfAuthor);

        JButton btnAddAuthor = new JButton("Add author");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(btnAddAuthor);

        form.add(new JLabel(""));
        form.add(buttonPanel);

        btnAddAuthor.addActionListener(e -> addAuthor());
    }

    @Override
    protected JPanel createExtraPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel label = new JLabel("Authors:");

        authorListModel = new DefaultListModel<>();
        authorList = new JList<>(authorListModel);
        authorList.setVisibleRowCount(8);
        authorList.setFixedCellHeight(24);
        authorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(authorList);
        scrollPane.setPreferredSize(new Dimension(420, 180));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(label, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addAuthor() {
        String authorName = tfAuthor.getText().trim();

        if (authorName.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Author name cannot be empty!",
                    "Add Author",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        if (authors.contains(authorName)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Author already exists!",
                    "Add Author",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        authors.add(authorName);
        authorListModel.addElement(authorName);

        tfAuthor.setText("");
    }

    @Override
    protected void addItemToStore() {
        String title = tfTitle.getText().trim();

        String category = tfCategory.getText().trim();

        float cost = 0;
        String costText = tfCost.getText().trim();

        if (!costText.isEmpty()) {
            cost = Float.parseFloat(costText);
        }

        Book book = new Book(title, category, cost);

        for (String author : authors) {
            book.addAuthor(author);
        }

        store.addMedia(book);

        JOptionPane.showMessageDialog(
                null,
                "Book has been added to store.",
                "Add Book",
                JOptionPane.INFORMATION_MESSAGE
        );

        this.dispose();
        new StoreScreen(store, cart);
    }
}