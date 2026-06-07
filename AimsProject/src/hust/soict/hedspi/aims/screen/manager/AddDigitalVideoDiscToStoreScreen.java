package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.screen.AddItemToStoreScreen;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfDirector;
    private JTextField tfLength;

    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD To Store");
    }

    @Override
    protected void addSpecialFields(JPanel form) {
        tfDirector = new JTextField(25);
        tfLength = new JTextField(25);

        form.add(new JLabel("Director:"));
        form.add(tfDirector);

        form.add(new JLabel("Length:"));
        form.add(tfLength);
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

        String director = tfDirector.getText().trim();

        int length = 0;
        String lengthText = tfLength.getText().trim();

        if (!lengthText.isEmpty()) {
            length = Integer.parseInt(lengthText);
        }

        DigitalVideoDisc dvd = new DigitalVideoDisc(
                title,
                category,
                director,
                length,
                cost
        );

        store.addMedia(dvd);

        JOptionPane.showMessageDialog(
                null,
                "DVD has been added to store.",
                "Add DVD",
                JOptionPane.INFORMATION_MESSAGE
        );

        this.dispose();
        new StoreScreen(store, cart);
    }
}