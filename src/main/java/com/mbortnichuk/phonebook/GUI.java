package com.mbortnichuk.phonebook;

/**
 * Created by Mariana on 18-Mar-17.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;


public class GUI {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JLabel msglabel;

//    public static BackEndOld backEndOld = new FileBackEndOld();
    public static BackEndOld backEndOld = new DataBaseBackEndOld();


    public GUI() {
        prepareGUI();
    }

    public static void main(String[] args) {
        GUI swingLayoutDemo = new GUI();
        swingLayoutDemo.showCardLayoutDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Phone Book Prototype");
        mainFrame.setSize(600, 400);
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

        JPanel savePanel = new JPanel();
//        savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.X_AXIS));
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

        JPanel changeFilePanel = new JPanel(new FlowLayout());
        JLabel changePathLable = new JLabel("File name");
        changeFilePanel.add(changePathLable);
        JTextField changeFileField = new JTextField("Enter file name", 10);
        changeFilePanel.add(changeFileField);
        JButton changeFileButton = new JButton("ChangeFile");
        changeFilePanel.add(changeFileButton);
        JLabel changeFileResult = new JLabel("Your file path: ");
        changeFilePanel.add(changeFileResult);

        JPanel deletePanel = new JPanel(new FlowLayout());
        JLabel deleteByPhoneL = new JLabel("Name");
        deletePanel.add(deleteByPhoneL);
        JTextField deleteField = new JTextField("Enter name", 10);
        deletePanel.add(deleteField);
        JButton deleteButton = new JButton("Delete");
        deletePanel.add(deleteButton);
        JLabel deleteResult = new JLabel("Deleted record: ");
        deletePanel.add(deleteResult);

        bluePanel.add("Save Panel", savePanel);
        bluePanel.add("Get Panel", getPanel);
        bluePanel.add("Get All Panel", getAllPanel);         //
        bluePanel.add("Get By Name Panel", getByNamePanel);
        bluePanel.add("Change File Panel", changeFilePanel); //
        bluePanel.add("Delete Panel", deletePanel);
        final DefaultComboBoxModel panelName = new DefaultComboBoxModel();

        panelName.addElement("Save Panel");
        panelName.addElement("Get Panel");
        panelName.addElement("Get All Panel");
        panelName.addElement("Get By Name Panel");
        panelName.addElement("Change File Panel");
        panelName.addElement("Delete Panel");

        final JComboBox listCombo = new JComboBox(panelName);

        listCombo.setSelectedIndex(0);
        JScrollPane listComboScrollPane = new JScrollPane(listCombo);
        JButton showButton = new JButton("Show");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> data = backEndOld.getAll(FileBackEndOld.filePath, true);
                String removedValue = data.remove(deleteField.getText());
                if (removedValue == null) {
                    deleteResult.setText("There is no such record");
                } else {
                    data = reverse(data);
                    backEndOld.put(FileBackEndOld.filePath, data, false); //override file with recent changes
                    deleteResult.setText("Deleted: " + deleteField.getText() + "-" + removedValue);
                    deleteField.setText("Enter name");
                }
            }
        });

        changeFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filePath = changeFileField.getText();
                FileBackEndOld.filePath = filePath;
                changeFileResult.setText(filePath);

            }
        });

        getAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> data1 = backEndOld.getAll(FileBackEndOld.filePath, true);
                if (data1.isEmpty()) {
                    getAllResultL.setText("Database is empty");
                } else {
                    StringBuilder textToDisplay = new StringBuilder("");
                    for (Map.Entry<String, String> entry : data1.entrySet()) {
                        textToDisplay.append(entry.getKey() + "-" + entry.getValue()).append(", ");
//                        getAllResultL.setText(entry.getKey() + "-" + entry.getValue());
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
                    String phone = backEndOld.get(FileBackEndOld.filePath, name, true);
                    getByNameResultL.setText(name + " - " + phone);
                    getByNameField.setText("Enter name");
                }
            }
        });

        getButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String phone = getNameField.getText();
                if (phone.isEmpty()) {
                    getResult.setText("Enter phone number to get name");
                } else {
                    String name = backEndOld.get(FileBackEndOld.filePath, phone, false);
                    getResult.setText(phone + " - " + name);
                    getNameField.setText("Enter phone");
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = saveNameField.getText();
                String phone = savePhoneField.getText();
                if (name.isEmpty() && phone.isEmpty()) {
                    saveResult.setText("Enter information to save");
                } else {
                    Map<String, String> saveB = new HashMap<>();
                    saveB.put(phone, name);
                    backEndOld.put(FileBackEndOld.filePath, saveB, true);
                    saveResult.setText("Saved: " + phone + " - " + name);
                    saveNameField.setText("Enter name");
                    savePhoneField.setText("Enter phone");
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

        controlPanel.add(listComboScrollPane);
        controlPanel.add(showButton);
        controlPanel.add(bluePanel);
        mainFrame.setVisible(true);
    }
}
