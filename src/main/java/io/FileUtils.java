package io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readLines(String path){
        List<String> rows = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String str = "";
            while ((str = bufferedReader.readLine()) != null){
                rows.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rows;
    }


    public static void writeLine(String value , String path){
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path,true))) {
            fileWriter.write(value + '\n');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
