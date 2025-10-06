package application.modal.board.squares;

//Go Square: player will receive capital after reaching or passing the Go Square
public class GoSquare extends Squares {
    private final int capital;

    public GoSquare(int position, int capital) {
        super("GO", position);
        this.capital = capital;
    }
}
