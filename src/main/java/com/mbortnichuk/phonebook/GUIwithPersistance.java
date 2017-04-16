package com.mbortnichuk.phonebook;

/**
 * Created by Mariana on 18-Mar-17.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;


public class GUIwithPersistance {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

    Record currentUpdRecord = null;

    //    public static BackEndOld backEndOld = new FileBackEndOld();
    // public static BackEndOld backEndOld = new DataBaseBackEndOld();
    Persistance persistance = new DatabasePersistance();


    public GUIwithPersistance() {
        prepareGUI();
    }

    public static void main(String[] args) {
        GUIwithPersistance swingLayoutDemo = new GUIwithPersistance();
        swingLayoutDemo.showCardLayoutDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Phone Book Prototype");
        mainFrame.setSize(1000, 400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    public static Map<String, String> reverse(Map<String, String> mapToReverse) {
        Map<String, String> newMap = new HashMap<>();
        for (Map.Entry<String, String> entry : mapToReverse.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            newMap.put(value, key);
        }
        return newMap;
    }

    private void showCardLayoutDemo() {
        headerLabel.setText("Phone Book Prototype");

        final JPanel bluePanel = new JPanel();
//        bluePanel.setLayout(new BoxLayout(bluePanel, BoxLayout.X_AXIS));
        bluePanel.setBackground(Color.BLUE);
        bluePanel.setSize(300, 500);

        CardLayout layout = new CardLayout();
        layout.setHgap(10);
        layout.setVgap(10);
        bluePanel.setLayout(layout);

        JPanel updatePanel = new JPanel(new FlowLayout());
        JLabel getULabel = new JLabel("Number");
        updatePanel.add(getULabel);
        JTextField getUTField = new JTextField("Enter phone number", 10);
        updatePanel.add(getUTField);
        JButton getUpdButton = new JButton("Get");
        updatePanel.add(getUpdButton);
        JLabel getULResult = new JLabel("");
        updatePanel.add(getULResult);
        JLabel updateIdLabel = new JLabel("ID");
        updatePanel.add(updateIdLabel);
        JTextField updateIdField = new JTextField("Enter ID", 10);
        updatePanel.add(updateIdField);
        JLabel updateNameL = new JLabel("Name");
        updatePanel.add(updateNameL);
        JTextField updateNameField = new JTextField("Enter name", 10);
        updatePanel.add(updateNameField);
        JLabel updatePhoneL = new JLabel("Phone");
        updatePanel.add(updatePhoneL);
        JTextField updatePhoneField = new JTextField("Enter phone", 10);
        updatePanel.add(updatePhoneField);
        JButton updateButton = new JButton("Update");
        updatePanel.add(updateButton);
        JLabel updateResult = new JLabel("Record updated: "); //  + savePhoneField + " - " + saveNameField; "<html>Economy<br>Regularity</html>"
        updatePanel.add(updateResult);

        JPanel savePanel = new JPanel(new FlowLayout());
//        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));
        JLabel saveIdLabel = new JLabel("ID");
        savePanel.add(saveIdLabel);
        JTextField saveIdField = new JTextField("Enter ID", 10);
        savePanel.add(saveIdField);
        JLabel saveNameL = new JLabel("Name");
        savePanel.add(saveNameL);
        JTextField saveNameField = new JTextField("Enter name", 10);
        savePanel.add(saveNameField);
        JLabel savePhoneL = new JLabel("Phone");
        savePanel.add(savePhoneL);
        JTextField savePhoneField = new JTextField("Enter phone", 10);
        savePanel.add(savePhoneField);
        JButton saveButton = new JButton("Save");
        savePanel.add(saveButton);
        JLabel saveResult = new JLabel("Saved: "); //  + savePhoneField + " - " + saveNameField; "<html>Economy<br>Regularity</html>"
        savePanel.add(saveResult);

        JPanel getPNPanel = new JPanel(new FlowLayout());
        JRadioButton nameRadioButton = new JRadioButton("Name");
        getPNPanel.add(nameRadioButton);
        nameRadioButton.setSelected(true);
        JRadioButton phoneRadioButton = new JRadioButton("Phone");
        getPNPanel.add(phoneRadioButton);
        JTextField getPNField = new JTextField("Enter info", 10);
        getPNPanel.add(getPNField);
        JButton getPNButton = new JButton("Get");
        getPNPanel.add(getPNButton);
        JLabel getPNLabel = new JLabel();
        getPNPanel.add(getPNLabel);

        JPanel getPanel = new JPanel(new FlowLayout());
        JLabel getNameByPhoneL = new JLabel("Phone");
        getPanel.add(getNameByPhoneL);
        JTextField getNameField = new JTextField("Enter phone", 10);
        getPanel.add(getNameField);
        JButton getButton = new JButton("Get");
        getPanel.add(getButton);
        JLabel getResult = new JLabel("Result: ");
        getPanel.add(getResult);

        JPanel getAllPanel = new JPanel(new FlowLayout());
        JButton getAllButton = new JButton("Get All");
        getAllPanel.add(getAllButton);
        JLabel getAllResultL = new JLabel("Result: ");
        getAllPanel.add(getAllResultL);

        JPanel getByNamePanel = new JPanel(new FlowLayout());
        JLabel getByNameL = new JLabel("Name");
        getByNamePanel.add(getByNameL);
        JTextField getByNameField = new JTextField("Enter name", 10);
        getByNamePanel.add(getByNameField);
        JButton getByNameButton = new JButton("Get By Name");
        getByNamePanel.add(getByNameButton);
        JLabel getByNameResultL = new JLabel("Result: ");
        getByNamePanel.add(getByNameResultL);

//        JPanel changeFilePanel = new JPanel(new FlowLayout());
//        JLabel changePathLable = new JLabel("File name");
//        changeFilePanel.add(changePathLable);
//        JTextField changeFileField = new JTextField("Enter file name", 10);
//        changeFilePanel.add(changeFileField);
//        JButton changeFileButton = new JButton("ChangeFile");
//        changeFilePanel.add(changeFileButton);
//        JLabel changeFileResult = new JLabel("Your file path: ");
//        changeFilePanel.add(changeFileResult);

        JPanel deletePanel = new JPanel(new FlowLayout());
        JLabel deleteByPhoneL = new JLabel("Name");
        deletePanel.add(deleteByPhoneL);
        JTextField deleteField = new JTextField("Enter name", 10);
        deletePanel.add(deleteField);
        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);
        JLabel deleteResult = new JLabel("Deleted record: ");
        deletePanel.add(deleteResult);

        ButtonGroup group = new ButtonGroup();
        group.add(nameRadioButton);
        group.add(phoneRadioButton);

        bluePanel.add("Save Panel", savePanel);
        bluePanel.add("Update Panel", updatePanel);
//        bluePanel.add("Get Panel", getPanel);
        bluePanel.add("Get Panel", getPNPanel);
        bluePanel.add("Get All Panel", getAllPanel);         //
//        bluePanel.add("Get By Name Panel", getByNamePanel);
//        bluePanel.add("Change File Panel", changeFilePanel); //
        bluePanel.add("Delete Panel", deletePanel);
        final DefaultComboBoxModel panelName = new DefaultComboBoxModel();

        panelName.addElement("Save Panel");
        panelName.addElement("Update Panel");
//        panelName.addElement("Get Panel");
        panelName.addElement("Get Panel");
        panelName.addElement("Get All Panel");
//        panelName.addElement("Get By Name Panel");
//        panelName.addElement("Change File Panel");
        panelName.addElement("Delete Panel");

        final JComboBox listCombo = new JComboBox(panelName);

        listCombo.setSelectedIndex(0);
        JScrollPane listComboScrollPane = new JScrollPane(listCombo);
        JButton showButton = new JButton("Show");
        JButton refreshButton = new JButton("Refresh");


        getUpdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String num = getUTField.getText();
                if (num.isEmpty()) {
                    getULResult.setText("Enter phone number to get name");
                } else {
                    java.util.List<Record> recList = persistance.read("phone_number", num);
                    if (recList.isEmpty()) {
                        //say smth
                    } else {
                        currentUpdRecord = recList.get(0);

                        updateIdField.setText(String.valueOf(currentUpdRecord.getId()));
                        updateNameField.setText(currentUpdRecord.getName());
                        updatePhoneField.setText(currentUpdRecord.getPhone());
                    }

//                    updateIdField.setText(recList.toString());
//                    updateNameField.setText(recList.toString());
//                    updatePhoneField.setText(recList.toString());
                }
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Record rec = new Record(Integer.valueOf(updateIdField.getText()), updatePhoneField.getText(), updateNameField.getText());
                int recordsAffected = persistance.update(rec, "phone_number", getUTField.getText());

                updateResult.setText("Successfully updated");

                getUTField.setText("Enter phone number");
                updateIdField.setText("Enter ID");
                updateNameField.setText("Enter name");
                updatePhoneField.setText("Enter phone number");
            }

        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = deleteField.getText();
                int recList = persistance.delete("name", name);
                if (name == null) {
                    deleteResult.setText("There is no such record");
                } else {
                    deleteResult.setText("Deleted: " + recList);
                }

            }
        });

        getAllButton.addActionListener(new ActionListener() { //readall
            @Override
            public void actionPerformed(ActionEvent e) {

                java.util.List<Record> recList = persistance.readALL();
                if (recList.isEmpty()) {
                    getAllResultL.setText("Database is empty");
                } else {
                    StringBuilder textToDisplay = new StringBuilder();
                    for (int i = 0; i < recList.size(); i++) {
                        Record rec = recList.get(i);
                        String str = rec.getName() + " - " + rec.getPhone() + ", ";
                        textToDisplay.append(str);
                    }
                    textToDisplay.deleteCharAt(textToDisplay.length() - 2);
                    getAllResultL.setText(textToDisplay.toString());
                }

            }
        });

        getByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = getByNameField.getText();
                if (name.isEmpty()) {
                    getByNameResultL.setText("Enter name to get phone number");
                } else {
                    java.util.List<Record> recList = persistance.read("name", name);
                    getByNameResultL.setText(recList.toString());
                }
            }
        });

        getPNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String info = getPNField.getText(); //get by phone
//                String name = getByNameField.getText(); // get by name

                if(phoneRadioButton.isSelected()){
                    if (info.isEmpty()) {
                        getPNLabel.setText("Enter phone number to get name");
                    } else {
                        java.util.List<Record> recList = persistance.read("phone_number", info);
                        getPNLabel.setText(recList.toString());
                    }
                }else if(nameRadioButton.isSelected()){
                    if (info.isEmpty()) {
                        getPNLabel.setText("Enter name to get phone number");
                    } else {
                        java.util.List<Record> recList = persistance.read("name", info);
                        getPNLabel.setText(recList.toString());
                    }
                }
                getPNField.setText("Enter info");
            }
        });

        getButton.addActionListener(new ActionListener() {   //read
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = getNameField.getText();
                if (phone.isEmpty()) {
                    getResult.setText("Enter phone number to get name");
                } else {
                    java.util.List<Record> recList = persistance.read("phone_number", phone);
                    getResult.setText(recList.toString());
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(saveIdField.getText());
                String phone = savePhoneField.getText();
                String name = saveNameField.getText();
                if (phone.isEmpty() && name.isEmpty()) {
                    saveResult.setText("Enter information to save");
                } else {
                    Record rec = new Record(id, phone, name);
                    persistance.create(rec);
                    saveResult.setText("Record have been saved");
                }

            }
        });

        showButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String data = "";
                if (listCombo.getSelectedIndex() != -1) {
                    CardLayout cardLayout = (CardLayout) (bluePanel.getLayout());
                    cardLayout.show(bluePanel,
                            (String) listCombo.getItemAt(listCombo.getSelectedIndex()));
                }
                statusLabel.setText(data);
            }
        });

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        controlPanel.add(listComboScrollPane);
        controlPanel.add(showButton);
        controlPanel.add(refreshButton);
        controlPanel.add(bluePanel);
        mainFrame.setVisible(true);
    }
}
