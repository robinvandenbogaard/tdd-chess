package nl.roka.chess.move;

import io.vavr.collection.HashSet;
import io.vavr.collection.Set;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static nl.roka.chess.move.MoveType.Attack;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class KingMovementStrategyTest {

	private final static Piece king = new PieceFactory().blackKing();
	private final static Position startingPosition = Position.position("d4");

	public static Set<Position> canAttackDiagonally() {
		return HashSet.of("c3", "c5", "e3", "e5").map(Position::position);
	}

	public static Set<Position> canAttackHorizontally() {
		return HashSet.of("c4", "e4").map(Position::position);
	}

	public static Set<Position> canAttackVertically() {
		return HashSet.of("d3", "d5").map(Position::position);
	}

	public static Set<Position> cannotMoveOtherThenDiagonally() {
		return PositionCollections.allPositions.remove(startingPosition)
											   .removeAll(canAttackDiagonally())
											   .removeAll(canAttackHorizontally())
											   .removeAll(canAttackVertically());
	}

	@ParameterizedTest
	@MethodSource
	void canAttackDiagonally(Position target) {
		var moveType = king.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void canAttackHorizontally(Position target) {
		var moveType = king.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void canAttackVertically(Position target) {
		var moveType = king.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void cannotMoveOtherThenDiagonally(Position target) {
		var moveType = king.getMoveType(startingPosition, target);
		assertThat(moveType, is(NotAllowed));
	}
}