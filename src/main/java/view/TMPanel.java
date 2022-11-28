package view;

import controller.Collision;
import controller.KeyInput;

import javax.swing.*;
import java.awt.*;

public class TMPanel extends JPanel{

    public static final int TILE_SIZE = 24;

    public static final int NUM_TILES = 31;

    public static final int GAME_SIZE = NUM_TILES * TILE_SIZE;

    private final KeyInput keys;

    // maze instance used later to prevent multiple games from running
    private static TMPanel triviaMazeInstance = null;



    /**
     * Properties of trivia maze
     */
    private TMPanel() {
        //Create singleton instance
        new GraphicDrawer();

        keys = new KeyInput();

        this.setPreferredSize(new Dimension(GAME_SIZE + 300, GAME_SIZE));
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

    public void update() {
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
