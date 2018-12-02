package mvc.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import mvc.model.Data;
import mvc.model.DataHolder;

public class Controller {
    private static Controller instance = new Controller();

    private Controller() {
    }

    public static Controller getInstance() {
        return instance;
    }

    public ObservableList<Data> getObservableData() {
        return FXCollections.observableList(DataHolder.getInstance().getData());
    }
}
