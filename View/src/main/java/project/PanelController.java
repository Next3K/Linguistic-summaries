package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import org.example.Generator;
import org.example.Util;
import org.example.model.LinguisticVariable;
import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.LabeledFuzzySet;
import org.example.model.statements.Summary;

import java.io.IOException;
import java.net.URL;
import java.util.*;

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
    private RadioButton radioAbsolute, radioRelative;

    @FXML
    private TableView tableGenerated;

    List<Entry> records;
    List<Quantifier> relativeQuantifiers;
    List<Quantifier> absoluteQuantifiers;
    List<LinguisticVariable> linguisticVariables;
    CheckBoxTreeItem<String> languages;
    List<LabeledFuzzySet> fuzzySets = new ArrayList<>(10);

    //Set<String> selectedNames = new HashSet<>();

    Map<String, List<String>> selectedNames = new HashMap<>();

    @FXML
    private void initialize() {
        records = Util.loadFromDatabase();
        relativeQuantifiers = Util.loadDefaultRelativeQuantifiers();
        absoluteQuantifiers = Util.loadDefaultAbsoluteQuantifiers();
        linguisticVariables = Util.getDefaultLinguisticVariables();

        comboQuantifier.setDisable(true);


        ToggleGroup toggleGroup = new ToggleGroup();
        radioAbsolute.setToggleGroup(toggleGroup);
        radioRelative.setToggleGroup(toggleGroup);

        // Słuchacz zdarzeń dla radioButton1
        radioAbsolute.setOnAction(event -> {
            if (radioAbsolute.isSelected()) {
                radioRelative.setSelected(false);
                comboQuantifier.setDisable(false);
                comboQuantifier.getItems().clear();
                for (int i = 0; i < absoluteQuantifiers.size(); i++) {
                    comboQuantifier.getItems().add(absoluteQuantifiers.get(i).getTextualRepresentation());
                }
            }
        });

        radioRelative.setOnAction(event -> {
            if (radioRelative.isSelected()) {
                radioAbsolute.setSelected(false);
                comboQuantifier.setDisable(false);
                comboQuantifier.getItems().clear();
                for (int i = 0; i < relativeQuantifiers.size(); i++) {
                    comboQuantifier.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
                }
            }
        });

        languages = new CheckBoxTreeItem<>("All qualifiers");

        for (int i = 0; i < linguisticVariables.size(); i++) {
            CheckBoxTreeItem<String> name = new CheckBoxTreeItem<>(linguisticVariables.get(i).getName());

            Set<String> variables = linguisticVariables.get(i).getLinguisticValues();
            List<String> v2 = variables.stream().toList();
            for (int j = 0; j < v2.size(); j++) {
                //System.out.println(v2.get(j));
                name.getChildren().add(new CheckBoxTreeItem<>(v2.get(j)));
            }
            languages.getChildren().add(name);
        }

        languages.setExpanded(true);
        listSummarizers.setRoot(languages);
        listSummarizers.setCellFactory(CheckBoxTreeCell.<String>forTreeView());


    }


    @FXML
    public void onBtnGenerate(ActionEvent actionEvent) {
        checkSelectedItems();
        ArrayList<Quantifier> oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(0)));
        ArrayList<Quantifier> zerQualifier = new ArrayList<>();

        if (radioRelative.isSelected()) {
            oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(checkRelativeVariableId(comboQuantifier.getValue().toString()))));
            zerQualifier = new ArrayList<>();
        } else {
            oneQualifier = new ArrayList<>();
            zerQualifier = new ArrayList<>(List.of(absoluteQuantifiers.get(checkAbsoluteVariableId(comboQuantifier.getValue().toString()))));
        }

        List<Double> weights =
                new ArrayList<>(List.of(Double.parseDouble(txtT1.getText()),
                        Double.parseDouble(txtT2.getText()),
                        Double.parseDouble(txtT3.getText()),
                        Double.parseDouble(txtT4.getText()),
                        Double.parseDouble(txtT5.getText()),
                        Double.parseDouble(txtT6.getText()),
                        Double.parseDouble(txtT7.getText()),
                        Double.parseDouble(txtT8.getText()),
                        Double.parseDouble(txtT9.getText()),
                        Double.parseDouble(txtT10.getText()),
                        Double.parseDouble(txtT11.getText())
                ));

        System.out.println("Zaczynam generowanie");
        List<Summary> statements =
                Generator.generateStatements(
                        records,
                        zerQualifier,
                        oneQualifier,
                        fuzzySets,
                        weights);

        System.out.println("Skonczylem generowanie");
        for (var s : statements) {
            System.out.println(s.getTextualSummary());
        }
    }

    private void checkSelectedItems() {
        System.out.println("Zaznaczone elementy:");
        selectedNames.clear();
        checkSelectedItemsRecursive(listSummarizers.getRoot(), selectedNames);

        Set<String> kk= selectedNames.keySet();
        List<String> keyNames = kk.stream().toList();

        for (int i=0; i<selectedNames.size(); i++) {
            fuzzySets.addAll(Util.getLabeledFuzzySets(
                    linguisticVariables.get(
                            checkLinguisticVariableId(keyNames.get(i))), selectedNames.get(keyNames.get(i))));
        }
    }

    private void checkSelectedItemsRecursive(TreeItem<String> parent, Map<String, List<String>> map) {
        for (TreeItem<String> child : parent.getChildren()) {
            if (child instanceof CheckBoxTreeItem) {
                CheckBoxTreeItem<String> checkBoxItem = (CheckBoxTreeItem<String>) child;
                if (checkBoxItem.isSelected()) {
                    String key = checkBoxItem.getParent().getValue();
                    String value = checkBoxItem.getValue();

                    if (!key.equals("All qualifiers")) {
                        List<String> values = map.getOrDefault(key, new ArrayList<>());
                        values.add(value);
                        map.put(key, values);
                    }
                }
            }
            checkSelectedItemsRecursive(child, map);
        }
    }

    private int checkLinguisticVariableId(String name) {
        return switch (name) {
            case "minimum temperature" -> 0;
            case "maximum temperature" -> 1;
            case "morning humidity" -> 2;
            case "afternoon humidity" -> 3;
            case "wind speed" -> 4;
            case "rainfall" -> 5;
            case "insolation" -> 6;
            case "evaporation" -> 7;
            case "radiation" -> 8;
            case "evapotranspiration" -> 9;
            default -> 10;
        };
    }

    private int checkAbsoluteVariableId(String name) {
        return switch (name) {
            case "About 280.0" -> 0;
            case "About 1200.0" -> 1;
            case "About 3600.0" -> 2;
            case "About 7200.0" -> 3;
            case "About 10800.0" -> 4;
            case "About 14600.0" -> 5;
            default -> 6;
        };
    }

    private int checkRelativeVariableId(String name) {
        return switch (name) {
            case "Almost none" -> 0;
            case "Few" -> 1;
            case "About quarter" -> 2;
            case "afternoon humidity" -> 3;
            case "Some" -> 4;
            case "About half" -> 5;
            case "About three quarters" -> 6;
            case "Many" -> 7;
            case "Almost all" -> 8;
            default -> 10;
        };
    }
}