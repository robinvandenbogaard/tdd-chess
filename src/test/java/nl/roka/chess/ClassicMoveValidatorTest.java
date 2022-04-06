package nl.roka.chess;

import nl.roka.chess.move.Move;
import nl.roka.chess.move.MoveType;
import nl.roka.chess.move.Position;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import nl.roka.chess.piece.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class ClassicMoveValidatorTest {

	private ClassicMoveValidator validator;
	private Piece passivePiece;
	private Piece unableToMovePiece;
	private Piece aggressivePiece;

	@BeforeEach
	void setUp() {
		validator = new ClassicMoveValidator();
		passivePiece = createPassiveMovePiece();
		unableToMovePiece = createUnableToMovePiece();
		aggressivePiece = createMustAttackMovePiece();
	}

	@Test
	void acceptMoveIfTargetIsEmptyAndPieceCanMovePassivelyToTheSpot() {
		var move = new Move(Position.position("a1"), passivePiece, Position.position("a1"), Piece.emptySquare);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void illegalMoveIfTargetIsNotEmptyAndPieceCanMovePassivelyToTheSpot() {
		var move = new Move(Position.position("a1"), passivePiece, Position.position("a1"), passivePiece);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void illegalMoveIfPieceIsNotAllowedToTheTargetSpot() {
		var move = new Move(Position.position("a1"), unableToMovePiece, Position.position("a1"), passivePiece);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	@Test
	void canMoveToEmptyTargetIfMoveTypeIsAggressive() {
		var move = new Move(Position.position("a1"), aggressivePiece, Position.position("a1"), Piece.emptySquare);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void aggressivePieceCanMoveToTargetIfOppositeColorPresent() {
		var move = new Move(Position.position("a1"), aggressivePiece, Position.position("a1"), unableToMovePiece);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Valid));
	}

	@Test
	void aggressivePieceCannotMoveToTargetIfSameColorPieceIsPresent() {
		var move = new Move(Position.position("a1"), aggressivePiece, Position.position("a1"), aggressivePiece);

		var moveValidation = validator.validate(move, new Board().reset());

		assertThat(moveValidation, is(MoveValidation.Illegal));
	}

	private Piece createPassiveMovePiece() {
		return new PieceFactory().builder().type(PieceType.Pawn).white().movement(
				(positionFrom, positionTo) -> MoveType.Passive).build();
	}

	private Piece createUnableToMovePiece() {
		return new PieceFactory().builder().type(PieceType.Pawn).white().movement(
				(positionFrom, positionTo) -> MoveType.NotAllowed).build();
	}

	private Piece createMustAttackMovePiece() {
		return new PieceFactory().builder().type(PieceType.Pawn).black().movement(
				(positionFrom, positionTo) -> MoveType.Attack).build();
	}
}