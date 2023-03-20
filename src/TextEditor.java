import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of TextEditor.
    JFrame frame; //Creating frame

    //Menu Bar
    JMenuBar menuBar; //creating menuBar

    JMenu file, edit; //creating menu items

    JMenuItem newFile, openFile, saveFile; // File Menu Items.
    JMenuItem cut, copy, paste, selectAll, close; // Edit Menu Items.

    //Text Area
    JTextArea textArea;
    TextEditor(){
        //initialize a frame
        frame = new JFrame();

        //Initialize a MenuBar;
        menuBar = new JMenuBar();
        //Initialize text area.
        textArea = new JTextArea();

        //Creating & setting Menus.
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initialize menu Items
        newFile = new JMenuItem("New");
        openFile = new JMenuItem("Open");
        saveFile = new JMenuItem("Save");

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        //Add action listener to menu items.
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);

        //Adding menu Items to file and Edit menus
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add Menus to MenuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Set MenuBar to JFrame
        frame.setJMenuBar(menuBar);

        //Create content pane
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5, 5,5,5));
        panel.setLayout(new BorderLayout(0, 0));

        //Add text area to Panel
        panel.add(textArea, BorderLayout.CENTER);

        //Create Scroll Panel
        JScrollPane scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        //Add Scroll Pane to panel.
        panel.add(scrollPane);

        //Add Panel to frame.
        frame.add(panel);

        //Set Dimensions of Frame.
        frame.setTitle("Text Editor (By Chanda Sahu)");
        frame.setBounds(200, 200, 400, 400);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource() == cut){
            //Perform cut operation.
            textArea.cut();
        }
        if(actionEvent.getSource() == copy){
            //Perform copy operation.
            textArea.copy();
        }
        if(actionEvent.getSource() == paste){
            //Perform paste Operation.
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //Perform Select All Operation.
            textArea.selectAll();
        }
        if(actionEvent.getSource() == close){
            //Perform close Operation.
            System.exit(0);
        }
        if(actionEvent.getSource() == openFile){
            //Open file chooser.
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);

            //if we have picked - open
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting the selected file.
                File file = fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath = file.getPath();
                try{
                    //Initialize file reader
                    FileReader fileReader = new FileReader(filePath);
                    //Initialize the buffered Reader
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "", output = "";
                    while((intermediate = bufferedReader.readLine()) != null){
                        output += intermediate+"\n";
                    }
                    //Set the output string to text area.
                    textArea.setText(output);

                } catch(IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == saveFile){
            //Initialize the file chooser/picker.
            JFileChooser fileChooser = new JFileChooser("C:");
            //Get choose Option.
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //create a new file with chosen directory path and filename.
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initialize the file writer.
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //write the contents of text area to file.
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource() == newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor();
    }
}