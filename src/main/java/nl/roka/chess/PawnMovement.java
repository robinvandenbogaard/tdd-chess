package nl.roka.chess;


import io.vavr.Tuple2;
import io.vavr.collection.HashMap;
import io.vavr.collection.Map;

import static nl.roka.chess.MoveType.NotAllowed;
import static nl.roka.chess.MoveType.Passive;
import static nl.roka.chess.Position.root;

public class PawnMovement implements MovementStrategy {
	private final MoveDirection direction;
	private final Map<Position, MoveType> moveVectors;
	private final Tuple2<Position, MoveType> firstMoveVector;

	public PawnMovement(MoveDirection d) {
		this.direction = d;
		this.firstMoveVector = new Tuple2<>(root().forward(d).forward(d), Passive);
		this.moveVectors = HashMap.of(root().forward(d), Passive);
	}

	@Override
	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		var options = moveVectors;
		if (isFirstMove(positionFrom))
			options = options.put(firstMoveVector);

		var desiredVector = positionTo.subtract(positionFrom);
		return options.get(desiredVector).getOrElse(NotAllowed);
	}

	/**
	 * Its the first move if the pawn is still at the row it start on. Black starts on row 7 (index 6) and white on row 2 (index 1).
	 *
	 * @param positionFrom currentPosition
	 * @return if it's currently on the starting row.
	 */
	private boolean isFirstMove(Position positionFrom) {
		return switch (direction) {
			case Up -> positionFrom.row() == 1;
			case Down -> positionFrom.row() == 6;
		};
	}
}
