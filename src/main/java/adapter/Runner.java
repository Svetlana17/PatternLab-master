package adapter;

import java.io.*;

public class Runner {

    public static void main(String[] args) throws IOException {
        OutputStreamAdapter streamAdapter = new OutputStreamAdapter(new File("file.obj"));// поток который подключается к текстовому файлу
        String[] s = {"string 1", "string#2", "string # 3"};//принимает массив строк
        streamAdapter.write(s);// запись в буфер
        streamAdapter.close();
        //  System.out.println("Data is written");

        BufferedReader br = new BufferedReader(new FileReader("file.obj"));
        String str;
        System.out.println("Data from file");
        while ((str = br.readLine()) != null)// чтение построчно
        {
            System.out.println(str);
        }
    }


}
