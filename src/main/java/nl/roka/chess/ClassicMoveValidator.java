package nl.roka.chess;

import io.vavr.collection.Map;
import nl.roka.chess.move.Move;
import nl.roka.chess.move.Position;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceType;

public class ClassicMoveValidator implements MoveValidator {

	@Override
	public MoveValidation validate(Move move, Board board) {
		var pieceToMove = move.pieceToMove();
		var pieceAtDestination = move.pieceAtDestination();

		var allowedToMove = isAllowedToMove(move.posFrom(), pieceToMove, move.posTo(), pieceAtDestination);
		allowedToMove = allowedToMove && isNotObstructed(move, board);
		allowedToMove = allowedToMove && isNotCausingKingToGetChecked(move, board);
		return allowedToMove ? MoveValidation.Valid : MoveValidation.Illegal;
	}

	private boolean isNotCausingKingToGetChecked(Move move, Board board) {
		var friendlyColor = move.pieceToMove().getColor();
		var friendlyKingPosition = board.getKingPosition(friendlyColor);
		boolean check = false;
		if (friendlyKingPosition.isPresent()) {
			var kingPosition = friendlyKingPosition.get();
			var enemyColor = move.pieceToMove().getOppositeColor();
			var peekAheadBoard = board.move(move);
			var enemiesInFieldOfVision = peekAheadBoard.getPiecesInFieldOfVision(kingPosition, enemyColor);
			var enemyKnights = peekAheadBoard.getKnightPositions(enemyColor);
			check = isChecked(kingPosition, board.pieceAt(kingPosition), enemiesInFieldOfVision);
			check |= isChecked(kingPosition, board.pieceAt(kingPosition), enemyKnights);
		}

		return !check;
	}

	private boolean isChecked(Position kingPosition, Piece friendlyKing, Map<Position, Piece> enemiesInFieldOfVision) {
		return !enemiesInFieldOfVision.filter(
											  (enemyPosition, enemyPiece) -> isAllowedToMove(enemyPosition, enemyPiece, kingPosition, friendlyKing))
									  .isEmpty();
	}

	private boolean isNotObstructed(Move move, Board board) {
		if (move.pieceToMove().getPieceType().equals(PieceType.Knight))
			return true;
		var positionsToCheck = move.posTo().positionsBetween(move.posFrom());
		return positionsToCheck.forAll(position -> board.pieceAt(position).equals(Piece.emptySpot));

	}

	private boolean isAllowedToMove(Position posFrom, Piece pieceToMove, Position posTo, Piece pieceAtDestination) {
		var moveType = pieceToMove.getMoveType(posFrom, posTo);
		return switch (moveType) {
			case AttackOrMove -> pieceAtDestination.isEmpty() || pieceAtDestination.isHostile(pieceToMove.getColor());
			case AttackOnly -> pieceAtDestination.isNotEmpty() && pieceAtDestination.isHostile(pieceToMove.getColor());
			case NotAllowed -> false;
			case Passive -> pieceAtDestination.equals(Piece.emptySpot);
		};
	}
}
