package nl.roka.chess;

public interface MoveValidator {
	MoveValidation validate(Move move, Board board);
}
