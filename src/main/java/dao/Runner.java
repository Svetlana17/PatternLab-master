package dao;

import Classes.Pupil;
import Classes.Pupils;
import Classes.Student;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
    StudentsDao dao = new SerializableDao("C:\\Users\\User\\Desktop\\text.txt"); // записать сериализовынные обьекты
      // StudentsDao dao = new TextFileDao("C:\\Users\\User\\Desktop\\text.txt");// записать в текстовом виде
        printPupils(dao.getAllStudents());

        Student s1 = new Student("Lapchenko", 2);
        s1.setSubjectAt(0, "Mathematics");
        s1.setSubjectAt(1, "Fisic");
        s1.setMarkAt(0, 5);
        s1.setMarkAt(1, 4);
       Student s2 = new Student("Kukushkin", 3);
        s2.setSubjectAt(0, "English");
        s2.setSubjectAt(1, "Math");
        s2.setSubjectAt(2, "OOP");
        s2.setMarkAt(0,3);
        s2.setMarkAt(1,3);
        s2.setMarkAt(2,3);


        dao.addStudent(s1);
        dao.addStudent(s2);
        printPupils(dao.getAllStudents());
        dao.removeStudentAt(0);
        printPupils(dao.getAllStudents());
    }

    private static void printPupils(List<Student> students){
        System.out.println("___PRINTING ENTITIES___");
        students.forEach(student -> System.out.println(Pupils.pupilToString(student)));
        System.out.println("______");
    }
}
