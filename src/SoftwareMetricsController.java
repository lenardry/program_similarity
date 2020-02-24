import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class SoftwareMetricsController {
    public AnchorPane softMetAnchorPane;
    public Button homeButton;
    public Button inputCodesButton;
    public Button seeLibraryButton;
    public Button simMatrixButton;
    public Button softwareMetricsBtn;
    public TextField fileInput;
    public Button selectFileButton;
    public Label lengthLabel;
    public Label vocabLabel;
    public Label volumeLabel;
    public Label difficultyLabel;
    public Label levelLabel;
    public Label effortLabel;

    public void onHomePress(ActionEvent actionEvent) throws IOException {
        AnchorPane thisPane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        softMetAnchorPane.getChildren().setAll(thisPane);
    }

    public void onInputPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/InputCode.fxml"));
        softMetAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSeeLibrary(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SeeLibrary.fxml"));
        softMetAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSimMatrixPress(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SimMatrix.fxml"));
        softMetAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSoftMetrPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/SoftwareMetrics.fxml"));
        softMetAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSelectFile(ActionEvent actionEvent) throws IOException {
        ArrayList<Double> metrics = new ArrayList<>();
        FileChooser fileChooser = new FileChooser();
        File thisFile = fileChooser.showOpenDialog((Stage) softMetAnchorPane.getScene().getWindow());
        fileInput.setText(thisFile.getName());

        SoftwareMetrics s = new SoftwareMetrics();

        metrics  = s.getMetrics(thisFile.getAbsolutePath());

        lengthLabel.setText(Double.toString(metrics.get(0)));
        vocabLabel.setText(Double.toString(metrics.get(1)));
        volumeLabel.setText(Double.toString(metrics.get(2)));
        difficultyLabel.setText(Double.toString(metrics.get(3)));
        levelLabel.setText(Double.toString(metrics.get(4)));
        effortLabel.setText(Double.toString(metrics.get(5)));
    }
}
