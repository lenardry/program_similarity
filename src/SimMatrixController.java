import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SimMatrixController implements Initializable {

    public AnchorPane simMatrixAnchorPane;
    public Button homeButton;
    public Button inputCodesButton;
    public Button seeLibraryButton;
    public Button simMatrixButton;
    public ListView fileList;
    public Button addButton;
    public Button selectAllButton;
    public ListView selectedFiles;
    public Button removeButton;
    public Button submitButton;
    public TextField selectFolderInput;
    public Button selectFolderButton;
    public Button removeAllButton;
    public Label promptLabel;
    public Button softwareMetricsBtn;

    public void onHomePress(ActionEvent actionEvent) throws IOException {
        AnchorPane thisPane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        simMatrixAnchorPane.getChildren().setAll(thisPane);
    }

    public void onInputPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/InputCode.fxml"));
        simMatrixAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSeeLibrary(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SeeLibrary.fxml"));
        simMatrixAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSimMatrixPress(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SimMatrix.fxml"));
        simMatrixAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSelectFolder(ActionEvent actionEvent) {
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Select Folder");
        File defaultDirectory = new File("C:\\Users\\Lenard Llarenas\\IdeaProjects\\CP2Dcourse\\Module1");
        chooser.setInitialDirectory(defaultDirectory);
        File selectedDirectory = chooser.showDialog((Stage) simMatrixAnchorPane.getScene().getWindow());
        getFilenames(selectedDirectory);
    }

    public void onAdd(ActionEvent actionEvent) {
        selectedFiles.getItems().add(fileList.getSelectionModel().getSelectedItem());
    }

    public void onSelectAll(ActionEvent actionEvent) {

        List<String> files = fileList.getItems();
        for(int i=0; i<files.size(); i++){
            selectedFiles.getItems().add(files.get(i));
        }
    }

    public void onRemove(ActionEvent actionEvent) {
        selectedFiles.getItems().remove(selectedFiles.getSelectionModel().getSelectedItem());
    }

    public void onRemoveAll(ActionEvent actionEvent) {
        selectedFiles.getItems().clear();
    }

    public void onSubmit(ActionEvent actionEvent) throws IOException {
        promptLabel.setText("Processing...");
        MatrixController matrix = new MatrixController();
        matrix.setList(selectedFiles);
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/Matrix.fxml"));
        simMatrixAnchorPane.getChildren().setAll(seeLibaryPane);
        /*
        MatrixController matrix = new MatrixController();
        matrix.createMatrix(selectedFiles);

         */
    }

    private void getFilenames(File selectedDirectory){
        boolean areCodes = false;
        ArrayList<String> files = new ArrayList<>();
        File folder = new File(selectedDirectory.getAbsolutePath());
        System.out.println(selectedDirectory.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();
        for(int i = 0; i<listOfFiles.length; i++){
            if(listOfFiles[i].getName().endsWith(".cpp") || listOfFiles[i].getName().endsWith(".java") || listOfFiles[i].getName().endsWith(".fxml")){
                fileList.getItems().add(listOfFiles[i].getName());
                areCodes=true;
            }
            else{
                fileList.getItems().add(listOfFiles[i].getName());
                //getFilesInFolders(selectedDirectory, listOfFiles[i].getName());
            }
        }
    }

    private void getFilesInFolders(File selectedDirectory, String folderName){
        File folder = new File(selectedDirectory.getAbsolutePath() + "\\" + folderName);
        File[] listOfFiles = folder.listFiles();
        for(int i = 0; i<listOfFiles.length; i++){
            fileList.getItems().add(listOfFiles[i].getName());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        selectedFiles.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void onSoftMetrPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/SoftwareMetrics.fxml"));
        simMatrixAnchorPane.getChildren().setAll(inputCodesPane);
    }
}
