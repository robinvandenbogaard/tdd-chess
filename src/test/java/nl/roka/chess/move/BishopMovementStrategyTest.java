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

class BishopMovementStrategyTest {

	private final static Piece bishop = new PieceFactory().whiteBishop();
	private final static Position startingPosition = Position.position("d4");

	public static Set<Position> canAttackDiagonally() {
		return HashSet.of("a1", "b2", "c3", "e5", "f6", "g7", "h8",
						  "a7", "b6", "c5", "e3", "f2", "g1").map(Position::position);
	}

	public static Set<Position> cannotMoveOtherThenDiagonally() {
		return PositionCollections.allPositions.removeAll(canAttackDiagonally()).remove(startingPosition);
	}

	@ParameterizedTest
	@MethodSource
	void canAttackDiagonally(Position target) {
		var moveType = bishop.getMoveType(startingPosition, target);
		assertThat(moveType, is(AttackOrMove));
	}

	@ParameterizedTest
	@MethodSource
	void cannotMoveOtherThenDiagonally(Position target) {
		var moveType = bishop.getMoveType(startingPosition, target);
		assertThat(moveType, is(NotAllowed));
	}
}