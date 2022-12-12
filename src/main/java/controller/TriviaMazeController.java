/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

import model.mazeElements.*;
import model.questions.Question;
import org.apache.commons.io.FilenameUtils;
import view.BuildUI;
import view.GraphicDrawer;
import view.SidebarManager;
import view.TMPanel;

/**
 * Main controller class that handles initialization of game and central functions.
 *
 * @author Kyler Robison
 */
public class TriviaMazeController {
    /**
     * Singleton variable.
     */
    private static TriviaMazeController instance;
    /**
     * Indicates whether sounds should be played or not
     */
    private boolean soundsEnabled;

    /**
     * Initializes and starts the game.
     */
    private TriviaMazeController() {
        instance = this;
        //Initial value for whether sounds will be played
        soundsEnabled = false;
        start();
    }

    /**
     * Global access point to the instance of TriviaMazeController.
     */
    public static TriviaMazeController getInstance() {
        if(instance == null) {
            instance = new TriviaMazeController();
        }
        return instance;
    }

    private static File validateFilename(final File theFile) {
        var file = theFile;
        if (!FilenameUtils.getExtension(file.getName()).equalsIgnoreCase("mze")) {
            file = new File(theFile.getParentFile(),
                    FilenameUtils.getBaseName(theFile.getName())+".mze");
        }
        return file;
    }
    /**
     * Creates a Game object and starts the process.
     * @param fromSave indicates whether the game is fresh or from a save.
     */
    public void startNewGame(final boolean fromSave) {
        if(!fromSave) {
            new TriviaMaze(5, 5, "CS_trivia_questions.db");
        }
        Game.getInstance().start();
    }

    /**
     * Fresh restarts the whole game.
     */
    public void restart() {
        //Kill the main window
        BuildUI.getInstance().window().dispose();

        //Wipe old instances of singletons in no specific order
        Game.resetInstance();
        GraphicDrawer.resetInstance();
        SidebarManager.resetInstance();
        BuildUI.resetInstance();
        TMPanel.resetInstance();
        PlayerController.resetInstance();

        //Call start to go back through the game initialization
        start();
    }

    /**
     * Saves the game to the selected file.
     * @param theFile the file to save to.
     * @return true if the save was successful, false otherwise.
     */
    public boolean save(final File theFile) {
        boolean successful = false;
        File file = validateFilename(theFile);
        try (FileOutputStream fout = new FileOutputStream(file.getPath())) {
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(TriviaMaze.getInstance());
            out.flush();
            out.close();

            successful = true;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return successful;
    }

    /**
     * Loads the game from the selected file.
     * @param theFilePath the file to load from.
     * @return true if the load was successful, false otherwise.
     */
    public boolean load(final String theFilePath) {
        boolean successful = false;
        try(FileInputStream fin = new FileInputStream(theFilePath)) {
            ObjectInputStream in = new ObjectInputStream(fin);
            TriviaMaze.setInstance((TriviaMaze) in.readObject());
            successful = true;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return successful;
    }

    /**
     * Processes the answer attempt and executes necessary side effects.
     * Makes a call to TileManager to update the door tile. Updates the
     * door state in model. And calls the algorithm to check whether
     * there is still a path to the exit. Ends the game if there is no
     * longer a path.
     * @param theQuestion the Question the user is answering.
     * @param theAnswer the answer that the user submitted.
     */
    public void processAnswerAttempt(final Question theQuestion, final String theAnswer) {
        String correctAnswer = theQuestion.getAnswer();
        Door door = TriviaMaze.getInstance().getDoor(theQuestion);
        if(correctAnswer.equalsIgnoreCase(theAnswer.trim())) {
            door.setState(DoorStates.OPENED);
            playSound(Sounds.DING);
        }
        else {
            door.setState(DoorStates.BLOCKED);
            playSound(Sounds.BUZZ);
        }

        TriviaMaze.getInstance().tileManager().updateDoorTile(door);
        //Refresh sidebar
        SidebarManager.getInstance().updateForCurrentRoom();

        //Check if the game is still winnable
        if(!TriviaMaze.getInstance().existsPathToExit()) {
            gameLost();
        }
    }

    /**
     * @return true if sounds are enabled, false otherwise.
     */
    public boolean soundsEnabled() {
        return soundsEnabled;
    }

    /**
     * Sets the condition for whether the class will play sounds or not.
     * @param theCondition the new value.
     */
    public void setSoundsEnabled(final boolean theCondition) {
        soundsEnabled = theCondition;
    }

    /**
     * Tells the sidebar manager to update its display for the current room.
     */
    void enteredNewRoom() {
        SidebarManager.getInstance().updateForCurrentRoom();
    }

    /**
     * Shows a message dialogue and then restarts the game.
     */
    void gameWon() {
        playSound(Sounds.WINNER);
        JOptionPane.showMessageDialog(BuildUI.getInstance().window(), "You Win!");
        restart();
    }

    /**
     * Shows a message dialogue and then restarts the game.
     */
    void gameLost() {
        JOptionPane.showMessageDialog(TMPanel.getInstance(), "Game over :(");
        restart();
    }

    /**
     * Checks if the player is in a new room by comparing the new room passed into this
     * method with the room object currently stored in the player. If the room is new,
     * updates the players room object.
     * @param theRoom the current room from controller to check against the player's saved
     *                room.
     * @return True if the room is a new room, false otherwise.
     */
    boolean playerInNewRoom(final Room theRoom) {
        boolean newRoom = theRoom != TriviaMaze.getInstance().player().getCurrentRoom();
        if(newRoom) {
            TriviaMaze.getInstance().player().setCurrentRoom(theRoom);
        }
        return newRoom;
    }

    /**
     * Starts the game by building the starting window.
     */
    private void start() {
        BuildUI.getInstance().buildFrame();
    }

    /**
     * Plays a sound in a separate thread to not block the flow of program execution.
     * @param theSound the sound to be played.
     */
    private void playSound(final Sounds theSound) {
        if(soundsEnabled) {
            Thread task = new Thread(() -> {
                try {
                    String filename = theSound.name().toLowerCase() + ".wav";
                    AudioInputStream aInputStream = AudioSystem.getAudioInputStream(new File
                            ("../TriviaMaze_Group8/src/main/resources/sounds/" + filename));

                    Clip sound = AudioSystem.getClip();
                    sound.open(aInputStream);
                    sound.start();

                    Thread.sleep(2000);

                    sound.stop();
                    sound.close();
                    aInputStream.close();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            task.start();
        }
    }
}
