package nl.roka.app;

import nl.roka.chess.ChessGame;
import nl.roka.chess.piece.Color;
import nl.roka.chess.piece.Piece;

public class Printer {


	public void print(ChessGame currentGame) {
		println("  a b c d e f g h");
		for (int row = 0; row < 8; row++) {
			print(row + 1);
			for (int column = 0; column < 8; column++) {
				print(currentGame.getPieceAt(toPos(row, column)));
			}
			println("");
		}
	}

	private void print(int row) {
		System.out.print(row + " ");
	}

	private void print(Piece piece) {
		var pieceText = switch (piece.getPieceType()) {
			case Pawn -> piece.getColor().equals(Color.White) ? "♟" : "♙";
			case Rook -> piece.getColor().equals(Color.White) ? "♜" : "♖";
			case Bishop -> piece.getColor().equals(Color.White) ? "♝" : "♗";
			case Knight -> piece.getColor().equals(Color.White) ? "♞" : "♘";
			case King -> piece.getColor().equals(Color.White) ? "♚" : "♔";
			case Queen -> piece.getColor().equals(Color.White) ? "♛" : "♕";
			case EmptySquare -> " ";
		};
		System.out.print(pieceText);
	}

	private String toPos(int row, int column) {
		return "%s%s".formatted("abcdefgh".charAt(column), "12345678".charAt(row));
	}

	public void println(String text) {
		System.out.println(text);
	}

}
