import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class InputCodeController {
    public Button homeButton;
    public Button inputCodesButton;
    public Button seeLibraryButton;
    public Button simMatrixButton;
    public TextField filenameInput;
    public TextArea codeInputField;
    public Button submitButton;
    public AnchorPane inputCodeAnchorPane;
    public Label confirmationLabel;

    public void onHomePress(ActionEvent actionEvent) throws IOException {
        AnchorPane thisPane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        inputCodeAnchorPane.getChildren().setAll(thisPane);
    }

    public void onInputPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/InputCode.fxml"));
        inputCodeAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSeeLibrary(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SeeLibrary.fxml"));
        inputCodeAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSimMatrixPress(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SimMatrix.fxml"));
        inputCodeAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException {
        FileWriter myWriter = new FileWriter(filenameInput.getText() + ".txt");
        myWriter.write(codeInputField.getText());
        myWriter.close();
        confirmationLabel.setText("File created successfully!");
    }
}
