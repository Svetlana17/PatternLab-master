package strategy;

import Classes.Pupil;
import Classes.Pupils;

import java.util.List;

// сортировка простым обменом
public class SorterB implements Sorter {

    @Override
    public void sort(List<Pupil> pupils) {

        for(int i = 0; i < pupils.size(); i++){
            int f = 0;
            for(int y = 0; y < pupils.size() - i; y++){
                Pupil p1 = pupils.get(y);
                Pupil p2 = pupils.get(y + 1);
                if( avg(p1) > avg(p2) ){
                    pupils.set(y, p2);
                    pupils.set(y + 1, p1);
                    f++;
                }
            }
            if(f == 0) break;
        }
    }

    private double avg(Pupil pupil){
        return Pupils.calculateAverageMark(pupil);
    }
}
