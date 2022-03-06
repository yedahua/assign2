
public class Rules {

    private int size;
    private Player actualPlayer;
    private final AllType[][] board;


    public Rules(int size) {
        this.size = size;
        board = new AllType[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (i == 0 || i == 1) {
                    board[i][j] = AllType.BLACK;}// Placing black pieces on 0 and 1st rows
                else if (i == size - 2 || i == size - 1) {
                    board[i][j] = AllType.WHITE;}// Placing white pieces on last 2 rows
                else {
                    board[i][j] = AllType.NONE;}
            }
        }
        actualPlayer = Player.WHITE;
    }

    public Player findWinner() {
        Player winner = null;
        for (int j = 0; j < size; j++) {
            if (getPieceAt(0, j) == AllType.WHITE) {
                winner = Player.WHITE;}
            if (getPieceAt(size - 1, j) == AllType.BLACK) {
                winner = Player.BLACK;
            }
        }
        return winner;
    }

    public void move(int fromRow, int fromColumn, int toRow, int toColumn) {
        if (isMoveValid(fromRow, fromColumn, toRow, toColumn)) {
            //System.out.println("size .. " + board.length);
            board[toRow][toColumn] = getPieceAt(fromRow, fromColumn);
            board[fromRow][fromColumn] = AllType.NONE;
            actualPlayer = actualPlayer == Player.WHITE ? Player.BLACK : Player.WHITE;
            findWinner();
        }
    }

    public boolean isMoveValid(int fromRow, int fromColumn, int toRow, int toColumn) {
        if(!isValidLocation(toRow, toColumn)){ return false;}
        //ensuring the pieces move by 1 unit only
        if(Math.abs(toRow-fromRow)>1){return false;}
        if(Math.abs(toColumn-fromColumn)>1){return false;}

        if(actualPlayer == Player.BLACK){
            if(toRow <= fromRow){return false;}
            if(board[fromRow][fromColumn] == AllType.WHITE){return false;}
            if(board[toRow][toColumn] == AllType.BLACK){return false;}
            if(board[toRow][toColumn] == AllType.WHITE && (fromRow + 1) == toRow && fromColumn == toColumn){return false;}

        }else{
            if(toRow >= fromRow){return false;}
            if(board[fromRow][fromColumn] == AllType.BLACK){return false;}
            if(board[toRow][toColumn] == AllType.WHITE){return false;}
            if(board[toRow][toColumn] == AllType.BLACK && (fromRow - 1) == toRow && fromColumn == toColumn){return false;}
        }
        return true;
    }

    private boolean isValidLocation(int row, int column) {
        if(row < 0 || row >= size || column < 0 || column >= size){return false;}
        return true;
    }
    public Player getActualPlayer() {
        return actualPlayer;
    }
    public AllType getPieceAt(int row, int column) {
        //System.out.println(board[row][column]);
        return board[row][column];
    }
    public AllType[][] getBoard(){
        return board;
    }
}
