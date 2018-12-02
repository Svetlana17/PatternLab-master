package Singliton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Singliton {
    private static Singliton instance = new Singliton();
    private final Properties properties;
    public Properties getProperties() {
        return properties;
    }
    public static Singliton getInstance() {
        if (instance == null) {
            instance = new Singliton();
        }
        return instance;
    }
    private Singliton() {
        properties = new Properties();
        try (FileInputStream input = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}