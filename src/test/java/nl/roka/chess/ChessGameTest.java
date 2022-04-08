package nl.roka.chess;

import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;


class ChessGameTest {

	private PieceFactory factory;

	@BeforeEach
	void setUp() {
		factory = new PieceFactory();
	}

	@Test
	void newChessBoardHasEightRows() {
		var game = new ChessGame();
		for (int i = 1; i <= 8; i++) {
			assertThat(game.getPieceAt("a%d".formatted(i)), is(Piece.emptySquare));
		}
	}

	@Test
	void rowZeroDoesNotExist() {
		var game = new ChessGame();
		assertThrows(IllegalArgumentException.class, () -> game.getPieceAt("a%d".formatted(0)));
	}

	@Test
	void rowNineDoesNotExist() {
		var game = new ChessGame();
		assertThrows(IllegalArgumentException.class, () -> game.getPieceAt("a%d".formatted(9)));
	}

	@Test
	void afterResetAllWhitePiecesAreInPlace() {
		var game = new ChessGame().reset();

		assertThat(game.getPieceAt("a1"), is(factory.whiteRook()));
		assertThat(game.getPieceAt("b1"), is(factory.whiteKnight()));
		assertThat(game.getPieceAt("c1"), is(factory.whiteBishop()));
		assertThat(game.getPieceAt("d1"), is(factory.whiteQueen()));
		assertThat(game.getPieceAt("e1"), is(factory.whiteKing()));
		assertThat(game.getPieceAt("f1"), is(factory.whiteBishop()));
		assertThat(game.getPieceAt("g1"), is(factory.whiteKnight()));
		assertThat(game.getPieceAt("h1"), is(factory.whiteRook()));

		assertThat(game.getPieceAt("a2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("b2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("c2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("d2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("e2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("f2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("g2"), is(factory.whitePawn()));
		assertThat(game.getPieceAt("h2"), is(factory.whitePawn()));
	}

	@Test
	void afterResetAllBlackPiecesAreInPlace() {
		var game = new ChessGame().reset();

		assertThat(game.getPieceAt("a8"), is(factory.blackRook()));
		assertThat(game.getPieceAt("b8"), is(factory.blackKnight()));
		assertThat(game.getPieceAt("c8"), is(factory.blackBishop()));
		assertThat(game.getPieceAt("d8"), is(factory.blackKing()));
		assertThat(game.getPieceAt("e8"), is(factory.blackQueen()));
		assertThat(game.getPieceAt("f8"), is(factory.blackBishop()));
		assertThat(game.getPieceAt("g8"), is(factory.blackKnight()));
		assertThat(game.getPieceAt("h8"), is(factory.blackRook()));

		assertThat(game.getPieceAt("a7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("b7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("c7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("d7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("e7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("f7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("g7"), is(factory.blackPawn()));
		assertThat(game.getPieceAt("h7"), is(factory.blackPawn()));
	}

	@Test
	void aValidMoveGetsExecuted() {
		var game = new ChessGame((move, board) -> MoveValidation.Valid).reset().move("a2", "a3");
		assertThat(game.getPieceAt("a2"), is(Piece.emptySquare));
		assertThat(game.getPieceAt("a3"), is(factory.whitePawn()));
	}

	@Test
	void cannotMakeInvalidMove() {
		var game = new ChessGame((move, board) -> MoveValidation.Illegal).reset();
		assertThrows(IllegalArgumentException.class, () -> game.move("a2", "a3"));
	}

	@Test
	void cannotMakeMoveIfResultIsCheck() {
		var game = new ChessGame((move, board) -> MoveValidation.Check).reset();
		assertThrows(IllegalArgumentException.class, () -> game.move("a2", "a3"));
	}
}