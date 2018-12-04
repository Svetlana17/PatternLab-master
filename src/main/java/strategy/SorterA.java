package strategy;

import Classes.Pupil;
import Classes.Pupils;

import java.util.List;

public class SorterA implements Sorter {

    @Override
    public void sort(List<Pupil> pupils) {
       // pupils.sort((o1, o2) -> Pupils.calculateAverageMark(o1) > Pupils.calculateAverageMark(o2) ? -1: 1);
        pupils.sort((o1, o2) -> Pupils.calculateAverageMark(o1) < Pupils.calculateAverageMark(o2)? -1:1);

    }
}
