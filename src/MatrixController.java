import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class MatrixController implements Initializable{

    public AnchorPane matrixAnchorPane;
    public Button homeButton;
    public Button inputCodesButton;
    public Button seeLibraryButton;
    public Button simMatrixButton;
    public VBox contentVbox;
    public ScrollPane scrollPane;
    public static List<String> codes;
    public AnchorPane contentAnchor;
    public StackPane contentStack;
    public VBox mainVbox;
    public Label ghostLabel;
    public VBox InnerVBox;
    public ScrollPane contentScrollPane;
    private GridPane theMatrix;
    private static DecimalFormat df = new DecimalFormat("0.00");

    public void onHomePress(ActionEvent actionEvent) throws IOException {
        AnchorPane thisPane = FXMLLoader.load(getClass().getResource("/MainMenu.fxml"));
        matrixAnchorPane.getChildren().setAll(thisPane);
    }

    public void onInputPress(ActionEvent actionEvent) throws IOException {
        AnchorPane inputCodesPane = FXMLLoader.load(getClass().getResource("/InputCode.fxml"));
        matrixAnchorPane.getChildren().setAll(inputCodesPane);
    }

    public void onSeeLibrary(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SeeLibrary.fxml"));
        matrixAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void onSimMatrixPress(ActionEvent actionEvent) throws IOException {
        AnchorPane seeLibaryPane = FXMLLoader.load(getClass().getResource("/SimMatrix.fxml"));
        matrixAnchorPane.getChildren().setAll(seeLibaryPane);
    }

    public void createMatrix() throws IOException {
        double grade;
        theMatrix = new GridPane();
        CompareCode compare = new CompareCode();
        List<String> files = codes;
        ArrayList<Double> folderGrades = new ArrayList<Double>();

        for(int i=0; i<files.size(); i++){
            theMatrix.addRow(i);
            for(int j=0; j<files.size(); j++){
                theMatrix.addColumn(j);
            }
        }
        for(int i = 1; i<files.size()+1; i++){
            Label nameLabel = new Label(files.get(i-1));
            nameLabel.setFont(new Font(15));
            theMatrix.add(nameLabel, i, 0);
        }
        for(int j = 1; j<files.size()+1; j++){
            Label nameLabel = new Label(files.get(j-1));
            nameLabel.setFont(new Font(15));
            theMatrix.add(nameLabel, 0, j);
        }
        for(int i = 1; i<files.size()+1;i++){
            for(int j=1; j<files.size()+1; j++){
                if((files.get(i-1).endsWith(".cpp") || (files.get(i-1).endsWith(".java") || (files.get(i-1).endsWith(".fxml"))))){
                    grade = compare.compareFiles(files.get(i-1), files.get(j-1));
                }
                else{
                    grade = Collections.max(compare.compareFolders(files.get(i-1), files.get(j-1)));
                }
                Label gradeLabel = new Label(df.format(grade));
                gradeLabel.setFont(new Font(15));
                VBox root = new VBox();
                if(grade==1.00) root.setStyle("-fx-background-color: #FF0000");
                else if((grade>=0.75)&&(grade<1.00)) root.setStyle("-fx-background-color: #DC143C");
                else if((grade>=0.50)&&(grade<0.75)) root.setStyle("-fx-background-color: #CD5C5C");
                else if((grade>=0.25)&&(grade<0.50)) root.setStyle("-fx-background-color: #F08080");
                else if((grade>0.0)&&(grade<0.25)) root.setStyle("-fx-background-color: #FA8072");
                else if(grade==0.0) root.setStyle("-fx-background-color: #00FF00");
                root.getChildren().add(gradeLabel);
                theMatrix.add(root, i, j);
                //System.out.println(theMatrix.getChildren());
            }
        }

        //theMatrix.setMaxSize(5000, 1500);
        theMatrix.setPrefSize(2500, 2000);
        theMatrix.setPadding(new Insets(10, 10, 10, 10));
        theMatrix.setAlignment(Pos.TOP_LEFT);
        theMatrix.setGridLinesVisible(true);
        theMatrix.setVgap(2);
        theMatrix.setHgap(2);
//        contentVbox.setAlignment(Pos.CENTER);
        //contentVbox.getChildren().add(theMatrix);
        Button button = new Button();
        contentScrollPane.setContent(theMatrix);
        //InnerVBox.getChildren().add(theMatrix);

        //System.out.println(contentStack.getChildren());
    }

    public void setList(ListView selectedFiles){
        codes = selectedFiles.getItems();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            createMatrix();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
