package dao;

import Classes.Pupil;
import Classes.Pupils;
import Classes.Student;

import java.util.List;

public class Runner {

    public static void main(String[] args) {
//        StudentsDao dao = new SerializableDao("D:\\desktop\\serializable.txt"); // используй это,
// если нужно записывать сериализовынные обьекты
        StudentsDao dao = new TextFileDao("C:\\Users\\User\\Desktop\\text.txt");
        printPupils(dao.getAllStudents());

        Student s1 = new Student("Lapchenko", 2);
        s1.setSubjectAt(0, "Mathematics");
        s1.setSubjectAt(1, "Fisic");
       // Student s2 = new Student("Kukushkin", 3);

        dao.addStudent(s1);
        printPupils(dao.getAllStudents());

      //  dao.addStudent(s2);
      //  printPupils(dao.getAllStudents());

        dao.removeStudentAt(0);
//        dao.removeStudentAt(1);

        printPupils(dao.getAllStudents());
    }

    private static void printPupils(List<Student> students){
        System.out.println("___PRINTING ENTITIES___");
        students.forEach(student -> System.out.println(Pupils.pupilToString(student)));
        System.out.println("______");
    }
}
