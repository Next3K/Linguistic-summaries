package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import org.example.Generator;
import org.example.Util;
import org.example.model.LinguisticVariable;
import org.example.model.db.Entry;
import org.example.model.functions.GaussianShape;
import org.example.model.functions.TrapezoidShape;
import org.example.model.functions.TriangularShape;
import org.example.model.quantifiers.AbsoluteQuantifier;
import org.example.model.quantifiers.Quantifier;
import org.example.model.quantifiers.RelativeQuantifier;
import org.example.model.sets.FuzzySet;
import org.example.model.sets.UniverseOfDiscourse;
import org.example.model.summary.MultiSubjectSummary;
import org.example.model.summary.Summary;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class PanelController {

    @FXML
    private TextField txtT1, txtT2, txtT3, txtT4, txtT5, txtT6, txtT7, txtT8, txtT9, txtT10, txtT11, linguisticName;

    @FXML
    private TextField q1Txt, q2Txt, q3Txt, q4Txt;

    @FXML
    private Label q1Lbl, q2Lbl, q3Lbl, q4Lbl, minLbl, maxLbl;

    @FXML
    private ComboBox comboQuantifier, comboQuantifierSecond, labelType, shapeType, linguisticType;

    @FXML
    private TreeView listSummarizers, listSummarizersSecond;

    @FXML
    private Button btnGenerate, btnSave, btnSaveSecond;

    @FXML
    private RadioButton radioAbsolute, radioRelative;

    @FXML
    private TableView<Results> tableGenerated, tableGeneratedSecond;

    List<Entry> records = new ArrayList<>();
    List<Quantifier> relativeQuantifiers = new ArrayList<>();
    List<Quantifier> absoluteQuantifiers = new ArrayList<>();
    List<LinguisticVariable> linguisticVariables = new ArrayList<>();
    CheckBoxTreeItem<String> languages = new CheckBoxTreeItem<>("All qualifiers");
    CheckBoxTreeItem<String> multiLanguages = new CheckBoxTreeItem<>("All qualifiers");
    List<FuzzySet> fuzzySets = new ArrayList<>(10);

    List<FuzzySet> uniqueFuzzySets = new ArrayList<>();

    ToggleGroup toggleGroup = new ToggleGroup();

    Functions func = new Functions();

    Map<String, List<String>> selectedNames = new HashMap<>();

    @FXML
    private void initialize() {
        func.loadDataFromUtil(records, relativeQuantifiers, absoluteQuantifiers, linguisticVariables);
        func.setFirstTableSettings(tableGenerated);
        func.setSecondTableSettings(tableGeneratedSecond);
        func.addComboBoxItems(labelType, shapeType);
        setOtherSetting();
        runOnActionEvents();
        func.loadTreeViews(languages, multiLanguages, linguisticVariables);
        listSummarizers.setRoot(languages);
        listSummarizers.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        listSummarizersSecond.setRoot(multiLanguages);
        listSummarizersSecond.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
        btnSave.setOnAction(event -> saveSelectedRows());
        btnSaveSecond.setOnAction(event -> saveSelectedRowsSecond());

    }

    @FXML
    public void onBtnGenerate(ActionEvent actionEvent) {
        fuzzySets.clear();
        ArrayList<Quantifier> oneQualifier = new ArrayList<>();
        ArrayList<Quantifier> zerQualifier = new ArrayList<>();
        checkSelectedItems(listSummarizers);

        if (radioRelative.isSelected()) {
            int index = func.checkAbsOrRelId(comboQuantifier.getValue().toString(), relativeQuantifiers);
            oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(index)));
        } else {
            int index = func.checkAbsOrRelId(comboQuantifier.getValue().toString(), absoluteQuantifiers);
            zerQualifier = new ArrayList<>(List.of(absoluteQuantifiers.get(index)));
        }

        List<Double> weights = getAllWeights();
        List<Summary> sum = Generator.generateSummaries(records, zerQualifier, oneQualifier, fuzzySets, weights);

        tableGenerated.getItems().clear();
        tableGenerated.setEditable(true);

        for (var s : sum) {
            tableGenerated.getItems().add(new Results(s));
        }
    }

    @FXML
    public void onBtnGenerateSecond(ActionEvent actionEvent) {
        fuzzySets.clear();

        int index = func.checkAbsOrRelId(comboQuantifierSecond.getValue().toString(), relativeQuantifiers);
        ArrayList<Quantifier> oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(index)));
        checkSelectedItems(listSummarizersSecond);

        List<MultiSubjectSummary> sum = Generator.generateTwoSubjectSummaries(records, oneQualifier, fuzzySets);

        tableGeneratedSecond.getItems().clear();
        tableGeneratedSecond.setEditable(true);

        for (var s : sum) {
            tableGeneratedSecond.getItems().add(new Results(s));
        }
    }

    @FXML
    public void onBtnLabelGenerate(ActionEvent actionEvent) {
        if (q1Txt != null && q2Txt != null && q3Txt != null && q4Txt != null ) {
             if (labelType.getValue() == "zmienna lingwistyczna") {
                 if(shapeType.getValue() == "trapez") {
                     linguisticVariables.get(func.checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             func.switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new TrapezoidShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText()), Double.parseDouble(q4Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 } else if(shapeType.getValue() == "trójkąt") {
                     linguisticVariables.get(func.checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             func.switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new TriangularShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 }
                 else if(shapeType.getValue() == "gauss") {
                     linguisticVariables.get(func.checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             func.switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new GaussianShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 }
                 listSummarizers.getRoot().getChildren().clear();
                 listSummarizersSecond.getRoot().getChildren().clear();
                 func.loadTreeViews(languages, multiLanguages, linguisticVariables);
             } else if (labelType.getValue() == "kwantyfiaktor relatywny") {
                 if(shapeType.getValue() == "trapez") {
                     relativeQuantifiers.add(new RelativeQuantifier(
                             linguisticName.getText(),
                             new TrapezoidShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText()), Double.parseDouble(q4Txt.getText())),
                             new UniverseOfDiscourse(0d, 1d)));
                 }

                 else if(shapeType.getValue() == "trójkąt") {
                     relativeQuantifiers.add(new RelativeQuantifier(
                             linguisticName.getText(),
                             new TriangularShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText())),
                             new UniverseOfDiscourse(0d, 1d)));
                 }

                 else if(shapeType.getValue() == "gauss") {
                     relativeQuantifiers.add(new RelativeQuantifier(
                             linguisticName.getText(),
                             new GaussianShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText())),
                             new UniverseOfDiscourse(0d, 1d)));
                 }
                 comboQuantifierSecond.getItems().clear();
                 comboQuantifier.getItems().clear();
                 for (int i = 0; i < relativeQuantifiers.size(); i++) {
                     comboQuantifierSecond.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
                 }
                 for (int i = 0; i < relativeQuantifiers.size(); i++) {
                     comboQuantifier.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
                 }
             }
             else if (labelType.getValue() == "kwantyfikator absolutny") {
                if(shapeType.getValue() == "trapez") {
                    absoluteQuantifiers.add(new AbsoluteQuantifier(
                            linguisticName.getText(),
                            new TrapezoidShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText()), Double.parseDouble(q4Txt.getText())),
                            new UniverseOfDiscourse(0d, 14_854d)));
                }

                else if(shapeType.getValue() == "trójkąt") {
                    absoluteQuantifiers.add(new AbsoluteQuantifier(
                            linguisticName.getText(),
                            new TriangularShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText())),
                            new UniverseOfDiscourse(0d, 14_854d)));
                }

                else if(shapeType.getValue() == "gauss") {
                    absoluteQuantifiers.add(new AbsoluteQuantifier(
                            linguisticName.getText(),
                            new GaussianShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText())),
                            new UniverseOfDiscourse(0d, 14_854d)));
                }
                 comboQuantifier.getItems().clear();
                 for (int i = 0; i < absoluteQuantifiers.size(); i++) {
                     comboQuantifier.getItems().add(absoluteQuantifiers.get(i).getTextualRepresentation());
                 }
            }
        }
    }

    @FXML
    public void onBtnSettingClean(ActionEvent actionEvent) {
        labelType.getSelectionModel().clearSelection();
        shapeType.getSelectionModel().clearSelection();
        linguisticType.getSelectionModel().clearSelection();
        linguisticType.getItems().clear();
        linguisticName.clear();

        if (!linguisticType.isDisabled()) {
            linguisticType.setDisable(true);
        }
        minLbl.setText("0");
        maxLbl.setText("0");
        q1Txt.clear();
        q2Txt.clear();
        q3Txt.clear();
        q4Txt.clear();
    }

    private void checkSelectedItems(TreeView lSummarizers) {
        selectedNames.clear();
        checkSelectedItemsRecursive(lSummarizers.getRoot(), selectedNames);

        for (Map.Entry<String, List<String>> entry : selectedNames.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();
            for (int i=0; i < values.size(); i++) {
                fuzzySets.add(linguisticVariables.get(func.checkLinguisticVariableId(key)).getSet(values.get(i)));
            }
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

    private void runOnActionEvents() {
        radioAbsolute.setOnAction(event -> {
            if (radioAbsolute.isSelected()) {
                radioRelative.setSelected(false);
                btnGenerate.setDisable(true);
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
                btnGenerate.setDisable(true);
                comboQuantifier.setDisable(false);
                comboQuantifier.getItems().clear();
                for (int i = 0; i < relativeQuantifiers.size(); i++) {
                    comboQuantifier.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
                }
            }
        });

        comboQuantifier.setOnAction(event -> {
            if (comboQuantifier.getValue() != null) {
                btnGenerate.setDisable(false);
            }
        });

        comboQuantifierSecond.setOnAction(event -> {
            if (comboQuantifierSecond.getValue() != null) {
                btnSaveSecond.setDisable(false);
            }
        });


        labelType.setOnAction(event -> {
            if (labelType.getValue() == "kwantyfiaktor relatywny") {
                linguisticType.setDisable(true);
                linguisticType.getItems().clear();
                minLbl.setText(Util.getRelative().getNonFuzzySet().getMinimum().toString());
                maxLbl.setText(Util.getRelative().getNonFuzzySet().getMaximum().toString());

            } else if (labelType.getValue() == "kwantyfikator absolutny") {
                linguisticType.setDisable(true);
                linguisticType.getItems().clear();
                minLbl.setText(Util.getAbsolute().getNonFuzzySet().getMinimum().toString());
                maxLbl.setText(Util.getAbsolute().getNonFuzzySet().getMaximum().toString());
            }

            else if (labelType.getValue() == "zmienna lingwistyczna") {
                minLbl.setText("0");
                maxLbl.setText("0");
                linguisticType.setDisable(false);
                for (LinguisticVariable var : linguisticVariables) {
                    Map<String, FuzzySet> lVal = var.getLinguisticValues();
                    int i = 0;

                    for (Map.Entry<String, FuzzySet> entry : lVal.entrySet()) {
                        if (i==0) {
                            FuzzySet fuzzySet = entry.getValue();
                            linguisticType.getItems().add(fuzzySet.getDatabaseColumn().variableName());
                            uniqueFuzzySets.add(fuzzySet);
                            i++;
                        }
                    }
                }
            }
        });



        shapeType.setOnAction(event -> {
            if (shapeType.getValue() == "trójkąt") {
                q3Txt.setVisible(true);
                q3Lbl.setVisible(true);
                q4Txt.setVisible(false);
                q4Lbl.setVisible(false);

                q1Lbl.setText("Pierwsza wartość minimalna:");
                q2Lbl.setText("Wartość maksymalna:");
                q3Lbl.setText("Druga wartość minimalna:");

            }  else if (shapeType.getValue() == "trapez") {
                q3Txt.setVisible(true);
                q3Lbl.setVisible(true);
                q4Txt.setVisible(true);
                q4Lbl.setVisible(true);

                q1Lbl.setText("Pierwsza wartość minimalna:");
                q2Lbl.setText("Pierwsza wartość maksymalna:");
                q3Lbl.setText("Druga wartość maksymalna:");

            } else if (shapeType.getValue() == "gauss") {
                q3Txt.setVisible(false);
                q3Lbl.setVisible(false);
                q4Txt.setVisible(false);
                q4Lbl.setVisible(false);

                q1Lbl.setText("Średnia:");
                q2Lbl.setText("Odchylenie standardowe:");
            }
        });

        linguisticType.setOnAction(event -> {
            if (linguisticType.getValue() != null) {
                searchLinguisticUniverse(linguisticType.getValue().toString());
            }
        });
    }

    private void setOtherSetting() {
        listSummarizers.setStyle("-fx-font-size: 10px;");
        listSummarizersSecond.setStyle("-fx-font-size: 10px;");

        linguisticType.setDisable(true);
        comboQuantifier.setDisable(true);
        btnGenerate.setDisable(true);

        for (int i = 0; i < relativeQuantifiers.size(); i++) {
            comboQuantifierSecond.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
        }

        radioAbsolute.setToggleGroup(toggleGroup);
        radioRelative.setToggleGroup(toggleGroup);
    }

    private void searchLinguisticUniverse(String name) {
        for (int i=0; i < uniqueFuzzySets.size(); i++) {
            if (Objects.equals(uniqueFuzzySets.get(i).getDatabaseColumn().variableName(), name)) {
                minLbl.setText(uniqueFuzzySets.get(i).getUniverseOfDiscourse().getNonFuzzySet().getMinimum().toString());
                maxLbl.setText(uniqueFuzzySets.get(i).getUniverseOfDiscourse().getNonFuzzySet().getMaximum().toString());
            }
        }
    }

    private void saveSelectedRows() {
        List<Results> selectedItems = new ArrayList<>();
        for (Results item : tableGenerated.getItems()) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }

        if (!selectedItems.isEmpty()) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wyniki do pliku");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(tableGenerated.getScene().getWindow());


            if (file != null) {
                String fileName = file.getAbsolutePath();
                if (!fileName.endsWith(".txt")) {
                    fileName += ".txt";
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (Results item : selectedItems) {
                        writer.write(item.getValue() + "; " + item.getT() + "; " + item.getT1() + "; " + item.getT2() + "; " +
                                item.getT3() + "; " + item.getT4() + "; " + item.getT5() + "; " + item.getT6() + "; " +
                                item.getT7() + "; " + item.getT8() + "; " + item.getT9() + "; " + item.getT10() + "; " + item.getT11());

                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveSelectedRowsSecond() {
        List<Results> selectedItems = new ArrayList<>();
        for (Results item : tableGeneratedSecond.getItems()) {
            if (item.isSelected()) {
                selectedItems.add(item);
            }
        }

        if (!selectedItems.isEmpty()) {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Zapisz wyniki do pliku");
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(tableGeneratedSecond.getScene().getWindow());


            if (file != null) {
                String fileName = file.getAbsolutePath();
                if (!fileName.endsWith(".txt")) {
                    fileName += ".txt";
                }

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (Results item : selectedItems) {
                        writer.write(item.getValue() + "; " + item.getT());

                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private List<Double> getAllWeights() {
        return new ArrayList<>(List.of(Double.parseDouble(txtT1.getText()),
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
    }
}
