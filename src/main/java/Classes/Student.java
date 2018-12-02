package Classes;

import java.util.Arrays;

public class Student implements Pupil {

    private final String secondName;
    private int[] marks;
    private String[] subjects;

    public Student(String secondName, int initialCountOfSubjects) {
        this.secondName = secondName;
        this.marks = new int[initialCountOfSubjects];
        this.subjects = new String[initialCountOfSubjects];
    }

    @Override
    public String getSecondName() {
        return secondName;
    }

    @Override
    public int getMarkAt(int index) {
        return marks[index];
    }

    @Override
    public void setMarkAt(int index, int mark) {
        marks[index] = mark;
    }

    @Override
    public String getSubjectAt(int index) {
        return subjects[index];
    }

    @Override
    public void setSubjectAt(int index, String subject) {
        subjects[index] = subject;
    }

    @Override
    public void addSubjectAndMark(String subjectName, int subjectMark) {
        subjects = Arrays.copyOf(subjects, subjects.length + 1);
        subjects[subjects.length - 1] = subjectName;
        marks = Arrays.copyOf(marks, marks.length + 1);
        marks[marks.length - 1] = subjectMark;
    }

    @Override
    public int getSubjectsCount() {
        return subjects.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Student clone = (Student) super.clone();
        clone.subjects = Arrays.copyOf(subjects, subjects.length);
        clone.marks = Arrays.copyOf(marks, marks.length);
        return clone;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Student student = (Student) o;
//
//        if (secondName != null ? !secondName.equals(student.secondName) : student.secondName != null) return false;
//        if (!Arrays.equals(marks, student.marks)) return false;
//         Probably incorrect - comparing Object[] arrays with Arrays.equals
//        return Arrays.equals(subjects, student.subjects);
//    }

//    @Override
//    public int hashCode() {
//        int result = secondName != null ? secondName.hashCode() : 0;
//        result = 31 * result + Arrays.hashCode(marks);
//        result = 31 * result + Arrays.hashCode(subjects);
//        return result;
//    }
}
