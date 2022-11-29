/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import javax.swing.*;

import controller.KeyInput;

/**
 *
 *
 * @author AJ Garcia
 */
public class TMPanel extends JPanel{

    /**
     * Size of the game tiles in pixels.
     */
    public static final int TILE_SIZE = 24;
    /**
     * The width and height dimension of the game in terms of tiles.
     */
    public static final int NUM_TILES = 31;
    /**
     * The size of the game board
     */
    public static final int GAME_SIZE = NUM_TILES * TILE_SIZE;
    /**
     * Singleton maze instance used later to prevent multiple games from running
     */
    private static TMPanel triviaMazeInstance = null;
    /**
     *
     */
    private final KeyInput keys;


    /**
     * Properties of trivia maze
     */
    private TMPanel() {
        //Create singleton instance
        new GraphicDrawer();

        keys = new KeyInput();

        this.setPreferredSize(new Dimension(GAME_SIZE, GAME_SIZE));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.addKeyListener(keys);
        this.setFocusable(true);
    }

    /**
     * Returns a new TriviaMazeUI if one has not yet been instantiated.
     * (semi-singleton)
     * @return
     */
    public static TMPanel getTriviaMaze() {
        if (triviaMazeInstance == null) {
            triviaMazeInstance = new TMPanel();
        }
        return triviaMazeInstance;
    }

    /**
     * Executes updates for each frame.
     */
    public void frameUpdate() {
        requestFocus(); // need this to keep key listener working
        repaint();
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

        GraphicDrawer.instance.drawTiles(g2);
        GraphicDrawer.instance.drawPlayer(g2);

        g2.dispose();
    }

    public KeyInput getKeys() {
        return keys;
    }
}
