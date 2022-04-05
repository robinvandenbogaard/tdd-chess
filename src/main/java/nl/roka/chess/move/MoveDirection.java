package nl.roka.chess.move;

enum MoveDirection {
	Up(1), Down(-1);

	private final int forward;

	MoveDirection(int forward) {
		this.forward = forward;
	}

	public int forward() {
		return forward;
	}
}
