package nl.roka.chess.move;

import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static nl.roka.chess.move.MoveType.*;
import static nl.roka.chess.move.Position.position;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PawnMovementTest {

	private Piece whitePawn;
	private Piece blackPawn;

	@BeforeEach
	void setUp() {
		PieceFactory factory = new PieceFactory();
		whitePawn = factory.whitePawn();
		blackPawn = factory.blackPawn();
	}

	@Test
	void whitePawnCanMoveForward() {
		var type = whitePawn.getMoveType(position("a2"), position("a3"));
		assertThat(type, is(Passive));
	}

	@Test
	void whitePawnCanMoveForwardTwoStepsIfStartingPosition() {
		var type = whitePawn.getMoveType(position("a2"), position("a4"));
		assertThat(type, is(Passive));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a3", "c3"})
	void whitePawnCanAttackDiagonally(String target) {
		var type = whitePawn.getMoveType(position("b2"), position(target));
		assertThat(type, is(AttackOnly));
	}

	@Test
	void whitePawnCannotMoveForwardTwoStepsIfNotAtStartingPosition() {
		var type = whitePawn.getMoveType(position("a3"), position("a5"));
		assertThat(type, is(NotAllowed));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a1", "b1", "c1"})
	void whitePawnCannotMoveBackwards(String target) {
		var type = whitePawn.getMoveType(position("b2"), position(target));
		assertThat(type, is(NotAllowed));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a2", "c2"})
	void whitePawnCannotMoveSideways(String target) {
		var type = whitePawn.getMoveType(position("b2"), position(target));
		assertThat(type, is(NotAllowed));
	}

	@Test
	void blackPawnCanMoveForward() {
		var type = blackPawn.getMoveType(position("a7"), position("a6"));
		assertThat(type, is(Passive));
	}

	@Test
	void blackPawnCanMoveForwardTwoStepsIfStartingPosition() {
		var type = blackPawn.getMoveType(position("a7"), position("a5"));
		assertThat(type, is(Passive));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a6", "c6"})
	void blackPawnCanAttackOnlyDiagonally(String target) {
		var type = blackPawn.getMoveType(position("b7"), position(target));
		assertThat(type, is(AttackOnly));
	}

	@Test
	void blackPawnCannotMoveForwardTwoStepsIfNotAtStartingPosition() {
		var type = blackPawn.getMoveType(position("a6"), position("a4"));
		assertThat(type, is(NotAllowed));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a8", "b8", "c8"})
	void blackPawnCannotMoveBackwards(String target) {
		var type = blackPawn.getMoveType(position("b7"), position(target));
		assertThat(type, is(NotAllowed));
	}

	@ParameterizedTest
	@ValueSource(strings = {"a7", "c7"})
	void blackPawnCannotMoveSideways(String target) {
		var type = blackPawn.getMoveType(position("b7"), position(target));
		assertThat(type, is(NotAllowed));
	}
}