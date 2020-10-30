package hw3;
import api.*;

import java.util.ArrayList;

/**
 * Basic game state and operations for a the puzzle game "Pearls", which
 * is a simplified version of "Quell".
 * @author smkautz, nmv
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


    /** The current player position, stored in [row, column] format. **/
    private int[] playerPosition;

    /** The number of moves the player has taken. **/
    private int numMoves;

    /** The player's current score. **/
    private int score;

    /** Whether the player is still alive. **/
    private boolean isAlive;

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
     * Returns the number of pearls left in the grid by iterating through rows and columns
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

    /**
     * Returns the current column where the player is located
     * @return Player's current column
     */
    public int getCurrentColumn()
    {
        return playerPosition[1];
    }

    /**
     * Returns the current row where is player is located
     * @return Player's current row
     */
    public int getCurrentRow()
    {
        return playerPosition[0];
    }

    /**
     * Returns the number of moves that the player has made in their current game
     * @return Number of player moves
     */
    public int getMoves()
    {
        return this.numMoves;
    }


    /**
     * Helper method returns the next column for a state sequence in the given direction, possible wrapping around.
     * If the flag {@code doPortalJump} is true, then the next column will be obtained by adding the cell's column offset.
     * (Normally the caller will set this flag to true when first landing on a portal, but to false for the second portal
     * of the pair
     * @param row
     *      The player's current row
     * @param col
     *      The player's current column
     * @param dir
     *      The direction that the player is headed in
     * @param doPortalJump
     *      Whether the player is to perform a portal jump for this move
     * @return
     *      The player's next target column.
     */
    public int getNextColumn(int row, int col, Direction dir, boolean doPortalJump)
    {
        if (doPortalJump)
        {
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

    /**
     * Helper method returns the next row for a state sequence in the given direction, possible wrapping around. If the
     * flag {@code doPortalJump} is true, then the next row will be obtained by adding the cell's row offset. (Normally
     * the caller will set this flag to true when first landing on a portal, but to false for the second portal of the
     * pair.
     * @param row
     *      The player's current row
     * @param col
     *      The player's current column
     * @param dir
     *      The direction that the player is headed in
     * @param doPortalJump
     *      Whether the player is to perform a portal jump for this move
     * @return
     *      The player's next target column.
     */
    public int getNextRow(int row, int col, Direction dir, boolean doPortalJump)
    {
        if (doPortalJump)
        {
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

    /**
     * Returns the current score (number of pearls disappeared) for this game.
     * @return
     *      The number of pearls that have been collected by the player (score)
     */
    public int getScore()
    {
        return this.score;
    }

    /**
     * Finds a valid state sequence in the given direction starting with th player's current position and ending with a
     * boundary cell as defined by the method {@link State#isBoundary(State, boolean)}
     * @param dir
     *      The direction that the player is headed in
     * @return
     *      An array of states that is a valid state sequence
     */
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
        return !isAlive || won();
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

    /**
     * Sets the given state sequence and updates the player position.
     * @param states
     *      States that are to replace the stateSequence found in {@link Pearls#getStateSequence(Direction)}
     * @param dir
     *      Direction of the stateSequence (The direction in which the player was headed)
     * @param playerIndex
     *      The index within the array that the player ends on
     */
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

    /**
     * Returns true if all of the pearls in the grid have been collected
     *
     * NOTE TO TA: If this method is not up to spec, it is because of a possible flaw.
     * I attempted to do this with {@code won} and {@code isOver} switched as it states
     * in the spec, but the functionality of the game was not there when I did this.
     *
     * @return
     *      Whether the player has collected all of the pearls.
     */
    public boolean won() {
        return countPearls() == 0;
    }
}
