package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.Util;
import org.example.model.LinguisticVariable;
import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PanelController {

    @FXML
    private TextField txtT1, txtT2, txtT3, txtT4, txtT5, txtT6, txtT7, txtT8, txtT9, txtT10, txtT11;

    @FXML
    private ComboBox comboQuantifier, comboSort;

    @FXML
    private TreeView listSummarizers;

    @FXML
    private Button btnGenerate, btnSave, btnClean;

    @FXML
    private TableView tableGenerated;

    List<Entry> records;
    List<Quantifier> relativeQuantifiers;
    List<Quantifier> absoluteQuantifiers;
    List<LinguisticVariable> linguisticVariables;

    @FXML
    private void initialize() {
        records = Util.loadFromDatabase();
        relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();
        absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();
        linguisticVariables = Util.getDefaultLinguisticVariables();

        for(int i=0; i<relativeQuantifiers.size(); i++) {
            comboQuantifier.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
        }
        for(int i=0; i<absoluteQuantifiers.size(); i++) {
            comboQuantifier.getItems().add(absoluteQuantifiers.get(i).getTextualRepresentation());
        }


    }


}
