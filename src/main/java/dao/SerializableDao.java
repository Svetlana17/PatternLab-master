package dao;

import Classes.Pupil;
import Classes.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializableDao implements StudentsDao {

    private File file;

    public SerializableDao(String filePath){
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
        ObjectOutputStream oos = null;
        FileOutputStream fout = null;
        try{
            fout = new FileOutputStream(file, false);
            oos = new ObjectOutputStream(fout);
            oos.writeObject(pupils);
        } catch (Exception ex) {}
        finally {
            try {
                if(oos != null) oos.close();
            } catch (Exception e) {}
        }
    }
//считывание данных
    @Override
    public List<Student> getAllStudents() {
        FileInputStream fout = null;
        ObjectInputStream oos = null;
        try {
            fout = new FileInputStream(file);
            oos = new ObjectInputStream(fout);
            return (List<Student>) oos.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }finally {
            try {
                if(fout != null) fout.close();
            } catch (Exception e) {}
            try {
                if(oos != null) oos.close();
            } catch (Exception e) {}
        }
    }
}
