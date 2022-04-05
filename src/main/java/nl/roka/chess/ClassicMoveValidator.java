package nl.roka.chess;

import nl.roka.chess.move.Move;

public class ClassicMoveValidator implements MoveValidator {
	@Override
	public MoveValidation validate(Move move, Board board) {
		var pieceToMove = move.pieceToMove();
		var pieceAtDestination = move.pieceAtDestination();

		var allowedToMove = switch (pieceToMove.getMoveType(move.posFrom(), move.posTo())) {
			case NotAllowed -> false;
			case Passive -> pieceAtDestination.equals(Piece.None);
		};

		return allowedToMove ? MoveValidation.Valid : MoveValidation.Illegal;
	}
}
