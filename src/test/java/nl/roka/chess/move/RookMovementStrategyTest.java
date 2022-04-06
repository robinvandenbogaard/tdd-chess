package nl.roka.chess.move;

import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nl.roka.chess.move.MoveType.Attack;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RookMovementStrategyTest {

	private Piece rook;

	@BeforeEach
	void setup() {
		rook = new PieceFactory().whiteRook();
	}

	@ParameterizedTest
	@ValueSource(strings = {"a4", "b4", "c4", "e4", "f4", "g4", "f4"})
	void rookCanMoveHorizontally(String target) {
		var moveType = rook.getMoveType(Position.position("d4"), Position.position(target));
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a1", "a2", "a3", "a5", "a6", "a7", "a8"})
	void rookCanMoveVertically(String target) {
		var moveType = rook.getMoveType(Position.position("a4"), Position.position(target));
		assertThat(moveType, is(Attack));
	}
}