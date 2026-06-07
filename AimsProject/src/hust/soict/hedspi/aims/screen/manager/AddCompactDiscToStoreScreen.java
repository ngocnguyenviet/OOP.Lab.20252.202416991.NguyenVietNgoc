package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;
import hust.soict.hedspi.aims.screen.AddItemToStoreScreen;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {

    private JTextField tfArtist;
    private JTextField tfTrackTitle;
    private JTextField tfTrackLength;

    private DefaultListModel<String> trackListModel;
    private JList<String> trackList;

    private ArrayList<Track> tracks = new ArrayList<>();

    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD To Store");
    }

    @Override
    protected void addSpecialFields(JPanel form) {
        tfArtist = new JTextField(25);
        tfTrackTitle = new JTextField(25);
        tfTrackLength = new JTextField(25);

        form.add(new JLabel("Artist:"));
        form.add(tfArtist);

        form.add(new JLabel("Track title:"));
        form.add(tfTrackTitle);

        form.add(new JLabel("Track length:"));
        form.add(tfTrackLength);

        JButton btnAddTrack = new JButton("Add track");

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(btnAddTrack);

        form.add(new JLabel(""));
        form.add(buttonPanel);

        btnAddTrack.addActionListener(e -> addTrack());
    }

    @Override
    protected JPanel createExtraPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JLabel label = new JLabel("Tracks:");

        trackListModel = new DefaultListModel<>();
        trackList = new JList<>(trackListModel);
        trackList.setVisibleRowCount(8);
        trackList.setFixedCellHeight(24);

        JScrollPane scrollPane = new JScrollPane(trackList);
        scrollPane.setPreferredSize(new Dimension(420, 180));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        panel.add(label, BorderLayout.WEST);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private void addTrack() {
        String trackTitle = tfTrackTitle.getText();
        int trackLength = Integer.parseInt(tfTrackLength.getText());

        Track track = new Track(trackTitle, trackLength);

        if (tracks.contains(track)) {
            JOptionPane.showMessageDialog(
                    null,
                    "Track already exists!",
                    "Add Track",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        tracks.add(track);
        trackListModel.addElement(trackTitle + " - " + trackLength + " minutes");

        tfTrackTitle.setText("");
        tfTrackLength.setText("");
    }

    @Override
    protected void addItemToStore() {
        String title = tfTitle.getText();
        String category = tfCategory.getText();
        float cost = Float.parseFloat(tfCost.getText());
        String artist = tfArtist.getText();

        CompactDisc cd = new CompactDisc(title, category, cost, artist);

        for (Track track : tracks) {
            cd.addTrack(track);
        }

        store.addMedia(cd);

        JOptionPane.showMessageDialog(
                null,
                "CD has been added to store.",
                "Add CD",
                JOptionPane.INFORMATION_MESSAGE
        );

        this.dispose();
        new StoreScreen(store, cart);
    }
}