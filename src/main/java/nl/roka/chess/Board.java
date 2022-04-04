package nl.roka.chess;

import java.util.Arrays;

public record Board(Piece[][] pieces) {

	public Board() {
		this(empty());
	}

	public static Piece[][] empty() {
		var board = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = Piece.None;
			}
		}
		return board;
	}

	public Board reset() {
		var board = empty();
		board[7][0] = Piece.BlackRook;
		board[7][1] = Piece.BlackHorse;
		board[7][2] = Piece.BlackBishop;
		board[7][3] = Piece.BlackKing;
		board[7][4] = Piece.BlackQueen;
		board[7][5] = Piece.BlackBishop;
		board[7][6] = Piece.BlackHorse;
		board[7][7] = Piece.BlackRook;

		board[0][0] = Piece.WhiteRook;
		board[0][1] = Piece.WhiteHorse;
		board[0][2] = Piece.WhiteBishop;
		board[0][3] = Piece.WhiteQueen;
		board[0][4] = Piece.WhiteKing;
		board[0][5] = Piece.WhiteBishop;
		board[0][6] = Piece.WhiteHorse;
		board[0][7] = Piece.WhiteRook;

		for (int i = 0; i < 8; i++) {
			board[6][i] = Piece.BlackPawn;
			board[1][i] = Piece.WhitePawn;
		}
		return new Board(board);
	}

	public Piece pieceAt(Position position) {
		return pieces()[position.row()][position.column()];
	}

	public Board move(Move move) {
		var pieces = copy();
		pieces[move.posFrom().row()][move.posFrom().column()] = Piece.None;
		pieces[move.posTo().row()][move.posTo().column()] = move.pieceToMove();
		return new Board(pieces);
	}

	private Piece[][] copy() {
		return Arrays.stream(pieces).
			  map(Piece[]::clone).toArray(a -> pieces.clone());
	}
}
