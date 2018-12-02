package factory_method;

import Classes.Pupil;
import Classes.Student;

public class StudentFactory implements PupilFactory {
    @Override
    public Pupil createInstance(String secondName, int subjectsCount) {
        return new Student(secondName, subjectsCount);
    }
}
