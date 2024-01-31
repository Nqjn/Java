
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JComponent;

/**
 * Minesweeper widget
 */
public class MinesWidget extends JComponent {

    private MinesGame game;
    private Point selected;
    private BufferedImage imageFlag;
    private BufferedImage imageSquare;


    /**
     * Default widget constructor.
     *
     * Creates a default game with 5x5 fields and 4 mines.
     */
    public MinesWidget() {
        this.game = new MinesGame(5, 5, 4);
    }
    

    /**
     * Constructor for the given (non-null) game.
     *
     * @param game
     * @throws NullPointerException if game is null.
     */
    public MinesWidget(MinesGame game) throws NullPointerException{
        if(game == null) throw new NullPointerException("The game is null!");
        this.game = game;
        
    }

    /**
     * Constructor for given plan size and mines number.
     *
     * It does not catch exceptions thrown by the game constructor.
     *
     * @param w width
     * @param h height
     * @param mines number of mines
     */
    public MinesWidget(int w, int h, int mines) {
            this.game  = new MinesGame(w,h, mines);
    }

    /**
     * Set coordinates of selected cell.
     *
     * Null value disables selection. The field is not highlighted if the
     * coordinates are outside the game plan.
     *
     * @param cell
     */
    public void setSelected(Point cell) {
        
    }

    /**
     * Returns the coordinates of the last selected field (or null).
     *
     * @return Point
     */
    public Point getSelected() {
        throw new UnsupportedOperationException("Funkce ještě není implementována.");
        
    }

    /**
     * Sets new game.
     *
     * @param game
     * @throws NullPointerException if game is null.
     */
    public void setGame(MinesGame game)  throws NullPointerException{
        if(game == null) throw new NullPointerException("The game is null!");
        this.game = game; 
        
    }

    /**
     * Returns the current game.
     *
     * @return MinesGame
     */
    public MinesGame getGame() {
        return this.game;
    }
    /**
     * Draws the game plan.
     *
     * The plan has a fixed box size. Covered fields are dark grey, uncovered
     * fields show the number of mines in the vicinity (except for the field
     * with 0 mines in the vicinity) or mine. The selected field is framed in
     * dark orange.
     *
     * @param g
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        try {
            imageFlag= ImageIO.read(getClass().getResource("flag.png"));
        } catch (IOException ex) {
            System.err.println("Cannot read Background.jpg");
        }
        
        try {
            imageSquare= ImageIO.read(getClass().getResource("square.png"));
        } catch (IOException ex) {
            System.err.println("Cannot read Background.jpg");
        }
        
        super.paintComponent(g);
        
        g.setColor(Color.BLACK);
        MinesPlan plan = game.getPlan();
        
        int w = this.getWidth() / plan.getWidth();
        int h = this.getHeight()/ plan.getHeight();
        int s = w < h ? w:h;
        
        for (int rows = 0; rows < plan.getWidth(); rows++) {
            for (int col = 0; col < plan.getHeight(); col++) {
                
                g.drawRect(rows*s, col*s, s, s);
                g.fillRect(rows*s, col*s, s, s);
                g.setColor(Color.DARK_GRAY);
//                g.drawImage(imageSquare, rows*s,  col*s, s, s, this);
                
            }
        }
    }
    
    /**
     * Set selected field from real pixel coordinates on widget
     * @param x_pix
     * @param y_pix 
     */
    public void selectPosition(int x_pix, int y_pix) {
        selected.setLocation(x_pix, y_pix);
        
    }
    
    /**
     * Uncover field on real pixel coordinates on widget
     * TODO
     * @param x_pix
     * @param y_pix 
     */
    public void uncoverPosition(int x_pix, int y_pix) {
        game.uncover(x_pix, y_pix);
    }

    /**
     * Flip marking on a field after real pixel coordinates on widget
     * TODO
     * @param x_pix
     * @param y_pix 
     */
    public void markingPosition(int x_pix, int y_pix) throws WrongActionException {
        game.switchMarked(y_pix, y_pix);
        
    }
    
    
    /**
     * Returns plan coordinates from pixel position on widget or null.
     * @param x_pix pixel x position on widget
     * @param y_pix pixel y position on widget
     * @return Point - plan coordinates or null if position outside of plan
     */
    private Point getCoordsFromPosition(int x_pix, int y_pix) {
        throw new UnsupportedOperationException("Funkce ještě není implementována.");
        

        
    }
    
    
    /**
     * Loads all images into image attributes or throws RuntimeException.
     */
    private void loadImages() {
    }
    
    

}
