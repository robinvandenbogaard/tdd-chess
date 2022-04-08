package nl.roka.chess.move;

import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;

public class BishopMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors = PositionCollections.diagonalVectors;

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? AttackOrMove : NotAllowed;
	}
}
