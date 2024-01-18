
import java.util.Arrays;
import java.util.Random;

/**
 * Minesweeper game plan
 */
public class MinesPlan {

    Random rand = new Random();
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

    }

    /**
     * Constructor for clear plan with given size.
     *
     * @param w width
     * @param h height
     * @throws BadNumberException if w or h is smaller than 2
     */
    public MinesPlan(int w, int h){
        if (w < 2 || h < 2) {
            throw new BadNumberException("Bad size, must be at least 2x2. But it was " + width + "x" + height);
        }
        this.width = w;
        this.height = h;
        this.mines = new boolean[h][w];
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                this.mines[j][i] = false;
                this.hidden[j][i] = false;
                this.marked[j][i] = false;

            }

        }

    }

    /**
     * Removes all mines from the game plan.
     */
    public void clearAllMines() {
        Arrays.fill(this.mines, false);

    }

    /**
     * Removes all marks from the game plan.
     */
    public void clearAllMarks() {
        Arrays.fill(this.marked, false);
    }

    /**
     * Covers all fields on the game plan.
     */
    public void coverAll() {
        for (int i = 0; i < hidden.length; i++) {
            for (int j = 0; j < hidden.length; j++) {
                if (hidden[i][j] == false) {
                    hidden[i][j] = true;

                }

            }

        }

    }

    /**
     * Uncovers all fields on the game plan.
     */
    public void uncoverAll() {
        for (int i = 0; i < hidden.length; i++) {
            for (int j = 0; j < hidden.length; j++) {
                if (hidden[i][j] == true) {
                    hidden[i][j] = false;

                }

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
            throw new BadCoordsException("Bad size, must be at least 2x2. But it was " + width + "x" + height);
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
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new BadCoordsException("Bad size, must be at least 2x2. But it was " + width + "x" + height);
        }
        return this.hidden[y][x];
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
            throw new BadCoordsException("Bad size, must be at least 2x2. But it was " + width + "x" + height);
        }
        return this.marked[y][x];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Returns number of all mines in the game plan.
     *
     * @return int
     */
    public int getNumberOfMines() {
        int count = 0;
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines.length; j++) {
                if (hidden[i][j] == true) {
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
        int count = 0;
        for (int i = 0; i < mines.length; i++) {
            for (int j = 0; j < mines.length; j++) {
                if (mines[(x + j) - 1][(y + i) - 1] == true) {
                    count += 1;

                }
                return count;

            }

        }
        return 0;
    }

    /**
     * Returns number of covered fields in the game plan.
     *
     * @return int
     */
    public int getNumberOfCovered() {
        int count = 0;
        for (int i = 0; i < hidden.length; i++) {
            for (int j = 0; j < hidden.length; j++) {
                if (hidden[i][j] == true) {
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

        for (int i = 0; i < 10; i++) {
            x = rand.nextInt(this.width);
            y = rand.nextInt(this.height);
            this.mines[y][x] = true;
        }

    }

    /**
     * Uncovers given field
     *
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are off the game plan
     */
    public void uncover(int x, int y) {
        hidden[y][x] = false;

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
        this.marked[y][x] = true;
    }

}
