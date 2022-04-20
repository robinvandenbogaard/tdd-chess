package nl.roka.chess;

import nl.roka.chess.move.Move;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static nl.roka.chess.move.Position.position;
import static nl.roka.chess.piece.Piece.emptySpot;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClassicMoveValidatorTest {

	private ClassicMoveValidator validator;
	private Piece blackPawn;
	private Piece blackQueen;
	private Piece blackKnight;
	private Piece whitePawn;
	private Piece blackBishop;
	private Piece whiteKing;

	@BeforeEach
	void setUp() {
		validator = new ClassicMoveValidator();
		var factory = new PieceFactory();
		blackPawn = factory.blackPawn();
		blackQueen = factory.blackQueen();
		blackKnight = factory.blackKnight();
		whitePawn = factory.whitePawn();
		blackBishop = factory.blackBishop();
		whiteKing = factory.whiteKing();
	}

	@Test
	void acceptMoveIfTargetIsEmptyAndPieceCanMovePassivelyToTheSpot() {
		var move = new Move(position("a7"), blackPawn, position("a6"), emptySpot);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void illegalMoveIfTargetIsNotEmptyAndPieceCanMovePassivelyToTheSpot() {
		var move = new Move(position("a1"), blackPawn, position("a2"), blackPawn);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void illegalMoveIfPieceIsNotAllowedToTheTargetSpot() {
		var move = new Move(position("a1"), blackPawn, position("h1"), emptySpot);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void canMoveToEmptyTargetIfMoveTypeIsAttack() {
		var move = new Move(position("a1"), blackQueen, position("a2"), emptySpot);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void canMoveToTargetIfOppositeColorPresent() {
		var move = new Move(position("a1"), blackQueen, position("a2"), whitePawn);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void cannotMoveToTargetIfSameColorPieceIsPresent() {
		var move = new Move(position("a7"), blackPawn, position("b6"), blackQueen);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void ifPieceCanAttackOnlyHostilePieceMustBeAtTarget() {
		var move = new Move(position("a7"), blackPawn, position("b6"), whitePawn);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void ifPieceCanAttackOnlyCannotMoveToSpotWithAFriendlyPieceOnIt() {
		var move = new Move(position("a7"), blackPawn, position("b6"), blackPawn);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void ifPieceCanAttackOnlyCannotMoveToAnEmptySpot() {
		var move = new Move(position("a7"), blackPawn, position("b6"), emptySpot);

		var moveValidation = validator.validate(move, new Board());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void ifPathToTargetIsObstructedMoveIsIllegalHorizontal() {
		var move = new Move(position("a1"), blackQueen, position("a8"), emptySpot);
		var board = BoardBuilder.empty().pieceAt(blackQueen, "a1").pieceAt(blackPawn, "a2").build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void ifPathToTargetIsObstructedMoveIsIllegalVertical() {
		var move = new Move(position("a1"), blackQueen, position("h1"), emptySpot);
		var board = BoardBuilder.empty().pieceAt(blackQueen, "a1").pieceAt(blackPawn, "b1").build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void ifPathToTargetIsObstructedMoveIsIllegalDiagonal() {
		var move = new Move(position("a1"), blackQueen, position("h8"), emptySpot);
		var board = BoardBuilder.empty().pieceAt(blackQueen, "a1").pieceAt(blackPawn, "b2").build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void knightIsNeverObstructed() {
		var move = new Move(position("a1"), blackKnight, position("b3"), emptySpot);
		var board = BoardBuilder.filled(blackPawn).pieceAt(blackKnight, "a1").pieceAt(emptySpot, "b3").build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void bishopCannotMoveLikeAKnight() {
		var move = new Move(position("c1"), blackBishop, position("b3"), emptySpot);
		var board = BoardBuilder.empty().pieceAt(blackBishop, "c1").build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@ParameterizedTest
	@CsvSource(value = {"b2,b3", "b4,b5", "d2,d3", "d4,d5"})
	void mustMoveKingIfItIsCheckedDiagonal(String pawnPosition, String pawnDestination) {
		var move = new Move(position(pawnPosition), whitePawn, position(pawnDestination), emptySpot);
		var board = BoardBuilder.empty()
								.pieceAt(whiteKing, "c3")
								.pieceAt(whitePawn, "b2")
								.pieceAt(whitePawn, "b4")
								.pieceAt(whitePawn, "d2")
								.pieceAt(whitePawn, "d4")
								.pieceAt(blackQueen, "a1")
								.pieceAt(blackQueen, "a5")
								.pieceAt(blackQueen, "e1")
								.pieceAt(blackQueen, "e5")
								.build();

		var moveValidation = validator.validate(move, board);

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}
}