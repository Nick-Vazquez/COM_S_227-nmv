package hw3;
import api.Cell;
import api.Direction;
import api.MoveRecord;
import api.StringUtil;

/**
 * Basic game state and operations for a the puzzle game "Pearls", which
 * is a simplified version of "Quell".
 * @author smkautz
 */
public class Pearls
{
  /**
   * Two-dimensional array of Cell objects representing the 
   * grid on which the game is played.
   */
  private Cell[][] grid;
  
  /**
   * Instance of PearlUtil to be used with this game.
   */
  private PearlUtil util;
  
  // TODO - any other instance variables you need
  
  
  /**
   * Constructs a game from the given string description.  The conventions
   * for representing cell states as characters can be found in 
   * <code>StringUtil</code>.  
   * @param init
   *   string array describing initial cell states
   * @param givenUtil
   *   PearlUtil instance to use in the <code>move</code> method
   */
  public Pearls(String[] init, PearlUtil givenUtil)
  {
    grid = StringUtil.createFromStringArray(init);
    util = givenUtil;

    // TODO - any other initialization you need
  }
  
  /**
   * Returns the number of columns in the grid.
   * @return
   *   width of the grid
   */
  public int getColumns()
  {
    return grid[0].length;
  }
  
  /**
   * Returns the number of rows in the grid.
   * @return
   *   height of the grid
   */
  public int getRows()
  {
    return grid.length;
  }
  
  /**
   * Returns the cell at the given row and column.
   * @param row
   *   row index for the cell
   * @param col
   *   column index for the cell
   * @return
   *   cell at given row and column
   */
  public Cell getCell(int row, int col)
  {
    return grid[row][col];
  }
  
  /**
   * Returns true if the game is over, false otherwise.  The game ends when all pearls
   * are removed from the grid or when the player lands on a cell with spikes.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {
    // TODO
    return false;
  }
  
  /**
   * Performs a move along a state sequence in the given direction, updating the score, 
   * the move count, and all affected cells in the grid.  The method returns an 
   * array of MoveRecord objects representing the states in original state sequence before 
   * modification, with their <code>movedTo</code> and <code>disappeared</code>
   * status set to indicate the cell states' new locations after modification.  
   * @param dir
   *   direction of the move
   * @return
   *   array of MoveRecord objects describing modified cells
   */
  public MoveRecord[] move(Direction dir)
  {
    // TODO
    return null;
  }
  
  
  
  // TODO - everything else...
  
  
}
