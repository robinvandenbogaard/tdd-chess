package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static nl.roka.chess.move.Position.root;

public class KnightMovementStrategy implements MovementStrategy {

	private final Set<Position> moveVectors = HashSet.of(root().up().up().left(),
														 root().up().up().right(),
														 root().down().down().left(),
														 root().down().down().right(),
														 root().left().left().up(),
														 root().left().left().down(),
														 root().right().right().up(),
														 root().right().right().down());

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? AttackOrMove : NotAllowed;
	}
}
