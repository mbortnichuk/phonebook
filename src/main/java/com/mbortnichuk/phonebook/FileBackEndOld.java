package com.mbortnichuk.phonebook;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mariana on 05-Feb-17.
 */
public class FileBackEndOld implements BackEndOld {

    public static final String DATABASE_FILE = "d:/temp/database.txt";

    public static String filePath = DATABASE_FILE;


    public  void put(String dataFile, Map<String, String> records, boolean append) {

        try (FileWriter fw = new FileWriter(dataFile, append);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for (Map.Entry<String, String> entry : records.entrySet()) {
                String fileRecord = entry.getKey() + "-" + entry.getValue();
                out.println(fileRecord);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public  Map<String, String> getAll(String dataFile, boolean getByName) { //add new parameter, boolean

        Map<String, String> dataBase = new HashMap<String, String>();

        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(dataFile);
            bufferedReader = new BufferedReader(fileReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) { //
                String[] phoneAndName = line.split("-");
                if (getByName) {                                  /////homework
                    dataBase.put(phoneAndName[1], phoneAndName[0]);  //key = name
                } else {
                    dataBase.put(phoneAndName[0], phoneAndName[1]);  //key = number
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) bufferedReader.close();
                if (fileReader != null) fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return dataBase;
    }

    public  String get(String dataFile, String key, boolean getByName) { ////homework
        Map<String, String> data = this.getAll(dataFile, getByName);
        String value = data.get(key);

        return value;
    }


}
