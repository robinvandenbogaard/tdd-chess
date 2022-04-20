package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;

import static nl.roka.chess.move.Position.vector;

public final class PositionCollections {
	public static final Set<Position> allPositions;
	public static final Set<Position> diagonalVectors;
	public static final Set<Position> axisVectors;
	public static final Set<Position> knightVectors;
	public static final Set<Position> horizontalDirections;
	public static final Set<Position> diagonalDirections;
	public static final Set<Position> allDirections;

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

		options = HashSet.of();
		for (int axis = 0; axis < 8; axis++) {
			options = options.add(Position.vector(-2, 1))
							 .add(Position.vector(-2, -1))
							 .add(Position.vector(-1, 2))
							 .add(Position.vector(1, 2))
							 .add(Position.vector(2, 1))
							 .add(Position.vector(2, -1))
							 .add(Position.vector(-1, -2))
							 .add(Position.vector(1, -2));
		}
		knightVectors = options;

		diagonalDirections = HashSet.of(vector(1, 1),
										vector(1, -1),
										vector(-1, 1),
										vector(-1, -1));

		horizontalDirections = HashSet.of(vector(-1, 0),
										  vector(1, 0),
										  vector(0, -1),
										  vector(0, 1));

		allDirections = diagonalDirections.addAll(horizontalDirections);
	}
}
