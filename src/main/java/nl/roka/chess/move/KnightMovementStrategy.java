package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.Attack;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static nl.roka.chess.move.Position.root;

public class KnightMovementStrategy implements MovementStrategy {
	private final Set<Position> moveVectors = HashSet.of(root().add(-2, -1),
														 root().add(-2, 1),
														 root().add(-1, -2),
														 root().add(-1, 2),
														 root().add(1, 2),
														 root().add(1, -2),
														 root().add(2, 1),
														 root().add(2, -1));

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? Attack : NotAllowed;
	}
}
