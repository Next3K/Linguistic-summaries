package project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
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

import java.util.*;

public class PanelController {

    @FXML
    private TextField txtT1, txtT2, txtT3, txtT4, txtT5, txtT6, txtT7, txtT8, txtT9, txtT10, txtT11, linguisticName;

    @FXML
    private TextField q1Txt, q2Txt, q3Txt, q4Txt;

    @FXML
    private Label q1Lbl, q2Lbl, q3Lbl, q4Lbl, minLbl, maxLbl;

    @FXML
    private ComboBox comboQuantifier, comboQuantifier_second, labelType, shapeType, linguisticType;

    @FXML
    private TreeView listSummarizers, listSummarizers_second;

    @FXML
    private Button btnGenerate, btnSave, btnClean, btnGenerate_second, btnLabelGenerate;

    @FXML
    private RadioButton radioAbsolute, radioRelative;

    @FXML
    private TableView tableGenerated, tableGenerated_second;

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

    //linguisticVariables.get(0).getLinguisticValues().put()

    @FXML
    private void initialize() {
        func.loadDataFromUtil(records, relativeQuantifiers, absoluteQuantifiers, linguisticVariables);

        func.setFirstTableSettings(tableGenerated);
        func.setSecondTableSettings(tableGenerated_second);

        setOtherSetting();
        runOnActionEvents();

        func.loadTreeViews(languages, multiLanguages, linguisticVariables);

        listSummarizers.setRoot(languages);
        listSummarizers.setCellFactory(CheckBoxTreeCell.<String>forTreeView());

        listSummarizers_second.setRoot(multiLanguages);
        listSummarizers_second.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    }

    @FXML
    public void onBtnGenerate(ActionEvent actionEvent) {
        fuzzySets.clear();
        ArrayList<Quantifier> oneQualifier;
        ArrayList<Quantifier> zerQualifier;
        checkSelectedItems(listSummarizers);

        if (radioRelative.isSelected()) {
            int index = checkRelativeVariableId(comboQuantifier.getValue().toString());
            oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(index)));
            zerQualifier = new ArrayList<>();
        } else {
            int index = checkAbsoluteVariableId(comboQuantifier.getValue().toString());
            oneQualifier = new ArrayList<>();
            zerQualifier = new ArrayList<>(List.of(absoluteQuantifiers.get(index)));
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
                Generator.generateSummaries(
                        records,
                        zerQualifier,
                        oneQualifier,
                        fuzzySets,
                        weights);


        System.out.println("Skonczylem generowanie");

        tableGenerated.getItems().clear();

        for (var s : statements) {
            tableGenerated.getItems().add(new Results(s));
        }

    }

    @FXML
    public void onBtnGenerateSecond(ActionEvent actionEvent) {
        fuzzySets.clear();

        int index = checkRelativeVariableId(comboQuantifier_second.getValue().toString());

        ArrayList<Quantifier> oneQualifier = new ArrayList<>(List.of(relativeQuantifiers.get(index)));
        checkSelectedItems(listSummarizers_second);


        System.out.println("Zaczynam generowanie");
        List<MultiSubjectSummary> summariesTwo =
                Generator.generateTwoSubjectSummaries(
                        records,
                        oneQualifier,
                        fuzzySets);


        System.out.println("Skonczylem generowanie");

        tableGenerated_second.getItems().clear();

        for (var s : summariesTwo) {
            tableGenerated_second.getItems().add(new Results(s));
            System.out.println(
                    s.getTwoSubjectSummaryForTable()
                            +";"+ String.format("%.2f", s.getQualityMeasure())
            );
        }

        System.out.println("\n\n\n");
    }


    @FXML
    public void onBtnLabelGenerate(ActionEvent actionEvent) {
        if (q1Txt != null && q2Txt != null && q3Txt != null && q4Txt != null ) {
             if (labelType.getValue() == "zmienna lingwistyczna") {
                 System.out.println("jestem");
                 if(shapeType.getValue() == "trapez") {
                     linguisticVariables.get(checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new TrapezoidShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText()), Double.parseDouble(q4Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 } else if(shapeType.getValue() == "trójkąt") {
                     System.out.println("tu tez");
                     linguisticVariables.get(checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new TriangularShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText()), Double.parseDouble(q3Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 }
                 else if(shapeType.getValue() == "gauss") {
                     linguisticVariables.get(checkLinguisticVariableId(linguisticType.getValue().toString())).getLinguisticValues().put(linguisticName.getText(), new FuzzySet(
                             linguisticName.getText(),
                             switchVariableNameToDatabaseName(linguisticType.getValue().toString()),
                             new GaussianShape(Double.parseDouble(q1Txt.getText()), Double.parseDouble(q2Txt.getText())),
                             new UniverseOfDiscourse(Double.parseDouble(minLbl.getText()), Double.parseDouble(maxLbl.getText())))
                     );
                 }
                 listSummarizers.getRoot().getChildren().clear();
                 listSummarizers_second.getRoot().getChildren().clear();
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
                 comboQuantifier_second.getItems().clear();
                 for (int i = 0; i < relativeQuantifiers.size(); i++) {
                     comboQuantifier_second.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
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
            }


        }
    }


    private void checkSelectedItems(TreeView lSummarizers) {
        System.out.println("Zaznaczone elementy:");
        selectedNames.clear();
        checkSelectedItemsRecursive(lSummarizers.getRoot(), selectedNames);

        //System.out.println(selectedNames.toString());

        for (Map.Entry<String, List<String>> entry : selectedNames.entrySet()) {
            String key = entry.getKey();
            List<String> values = entry.getValue();

            System.out.println("Klucz: " + key);
            System.out.println("Lista stringów: " + values);
            for (int i=0; i < values.size(); i++) {
                fuzzySets.add(linguisticVariables.get(checkLinguisticVariableId(key)).getSet(values.get(i)));
            }
        }
        System.out.println(fuzzySets.toString());
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

    private Entry.DatabaseColumn switchVariableNameToDatabaseName(String name) {
        return switch (name) {
            case "minimum temperature" -> Entry.DatabaseColumn.MIN_TEMPERATURE;
            case "maximum temperature" -> Entry.DatabaseColumn.MAX_TEMPERATURE;
            case "morning humidity" -> Entry.DatabaseColumn.MORNING_HUMIDITY;
            case "afternoon humidity" -> Entry.DatabaseColumn.AFTERNOON_HUMIDITY;
            case "wind" -> Entry.DatabaseColumn.WIND;
            case "rainfall" -> Entry.DatabaseColumn.RAINFALL;
            case "insolation" -> Entry.DatabaseColumn.INSOLATION;
            case "evaporation" -> Entry.DatabaseColumn.EVAPORATION;
            case "radiation" -> Entry.DatabaseColumn.RADIATION;
            case "evapotranspiration" -> Entry.DatabaseColumn.EVAPOTRANSPIRATION;
            default -> Entry.DatabaseColumn.EVAPOTRANSPIRATION;
        };
    }

    private int checkLinguisticVariableId(String name) {
        return switch (name) {
            case "minimum temperature" -> 0;
            case "maximum temperature" -> 1;
            case "morning humidity" -> 2;
            case "afternoon humidity" -> 3;
            case "wind" -> 4;
            case "rainfall" -> 5;
            case "insolation" -> 6;
            case "evaporation" -> 7;
            case "radiation" -> 8;
            case "evapotranspiration" -> 9;
            default -> 10;
        };
    }

    private int checkAbsoluteVariableId(String name) {
        int idx = 0;
        for (Quantifier q : absoluteQuantifiers) {
            if (q.getLabel() == name) {
                return idx;
            }
            idx++;
        }
        return idx;
    }

    private int checkRelativeVariableId(String name) {
        int idx = 0;
        for (Quantifier q : relativeQuantifiers) {
            if (q.getLabel() == name) {
                return idx;
            }
            idx++;
        }
        return idx;
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

        comboQuantifier_second.setOnAction(event -> {
            if (comboQuantifier_second.getValue() != null) {
                btnGenerate_second.setDisable(false);
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
        listSummarizers_second.setStyle("-fx-font-size: 10px;");

        labelType.getItems().add("kwantyfikator absolutny");
        labelType.getItems().add("kwantyfiaktor relatywny");
        labelType.getItems().add("zmienna lingwistyczna");

        shapeType.getItems().add("trójkąt");
        shapeType.getItems().add("trapez");
        shapeType.getItems().add("gauss");


        comboQuantifier.setDisable(true);
        btnGenerate.setDisable(true);

        for (int i = 0; i < relativeQuantifiers.size(); i++) {
            comboQuantifier_second.getItems().add(relativeQuantifiers.get(i).getTextualRepresentation());
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
}
