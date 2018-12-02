package factory_method;

import Classes.Pupil;
import Classes.SchoolGirl;

public class SchoolGirlFactory implements PupilFactory {
    @Override
    public Pupil createInstance(String secondName, int subjectsCount) {
        return new SchoolGirl(secondName, subjectsCount);
    }
}
