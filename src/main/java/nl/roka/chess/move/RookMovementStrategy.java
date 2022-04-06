package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.Attack;
import static nl.roka.chess.move.MoveType.NotAllowed;

public class RookMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors;

	public RookMovementStrategy() {
		Set<Position> options = HashSet.of();
		for (int axis = 1; axis < 7; axis++) {
			options = options.add(Position.vector(0, axis))
							 .add(Position.vector(0, -axis))
							 .add(Position.vector(axis, 0))
							 .add(Position.vector(-axis, 0));
		}
		this.moveVectors = options;
	}

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? Attack : NotAllowed;
	}
}
