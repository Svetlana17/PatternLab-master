package decorator;

import Classes.Pupil;

public class PupilSynchronized implements Pupil {


      private final Pupil pupil;
//
    public PupilSynchronized(Pupil pupil) {
        this.pupil = pupil;
    }

    @Override
    public synchronized String getSecondName() {
        return "Synchronized " + pupil.getSecondName();
    }

    @Override
    public synchronized String getSubjectAt(int index) {
        return pupil.getSubjectAt(index);
    }

    @Override
    public synchronized void setSubjectAt(int index, String subject) {
        pupil.setSubjectAt(index, subject);
    }

    @Override
    public synchronized int getMarkAt(int index) {
        return pupil.getMarkAt(index);
    }

    @Override
    public synchronized void setMarkAt(int index, int mark) {
        pupil.setMarkAt(index, mark);
    }

    @Override
    public synchronized void addSubjectAndMark(String subjectName, int subjectMark) {
        pupil.addSubjectAndMark(subjectName, subjectMark);
    }

    @Override
    public synchronized int getSubjectsCount() {
        return pupil.getSubjectsCount();
    }

    @Override
    public synchronized Object clone() throws CloneNotSupportedException {
        return pupil.clone();
    }
}
