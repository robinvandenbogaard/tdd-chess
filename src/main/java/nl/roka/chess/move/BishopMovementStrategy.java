package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.Attack;
import static nl.roka.chess.move.MoveType.NotAllowed;

public class BishopMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors;

	public BishopMovementStrategy() {
		Set<Position> options = HashSet.of();
		for (int axis = 1; axis < 7; axis++) {
			options = options.add(Position.vector(axis, axis))
							 .add(Position.vector(axis, -axis))
							 .add(Position.vector(-axis, axis))
							 .add(Position.vector(-axis, -axis));
		}
		this.moveVectors = options;
	}

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? Attack : NotAllowed;
	}
}
