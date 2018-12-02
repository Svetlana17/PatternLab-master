package Classes;

import decorator.PupilSynchronized;
import factory_method.PupilFactory;
import factory_method.StudentFactory;

import java.util.Random;

public class Pupils {

    private Pupils() {
    }

    private static PupilFactory pupilFactory = new StudentFactory();
    private static String[] studentNames = {"Ivanov", "Petrov", "Sidorov", "Vasechkin", "Sokolova", "Savelyeva", "Kraynova", "Fedorova"};
    private static String[] subjectNames = {"Physics", "Algebra", "Geometry", "Philosophy", "Chemistry", "Biology", "Literature"};
    private static Random random = new Random();

    public static void log(Pupil pupil) {
        System.out.println(pupilToString(pupil));
    }
//
    public static String pupilToString(Pupil pupil) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(pupil.getSecondName()).append('\n');
        stringBuilder.append(pupil.getSubjectsCount()).append('\n');
        for (int i = 0; i < pupil.getSubjectsCount(); i++) {
            stringBuilder.append(String.format("%s %s\n", pupil.getSubjectAt(i), pupil.getMarkAt(i)));
        }
        return stringBuilder.toString();
    }

    public static double calculateAverageMark(Pupil pupil) {
        int sum = 0;
        for (int i = 0; i < pupil.getSubjectsCount(); i++) {
            sum += pupil.getMarkAt(i);
        }
        return 1.0 * sum / pupil.getSubjectsCount();
    }
///Ссылка на текущую фабрику
    public static void setPupilFactory(PupilFactory pupilFactory) {
        Pupils.pupilFactory = pupilFactory;
    }
/// Описать метод createInstance  создает экзмепляр ученика
    public static Pupil createInstance(String secondName, int subjectsCount) {
        return pupilFactory.createInstance(secondName, subjectsCount);
    }

    public static Pupil createSynchronizedInstance(Pupil pupil) {
        return new PupilSynchronized(pupil);
    }

    public static String randomStudentName() {
        return randomName(studentNames);
    }

    public static String randomSubjectName() {
        return randomName(subjectNames);
    }

    private static String randomName(String[] names) {
        return names[random.nextInt(names.length)];
    }

    public static int randomMark() {
        return 2 + random.nextInt(4);
    }

    public static void fillPupilMarks(Pupil pupil) {
        for (int i = 0; i < pupil.getSubjectsCount(); i++) {
            pupil.setSubjectAt(i, Pupils.randomSubjectName());
            pupil.setMarkAt(i, Pupils.randomMark());
        }
    }
}
