package controller;

import model.mazeElements.TriviaMaze;
import view.TMPanel;

public class Game implements Runnable {

    private Thread gameThread;

    // game FPS
    private final int fps = 25;
    // Player refresh speed multiplier
    private final int speedMultiplier = 3;

    private TMPanel myPanel;

    private PlayerController myPlayerController;


    public Game(final TriviaMaze theMaze, final TMPanel thePanel) {
        myPanel = thePanel;
        myPlayerController = new PlayerController(theMaze, myPanel.getPlayer(), myPanel.getKeys());
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
    }


    @Override
    public void run() {
        double interval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + interval;

        while (gameThread != null) {
            myPanel.update();
            myPlayerController.updatePlayer();
            myPlayerController.updateCurrentRoom();

            // delays the key press listener
            try {
                double remainingDrawTime = nextDrawTime - System.nanoTime();
                remainingDrawTime = remainingDrawTime / 1000000;
                Thread.sleep((long)remainingDrawTime);
                nextDrawTime += interval * speedMultiplier;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
