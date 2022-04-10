package nl.roka.chess;

import nl.roka.chess.move.Position;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;

public final class BoardBuilder {

	private final Piece[][] field = new Piece[8][8];

	public static BoardBuilder empty() {
		return new BoardBuilder().filledAllWith(Piece.emptySpot);
	}

	public static BoardBuilder filled(Piece piece) {
		return new BoardBuilder().filledAllWith(piece);
	}

	private BoardBuilder filledAllWith(Piece piece) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				field[i][j] = piece;
			}
		}
		return this;
	}

	public BoardBuilder pieceAt(Piece piece, String position) {
		var pos = Position.position(position);
		field[pos.row()][pos.column()] = piece;
		return this;
	}

	public Board build() {
		return new Board(field, new PieceFactory());
	}
}
