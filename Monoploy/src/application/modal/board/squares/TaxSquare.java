package application.modal.board.squares;

public class TaxSquare extends Squares{
	private final int tax;
	
	public TaxSquare(String name, int position, int tax) {
		super(name, position);
		this.tax = tax;
	}
}
