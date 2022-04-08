package nl.roka.chess;

import nl.roka.chess.move.Move;
import nl.roka.chess.piece.Piece;

public class ClassicMoveValidator implements MoveValidator {
	@Override
	public MoveValidation validate(Move move, Board board) {
		var pieceToMove = move.pieceToMove();
		var pieceAtDestination = move.pieceAtDestination();

		var allowedToMove = switch (pieceToMove.getMoveType(move.posFrom(), move.posTo())) {
			case Attack -> pieceAtDestination.isHostile(pieceToMove.getColor());
			case AttackOnly -> pieceAtDestination.isHostile(pieceToMove.getColor()) && !pieceAtDestination.equals(
					Piece.emptySquare);
			case NotAllowed -> false;
			case Passive -> pieceAtDestination.equals(Piece.emptySquare);
		};

		return allowedToMove ? MoveValidation.Valid : MoveValidation.Illegal;
	}
}
