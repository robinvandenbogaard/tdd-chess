package nl.roka.chess;

public class ChessGame {

	private final Board board;
	private final MoveValidator validator;

	public ChessGame() {
		this(new Board());
	}

	public ChessGame(Board board) {
		this(board, new ClassicMoveValidator());
	}

	public ChessGame(MoveValidator validator) {
		this(new Board(), validator);
	}

	public ChessGame(Board board, MoveValidator validator) {
		this.board = board;
		this.validator = validator;
	}

	public Piece getPieceAt(String pos) {
		var position = Position.position(pos);
		return board.pieceAt(position);
	}

	public ChessGame move(String positionFrom, String positionTo) {
		var posFrom = Position.position(positionFrom);
		var pieceToMove = board.pieceAt(posFrom);

		var posTo = Position.position(positionTo);
		var pieceAtDestination = board.pieceAt(posTo);

		var move = new Move(posFrom, pieceToMove, posTo, pieceAtDestination);
		var moveValidation = validator.validate(move, board);

		switch (moveValidation) {
			case Illegal, Check -> throw new IllegalArgumentException("Illegal move");
		}

		return new ChessGame(board.move(move), validator);
	}

	public ChessGame reset() {
		return new ChessGame(board.reset(), validator);
	}
}
