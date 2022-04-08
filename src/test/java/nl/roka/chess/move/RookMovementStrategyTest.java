package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static nl.roka.chess.move.MoveType.AttackOrMove;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class RookMovementStrategyTest {

	private final static Piece rook = new PieceFactory().whiteRook();
	private final static Position startingPosition = Position.position("d4");

	public static Set<Position> rookCanMoveHorizontally() {
		return HashSet.of("a4", "b4", "c4", "e4", "f4", "g4", "h4").map(Position::position);
	}

	public static Set<Position> rookCanMoveVertically() {
		return HashSet.of("d1", "d2", "d3", "d5", "d6", "d7", "d8").map(Position::position);
	}

	public static Set<Position> rookCannotMoveOtherThenOverStraightAxis() {
		return PositionCollections.allPositions.remove(startingPosition).removeAll(rookCanMoveVertically())
											   .removeAll(rookCanMoveHorizontally());
	}

	@ParameterizedTest
	@MethodSource
	void rookCanMoveHorizontally(Position target) {
		var moveType = rook.getMoveType(startingPosition, target);
		assertThat(moveType, is(AttackOrMove));
	}

	@ParameterizedTest
	@MethodSource
	void rookCanMoveVertically(Position target) {
		var moveType = rook.getMoveType(startingPosition, target);
		assertThat(moveType, is(AttackOrMove));
	}

	@ParameterizedTest
	@MethodSource
	void rookCannotMoveOtherThenOverStraightAxis(Position target) {
		var moveType = rook.getMoveType(startingPosition, target);
		assertThat(moveType, is(NotAllowed));

	}
}