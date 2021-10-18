enum Square{
    X,
    O,
    vacio
}

public class Board {
    private Square[][] squares;

    public Board(){
        squares = new Square[3][3];
        restartGame();
    }

    public void restartGame(){
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                squares[i][j] = Square.vacio;
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }
}
