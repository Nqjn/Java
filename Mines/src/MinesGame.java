
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Minesweeper game class
 */
public class MinesGame {

    private MinesPlan plan;
    static int STATE_PLAYING = 1;
    static int STATE_EXPLODED = 2;
    static int STATE_DONE = 3;
    Random rand = new Random();

    /**
     * Game constructor for new game
     *
     * @param w plan width (min. 2)
     * @param h plan height (min. 2)
     * @param mines number of mines (min. 1, max. w*h-1)
     * @throws BadNumberException if w, h or mines has a bad value
     */
    public MinesGame(int w, int h, int mines)  {
        if(w < 2 || h < 2) {
            throw new BadNumberException("Bad size, must be at least 2x2. But it was " + w + "x" + h);
        }
        
        this.plan = new MinesPlan(w, h);
        placeMines(mines);
    }

    /**
     * Game constructor for given plan
     *
     * @param plan ready to use game plan
     * @throws BadNumberException if the plan is smaller than 2x2 or if there is
     * no mine in the plan
     * @throws NullPointerException if plan is null
     */
    public MinesGame(MinesPlan plan) {
        if (plan.getWidth() < 2 || plan.getHeight() < 2 || plan.getNumberOfMines() == 0) {
            throw new BadNumberException("Bad size, must be at least 2x2. But it was " + plan.getWidth() + "x" + plan.getHeight());
        }
        this.plan = plan;
      
    }

    public MinesPlan getPlan() {
        return plan;

    }

    /**
     * Plan setter
     *
     * @param plan ready to use game plan
     * @throws BadNumberException if the plan is smaller than 2x2 or if there is
     * no mine in the plan
     * @throws NullPointerException if plan is null
     */
    public void setPlan(MinesPlan plan) {
        if (plan.getWidth() < 2 || plan.getHeight() < 2 ||plan.getNumberOfMines() == 0) {
            throw new BadNumberException("Bad size, must be at least 2x2. But it was " + plan.getWidth() + "x" + plan.getHeight());
        }
        this.plan = plan;
    }

    /**
     * Returns plan state
     *
     * @retval STATE_PLAYING Game is playable
     * @retval STATE_EXPLODED A player has uncovered a minefield
     * @retval STATE_DONE Player has uncovered all squares without mines
     */
    public int getState() {
        
         for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (plan.isMineAt(i, j) && !plan.isCoveredAt(i,j )) {
                    return STATE_EXPLODED;
                    

                }

            }

        }
         
       for (int i = 0; i < plan.getWidth(); i++) {
            for (int j = 0; j < plan.getHeight(); j++) {
                if (!plan.isMineAt(i, j) && plan.isCoveredAt(i,j )) {
                    return STATE_PLAYING;
                    

                }

            }

        }
       return STATE_DONE;
        
    }

    /**
     * Change marking state of an uncovered field during game.
     *
     * Mark unmarked, unmark marked. It will not make the change if the game is
     * not playable.
     *
     * @param x x-coord
     * @param y y-coord
     * @throws WrongActionException if field is uncovered
     * @throws BadCoordsException if coordinates are out of plan
     */
    public void switchMarked(int x, int y) throws WrongActionException  {
        if(!plan.isCoveredAt(x, y)) throw new WrongActionException("Field is uncovered mus be covered");
        if(x >= plan.getWidth()  || x < 0  || y >= plan.getHeight() || y < 0) throw new BadCoordsException("Coords are out of field");
        if (getState() == 1) {
            
        
        
        boolean markAt = plan.isMarkedAt(x, y);
        plan.mark(x, y, !markAt);
        }

    }

    /**
     * Uncover field during game.
     *
     * Sets the field as uncovered if the game is playable and the field is not
     * marked. Otherwise, no change is made. At 0 mines in the vicinity, it
     * triggers a recursive uncovering.
     *
     * @param x x-coord
     * @param y y-coord
     * @throws BadCoordsException if the coordinates are outside the game plan.
     */
    public void uncover(int x, int y) {
        if (x < 0 || x >= plan.getWidth() || y < 0 || y >= plan.getHeight()) {
            throw new BadCoordsException("out of bound x:" + x + "y:" + y );
           
        }
        
        if(plan.isMarkedAt(x, y)){
            return;
        }
        if (plan.isMineAt(x, y)) {
            plan.uncover(x, y);
        }
        
        if (plan.getNumberOfMines(x,y) == 0) {
            uncoverZero(x, y);
           
        }else{
            plan.uncover(x, y);
                
        }
        
       
             
            
         

    }
    

    /**
     * Recursive method for uncovering fields in the vicinity of the free field.
     *
     * It does nothing if the field is uncovered or marked or off the game plan.
     * It uncovers the field and recursively starts uncovering in the
     * surrounding eight fields if there are no mines in the vicinity. It only
     * uncovers the field and does no recursion if there are any mines in the
     * vicinity.
     *
     * @param x x-coord
     * @param y y-coord
     */
    private void uncoverZero(int x, int y) {
        if (x < 0 || x >= plan.getWidth() || y < 0 || y >= plan.getHeight()) {
            return;
        }
        

        if (!plan.isCoveredAt(x, y) || plan.isMineAt(x, y)) {
            return;
        }
        
  
        
        plan.uncover(x, y);

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int newX = x + j;
                int newY = y + i;
                uncoverZero(newX, newY);

                
            }
        }

    }

    

           

    /**
     * Randomly place a given number of mines in the covered fields in the plan.
     *
     * @param count Mines count, must be at least 1
     * @throws BadNumberException if the number of mines is less than 1 or
     * greater than the number of covered fields
     */
    private void placeMines(int count) {
        Set<String> pos = new HashSet();

        while (pos.size() < count) {
            int x = rand.nextInt(plan.getWidth());
            int y = rand.nextInt(plan.getHeight());
            String post = x + "," + y;

            if (!pos.contains(post)) {
                pos.add(post);
                plan.setMineAt(x, y);

            }

        }

    }

}
