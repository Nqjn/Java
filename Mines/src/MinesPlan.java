
import java.util.Arrays;

/**
 * Minesweeper game plan
 */
public class MinesPlan {

    private boolean[][] mines;
    private boolean[][] hidden;
    private boolean[][] marked;
    private int width;
    private int height;
///Array of mines

    /**
     * Default constructor - clear 2x2 plan.
     */
    public MinesPlan() {
        this.width = 2;
        this.height = 2;
        this.mines = new boolean[this.height][this.width];
        this.hidden = new boolean[this.height][this.width];
        this.marked = new boolean[this.height][this.width];
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.mines[i][j] = false;
                this.hidden[i][j] = true;
                this.marked[i][j] = false;

            }

        }

    }

    /**
     * Constructor for clear plan with given size.
     *
     * @param w width
     * @param h height
     * @throws BadNumberException if w or h is smaller than 2
     */
    public MinesPlan(int w, int h) {
        if (w < 2 || h < 2) {
            throw new BadNumberException("Bad size, must be at least 2x2. But it was " + w + "x" + h);
        }
        this.width = w;
        this.height = h;
        this.mines = new boolean[h][w];
        this.hidden = new boolean[h][w];
        this.marked = new boolean[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                this.mines[i][j] = false;
                this.hidden[i][j] = true;
                this.marked[i][j] = false;

            }

        }

    }

    /**
     * Removes all mines from the game plan.
     */
    public void clearAllMines() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.mines[i][j] = false;

            }

        }
    }

    /**
     * Removes all marks from the game plan.
     */
    public void clearAllMarks() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                this.marked[i][j] = false;

            }

        }
    }

    /**
     * Covers all fields on the game plan.
     */
    public void coverAll() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {

                this.hidden[i][j] = true;

            }

        }

    }

    /**
     * Uncovers all fields on the game plan.
     */
    public void uncoverAll() {
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
              
                    this.hidden[i][j] = false;

                

            }

        }

    }

    /**
     * Checks if there is a mine in the specified field.
     *
     * @return boolean
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public boolean isMineAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }
        return this.mines[y][x];

    }

    /**
     * Checks if the specified field is covered.
     *
     * @return boolean
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public boolean isCoveredAt(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new BadCoordsException();
        }
        
        
        
        return this.hidden[y][x] ;
    }

    /**
     * Checks if the specified field is marked.
     *
     * @return boolean
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public boolean isMarkedAt(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }
        return this.marked[y][x];
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    /**
     * Returns number of all mines in the game plan.
     *
     * @return int
     */
    public int getNumberOfMines() {
        int count = 0;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.mines[j][i] == true) {
                    count += 1;

                }

            }

        }
        return count;
    }

    /**
     * Returns the number of mines in the surrounding 8 fields.
     *
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public int getNumberOfMines(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }

        int count = 0;
        if (this.mines[y][x]) {
            count -= 1;

        }
        
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + j;
                int newY = y + i;

                if (newX >= 0 && newX < this.width && newY >= 0 && newY < this.height) {
                    if (this.mines[newY][newX]) {
                        count += 1;

                    }

                }

            }

        }
        return count;
    }

    /**
     * Returns number of covered fields in the game plan.
     *
     * @return int
     */
    public int getNumberOfCovered() {
        int count = 0;
        for (int i = 0; i < this.width; i++) {
            for (int j = 0; j < this.height; j++) {
                if (this.hidden[j][i]) {
                    count += 1;

                }

            }

        }
        return count;
    }

    /**
     * Sets new mine at given coordinates.
     *
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public void setMineAt(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }

        this.mines[y][x] = true;
    }

    /**
     * Uncovers given field
     *
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public void uncover(int x, int y) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }
      
          
        this.hidden[y][x] = false;

     

    }

    /**
     * Sets given field marked or unmarked
     *
     * @param x x-coord
     * @param y y-coord
     * @param marked marked state
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public void mark(int x, int y, boolean marked) {
        if (x < 0 || x >= this.width || y < 0 || y >= this.height) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
        }
        this.marked[y][x] = marked;

    }

}
