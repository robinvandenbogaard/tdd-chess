package nl.roka.chess;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ChessGameTest {

	@Test
	void newChessBoardHasEightRows() {
		var game = new ChessGame();
		for (int i = 1; i <= 8; i++) {
			assertThat(game.getPieceAt("a%d".formatted(i)), is(Piece.None));
		}
	}

	@Test
	void rowZeroDoesNotExist() {var game = new ChessGame();
		assertThrows(IllegalArgumentException.class, ()->game.getPieceAt("a%d".formatted(0)));
	}

	@Test
	void rowNineDoesNotExist() {var game = new ChessGame();
		assertThrows(IllegalArgumentException.class, ()->game.getPieceAt("a%d".formatted(9)));
	}

	@Test
	void afterResetAllWhitePiecesAreInPlace() {
		var game = new ChessGame().reset();

		assertThat(game.getPieceAt("a1"), is(Piece.WhiteRook));
		assertThat(game.getPieceAt("b1"), is(Piece.WhiteHorse));
		assertThat(game.getPieceAt("c1"), is(Piece.WhiteBishop));
		assertThat(game.getPieceAt("d1"), is(Piece.WhiteQueen));
		assertThat(game.getPieceAt("e1"), is(Piece.WhiteKing));
		assertThat(game.getPieceAt("f1"), is(Piece.WhiteBishop));
		assertThat(game.getPieceAt("g1"), is(Piece.WhiteHorse));
		assertThat(game.getPieceAt("h1"), is(Piece.WhiteRook));

		assertThat(game.getPieceAt("a2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("b2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("c2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("d2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("e2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("f2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("g2"), is(Piece.WhitePawn));
		assertThat(game.getPieceAt("h2"), is(Piece.WhitePawn));
	}

	@Test
	void afterResetAllBlackPiecesAreInPlace() {
		var game = new ChessGame().reset();

		assertThat(game.getPieceAt("a8"), is(Piece.BlackRook));
		assertThat(game.getPieceAt("b8"), is(Piece.BlackHorse));
		assertThat(game.getPieceAt("c8"), is(Piece.BlackBishop));
		assertThat(game.getPieceAt("d8"), is(Piece.BlackKing));
		assertThat(game.getPieceAt("e8"), is(Piece.BlackQueen));
		assertThat(game.getPieceAt("f8"), is(Piece.BlackBishop));
		assertThat(game.getPieceAt("g8"), is(Piece.BlackHorse));
		assertThat(game.getPieceAt("h8"), is(Piece.BlackRook));

		assertThat(game.getPieceAt("a7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("b7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("c7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("d7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("e7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("f7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("g7"), is(Piece.BlackPawn));
		assertThat(game.getPieceAt("h7"), is(Piece.BlackPawn));
	}

	@Test
	void aValidMoveGetsExecuted() {
		var game = new ChessGame((move, board) -> MoveValidation.Valid).reset().move("a2", "a3");
		assertThat(game.getPieceAt("a2"), is(Piece.None));
		assertThat(game.getPieceAt("a3"), is(Piece.WhitePawn));
	}

	@Test
	void cannotMakeInvalidMoveGetsExecuted() {
		var game = new ChessGame((move, board) -> MoveValidation.Illegal).reset();
		assertThrows(IllegalArgumentException.class, ()->game.move("a2", "a3"));
	}

	@Test
	void cannotMakeMoveIfResultIsCheck() {
		var game = new ChessGame((move, board) -> MoveValidation.Check).reset();
		assertThrows(IllegalArgumentException.class, ()->game.move("a2", "a3"));
	}
}