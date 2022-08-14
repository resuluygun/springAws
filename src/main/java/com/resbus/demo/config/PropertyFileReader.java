package com.resbus.demo.config;

import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

@Configuration
public class PropertyFileReader {
    public void getPropValues() throws IOException {
        InputStream inputStream = null;
        try {
            Properties prop = new Properties();
            String propFileName = "src/main/java/com/resbus/demo/config/config.properties";

            inputStream = new FileInputStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            Date time = new Date(System.currentTimeMillis());

            String prop1 = prop.getProperty("awsAccessKey");
            String prop2 = prop.getProperty("awsSecretKey");


            System.setProperty("awsAccessKey",prop1);
            System.setProperty("awsSecretKey",prop2);

            System.out.println( "\n Properties Successfully Loaded On " + time);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null)  inputStream.close();
        }
    }
}