package nl.roka.chess;

import nl.roka.chess.move.Move;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceType;

public class ClassicMoveValidator implements MoveValidator {
	@Override
	public MoveValidation validate(Move move, Board board) {
		var pieceToMove = move.pieceToMove();
		var pieceAtDestination = move.pieceAtDestination();

		var allowedToMove = isAllowedToMove(move, pieceToMove, pieceAtDestination);
		allowedToMove = allowedToMove && isNotObstructed(move, board);
		return allowedToMove ? MoveValidation.Valid : MoveValidation.Illegal;
	}

	private boolean isNotObstructed(Move move, Board board) {
		if (move.pieceToMove().getPieceType().equals(PieceType.Knight))
			return true;
		var positionsToCheck = move.posTo().positionsBetween(move.posFrom());
		return positionsToCheck.forAll(position -> board.pieceAt(position).equals(Piece.emptySpot));

	}

	private boolean isAllowedToMove(Move move, Piece pieceToMove, Piece pieceAtDestination) {
		return switch (pieceToMove.getMoveType(move.posFrom(), move.posTo())) {
			case AttackOrMove -> pieceAtDestination.isEmpty() || pieceAtDestination.isHostile(pieceToMove.getColor());
			case AttackOnly -> pieceAtDestination.isNotEmpty() && pieceAtDestination.isHostile(pieceToMove.getColor());
			case NotAllowed -> false;
			case Passive -> pieceAtDestination.equals(Piece.emptySpot);
		};
	}
}
