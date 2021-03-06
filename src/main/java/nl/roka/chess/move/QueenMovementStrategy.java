package nl.roka.chess.move;

import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;

public class QueenMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors = PositionCollections.diagonalVectors.addAll(
			PositionCollections.axisVectors);

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? AttackOrMove : NotAllowed;
	}
}
