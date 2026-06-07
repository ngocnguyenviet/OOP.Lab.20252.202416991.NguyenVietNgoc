package hust.soict.hedspi.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PainterController {

    @FXML
    private Pane drawingAreaPane;

    @FXML
    void clearButtonPressed(ActionEvent event) {
        drawingAreaPane.getChildren().clear();
    }

    @FXML
    private RadioButton eraser;

    @FXML
    private RadioButton pen;

    @FXML
    void drawingAreaMouseDragged(MouseEvent event) {
        if (pen.isSelected()) {
            Circle newCircle = new Circle(event.getX(), event.getY(), 4,
                    Color.BLACK);
            drawingAreaPane.getChildren().add(newCircle);
        }
        else {
            Circle newCircle = new Circle(event.getX(), event.getY(), 10,
                    Color.WHITE);
            drawingAreaPane.getChildren().add(newCircle);
        }
    }

}
