package factory_method;

import Classes.Pupil;
import Classes.Pupils;

import java.util.Random;

public class Runner {

    private static Random random = new Random();

    public static void main(String[] args) {
//

        System.out.println("Create SchoolGirl");
        Pupils.setPupilFactory(new SchoolGirlFactory());

        ArithmeticMean();
        System.out.println("Create Student");
        Pupils.setPupilFactory(new StudentFactory());

        ArithmeticMean();
    }

    private static void ArithmeticMean()

    {
        Pupil pupil = Pupils.createInstance(Pupils.randomStudentName(), 4);
        Pupils.fillPupilMarks(pupil);
        System.out.println("Pupil: " + pupil);
        Pupils.log(pupil);
        System.out.printf("Average mark = %.2f\n\n", Pupils.calculateAverageMark(pupil));
    }
}
