package project;

import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.Util;
import org.example.model.LinguisticVariable;
import org.example.model.db.Entry;
import org.example.model.quantifiers.Quantifier;
import org.example.model.sets.FuzzySet;

import java.util.List;
import java.util.Map;

public class Functions {

    public Functions() {

    }

    public void setFirstTableSettings(TableView table) {
        TableColumn<Results, String> result = new TableColumn<>("Result");
        result.setCellValueFactory(new PropertyValueFactory<>("text"));
        result.setStyle("-fx-font-size: 10px;");
        result.setPrefWidth(900);

        TableColumn<Results, String> T = new TableColumn<>("T");
        T.setCellValueFactory(new PropertyValueFactory<>("T"));
        T.setStyle("-fx-font-size: 10px;");
        T.setPrefWidth(30);

        TableColumn<Results, String> T1 = new TableColumn<>("T1");
        T1.setCellValueFactory(new PropertyValueFactory<>("T1"));
        T1.setStyle("-fx-font-size: 10px;");
        T1.setPrefWidth(30);

        TableColumn<Results, String> T2 = new TableColumn<>("T2");
        T2.setCellValueFactory(new PropertyValueFactory<>("T2"));
        T2.setStyle("-fx-font-size: 10px;");
        T2.setPrefWidth(30);

        TableColumn<Results, String> T3 = new TableColumn<>("T3");
        T3.setCellValueFactory(new PropertyValueFactory<>("T3"));
        T3.setStyle("-fx-font-size: 10px;");
        T3.setPrefWidth(30);

        TableColumn<Results, String> T4 = new TableColumn<>("T4");
        T4.setCellValueFactory(new PropertyValueFactory<>("T4"));
        T4.setStyle("-fx-font-size: 10px;");
        T4.setPrefWidth(30);

        TableColumn<Results, String> T5 = new TableColumn<>("T5");
        T5.setCellValueFactory(new PropertyValueFactory<>("T5"));
        T5.setStyle("-fx-font-size: 10px;");
        T5.setPrefWidth(30);

        TableColumn<Results, String> T6 = new TableColumn<>("T6");
        T6.setCellValueFactory(new PropertyValueFactory<>("T6"));
        T6.setStyle("-fx-font-size: 10px;");
        T6.setPrefWidth(30);

        TableColumn<Results, String> T7 = new TableColumn<>("T7");
        T7.setCellValueFactory(new PropertyValueFactory<>("T7"));
        T7.setStyle("-fx-font-size: 10px;");
        T7.setPrefWidth(30);

        TableColumn<Results, String> T8 = new TableColumn<>("T8");
        T8.setCellValueFactory(new PropertyValueFactory<>("T8"));
        T8.setStyle("-fx-font-size: 10px;");
        T8.setPrefWidth(30);

        TableColumn<Results, String> T9 = new TableColumn<>("T9");
        T9.setCellValueFactory(new PropertyValueFactory<>("T9"));
        T9.setStyle("-fx-font-size: 10px;");
        T9.setPrefWidth(30);

        TableColumn<Results, String> T10 = new TableColumn<>("T10");
        T10.setCellValueFactory(new PropertyValueFactory<>("T10"));
        T10.setStyle("-fx-font-size: 10px;");
        T10.setPrefWidth(30);

        TableColumn<Results, String> T11 = new TableColumn<>("T11");
        T11.setCellValueFactory(new PropertyValueFactory<>("T11"));
        T11.setStyle("-fx-font-size: 10px;");
        T11.setPrefWidth(30);

        table.getColumns().addAll(result, T, T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11);
    }


    public void setSecondTableSettings(TableView table) {
        TableColumn<Results, String> result_second = new TableColumn<>("Result");
        result_second.setCellValueFactory(new PropertyValueFactory<>("text"));
        result_second.setStyle("-fx-font-size: 10px;");
        result_second.setPrefWidth(1200);

        TableColumn<Results, String> T_second = new TableColumn<>("T");
        T_second.setCellValueFactory(new PropertyValueFactory<>("T"));
        T_second.setStyle("-fx-font-size: 10px;");
        T_second.setPrefWidth(30);

        table.getColumns().addAll(result_second, T_second);
    }

    public void loadDataFromUtil(List<Entry> r, List<Quantifier> rq, List<Quantifier> aq, List<LinguisticVariable> lv) {
        r.addAll(Util.loadFromDatabase());
        rq.addAll(Util.loadDefaultRelativeQuantifiers());
        aq.addAll(Util.loadDefaultAbsoluteQuantifiers());
        lv.addAll(Util.getDefaultLinguisticVariables());
    }

    public void loadTreeViews(CheckBoxTreeItem<String> l, CheckBoxTreeItem<String> ml, List<LinguisticVariable> lv) {
        CheckBoxTreeItem<String> name = null;
        CheckBoxTreeItem<String> nameSecond = null;

        for (LinguisticVariable var : lv) {
            Map<String, FuzzySet> lVal = var.getLinguisticValues();
            int i = 0;

            for (Map.Entry<String, FuzzySet> entry : lVal.entrySet()) {
                String key = entry.getKey();
                FuzzySet fuzzySet = entry.getValue();

                if (i == 0) {
                    name = new CheckBoxTreeItem<>(fuzzySet.getDatabaseColumn().variableName());
                    nameSecond = new CheckBoxTreeItem<>(fuzzySet.getDatabaseColumn().variableName());
                    i++;
                }
                name.getChildren().add(new CheckBoxTreeItem<>(fuzzySet.getLabel()));
                nameSecond.getChildren().add(new CheckBoxTreeItem<>(fuzzySet.getLabel()));
            }
            l.getChildren().add(name);
            ml.getChildren().add(nameSecond);

            l.setExpanded(true);
            ml.setExpanded(true);
        }
    }

}