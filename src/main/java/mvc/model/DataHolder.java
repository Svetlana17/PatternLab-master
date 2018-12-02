package mvc.model;

import java.util.ArrayList;
import java.util.List;

public class DataHolder {

    private static DataHolder instance = new DataHolder();

    public static DataHolder getInstance() {
        return instance;
    }

    private final List<Data> data;

    private DataHolder() {
        data = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            data.add(new Data(i));
        }
    }

    public List<Data> getData() {
        return data;
    }
}
