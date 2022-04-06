package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

public final class PositionCollections {
	public static final Set<Position> allPositions;
	public static final Set<Position> diagonalVectors;
	public static final Set<Position> axisVectors;

	static {
		Set<Position> options = HashSet.of();
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				options = options.add(Position.vector(row, column));
			}
		}
		allPositions = options;

		options = HashSet.of();
		for (int axis = 0; axis < 8; axis++) {
			options = options.add(Position.vector(axis, axis))
							 .add(Position.vector(axis, -axis))
							 .add(Position.vector(-axis, axis))
							 .add(Position.vector(-axis, -axis));
		}
		diagonalVectors = options;

		options = HashSet.of();
		for (int axis = 0; axis < 8; axis++) {
			options = options.add(Position.vector(0, axis))
							 .add(Position.vector(0, -axis))
							 .add(Position.vector(axis, 0))
							 .add(Position.vector(-axis, 0));
		}
		axisVectors = options;
	}
}
