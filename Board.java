
public class Board  {


    private int rows;
    private int cols;
    
    /** The grid of pieces */
    private Player[][] grid;
    
    

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Player[rows][cols];
        // set each cell of the board to null (empty).
        reset();

    }
    
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = null;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    
    /**
    * Returns the Player whose piece occupies the given location, 
    * @param row int
    * @param col int
    */
    public Player getCell(int row, int col ) throws IndexOutOfBoundsException{
        if( (row < 0) || (col < 0) || (row >= rows) || (col >= cols) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid[row][col];
        }
    }
    
    //returns true if there are no more plays left
    public boolean boardFilled(){
        //TODO: write this
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                if(grid[row][col] == null)
                    return false;
            }
        }
        return true; 
    }

    // Returns true if move is possible given board state.  
    public boolean possibleMove(Move move) {
        // TODO: write this.  Right now, it ignores filled columns, claiming any move is possible
        for(int row = 0; row < rows; row++){
            if(grid[row][move.getColumn()] == null)
                return true;
        }
        return false;
    }
    
    // Adds a piece to the board for a given Move
    public void addPiece(Move move) {
        //TODO: this is a test stub, you need to rewrite this.
        int emptyRow = 0;
        for(int row = 0; row < rows; row++){
            if(grid[row][move.getColumn()] == null){
                emptyRow = row;
                break;
            }
        }
    	grid[emptyRow][move.getColumn()] = move.getPlayer();
    }

    // if the board contains a winning position, returns the Player that wins.
    // Otherwise, returns null.  You could ignore lastMove.
    public Player winner(Move lastMove) {
        // TODO: write this.  Currently, there is never a winnder.
        if(columnWinner() != null)
            return columnWinner();
        if(rowWinner() != null)
            return rowWinner();
        if(diagonalWinner() != null)
            return diagonalWinner();
        return null;
    }
    
    public Player columnWinner(){
        for(int col = 0; col < cols; col++){
            int counter = 1;
            for(int row = 0; row < rows - 1; row++){
                if(grid[row][col] != null && grid[row + 1][col] == grid[row][col])
                    counter++;
                else
                    counter = 1;
                if(counter == 4)
                    return grid[row][col];
            }
        }
        return null;
    }

    public Player rowWinner(){
        for(int row = 0; row < rows; row++){
            int counter = 1;
            for(int col = 0; col < cols - 1; col++){
                if(grid[row][col] != null && grid[row][col + 1] == grid[row][col])
                    counter++;
                else
                    counter = 1;
                if(counter == 4)
                    return grid[row][col];
            }
        }
        return null;
    }

    public Player diagonalWinner(){
        for(int row = 0; row < rows; row++){
            for(int col = 0; col < cols; col++){
                int currentRow = row;
                int currentCol = col;
                int leftCounter = 1;
                while(currentRow < rows - 1 && currentCol > 0){
                    if(grid[currentRow][currentCol] != null && grid[currentRow + 1][currentCol - 1] == grid[currentRow][currentCol])
                        leftCounter++;
                    else
                        leftCounter = 1;
                    if(leftCounter == 4)
                        return grid[currentRow][currentCol];
                    currentRow++;
                    currentCol--;
                }
                currentRow = row;
                currentCol = col;
                int rightCounter = 1;
                while(currentRow < rows - 1 && currentCol < cols - 1){
                    if(grid[currentRow][currentCol] != null && grid[currentRow + 1][currentCol + 1] == grid[currentRow][currentCol])
                        rightCounter++;
                    else
                        rightCounter = 0;
                    if(rightCounter == 4)
                        return grid[currentRow][currentCol];
                    currentRow++;
                    currentCol++;
                } 
            }
        }
        return null;
    }

} // end Board class
