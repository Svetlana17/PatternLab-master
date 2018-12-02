package factory_method;

import Classes.Pupil;

public interface PupilFactory {
    Pupil createInstance(String secondName, int subjectsCount);
}
