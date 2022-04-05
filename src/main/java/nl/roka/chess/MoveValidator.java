package nl.roka.chess;

import nl.roka.chess.move.Move;

public interface MoveValidator {
	MoveValidation validate(Move move, Board board);
}
