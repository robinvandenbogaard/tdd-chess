package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static nl.roka.chess.move.Position.root;

public class KingMovementStrategy implements MovementStrategy {

	private final Set<Position> moveVectors = HashSet.of(root().up(),
														 root().up().right(),
														 root().up().left(),
														 root().down(),
														 root().down().left(),
														 root().down().right(),
														 root().left(),
														 root().right());

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return moveVectors.contains(positionTo.subtract(positionFrom)) ? AttackOrMove : NotAllowed;
	}
}
