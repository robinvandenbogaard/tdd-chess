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

class QueenMovementStrategyTest {

	private final static Piece queen = new PieceFactory().blackQueen();
	private final static Position startingPosition = Position.position("d4");

	public static Set<Position> canAttackDiagonally() {
		return HashSet.of("a1", "b2", "c3", "e5", "f6", "g7", "h8",
						  "a7", "b6", "c5", "e3", "f2", "g1").map(Position::position);
	}

	public static Set<Position> canAttackHorizontally() {
		return HashSet.of("a4", "b4", "c4", "e4", "f4", "g4", "h4").map(Position::position);
	}

	public static Set<Position> canAttackVertically() {
		return HashSet.of("d1", "d2", "d3", "d5", "d6", "d7", "d8").map(Position::position);
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
		var moveType = queen.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void canAttackHorizontally(Position target) {
		var moveType = queen.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void canAttackVertically(Position target) {
		var moveType = queen.getMoveType(startingPosition, target);
		assertThat(moveType, is(Attack));
	}

	@ParameterizedTest
	@MethodSource
	void cannotMoveOtherThenDiagonally(Position target) {
		var moveType = queen.getMoveType(startingPosition, target);
		assertThat(moveType, is(NotAllowed));
	}
}