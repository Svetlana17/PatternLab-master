package Singliton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class MainSingliton {
    public static void main(String[] args) {
        //Create Singliton
        Singliton singliton=Singliton.getInstance();
        System.out.println(singliton);
        FileInputStream fileInputStream;
        Properties prop = new Properties();
        try {
            //обращаемся к файлу и получаем данные
            fileInputStream = new FileInputStream("src/main/resources/config.properties");
            prop.load(fileInputStream);
            //печатаем полученные данные в консоль
            System.out.println("Чтение файла singliton.properties: " + prop);
        } catch (IOException e) {
            System.out.println("Ошибка в программе: файл " + "resources/singliton.properties" + " не обнаружено");
            e.printStackTrace();
        } }
}