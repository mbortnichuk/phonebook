package com.mbortnichuk.phonebook;



import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static com.mbortnichuk.phonebook.FileBackEndOld.filePath;

/**
 * Created by Mariana on 15-Jan-17.
 */
public class Console {

    public static final String COMMAND_EXIT = "exit";
    public static final String COMMAND_SAVE = "save";
    public static final String COMMAND_SAVEM = "savem";
    public static final String COMMAND_GET = "get";
    public static final String COMMAND_GETALL = "getall";
    public static final String COMMAND_CHANGEFILE = "changefile";
    public static final String COMMAND_DELETE = "delete";
    public static final String COMMAND_DELETEM = "deletem";
    public static final String COMMAND_GETNAME = "getname";

    public static BackEndOld fileBackEndOld = new FileBackEndOld();

//    public static BackEndOld dataBaseBackEnd = new DataBaseBackEndOld();

    public static Scanner keyboard = null;

    public static boolean doExit = false;

    public static void main(String[] args) {

        try {
            System.out.println("Please enter command, " + filePath);

            keyboard = new Scanner(System.in);

            String command = keyboard.next();

            while (!doExit) {
                try {
                    if (command.equals(COMMAND_SAVE)) {
                        System.out.println("Enter information by using example 'Phone-name' ");
                        String informaton = keyboard.next();
                        String[] phoneAndName = informaton.split("-");
                        if (phoneAndName.length < 2 || phoneAndName[0].isEmpty()) {
                            System.out.println("Wrong input");
                        } else {
                            Map<String, String> info = new HashMap<>();
                            info.put(phoneAndName[0], phoneAndName[1]);
                            fileBackEndOld.put(filePath, info, true);
//                    dataBase.put(phoneAndName[0], phoneAndName[1]);
                            System.out.println("Saved");
                        }

                    } else if(command.equals(COMMAND_SAVEM)){
                        System.out.println("Enter information by using example 'Phone-name' ");
                        String informaton = keyboard.next();

                        while(!informaton.equals("x")){
                            String[] phoneAndName = informaton.split("-");
                            if (phoneAndName.length < 2 || phoneAndName[0].isEmpty()) {
                                System.out.println("Wrong input");
                            } else {
                                Map<String, String> info = new HashMap<>();
                                info.put(phoneAndName[0], phoneAndName[1]);
                                fileBackEndOld.put(filePath, info, true);
//                    dataBase.put(phoneAndName[0], phoneAndName[1]);
                                System.out.println("Saved");
                            }
                            informaton = keyboard.next();
                        }
                        System.out.println("X");


                    }else if (command.equals(COMMAND_EXIT)) {
                        System.out.println("Are you sure you want to exit? yes/no");
                        String input = keyboard.next();
                        if (input.equals("yes")) {
                            doExit = true;
                            System.out.println("Exiting");
                            System.exit(0);
                        }

                    } else if (command.equals(COMMAND_DELETE)) {

                        System.out.println("Enter name to delete");
                        String input = keyboard.next();

                        Map<String, String> data = fileBackEndOld.getAll(filePath, true);
                        String removedValue = data.remove(input);
                        if (removedValue == null) {
                            System.out.println("There is no such record");
                        } else {
                            data = reverse(data);
                            fileBackEndOld.put(filePath, data, false); //override file with recent changes
                            System.out.println("Record has been deleted:" + input + "-" + removedValue);
                        }

                    } else if (command.equals(COMMAND_DELETEM)) {

                        System.out.println("Enter name to delete, or x to exit to main mode.");
                        String input = keyboard.next();

                        while (!input.equals("x")) {
                            Map<String, String> data = fileBackEndOld.getAll(filePath, true);
                            String removedValue = data.remove(input);

                            if (removedValue == null) {
                                System.out.println("There is no such record");
                            } else {
                                data = reverse(data);
                                fileBackEndOld.put(filePath, data, false);
                                System.out.println("Record has been deleted:" + removedValue);
                            }
                            input = keyboard.next();
                        }
                        System.out.println("X");


                    } else if (command.equals(COMMAND_GETNAME)) {      //////homework
                        System.out.println("Enter name");
                        String name = keyboard.next();
                        String phone = fileBackEndOld.get(filePath, name, true);
                        if (phone == null) {
                            System.out.println("There is no such number");
                        } else {
                            System.out.println("Name " + name + " Phone " + phone);
                        }

                    } else if (command.equals(COMMAND_GET)) {
                        System.out.println("Enter phone number");
                        String phoneNumber = keyboard.next();
//                String name = dataBase.get(phoneNumber); //here
                        String userName = fileBackEndOld.get(filePath, phoneNumber, false);
                        if (userName == null) {
                            System.out.println("There is no such name");
                        } else {
                            System.out.println("Phone number " + phoneNumber + " User name " + userName);
                        }


                    } else if (command.equals(COMMAND_GETALL)) {
                        Map<String, String> data1 = fileBackEndOld.getAll(filePath, true);
                        if (data1.isEmpty()) {
                            System.out.println("Database is empty");
                        } else {
                            for (Map.Entry<String, String> entry : data1.entrySet()) {
                                System.out.println(entry.getKey() + "-" + entry.getValue());
                            }
                        }

                    } else if (command.equals(COMMAND_CHANGEFILE)) {
                        System.out.println("Enter file path");
                        String filePath = keyboard.next();
                        filePath = filePath;
                        System.out.println("File name has been changed");


                    } else {
                        throw new CommandException("Unrecognized command");
                    }
                    System.out.println("Please enter command, " + filePath);
                    command = keyboard.next();
                } catch (CommandException e) {
                    System.out.println(e.getMessage());
                    System.out.println("Please enter command, " + filePath);
                    command = keyboard.next();
                }

            }
            System.out.println("Exiting program");
        } finally {
            keyboard.close();
        }
    }

    public static Map<String, String> reverse(Map<String, String> mapToReverse){
        Map<String, String> newMap = new HashMap<>();
        for(Map.Entry<String, String> entry : mapToReverse.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            newMap.put(value, key);
        }
        return newMap;
    }
}





