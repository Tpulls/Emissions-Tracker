/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emissionstracker2;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
/**
 *
 * @author thomas
 */
/*
Purpose: To contain a library of utility methods that can be accesse from other Java Applications.

Methods:
- LocateAJLabel - for positioning a JLabel using the layout manager: SpringLayout
- LocateAJTextfield - for positioning a JTextField using SpringLayout
- LocateAJButton - for positioning a JButoon using SpringLayout
- LocateAJTextArea - for positioning a JTextArea using SpringLayout
*/
public class LibraryComponents
{
    
   /**
    * Purpose: Locate a single JLabel within a JFrame
    * @param JFrame, Layout_manager, JLabel_Caption, Width, X_Position, Y_Position
    * @return the JLabel
    */
    public static JLabel LocateAJLabel(JFrame myJFrame, SpringLayout myJLabelLayout, String JLabelCaption, int x, int y)
    {
        // Declare and instantiate the JLabel
        JLabel myJLabel = new JLabel(JLabelCaption);
        // Add the JLabel to the screen
        myJFrame.add(myJLabel);
        // Set the position of the JLabel (from left hand side of the JFrame (West), and from top of JFrame(North)
        myJLabelLayout.putConstraint(SpringLayout.WEST, myJLabel, x, SpringLayout.WEST, myJFrame);
        myJLabelLayout.putConstraint(SpringLayout.NORTH, myJLabel, y, SpringLayout.NORTH, myJFrame);
        // return the lable to the calling method\
        return myJLabel;
    }
    /**
     * Purpose: Locate a single JTextField within a JFrame
     * @param JFrame, Layout_Manager, Width, X_Position, Y_Position
     * @returns the JTextField
     */
    public static JTextField LocateAJTextField(JFrame myJFrame, KeyListener myKeyLstnr, SpringLayout myJTextFieldLayout, int width, int x, int y)
    {
        // Declare and instantiate teh JTextField with width parameter
        JTextField myJTextField = new JTextField(width);
        // add JTextField to the screen
        myJFrame.add(myJTextField);
        // Add the key listener to the field
        myJTextField.addKeyListener(myKeyLstnr);
        // Set the position of the JTextField (from left hand side of the JFrame (West), and from top of JFrame(North)
        myJTextFieldLayout.putConstraint(SpringLayout.WEST, myJTextField, x, SpringLayout.WEST, myJFrame);
        myJTextFieldLayout.putConstraint(SpringLayout.NORTH, myJTextField, y, SpringLayout.NORTH, myJFrame);
        // return te JTextField to the calling method
        return myJTextField;
        
    }
        /**
     * Purpose: Locate a single JButton within a JFrame
     * @param JFrame, Layout_Manager, JButton_name, JButton_Caption, X_Position, Y_Position, Width, Height
     * @returns the JButton
     */
    
    public static JButton LocateAJButton(JFrame myJFrame, ActionListener myActnLstnr,SpringLayout myJButtonLayout, String JButtonCaption, int x, int y, int w, int h)
    {
        // Declare and instantiate the JButton with width parameter
        JButton myJButton = new JButton(JButtonCaption);
        // add JTextField to the screen
        myJFrame.add(myJButton);
        // Add the action listener to the field
        myJButton.addActionListener(myActnLstnr);
        // Set the position of the JButton (from left hand side of the JFrame (West), and from top of JFrame(North)
        myJButtonLayout.putConstraint(SpringLayout.WEST, myJButton, x, SpringLayout.WEST, myJFrame);
        myJButtonLayout.putConstraint(SpringLayout.NORTH, myJButton, y, SpringLayout.NORTH, myJFrame);
        myJButton.setPreferredSize(new Dimension(w,h));
        // return the JButton to the calling method
        return myJButton;
    }
    
    public static JTextArea LocateAJTextArea(JFrame myJFrame, SpringLayout myLayout, JTextArea myJTextArea, int x, int y, int w, int h)
    {
        // Declare and instantiate the JTextArea with width and height parameter
        myJTextArea = new JTextArea(w,h);
        // Add the JTextArea to screen
        myJFrame.add(myJTextArea);
        // Set the position of the JButton (from left hand side of the JFrame (West), and from top of JFrame(North)
        myLayout.putConstraint(SpringLayout.WEST, myJTextArea, x, SpringLayout.WEST, myJFrame);
        myLayout.putConstraint(SpringLayout.NORTH, myJTextArea, y, SpringLayout.NORTH, myJFrame);
        // return the myJTextArea to the calling method
        return myJTextArea;
    }
            
}
