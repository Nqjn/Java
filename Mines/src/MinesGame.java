/**
 * Minesweeper game class
 */
public class MinesGame {
    private MinesPlan plan ;
    int STATE_PLAYING = 1;
    int STATE_EXPLODED = 2;
    int STATE_DONE = 3;
    
    
    

   /**
    * Game constructor for new game
    * 
    * @param        w       plan width (min. 2)
    * @param        h       plan height (min. 2)
    * @param        mines   number of mines (min. 1, max. w*h-1)
    * @throws       BadNumberException  if w, h or mines has a bad value
    */
   public MinesGame(int w, int h, int mines) {
    this.plan = new MinesPlan (2, 2);
       
   }

   /**
    * Game constructor for given plan
    * 
    * @param        plan    ready to use game plan
    * @throws       BadNumberException    if the plan is smaller than 2x2 or if there is no mine in the plan
    * @throws       NullPointerException  if plan is null
    */
   public MinesGame(MinesPlan plan) {
       this.plan = new MinesPlan();
   }
   
   public MinesPlan getPlan()  {
       return plan;
       
   }
   



   /**
    * Plan setter
    * 
    * @param        plan    ready to use game plan
    * @throws       BadNumberException     if the plan is smaller than 2x2 or if there is no mine in the plan
    * @throws       NullPointerException   if plan is null
    */
   
   public void setPlan(MinesPlan plan)  {
       this.plan = plan;
   }


   /**
    * Returns plan state
    *  
    * @retval  STATE_PLAYING   Game is playable
    * @retval  STATE_EXPLODED  A player has uncovered a minefield
    * @retval  STATE_DONE      Player has uncovered all squares without mines
    */
   public int getState()  {
       return 0;
       
   }


   /**
    * Change marking state of an uncovered field during game.
    * 
    * Mark unmarked, unmark marked. It will not make the change if the game
    * is not playable.
    * 
    * @param  x   x-coord
    * @param  y   y-coord
    * @throws WrongActionException   if field is uncovered
    * @throws BadCoordsException     if coordinates are out of plan
    */
   public void switchMarked(int x, int y) {
       boolean markAt =plan.isMarkedAt(x, y);
       plan.mark(x, y, markAt);
      
       
   }


   /**
    * Uncover field during game.
    * 
    * Sets the field as uncovered if the game is playable and the field is
    * not marked. Otherwise, no change is made. At 0 mines in the vicinity,
    * it triggers a recursive uncovering.
    * 
    * @param x x-coord
    * @param y y-coord
    * @throws BadCoordsException   if the coordinates are outside the game plan.
    */
   public void uncover(int x, int y) {
       if (plan.getNumberOfMines() == 0) {
           plan.uncoverAll();
       }
       if (!plan.isMarkedAt(x, y)) {
           plan.uncover(x, y);
       }
       
   }
   /**
    * Recursive method for uncovering fields in the vicinity of the free
    * field.
    * 
    * It does nothing if the field is uncovered or marked or off the game
    * plan. It uncovers the field and recursively starts uncovering in the
    * surrounding eight fields if there are no mines in the vicinity. It only
    * uncovers the field and does no recursion if there are any mines in the
    * vicinity.
    * 
    * @param x x-coord
    * @param y y-coord
    */
   private void uncoverZero(int x, int y)  {
   }

   /**
    * Randomly place a given number of mines in the covered fields in the
    * plan.
    * 
    * @param  count Mines count, must be at least 1
    * @throws BadNumberException  if the number of mines is less than 1 or
    * greater than the number of covered fields
    */
   private void placeMines(int count)  {
       
  
       
   }

}
