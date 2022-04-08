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

class KnightMovementStrategyTest {

	private final static Piece knight = new PieceFactory().blackKnight();
	private final static Position startingPosition = Position.position("d4");

	public static Set<Position> attackablePositions() {
		return HashSet.of("b5", "b3", "c2", "c6", "e2", "e6", "f3", "f5").map(Position::position);
	}

	public static Set<Position> cannotMoveToOtherPositions() {
		return PositionCollections.allPositions.removeAll(attackablePositions()).remove(startingPosition);
	}

	@ParameterizedTest
	@MethodSource
	void attackablePositions(Position target) {
		var moveType = knight.getMoveType(startingPosition, target);
		assertThat(moveType, is(AttackOrMove));
	}

	@ParameterizedTest
	@MethodSource
	void cannotMoveToOtherPositions(Position target) {
		var moveType = knight.getMoveType(startingPosition, target);
		assertThat(moveType, is(NotAllowed));
	}
}