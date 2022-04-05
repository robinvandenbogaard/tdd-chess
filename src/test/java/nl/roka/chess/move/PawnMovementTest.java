package nl.roka.chess.move;

import org.junit.jupiter.api.Test;

import static nl.roka.chess.Piece.BlackPawn;
import static nl.roka.chess.Piece.WhitePawn;
import static nl.roka.chess.move.MoveType.NotAllowed;
import static nl.roka.chess.move.MoveType.Passive;
import static nl.roka.chess.move.Position.position;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class PawnMovementTest {

	@Test
	void whitePawnCanMoveForward() {
		var type = WhitePawn.getMoveType(position("a2"), position("a3"));
		assertThat(type, is(Passive));
	}

	@Test
	void pawnCanMoveForwardTwoStepsIfStartingPosition() {
		var type = WhitePawn.getMoveType(position("a2"), position("a4"));
		assertThat(type, is(Passive));
	}


	@Test
	void whitePawnCannotMoveBackwards() {
		var type = WhitePawn.getMoveType(position("a2"), position("a1"));
		assertThat(type, is(NotAllowed));
	}

	@Test
	void pawnCannotMoveForwardTwoStepsIfNotAtStartingPosition() {
		var type = WhitePawn.getMoveType(position("a3"), position("a5"));
		assertThat(type, is(NotAllowed));
	}

	@Test
	void blackPawnCanMoveForward() {
		var type = BlackPawn.getMoveType(position("a7"), position("a6"));
		assertThat(type, is(Passive));
	}

	@Test
	void blackPawnCannotMoveBackwards() {
		var type = BlackPawn.getMoveType(position("a7"), position("a8"));
		assertThat(type, is(NotAllowed));
	}
}