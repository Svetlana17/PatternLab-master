package decorator;

import Classes.Pupil;
import Classes.Pupils;

import java.io.IOException;

public class Runner {

    public static void main(String[] args) throws IOException {
        Pupil pupil = Pupils.createSynchronizedInstance(Pupils.createInstance(Pupils.randomStudentName(), 10));
        Pupils.fillPupilMarks(pupil);
        Pupils.log(pupil);
    }
}
