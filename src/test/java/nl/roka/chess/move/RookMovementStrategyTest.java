package nl.roka.chess.move;

import io.vavr.collection.List;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static nl.roka.chess.move.MoveType.Attack;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RookMovementStrategyTest {

	private final static Piece rook = new PieceFactory().whiteRook();
	private final static Position startingPosition = Position.position("d4");

	public static List<Position> rookCanMoveHorizontally() {
		return List.of("a4", "b4", "c4", "e4", "f4", "g4", "f4").map(Position::position);
	}

	public static List<Position> rookCanMoveVertically() {
		return List.of("d1", "d2", "d3", "d5", "d6", "d7", "d8").map(Position::position);
	}

	@ParameterizedTest
	@MethodSource
	void rookCanMoveHorizontally(Position target) {
		var moveType = rook.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void rookCanMoveVertically(Position target) {
		var moveType = rook.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}
}