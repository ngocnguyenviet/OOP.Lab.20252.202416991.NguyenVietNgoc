package hust.soict.hedspi.aims.screen.manager;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.exception.PlayerException;
import hust.soict.hedspi.aims.media.*;
import hust.soict.hedspi.aims.screen.StoreScreen;
import hust.soict.hedspi.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;


public class CartScreenController {

    private Cart cart;
    private Store store;
    private JFrame currentFrame;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media,String> colMediaTitle;

    @FXML
    private TableColumn<Media,String> colMediaCategory;

    @FXML
    private TableColumn<Media,Float> colMediaCost;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    @FXML
    private Label lblTotalCost;

    @FXML
    private Button btnPlaceOrder;

    private ObservableList<Media> observableMediaList;
    private FilteredList<Media> filteredMediaList;

    public CartScreenController(Cart cart, Store store, JFrame currentFrame) {
        super();
        this.cart = cart;
        this.store = store;
        this.currentFrame = currentFrame;
    }

    @FXML
    public void initialize() {

        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediaCategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));
        tblMedia.setItems(this.cart.getItemsOrdered());

        updateTotalCost();

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new  ChangeListener<Media>() {

                    @Override
                    public void changed(ObservableValue<? extends Media> observable,
                                        Media oldValue, Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        }
                    }
                }
        );

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        radioBtnFilterId.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue,
                                Boolean newValue) {
                showFilteredMedia(tfFilter.getText());
            }
        });

        radioBtnFilterTitle.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable,
                                Boolean oldValue,
                                Boolean newValue) {
                showFilteredMedia(tfFilter.getText());
            }
        });

        cart.getItemsOrdered().addListener((javafx.collections.ListChangeListener<Media>) change -> {
            updateTotalCost();
        });
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        }
        else {
            btnPlay.setVisible(false);
        }
    }

    void showFilteredMedia(String filterText) {
        if (filterText == null || filterText.trim().isEmpty()) {
            tblMedia.setItems(cart.getItemsOrdered());
            return;
        }

        Media media = null;

        if (radioBtnFilterId.isSelected()) {
            try {
                int id = Integer.parseInt(filterText.trim());
                media = cart.searchById(id);
            } catch (NumberFormatException e) {
                tblMedia.setItems(FXCollections.observableArrayList());
                return;
            }
        }

        if (radioBtnFilterTitle.isSelected()) {
            media = cart.searchByTitle(filterText.trim());
        }

        if (media != null) {
            tblMedia.setItems(FXCollections.observableArrayList(media));
        } else {
            tblMedia.setItems(FXCollections.observableArrayList());
        }
    }

    private void updateTotalCost() {
        lblTotalCost.setText(String.format("%.2f $", cart.totalCost()));
    }

    @FXML
    private void btnPlayPressed() {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        if (selectedMedia == null) {
            showErrorAlert("No media selected", "Please select a media to play.");
            return;
        }

        if (selectedMedia instanceof CompactDisc) {
            playCompactDisc((CompactDisc) selectedMedia);
            return;
        }

        if (selectedMedia instanceof Playable) {
            try {
                ((Playable) selectedMedia).play();

                showAlert(
                        "Playing media",
                        selectedMedia.getTitle() + " is playing."
                );

            } catch (PlayerException e) {
                showErrorAlert(
                        "Cannot play media",
                        e.getMessage()
                );
            }
        } else {
            showErrorAlert(
                    "Cannot play media",
                    "This media is not playable."
            );
        }
    }

    @FXML
    private void btnRemovePressed() {
        Media selectedMedia = tblMedia.getSelectionModel().getSelectedItem();

        if (selectedMedia == null) {
            showErrorAlert("No media selected", "Please select a media to remove.");
            return;
        }

        cart.removeMedia(selectedMedia);
        tblMedia.setItems(cart.getItemsOrdered());
        updateTotalCost();
    }

    @FXML
    private void btnPlaceOrderPressed() {
        if (cart.getItemsOrdered().isEmpty()) {
            showAlert("Empty cart", "Your cart is empty.");
            return;
        }

        cart.clearCart();
        tblMedia.setItems(cart.getItemsOrdered());
        updateTotalCost();

        showAlert("Order created", "An order has been created successfully.");
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void menuViewStorePressed() {
        currentFrame.dispose();
        new StoreScreen(store, cart);
    }

    @FXML
    private void menuViewCartPressed() {
        tblMedia.setItems(cart.getItemsOrdered());
        updateTotalCost();
    }

    private void playCompactDisc(CompactDisc cd) {
        if (cd.getTracks() == null || cd.getTracks().isEmpty()) {
            showErrorAlert(
                    "Cannot play CD",
                    "ERROR: CD has no tracks!"
            );
            return;
        }

        showAlert(
                "Playing CD",
                "Playing disc: " + cd.getTitle()
                        + "\nDisc length: " + cd.getLength() + " minutes"
                        + "\nNumber of tracks: " + cd.getTracks().size()
        );

        for (Track track : cd.getTracks()) {
            try {
                track.play();

                showAlert(
                        "Playing track",
                        "Track: " + track.getTitle()
                                + "\nLength: " + track.getLength() + " minutes"
                );

            } catch (PlayerException e) {
                showErrorAlert(
                        "Cannot play track",
                        "Track: " + track.getTitle()
                                + "\n" + e.getMessage()
                );
            }
        }
    }
}
