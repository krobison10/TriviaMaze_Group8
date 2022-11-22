package view;

import controller.KeyInput;
import model.mazeElements.Player;

import javax.swing.*;
import java.awt.*;

public class TMPanel extends JPanel implements Runnable{

    public static final int TILE_SIZE = 24;

    public static final int NUM_TILES = 31;

    // game size
    public static final int GAME_SIZE = NUM_TILES * TILE_SIZE;
    // key inputs
    private KeyInput keys = new KeyInput();
    // Thread to contain the maze
    private Thread gameThread;

    // Player instance
    private Player player = new Player(this, keys);
    // game FPS
    private final int fps = 25;
    // Player refresh speed multiplier
    private final int speedMultiplier = 3;
    // maze instance used later to prevent multiple games from running
    private static TMPanel triviaMazeInstance = null;

    public final GraphicDrawer graphicDrawer = new GraphicDrawer(player);

    /**
     * Properties of trivia maze
     */
    private TMPanel() {
        this.setPreferredSize(new Dimension(GAME_SIZE + 300, GAME_SIZE));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
    }

    /**
     * Returns a new TriviaMazeUI is one has not yet been instantiated.
     * (emi-singleton)
     * @return
     */
    public static TMPanel getTriviaMaze() {
        if (triviaMazeInstance == null) {
            triviaMazeInstance = new TMPanel();
        }
        return triviaMazeInstance;
    }

    /**
     * Begins trivia maze thread
     */
    public void BeginTriviaMaze() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Updates player location in the game
     */
    public void updateGame() {
        player.updatePlayer();
    }

    /**
     * Keeps thread running.
     * Tracks sleep time between key presses for player movement.
     * Sleep time is important so movement is manageable.
     */
    @Override
    public void run() {
        double interval = 1000000000 / fps;
        double nextDrawTime = System.nanoTime() + interval;

        while (gameThread != null) {
            requestFocus(); // need this to keep key listener working
            updateGame();
            repaint();

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

    /**
     * Called using repaint()
     * Draws models to the UI
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Casts graphics to 2D graphics for more functionality
        Graphics2D g2 = (Graphics2D) g;

        graphicDrawer.drawTiles(g2);
        graphicDrawer.drawPlayer(g2);

        g2.dispose();
    }
}
