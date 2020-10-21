### Complete `Pearls` and `PearlUtil`
Add code to update the `MoveRecord` objects

### Synopsis
There is a grid consisting of various items, _cells_
There is also a _player_ pictured as a **raindrop**
Other types of cells are:
1. Small round _pearls_
2. Dark blocks _movables_
3. Red Pointy _spikes_
4. Gold rings, _portals_

Player can move in any direction, but will continue to move until they encounter a boundary
If the player goes off the edge of the grid, it wraps around to the other side

#### The goal is to collect all the pearls without getting stuck by the spikes

(It is OK to bump into the non-pointy side of the spikes)

Portals occur in pairs - going to one portal, the player will emerge from the portal going the same direction

Movable blocks can be pushed by the player

If _positive_ and _negative_ blocks are pushed together, they merge and disappear

If the player passes through a _gate_, that gate is closed and cannot be passed through again

##Provide all the backend logic for the state of the game

The grid is made up of a 2D array of cell objects.

Cell includes a **flag** as to whether the player is present in the cell.
This also includes row and column offsets that are used by portals to store the relative location of its companion.

### A suggested algorithm
1. When a move is initiated in a given direction, copy the affected cell states into a 1D array starting at the current
player position and working cell by cell in the current direction
(This would be a great place to implement recursion to see how long the player can move)
2. Pass the array to methods in PearlUtil that do the actual movement to states in the 1D array, now shifting them left
to right only within this 1D array
3. Copy the modified array back into the 2D grid, again starting at the original player location and workig in the
original direction of the move

Step 2 encapsulates **moveBlocks** and **movePlayer**

Check the method State.spikesAreDeadly to see if the player is impaled on the spikes

## Pearls class
Basic game play: **_`move()`_** method:
Basic game play takes place with `MoveRecord[]` method
* Moves the player in the indicated direction

The method `getStateSequence`:
This creates the 1D array
* Starts at the current players position
* Follows cell in given direction until boundary is reached
* _This will always satisfy `isValidForMoveBlocks`_

**When you get to the second portal in a pair, do not apply its' offset. This will return you to the first portal.**

1. Start with (row, column) at player location
2. While (row, column) is not a boundary state
    3. Add state at (row, column) to my list of states
    4. Update row and column to be the next cell in the given direction (possibly a portal jump)


The `move()` method does the following things:
1. Gets the state sequence array
2. [Creates a parallel array of MoveRecords]
3. Calls moveBlocks [passing in the MoveRecords array]