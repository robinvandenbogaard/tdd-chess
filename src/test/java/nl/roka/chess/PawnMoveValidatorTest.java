package nl.roka.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static nl.roka.chess.Piece.BlackPawn;
import static nl.roka.chess.Position.position;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PawnMoveValidatorTest {

	private Piece[][] pieces;

	@BeforeEach
	void setUp() {
		pieces = Board.empty();
	}

	@Test
	void blackPawnCanMoveForward() {
		setPieceAt(position("a7"), BlackPawn);

		var game = new ChessGame(new Board(pieces)).move("a7", "a6");

		assertThat(game.getPieceAt("a6"), is(BlackPawn));
	}

	//@Test
	void blackPawnCannotMoveBackwards() {
		setPieceAt(position("a7"), BlackPawn);

		var game = new ChessGame(new Board(pieces));

		assertThrows(IllegalArgumentException.class, ()->game.move("a7", "a8"));
	}

	private void setPieceAt(Position pos, Piece piece) {
		pieces[pos.row()][pos.column()] = piece;
	}

}