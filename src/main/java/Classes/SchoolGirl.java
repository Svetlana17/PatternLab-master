package Classes;

import java.util.Arrays;
import java.util.Iterator;

public class SchoolGirl implements Pupil, Iterable<SchoolGirl.Register> {

    private final String secondName;
    private Register[] registers;

    public SchoolGirl(String secondName, int initialCountOfSubjects) {
        this.secondName = secondName;
        this.registers = new Register[initialCountOfSubjects];
        for (int i = 0; i < registers.length; i++) {
            registers[i] = new Register();
        }
    }

    @Override
    public String getSecondName() {
        return secondName;
    }

    @Override
    public int getMarkAt(int index) {
        return registers[index].mark;
    }

    @Override
    public void setMarkAt(int index, int mark) {
        registers[index].mark = mark;
    }

    @Override
    public String getSubjectAt(int index) {
        return registers[index].subjectName;
    }

    @Override
    public void setSubjectAt(int index, String subject) {
        registers[index].subjectName = subject;
    }

    @Override
    public void addSubjectAndMark(String subjectName, int subjectMark) {
        registers = Arrays.copyOf(registers, registers.length + 1);
        registers[registers.length - 1] = new Register(subjectName, subjectMark);
    }

    @Override
    public int getSubjectsCount() {
        return registers.length;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        SchoolGirl clone = (SchoolGirl) super.clone();
        clone.registers = Arrays.copyOf(registers, registers.length);
        for (int i = 0; i < registers.length; i++) {
            clone.registers[i] = new Register(registers[i].subjectName, registers[i].mark);
        }
        return clone;
    }
///ITERATOR
    @Override
    public Iterator<Register> iterator() {
        return new SchoolGirlIterator();
    }

    private class SchoolGirlIterator implements
           Iterator<Register> {
        private int cursor;

        @Override
        public boolean hasNext() {
            return cursor < getSubjectsCount();
        }

        @Override
        public Register next() {
            return registers[cursor++];
        }
    }
/////////////
    static class Register {
        private String subjectName;
        private int mark;


















        ////////////////////

        Register() {
        }

        Register(String subjectName, int mark) {
            this.subjectName = subjectName;
            this.mark = mark;
        }

        @Override
        public String toString() {
            return subjectName + ": " + mark;
        }
    }
}
