package nl.roka.chess.move;

import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;

public class RookMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors = PositionCollections.axisVectors;

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.remove(Position.root())
						  .contains(positionTo.subtract(positionFrom)) ? AttackOrMove : NotAllowed;
	}
}
