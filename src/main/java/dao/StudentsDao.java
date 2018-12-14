package dao;

import Classes.Pupil;
import Classes.Student;

import java.util.List;

public interface StudentsDao {

    void addStudent(Student pupil);
    Pupil getStudentAt(int index);
    void removeStudentAt(int index);
    void updateAllStudents(List<Student> pupils);
    List<Student> getAllStudents();
}
