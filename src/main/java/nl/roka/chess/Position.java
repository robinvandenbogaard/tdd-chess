package nl.roka.chess;

public record Position(int row, int column) {
	private static final String COLUMN = "abcdefgh";
	private static final String ROWS = "12345678";

	public static Position position(String position) {
		if (position.matches("[abcdefgh][12345678]")){
			return new Position(ROWS.indexOf(position.charAt(1)), COLUMN.indexOf(position.charAt(0)));
		} else {
			throw new IllegalArgumentException("Position %s does not exist".formatted(position));
		}
	}
}

