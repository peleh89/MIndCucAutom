package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static FileInputStream input;
    private static Properties properties;

    static{
        String path = System.getProperty("user.dir")+"\\src\\test\\resources\\configurations\\Config.properties";

        try {
            input = new FileInputStream(path);
            properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("The path for properties is incorrect!!!");
        } catch (IOException e){
            System.out.println("Cold not read properties file");
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                System.out.println("Could not close input object.");
            }
        }
    }

    public static String getProperty(String key){
       return properties.getProperty(key);
    }
}
