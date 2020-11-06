package sample;

import company.Communication;
import company.imp.ImporterOperatorFactory;
import company.imp.OperatorException;
import company.imp.OperatorParser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class Controller {

    @FXML
    private Button bImporOperator;
    @FXML
    private Button bViewTables;

    @FXML
    private TextArea textArea;

    private OperatorParser operatorParser = null;

    @FXML
    private void OnActionImportOperator(){
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            try {
                operatorParser = ImporterOperatorFactory.getInstance().getImporterParser(file);

            } catch (OperatorException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    private void OnViewTables(){
        if(operatorParser != null){
            try {
                StringBuilder builder = new StringBuilder();
                String t = new String();
                List<Communication> list = operatorParser.getCommunications();
                for(Communication c : list){
                    t.concat(c.toString());
                }
                textArea.setText(t);
            } catch (OperatorException e) {
                e.printStackTrace();
            }
        }
    }

    public String getOperatorName(){
        return operatorParser.getNameOperator();
    }

}
