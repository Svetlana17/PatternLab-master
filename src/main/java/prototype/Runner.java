package prototype;

import Classes.Pupil;
import Classes.Pupils;
import factory_method.SchoolGirlFactory;

public class Runner {

    public static void main(String[] args) throws CloneNotSupportedException {
        Prototype prototype = new Prototype();

        Pupil student = Pupils.createInstance(Pupils.randomStudentName(), 5);
        Pupils.fillPupilMarks(student);
        test(student, prototype);

        Pupils.setPupilFactory(new SchoolGirlFactory());
        Pupil studentGirl = Pupils.createInstance(Pupils.randomStudentName(), 5);
        System.out.println("Клонирование школьника");
        Pupils.fillPupilMarks(studentGirl);
        test(studentGirl, prototype);
    }

    private static void test(Pupil instance, Prototype prototype) throws CloneNotSupportedException {
        System.out.println("Оригинал студента:"); // клонированный ориганал без изменения
        System.out.println(instance);
        Pupils.log(instance);

        prototype.setPrototype(instance);
        Pupil clonedInstance = prototype.createInstance();
        System.out.println("Клонирование студента:");
        System.out.println(instance);
        Pupils.log(clonedInstance); /// подтвержаем что копия сделана верно
        System.out.println("----------------------");
        instance.setSubjectAt(0, "Удалить прдемет");
        instance.setMarkAt(0, -1);
        System.out.println(" Тестирование что было:");
        Pupils.log(clonedInstance);
        System.out.println("Что стало после удаления:");
        Pupils.log(instance);


    }
}
