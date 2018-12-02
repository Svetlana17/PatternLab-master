package dao;

import Classes.Pupil;
import Classes.Student;
import factory_method.StudentFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TextFileDao implements StudentsDao {

    private File file;

    public TextFileDao(String filePath){
        file = new File(filePath);
    }

    @Override
    public void addStudent(Student pupil) {
        List<Student> pupils = getAllStudents();
        pupils.add(pupil);
        updateAllStudents(pupils);
    }

    @Override
    public Pupil getStudentAt(int index) {
        return getAllStudents().get(index);
    }

    @Override
    public void removeStudentAt(int index) {
        List<Student> pupils = getAllStudents();
        pupils.remove(index);
        updateAllStudents(pupils);
    }

    @Override
    public void updateAllStudents(List<Student> pupils){
        FileWriter fileWriter = null;
        BufferedWriter writer = null;
        try {
            fileWriter = new FileWriter(file, true);
            writer = new BufferedWriter(fileWriter);

            for(Student student : pupils){
                writer.write(studentToLine(student) + "\n");
            }

        }catch (Exception e){}
        finally {

            if(writer != null) try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();

        FileReader fileReader = null;
        BufferedReader reader = null;
        try {
            fileReader = new FileReader(file);
            reader = new BufferedReader(fileReader);

            String s;
            while ((s = reader.readLine()) != null ){
                students.add(getStudentFromLine(s));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {

            if(reader != null) try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return students;
    }

    private String studentToLine(Student student){
        StringBuilder line = new StringBuilder();

        line.append(student.getSecondName());
        line.append(" ").append(student.getSubjectsCount()).append(" ");

        for(int i = 0; i < student.getSubjectsCount(); i++){
            line.append(student.getSubjectAt(i)).append("|").append(student.getMarkAt(i)).append(" ");
        }

        return line.toString();
    }

    private Student getStudentFromLine(String line){
        String internalLine = line;
        String secondName = internalLine.substring(0, internalLine.indexOf(" "));
        internalLine = internalLine.substring(internalLine.indexOf(" ") + 1, internalLine.length());

        String countStr = internalLine.substring(0, internalLine.indexOf(" "));
        int countInt = Integer.parseInt(countStr);
        internalLine = internalLine.substring(internalLine.indexOf(" ") + 1, internalLine.length());

        Student student = new Student(secondName, countInt);

        for(int i = 0; i < countInt; i++){
            String sub = internalLine.substring(0, internalLine.indexOf("|"));
            int mark = Integer.parseInt(internalLine.substring(internalLine.indexOf("|") + 1, internalLine.indexOf(" ")));
            internalLine = internalLine.substring(internalLine.indexOf(" ") + 1, internalLine.length());

            student.setSubjectAt(i, sub);
            student.setMarkAt(i, mark);
        }

        return student;
    }
}
