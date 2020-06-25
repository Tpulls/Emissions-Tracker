/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emissionstracker2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author thomas
 */
public class EmissionsTracker2 extends JFrame implements ActionListener, KeyListener
{

    // Set the variables
    private int totalX = 7;
    private int totalY = 12;

    private JTextField[][] fields = new JTextField[totalX][totalY];
    private JButton btnClear, btnSave, btnExit, btnSaveTable;

    private String dataFileName = "EmissionsTracker.csv";
    private String tableFileName = "EmissionsTable.csv";

    private String[] headingsAtTheTop =
    {
        "Time", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Weekly Average"
    };
    private String[] headingsOnTheSide =
    {
        "Emissions", "9:00am", "10:00am", "11:00am", "12:00pm", "1:00pm", "2:00pm", "3:00pm", "4:00pm", "5:00pm", "Total", "Average"
    };

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Instantiate a new Program
        EmissionsTracker2 emissionsTrackingApplication = new EmissionsTracker2();
        emissionsTrackingApplication.run();
    }

    private void run()
    {
        setBounds(100, 50, 860, 400);
        setTitle("Emissions Tracker");

        // In instatiating a window adapterm we can reduce the required methods from 7. 
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });

        displayGUI();
        // Call the method to input the data from the data file
        readDataFile(dataFileName);
        // Call the method to calculate the totals
        calculateTotals();
        setResizable(false);
        setVisible(true);
    }

    private void displayGUI()
    {
        SpringLayout springLayout = new SpringLayout();
        setLayout(springLayout);

        displayTextFields(springLayout);
        displayButtons(springLayout);
        setupTable(springLayout);

    }

    private void displayTextFields(SpringLayout layout)
    {
        for (int y = 0; y < totalY; y++)
        {
            for (int x = 0; x < totalX; x++)
            {
                int xPos = x * 120 + 20;
                int yPos = y * 25 + 20;
                fields[x][y] = LibraryComponents.LocateAJTextField(this, this, layout, 9, xPos, yPos);
            }
        }
    }

    private void displayButtons(SpringLayout layout)
    {
        btnClear = LibraryComponents.LocateAJButton(this, this, layout, "Clear", 20, 330, 80, 25);
        btnSaveTable = LibraryComponents.LocateAJButton(this, this, layout, "Save Table", 572, 330, 120, 25);
        btnSave = LibraryComponents.LocateAJButton(this, this, layout, "Save", 692, 330, 80, 25);
        btnExit = LibraryComponents.LocateAJButton(this, this, layout, "Exit", 762, 330, 80, 25);

    }

    private void setupTable(SpringLayout layout)
    {
        for (int y = 0; y < totalY; y++)
        {
            fields[0][y].setText(headingsOnTheSide[y]);
            setFieldProperties(0, y, false, 220, 220, 255);
            setFieldProperties(6, y, false, 220, 255, 220);
        }

        for (int x = 0; x < totalX; x++)
        {
            fields[x][0].setText(headingsAtTheTop[x]);
            setFieldProperties(x, 0, false, 220, 220, 255);
            setFieldProperties(x, 10, false, 220, 255, 220);
            setFieldProperties(x, 11, false, 220, 255, 220);
        }
        setFieldProperties(6, 10, false, 254, 253, 205);
        setFieldProperties(6, 11, false, 254, 253, 205);

    }

    private void setFieldProperties(int x, int y, boolean editable, int r, int g, int b)
    {
        fields[x][y].setEditable(editable);
        fields[x][y].setBackground(new Color(r, g, b));
    }

    // Add the structure for the three application buttons
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == btnClear)
        {
            // Call the method to clear the table
            ClearData();
        }
        
        if (e.getSource() == btnSaveTable)
        {
            saveEmissionsTableToFile(tableFileName);
        }

        if (e.getSource() == btnSave)
        {
            // Call the method to output to the data file
            writeDataFileName(dataFileName);
        }

        if (e.getSource() == btnExit)
        {
            System.exit(0);
        }

    }

    // Add the three methods required for a key listener
    @Override
    public void keyTyped(KeyEvent e)
    {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {

    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        // Call the method to re-calculate the totals
        calculateTotals();
    }

    private void readDataFile(String fileName)
    {
        try
        {
            // Open a BufferedReader ready for data input
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            // Loop through each line of data in the data file and place the third entry 
            // into the next spot of the interface
            for (int x = 1; x < totalX - 1; x++)
            {
                for (int y = 1; y < totalY - 2; y++)
                {
                    String temp[] = br.readLine().split(",");
                    fields[x][y].setText(temp[2]);
                }
            }
            br.close();
        } 
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void writeDataFileName(String fileName)
    {
        try
        {
            // Open a Buffered Writer ready for output
            BufferedWriter outFile = new BufferedWriter(new FileWriter("EmissionsTracker_New.csv"));
            for (int x = 1; x < totalX - 1; x++)
            {
                for (int y = 1; y < totalY - 1; y++)
                {
                    outFile.write(fields[0][y].getText() + "," + fields[x][0].getText() + "," + fields[x][y].getText());
                    outFile.newLine();
                }
            }
            outFile.close();
            // Temporary line of testing output:
            JOptionPane.showMessageDialog(this, "Emissions Tracker data has been saved");
        }
        catch (Exception e)
                {
                    System.err.println("Error: " + e.getMessage());
                }
    }

    private void ClearData()
    {
        for (int y = 1; y < totalY - 1; y++)
        {
            for (int x = 1; x < totalX - 1; x++)
            {
                fields[x][y].setText("0");
                fields[6][y].setText("0");
                fields[x][11].setText("0");
                fields[6][10].setText("0");
                fields[6][11].setText("0");
            }
        }
    }

    private void saveEmissionsTableToFile(String fileName)
    {
        try 
        {
            BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
            for (int y = 0; y < totalY; y++)
            {
                for (int x = 0; x < totalX; x++)
                {
                    outFile.write(fields[x][y].getText() + "," );
                }
                outFile.write(fields[6][y].getText() + "," );
                outFile.newLine();
            }
            outFile.close();
            JOptionPane.showMessageDialog(this, "Emissions Tracker table has been saved");
        }
        catch (Exception e)
        {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private void calculateTotals()
    {
        int total = 0;
        
        for (int y = 1; y < totalY - 2; y++)
        {
            for (int x = 1; x < totalX - 1; x++)
            {
                total = total + Integer.parseInt(checkInteger(fields[x][y].getText()));
            }
            fields[6][y].setText("" + String.format("%.2f",((double) total / ((double) totalX -2))));
            total = 0;
        }
        
        for (int x = 1; x < totalX - 1; x++)
        {
            for (int y = 1; y < totalY -2; y++)
            {
                total = total + Integer.parseInt(checkInteger(fields[x][y].getText()));
            }
            fields[x][10].setText("" + total);
            fields[x][11].setText("" + String.format("%.2f",((double) total / ((double) totalY - 3))));
            total = 0;
        }
        
        for (int x = 1; x < totalX - 1; x++)
        {
            total = total + Integer.parseInt(checkInteger(fields[x][10].getText()));
        }
        fields[6][10].setText("" + total);
        fields[6][11].setText("" + String.format("%.2f",((double) total / ( ((double) totalY - 3) * ((double) totalX - 2) ))));
        total = 0;
    }

    private String checkInteger(String strValue)
    {
        try
        {
            Integer.parseInt(strValue);
            return strValue;
        }
        catch (Exception e)
        {
            return "0";
        }
    }

}
