import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.awt.Desktop;
import java.io.File;

public class SeeLibraryController {
    public Button homeButton;
    public Button inputCodesButton;
    public Button seeLibraryButton;
    public Button simMatrixButton;
    public AnchorPane seeLibraryAnchorPane;
    public TextArea code1Display;
    public TextField seeFileInput1;
    public Button selectFile1;
    public TextArea code2Display;
    public TextField seeFileInput2;
    public Button selectFile2;

    public void onHomePress(ActionEvent actionEvent) throws IOException {
        AnchorPane thisPane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        seeLibraryAnchorPane.getChildren().setAll(thisPane);
    }

    public void onInputPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/InputCode.fxml"));
        seeLibraryAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSeeLibrary(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SeeLibrary.fxml"));
        seeLibraryAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSimMatrixPress(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SimMatrix.fxml"));
        seeLibraryAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSelectFile1(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File thisFile = fileChooser.showOpenDialog((Stage) seeLibraryAnchorPane.getScene().getWindow());
        seeFileInput1.setText(thisFile.getName());

        File file = new File(thisFile.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            code1Display.appendText(line);
            code1Display.appendText("\n");
        }
    }


    public void onSelectFile2(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser = new FileChooser();
        File thisFile = fileChooser.showOpenDialog((Stage) seeLibraryAnchorPane.getScene().getWindow());
        seeFileInput2.setText(thisFile.getName());

        File file = new File(thisFile.getAbsolutePath());

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String line;
        while ((line = reader.readLine()) != null) {
            code2Display.appendText(line);
            code2Display.appendText("\n");
        }
    }
}
