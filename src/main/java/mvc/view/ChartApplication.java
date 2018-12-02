package mvc.view;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import mvc.controller.Controller;
import mvc.model.Data;

import java.util.function.Function;

public class ChartApplication extends Application {

    private TableView<Data> table;
    private LineChart<Number, Number> lineChart;

  private static final Function<Double, Double> FUNCTION = x -> x * x * Math.sin(x) + 2 * x;


  //настройки окна
    @Override
    public void start(Stage stage) {


        table = createTableView();
        table.setItems(Controller.getInstance().getObservableData());

        lineChart = createLineChart();//график

        updateSeries();

        initStage(stage);
        stage.show();
    }

    private void initStage(Stage stage) {
        HBox hb = new HBox();
        TextField addXName = new TextField(); //текстовое поле
        addXName.setPromptText("Сoefficient");


        Button addButton = new Button("Добавить");
        addButton.setOnAction(e -> {
            table.getItems().add(new Data(Double.parseDouble(addXName.getText())));
            addXName.clear();
            updateSeries();
        });

        addButton.prefWidthProperty().bind(table.widthProperty().divide(2));
        hb.getChildren().addAll(addXName, addButton);
        hb.setSpacing(5);

        VBox vbox = new VBox(5);//вертикальный список
        vbox.getChildren().addAll(lineChart, table, hb);
        vbox.setAlignment(Pos.TOP_LEFT);
        vbox.setMinWidth(500);

        HBox layout = new HBox();
        layout.getChildren().add(vbox);
        HBox.setHgrow(vbox, Priority.ALWAYS);//увеличивает таблицу по горизонтали

        Scene scene = new Scene(layout, 500, 400);
        stage.setScene(scene);
    }
//отрисовка графика
    private LineChart<Number, Number> createLineChart() {
        NumberAxis xAxis = new NumberAxis();
        xAxis.setTickLength(0);
        NumberAxis yAxis = new NumberAxis();

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setStyle("-fx-padding: 0;");
        lineChart.setLegendVisible(false);

        return lineChart;
    }
//добавление серии точек
    private Series<Number, Number> createSeries(ObservableList<Data> data) {
        Series<Number, Number> series = new Series<>();
        for (Data point : data) {
            series.getData().add(new XYChart.Data<>(point.getX(), FUNCTION.apply(point.getX())));
        }
        return series;
    }

    private TableView<Data> createTableView() {
        TableView<Data> table = new TableView<>();
        table.setEditable(true);

        TableColumn<Data, Number> parametersColumn = new TableColumn<>("Shag");
        parametersColumn.prefWidthProperty().bind(table.widthProperty().divide(2));
        parametersColumn.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
        parametersColumn.setCellValueFactory(new PropertyValueFactory<>("x"));
        parametersColumn.setOnEditCancel(event -> System.err.println("edit canceled"));
        parametersColumn.setOnEditCommit(event -> {
            int rowId = event.getTablePosition().getRow();
            table.getItems().get(rowId).setX(event.getNewValue().doubleValue());
            updateSeries();
        });

        TableColumn<Data, Double> valuesColumn = new TableColumn<>("Value");


        valuesColumn.prefWidthProperty().bind(table.widthProperty().divide(2));//разделена на 2 колоки
        valuesColumn.setCellValueFactory(new PropertyValueFactory<Data, Double>("x") {
            @Override
            public ObservableValue<Double> call(TableColumn.CellDataFeatures<Data, Double> param) {
                return new ObservableValueBase<Double>() {
                    @Override
                    public Double getValue() {
                        return FUNCTION.apply(param.getValue().getX());
                    }
                };
            }
        });
//обработчик событий клавиатуры удаление из таблицы
        table.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.DELETE) {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    table.getItems().remove(table.getSelectionModel().getSelectedItem());
                    updateSeries();
                }
            }
        });

        table.getColumns().addAll(parametersColumn, valuesColumn);

        return table;
    }

    private void updateSeries() {
        lineChart.getData().clear();
        lineChart.getData().add(createSeries(table.getItems()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}