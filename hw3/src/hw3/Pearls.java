package hw3;
import api.*;

import java.util.ArrayList;

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
  private int[] playerPosition;

  private int numMoves;
  private int score;

  private boolean isAlive;
  private boolean didPortalJump;
  
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

    //  Get the current player position
    boolean found = false;
    while (!found)
    {
      for (int row = 0; row < grid.length; row++) {
        // Then, iterate through each column
        for (int column = 0; column < grid[row].length; column++) {
          // Iterate through each cell and increment pearls when found
          if (grid[row][column].isPlayerPresent()) {
            this.playerPosition = new int[]{row, column};
            found = true;
          }
        }
      }
    }

    this.numMoves = 0;
    this.score = 0;
    this.isAlive = true;
  }

  /**
   * Iterate through the grid, and returns the number of pearls left.
   * @return Number of Pearls Left
   */
  public int countPearls()
  {
    // Initialize number of pearls found to 0
    int numPearls = 0;

    // First, iterate through each row
    for (Cell[] cells : grid) {
      // Then, iterate through each column
      for (Cell cell : cells) {
        // Iterate through each cell and increment pearls when found
        if (cell.getState() == State.PEARL) {
          numPearls++;
        }
      }
    }

    return numPearls;
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
   * Returns the number of columns in the grid.
   * @return
   *   width of the grid
   */
  public int getColumns()
  {
    return grid[0].length;
  }

  public int getCurrentColumn()
  {
    return playerPosition[1];
  }

  public int getCurrentRow()
  {
    return playerPosition[0];
  }

  public int getMoves()
  {
    return this.numMoves;
  }

  // TODO: Check, have to get the doPortalJump in
  public int getNextColumn(int row, int col, Direction dir, boolean doPortalJump)
  {
    if (doPortalJump)
    {
      // TODO: Check to see if these are 0 when there's no portal
      int columnOffset = grid[row][col].getColumnOffset();
      return col + columnOffset;
    }
    else {
      return switch (dir) {
        case UP, DOWN -> col;
        case LEFT -> (col - 1 + getColumns()) % getColumns();
        case RIGHT -> (col + 1 + getColumns()) % getColumns();
      };
    }
  }

  // TODO: Check
  public int getNextRow(int row, int col, Direction dir, boolean doPortalJump)
  {
    if (doPortalJump)
    {
      // TODO: Check to see if these are 0 when there's no portal
      int rowOffset = grid[row][col].getRowOffset();
      return row + rowOffset;
    }
    else {
      return switch (dir) {
        case LEFT, RIGHT -> row;
        case UP -> (row - 1 + getRows()) % getRows();
        case DOWN -> (row + 1 + getRows()) % getRows();
      };
    }
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

  public int getScore()
  {
    return this.score;
  }

  public State[] getStateSequence(Direction dir)
  {
    boolean doPortalJump;
    boolean sequenceContainsMovable = false;

    int currentRow = getCurrentRow();
    int currentColumn = getCurrentColumn();
    int nextRow;
    int nextColumn;

    // Arraylist to hold the sequence for the time being
    ArrayList<State> statesList = new ArrayList<>();

    do
    {
      doPortalJump = grid[currentRow][currentColumn].getState() == State.PORTAL &&
              statesList.get(statesList.size() - 1) != State.PORTAL;

      // Add the current cell's state to the list
      statesList.add(grid[currentRow][currentColumn].getState());
      // Set the containsMovable variable to true when a movable block enters the sequence
      if (!sequenceContainsMovable)
      {
        sequenceContainsMovable = State.isMovable(grid[currentRow][currentColumn].getState());
      }

      nextRow = getNextRow(currentRow, currentColumn, dir, doPortalJump);
      nextColumn = getNextColumn(currentRow, currentColumn, dir, doPortalJump);

      // Check to see if the next tile keeps the sequence valid
      currentRow = nextRow;
      currentColumn = nextColumn;
    }
    while (!State.isBoundary(statesList.get(statesList.size() - 1), sequenceContainsMovable));

    // Convert into Array
    State[] states = new State[statesList.size()];
    for (int index = 0; index < statesList.size(); index++)
    {
      states[index] = statesList.get(index);
    }

    return states;
  }
  
  /**
   * Returns true if the game is over, false otherwise.  The game ends when all pearls
   * are removed from the grid or when the player lands on a cell with spikes.
   * @return
   *   true if the game is over, false otherwise
   */
  public boolean isOver()
  {
    return countPearls() == 0;
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
    State[] stateSequence;
    MoveRecord[] moveRecords;

    int originalPearls = countPearls();
    stateSequence = getStateSequence(dir);
    moveRecords = new MoveRecord[stateSequence.length];
    for (int i = 0; i < stateSequence.length; i++)
    {
      moveRecords[i] = new MoveRecord(stateSequence[i], i);
    }
    util.moveBlocks(stateSequence, moveRecords);
    int playerIndex = util.movePlayer(stateSequence, moveRecords, dir);

    // Handle Collisions
    if (State.spikesAreDeadly(stateSequence[playerIndex], dir))
    {
      this.isAlive = false;
    }

    setStateSequence(stateSequence, dir, playerIndex);
    score = score + (originalPearls - countPearls());
    this.numMoves++;
    return moveRecords;
  }

  public void setStateSequence(State[] states, Direction dir, int playerIndex)
  {
    int currentRow = playerPosition[0];
    int currentColumn = playerPosition[1];
    int nextRow;
    int nextColumn;
    boolean doPortalJump;

    // Remove the player from the grid
    grid[currentRow][currentColumn].setPlayerPresent(false);

    for (int index = 0; index < states.length; index++) {
      doPortalJump = grid[currentRow][currentColumn].getState() == State.PORTAL &&
              states[index - 1] != State.PORTAL;

      // Set the state of the current cell to the state at index
      grid[currentRow][currentColumn].setState(states[index]);

      // If the player is on this index, set the present flag to true
      if (index == playerIndex) {
        grid[currentRow][currentColumn].setPlayerPresent(true);
        this.playerPosition[0] = currentRow;
        this.playerPosition[1] = currentColumn;
      }
      nextRow = getNextRow(currentRow, currentColumn, dir, doPortalJump);
      nextColumn = getNextColumn(currentRow, currentColumn, dir, doPortalJump);

      currentRow = nextRow;
      currentColumn = nextColumn;
    }
  }

  public boolean won()
  {
    return isOver() && this.isAlive;
  }
  
  
  
  // TODO - everything else...
  public static void main(String[] args) {
    PearlUtil util = new PearlUtil();
    String test = "..@.o..@.#";
    State[] states = StringUtil.createFromString(test);
    MoveRecord[] records = new MoveRecord[states.length];
    for (int i = 0; i < states.length; i++)
    {
      records[i] = new MoveRecord(states[i], i);
    }
    StringUtil.printStateArray(states, 0);
    System.out.println();
    util.movePlayer(states, records, Direction.DOWN);
    System.out.println();

  }
  
}
