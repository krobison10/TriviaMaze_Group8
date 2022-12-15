/*
 * TCSS 360 Prof. Tom Capaul
 * Trivia Maze, Group 8
 * Fall 2022
 */

package view;

import java.awt.*;

import javax.swing.*;

/**
 * The panel of the game board.
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
    private static TMPanel instance = null;


    /**
     * Properties of trivia maze
     */
    private TMPanel() {
        this.setPreferredSize(new Dimension(GAME_SIZE, GAME_SIZE));
        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
    }

    /**
     * @return a new TriviaMazeUI if one has not yet been instantiated.
     * (semi-singleton)
     */
    public static TMPanel getInstance() {
        if (instance == null) {
            instance = new TMPanel();
        }
        return instance;
    }

    /**
     * Resets the instance by setting the field to null.
     * Next time getInstance() is called, a new instance will be created.
     */
    public static void resetInstance() {
        instance = null;
    }

    /**
     * Executes updates for each frame.
     */
    public void update() {
        requestFocus(); // need this to keep key listener working
        repaint();
    }

    /**
     * Called using repaint()
     * Draws models to the UI
     * @param theG the <code>Graphics</code> object to protect
     */
    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        // Casts graphics to 2D graphics for more functionality
        Graphics2D g2 = (Graphics2D) theG;

        GraphicDrawer.drawTiles(g2);
        GraphicDrawer.drawPlayer(g2);

        g2.dispose();
    }
}
