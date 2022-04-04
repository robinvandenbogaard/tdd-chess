package nl.roka.chess;

public class ClassicMoveValidator implements MoveValidator {
	@Override
	public MoveValidation validate(Move move, Board board) {

		return MoveValidation.Valid;
	}
}
