package nl.roka.chess.move;

import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.roka.chess.move.MoveType.NotAllowed;
import static nl.roka.chess.move.MoveType.Passive;
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
	void pawnCanMoveForwardTwoStepsIfStartingPosition() {
		var type = whitePawn.getMoveType(position("a2"), position("a4"));
		assertThat(type, is(Passive));
	}


	@Test
	void whitePawnCannotMoveBackwards() {
		var type = whitePawn.getMoveType(position("a2"), position("a1"));
		assertThat(type, is(NotAllowed));
	}

	@Test
	void pawnCannotMoveForwardTwoStepsIfNotAtStartingPosition() {
		var type = whitePawn.getMoveType(position("a3"), position("a5"));
		assertThat(type, is(NotAllowed));
	}

	@Test
	void blackPawnCanMoveForward() {
		var type = blackPawn.getMoveType(position("a7"), position("a6"));
		assertThat(type, is(Passive));
	}

	@Test
	void blackPawnCannotMoveBackwards() {
		var type = blackPawn.getMoveType(position("a7"), position("a8"));
		assertThat(type, is(NotAllowed));
	}
}