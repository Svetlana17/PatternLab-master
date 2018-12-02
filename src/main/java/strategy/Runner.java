package strategy;

import Classes.Pupil;
import Classes.Pupils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Runner {

    private static Worker worker;

    public static void main(String[] args) {

        System.out.println("SorterA ******************************************");
        List<Pupil> pupils1 = makePupils(10);
        worker = new Worker(new SorterA());
        worker.work(pupils1);

        System.out.println();

        System.out.println("SorterB ******************************************");
        List<Pupil> pupils2 = makePupils(10);
        worker = new Worker(new SorterA());
        worker.work(pupils2);
    }

    private static List<Pupil> makePupils(int count) {
        List<Pupil> pupils = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Pupil pupil = Pupils.createInstance(Pupils.randomStudentName(), 10);
            Pupils.fillPupilMarks(pupil);
            pupils.add(pupil);
        }
        return pupils;
    }

}

class Worker {

    private Sorter sorter;

    public Worker(Sorter sorter) {
        this.sorter = sorter;
    }

    public Sorter getSorter() {
        return sorter;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public void work(List<Pupil> pupils){
        System.out.println("Original order:");
        printPupilsAvgMarks(pupils);
        sorter.sort(pupils);
        System.out.println("Sorted order:");
        printPupilsAvgMarks(pupils);
    }

    private void printPupilsAvgMarks(List<Pupil> pupils) {
        double[] avg = new double[pupils.size()];
        for (int i = 0; i < pupils.size(); i++) {
            avg[i] = Pupils.calculateAverageMark(pupils.get(i));
        }
        System.out.println(Arrays.toString(avg));
    }
}


