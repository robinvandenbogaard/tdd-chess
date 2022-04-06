package nl.roka.chess.move;

public record Position(int row, int column) {
	private static final String COLUMN = "abcdefgh";
	private static final String ROWS = "12345678";

	public static Position position(String position) {
		if (position.matches("[abcdefgh][12345678]")) {
			return new Position(ROWS.indexOf(position.charAt(1)), COLUMN.indexOf(position.charAt(0)));
		} else {
			throw new IllegalArgumentException("Position %s does not exist".formatted(position));
		}
	}

	public static Position root() {
		return new Position(0, 0);
	}

	public static Position vector(int row, int column) {
		return new Position(row, column);
	}

	public Position subtract(Position other) {
		return Position.vector(row - other.row, column - other.column);
	}

	public Position normalize(MoveDirection direction) {
		return Position.vector(row * direction.forward(), column);
	}

	public Position forward(MoveDirection direction) {
		return vector(row + direction.forward(), column);
	}

	public Position backward(MoveDirection direction) {
		return vector(row - direction.forward(), column);
	}

	public Position right() {
		return vector(row, column + 1);
	}

	public Position left() {
		return vector(row, column - 1);
	}
}

