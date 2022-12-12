/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package controller;

import view.TMPanel;

/**
 * Represents the basic game logic for executing frames.
 *
 * @author AJ Garica, Refactored by Kyler Robison
 */
public class Game implements Runnable {
    /**
     * Singleton instance of Game.
     */
    private static Game instance;
    /**
     * Framerate of the game.
     */
    private static final int FPS = 25;
    /**
     * Speed multiplier.
     */
    private static final int SPEED_MULTIPLIER = 3;
    /**
     * Thread object that executes the game.
     */
    private Thread gameThread;

    private Game() {}

    /**
     * @return the singleton instance of Game. Creates a new one if one does not yet exist.
     */
    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    /**
     * Resets the instance by setting the field to null.
     * Next time getInstance() is called, a new instance will be created.
     * Also kills the currently running thread.
     */
    public static void resetInstance() {
        if(instance != null) {
            instance.kill();
        }
        instance = null;
    }

    /**
     * Kills the currently running Thread.
     */
    private void kill() {
        gameThread = null;
    }

    /**
     * Called to begin the game. Creates a new thread and starts this process in it.
     */
    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * The main game method. Loops for each frame at a rate determined by the framerate.
     * Makes necessary updates to entities in the game every frame.
     */
    @Override
    public void run() {
        double interval = 1000000000D / FPS;
        double nextDrawTime = Math.abs(System.nanoTime() + interval);

        while (gameThread != null) {
            TMPanel.getInstance().frameUpdate();
            PlayerController.getInstance().frameUpdate();

            // delays the key press listener
            try {
                double remainingDrawTime = Math.abs(nextDrawTime - System.nanoTime());
                remainingDrawTime = remainingDrawTime / 1000000;
                //noinspection BusyWait
                Thread.sleep((long)remainingDrawTime);
                nextDrawTime += interval * SPEED_MULTIPLIER;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
