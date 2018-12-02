package strategy;

import java.util.List;

public class SortUtils {
    private SortUtils() {
    }

    public static <T> void sort(List<T> list, SortStrategy<T> sortStrategy) {
        sortStrategy.sort(list);//происходит сортировка
    }

    public interface SortStrategy<T> {
        void sort(List<T> list);
    }
}
