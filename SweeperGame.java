/**
 * TCSS 143
 * Instructor: Raghavi Sakpal
 * Sweeper Game
 * Kyler Robsion
 */
import java.util.Arrays;
/**
 * Creates a gameboard object, along with many other functions. Creates and uses 
 * a 2d array to assist the driver program in running the sweeper game
 */
public class SweeperGame 
{
    private int boardHeight;
    private int boardWidth;
    private int totalMoves;
    private char[][] gameBoard;
    private int treasureX;
    private int treasureY;
    private boolean found;

    /**
     * Receives two integer parameters as the user�s input for the height (number of rows) and width (numbers of columns) of the grid where the treasure is buried.
     * @param height user defined height of gameboard
     * @param width user defined width of gameboard
     */
    public SweeperGame (int height, int width)
    {
        if(height <= 1 || width <= 1 || height > 30 || width > 30)
        {
            throw new IllegalArgumentException("Board size too large or small");
        }
        boardHeight = height;
        boardWidth = width;
        treasureX = (int)(Math.random()*(boardWidth));
        treasureY = (int)(Math.random()*(boardHeight));
        gameBoard = new char[boardHeight][boardWidth];
        initializeGameBoard();
        //gameBoard[treasureY][treasureX] = 'T'; //Uncomment this line to see treasure location throughout the game, for testing purposes
        totalMoves = 0;
        found = false;
    }
    /**
     * receives the x coordinate and y coordinate to be searched and returns true if the
     * values are within the array indices range, false otherwise (used to check that these indices will not cause
     * an error when applied to the array)
     * @param x current x value
     * @param y current y value
     * @return true if in bounds
     */
    public boolean checkOutOfBounds(int x, int y)
    {
        if(y < (gameBoard.length) && x < (gameBoard[0].length) ){return true;}
        else{return false;}
    }
    /**
     * receives the x coordinate and y coordinate to be searched and returns true if the space has already been searched, false otherwise. 
     * @param x current x coordinate
     * @param y current y coordinate
     * @return false if space is empty or has the unfound treasure, true if else
     */
    public boolean beenSwept(int x, int y)
    {
        if(gameBoard[y][x] == ' ' || gameBoard[y][x] == 'T')
        {
            return false;
        }
        else{return true;}
    }
    /**
     *Receives the x coordinate and y coordinate to be searched returns true if the treasure is found,
     *false otherwise and sets the found field to true. If the treasure is NOT found digSand also sets the
     *gameBoard array at the received x coordinate and y coordinate location to display the Manhattan
     *distance to the treasure. Increment the number of moves taken if the treasure has not been found and the
     *space has not been previously searched.
     * @param x current x value
     * @param y current y value
     * @return false to indicate tresure isnt found and to continue game or true to indicate game is over and break the loop
     */
    public boolean digSand(int x, int y)
    {
        totalMoves++;
        if(treasureFound(x,y))
        {
            found = true;
            return true;
        }
        else
        {
            gameBoard[y][x] = getManhattanDistance(x, y);
            return false;
        }
    }
    /**
     * Receives the x coordinate and y coordinate to be searched and returns true if the treasure is found there, false otherwise.
     * @param x current x coordinate
     * @param y current y coordinate
     * @return true if treasure found
     */
    public boolean treasureFound(int x, int y)
    {
        if(x == treasureX && y == treasureY){
            gameBoard[treasureY][treasureX] = 'T';
            return true;
        }
        else{return false;}
    }
    /**
     * Returns the manhattan distance to the treasure, if the distance is greater than 9
     * and can't be converted to a char, returns O to represent an empty hole, enables larger game
     * boards with the single-char system
     * @param x value of current point
     * @param y value of current point
     * @return manhattan distance between current point and the treasure or 'O'
     */
    public char getManhattanDistance(int x, int y)
    {
        int distance = Math.abs(treasureY - y) + Math.abs(treasureX - x);
        if(distance > 9) {return 'O';}
        else {return Character.forDigit(distance, 10);}
    }
    /**
     * Method that returns total number of moves
     * @return total number of moves
     */
    public int getTotalMoves()
    {
        return totalMoves;
    }
    /**
     * returns board height
     * @return the board height
     */
    public int getBoardHeight()
    {
        return boardHeight;
    }
    /**
     * returns board width
     * @return the board width
     */
    public int getBoardWidth()
    {
        return boardWidth;
    }
    /**
     * When called, prints the gameBoard array to the console as a grid (acts as toString method for the game)
     * Also prints out a coordinate legend to help the user visualize the coordinate system
     */
    public void printArray()
    {
        //Initialize an array to serve as the labels for the grid, will match the width of gameBoard
        int labelArray[] = new int[boardWidth];
        int number = 0;
        for(int i = 0; i < boardWidth; i++)
        {
            labelArray[i] = number;
            number++;
        }
        for(int row = boardHeight - 1; row >= 0; row--)
        {
            if(row > 9){
                System.out.println(row + Arrays.toString(gameBoard[row]));
            }
            else
            {
                System.out.println(" " + row + Arrays.toString(gameBoard[row]));
            }
        }
        if(boardHeight < 11)
        {
            System.out.println("  " + Arrays.toString(labelArray));
        }
        else
        {
            System.out.println("  " + Arrays.toString(labelArray));
        }

    }
    /**
     * Initializes the game board by setting all values of the array to ' ' using a nested for-loop
     */
    void initializeGameBoard()
    {
        for(int row = 0; row < boardHeight; row++)
        {
            for(int col = 0; col < boardWidth; col++)
            {
                gameBoard[row][col] = ' ';
            }
        }
    }
}