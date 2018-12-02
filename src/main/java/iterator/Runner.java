package iterator;

import Classes.Pupils;
import Classes.SchoolGirl;

import java.io.IOException;
import java.util.Iterator;

public class Runner {

    public static void main(String[] args) throws IOException {
        SchoolGirl schoolGirl = new SchoolGirl(Pupils.randomStudentName(), 10);
        Pupils.fillPupilMarks(schoolGirl);

        Iterator<?> iterator = schoolGirl.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
