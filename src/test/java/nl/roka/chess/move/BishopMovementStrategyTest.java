package nl.roka.chess.move;

import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nl.roka.chess.move.MoveType.Attack;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BishopMovementStrategyTest {

	private Piece bishop;

	@BeforeEach
	void setup() {
		bishop = new PieceFactory().whiteBishop();
	}

	@ParameterizedTest
	@ValueSource(strings = {"a1", "b2", "c3", "e5", "f6", "g7",
			"a7", "b6", "c5", "e3", "f2", "g1"})
	void canMoveDiagonally(String target) {
		var moveType = bishop.getMoveType(Position.position("d4"), Position.position(target));
		assertThat(moveType, is(Attack));
	}
}