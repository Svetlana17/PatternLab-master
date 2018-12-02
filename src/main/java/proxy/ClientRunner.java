package proxy;

import java.io.*;
import java.util.Scanner;

public class ClientRunner {

    private static Scanner in = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        System.out.println("Введите 2 числа:");
    try {
            int a = in.nextInt();
            int b = in.nextInt();
         //   int c= a*b;
            System.out.println(Server.getSum(a, b));


        }
        catch (Exception e) {
            System.out.println("Errors");
        }

    }
}


