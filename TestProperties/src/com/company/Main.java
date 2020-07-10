package com.company;

import java.io.IOException;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws IOException {
        // write your code here
        String f = "settings.properties";
        Properties pros = new Properties();
        pros.load(new java.io.FileInputStream(f));

        String filepath = pros.getProperty("last_open_file");
        String interval = pros.getProperty("auto_save_interval", "100");
    }
}
