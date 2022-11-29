/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.swing.*;

import model.questions.Question;
import model.questions.QuestionSelection;

/**
 * Contains features to build a popup window for questions and collect the input from the user.
 *
 * @author Kyler Robison
 */
public class QuestionPopup {

    /**
     * Represents the width of the standard elements in the popup window
     */
    private static final int COMPONENT_WIDTH = 260;

    /**
     * The Question object from which this popup get its data.
     */
    private final Question myQuestion;

    /**
     * Contains the radio buttons for selection questions.
     */
    private ButtonGroup myButtons;

    /**
     * Contains the input field for free answer questions.
     */
    private JTextField myInputField;

    /**
     * Contains the icon for the popup.
     */
    private final ImageIcon icon;

    /**
     * Represents the type of popup. 0 = undefined, 1 = Selection, 2 = Free answer.
     */
    private int myType = 0;

    /**
     * Initializes the icon and question for the popup. Invoke promptQuestion() to
     * run the popup.
     * @param theQuestion the question Object to be used.
     */
    QuestionPopup(final Question theQuestion) {
        icon = new ImageIcon();
        setIcon();
        myQuestion = theQuestion;
    }

    /**
     * Displays the popup and collects the input. If the user enters an answer
     * and clicks submit, method will call necessary code in controller to
     * process the input.
     */
    public void promptQuestion() {
        Object[] options = {"Submit", "Close"};
        int result = JOptionPane.showOptionDialog(TMPanel.getTriviaMaze(), buildWindow(), "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

        if(result == 0) {
            if(getInput() != null) {
                //Code to tell controller that an attempt was made, getInput() will have the answer attempt
            }
        }
    }

    /**
     * Reads in and stores an icon from a file.
     */
    private void setIcon() {
        try {
            BufferedImage iconImage = ImageIO.read
                    (Objects.requireNonNull(getClass().getResourceAsStream("/icons/question-mark.png")));
            icon.setImage(iconImage);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Builds a JPanel containing proper components to be used in the
     * JOptionPane. Also initializes the type field.
     * @return the new JPanel, structure varies based on question type.
     */
    private JPanel buildWindow() {
        JPanel window = new JPanel();
        window.setPreferredSize(new Dimension(COMPONENT_WIDTH, 300));

        //Use embedded html and css to enable text wrapping in the JLabel
        String text = myQuestion.getPrompt();
        int width = COMPONENT_WIDTH - 60; //Offset for the icon to the left I guess
        String prompt = String.format("<html><div style=\"width:%dpx;\">%s</div></html>", width, text);
        JLabel qPrompt = new JLabel(prompt);

        window.add(qPrompt);
        window.add(new JLabel(" ")); //Spacer

        if(myQuestion instanceof QuestionSelection) {
            myType = 1;
            addRadioButtons(window);
        }
        else {
            myType = 2;
            addInputField(window);
        }
        return window;
    }


    /**
     * Adds radio buttons for each option in the question object. Adds the
     * buttons to the ButtonGroup myButtons so that only one can be
     * selected at any given time.
     * @param theWindow the JPanel to add the buttons to.
     */
    private void addRadioButtons(final JPanel theWindow) {
        QuestionSelection q = (QuestionSelection) myQuestion;
        myButtons = new ButtonGroup();
        for(String s : q.getOptions()) {
            var b = new JRadioButton(s);
            b.setPreferredSize(new Dimension(COMPONENT_WIDTH, 25));
            myButtons.add(b);
            theWindow.add(b);
        }
    }

    /**
     * Adds a JTextField to the JPanel.
     * @param theWindow the JPanel to add the text field to.
     */
    private void addInputField(final JPanel theWindow) {
        myInputField = new JTextField();
        myInputField.setPreferredSize(new Dimension(COMPONENT_WIDTH, 25));
        theWindow.add(myInputField);
    }

    /**
     * @return the input from the user, will either be the selected radio button,
     * the text in the input field, or null if no input can be collected.
     */
    private String getInput() {
        String answer = "";
        if(myType == 1) {
            answer = findSelectedOption();
        }
        if(myType == 2) {
            answer = myInputField.getText();
        }
        if(answer.length() == 0) {
            return null;
        }
        return answer;
    }

    /**
     * Finds the selected option from the set of buttons in myButtons.
     * @return the text from the selected option.
     */
    private String findSelectedOption() {
        Iterator<AbstractButton> it = myButtons.getElements().asIterator();
        while(it.hasNext()) {
            AbstractButton b = it.next();
            if(b.isSelected()) {
                return b.getText();
            }
        }
        return "";
    }
}
