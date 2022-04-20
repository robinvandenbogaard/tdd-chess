package nl.roka.chess.move;

import io.vavr.collection.List;

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

	public Position up() {
		return vector(this.row + 1, this.column);
	}

	public Position down() {
		return vector(this.row - 1, this.column);
	}

	public List<Position> positionsBetween(Position other) {
		var direction = direction(other);
		Position inBetween = this.add(direction);
		List<Position> result = List.of();
		while (!inBetween.equals(other)) {
			result = result.append(inBetween);
			inBetween = inBetween.add(direction);
		}

		return result;
	}

	public Position add(Position other) {
		return vector(this.row + other.row, this.column + other.column);
	}

	public Position direction(Position other) {
		return other.subtract(this).normalize();
	}

	private Position normalize() {
		var row = this.row != 0 ? this.row / Math.abs(this.row) : 0;
		var column = this.column != 0 ? this.column / Math.abs(this.column) : 0;
		return vector(row, column);
	}

	public boolean isInBounds() {
		return isBetween(this.row) && isBetween(this.column);
	}

	private boolean isBetween(int value) {
		return value >= 0 && value <= 7;
	}
}

