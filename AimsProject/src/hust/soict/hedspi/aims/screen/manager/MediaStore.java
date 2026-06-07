package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.media.Track;

import javax.swing.*;
import java.awt.*;

public class MediaStore extends JPanel {
    private Media media;
    private Cart cart;

    public MediaStore(Media media, Cart cart) {

        this.media = media;
        this.cart = cart;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel(media.getTitle());
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 20));
        title.setAlignmentX(CENTER_ALIGNMENT);

        JLabel cost = new JLabel("" + media.getCost() + " $");
        cost.setAlignmentX(CENTER_ALIGNMENT);

        JPanel container = new JPanel();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        JButton btnAddToCart = new JButton("Add to cart");

        container.add(btnAddToCart);

        btnAddToCart.addActionListener(e -> {
            cart.addMedia(media);

            JOptionPane.showMessageDialog(
                    null,
                    media.getTitle() + " has been added to cart.",
                    "Added to cart",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        if (media instanceof Playable) {
            JButton btnPlay = new JButton("Play");

            btnPlay.addActionListener(e -> {
                if (media instanceof CompactDisc) {
                    playCompactDisc((CompactDisc) media);
                    return;
                }

                try {
                    ((Playable) media).play();

                    JOptionPane.showMessageDialog(
                            null,
                            media.getTitle() + " is playing.",
                            "Playing media",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } catch (PlayerException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            ex.getMessage(),
                            "Cannot play media",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            });

            container.add(btnPlay);
        }

        this.add(Box.createVerticalGlue());
        this.add(title);
        this.add(cost);
        this.add(Box.createVerticalGlue());
        this.add(container);

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    private void playCompactDisc(CompactDisc cd) {
        if (cd.getTracks() == null || cd.getTracks().isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "ERROR: CD has no tracks!",
                    "Cannot play CD",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        JOptionPane.showMessageDialog(
                null,
                "Playing disc: " + cd.getTitle()
                        + "\nDisc length: " + cd.getLength() + " minutes"
                        + "\nNumber of tracks: " + cd.getTracks().size(),
                "Playing CD",
                JOptionPane.INFORMATION_MESSAGE
        );

        for (Track track : cd.getTracks()) {
            try {
                track.play();

                JOptionPane.showMessageDialog(
                        null,
                        "Track: " + track.getTitle()
                                + "\nLength: " + track.getLength() + " minutes",
                        "Playing track",
                        JOptionPane.INFORMATION_MESSAGE
                );

            } catch (PlayerException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Track: " + track.getTitle()
                                + "\n" + ex.getMessage(),
                        "Cannot play track",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }
}