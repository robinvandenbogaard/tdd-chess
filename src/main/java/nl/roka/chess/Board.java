package nl.roka.chess;

import io.vavr.collection.HashMap;
import io.vavr.collection.List;
import io.vavr.collection.Map;
import nl.roka.chess.move.Move;
import nl.roka.chess.move.Position;
import nl.roka.chess.piece.Color;
import nl.roka.chess.piece.Piece;
import nl.roka.chess.piece.PieceFactory;
import nl.roka.chess.piece.PieceType;

import java.util.Arrays;
import java.util.Optional;

import static nl.roka.chess.move.Position.vector;

public record Board(Piece[][] pieces, PieceFactory factory) {

	public Board() {
		this(new PieceFactory());
	}

	public Board(PieceFactory factory) {
		this(empty(), factory);
	}

	public static Piece[][] empty() {
		var board = new Piece[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = Piece.emptySpot;
			}
		}
		return board;
	}

	public Board reset() {
		var board = empty();
		board[7][0] = factory.blackRook();
		board[7][1] = factory.blackKnight();
		board[7][2] = factory.blackBishop();
		board[7][3] = factory.blackKing();
		board[7][4] = factory.blackQueen();
		board[7][5] = factory.blackBishop();
		board[7][6] = factory.blackKnight();
		board[7][7] = factory.blackRook();

		board[0][0] = factory.whiteRook();
		board[0][1] = factory.whiteKnight();
		board[0][2] = factory.whiteBishop();
		board[0][3] = factory.whiteQueen();
		board[0][4] = factory.whiteKing();
		board[0][5] = factory.whiteBishop();
		board[0][6] = factory.whiteKnight();
		board[0][7] = factory.whiteRook();

		for (int i = 0; i < 8; i++) {
			board[6][i] = factory.blackPawn();
			board[1][i] = factory.whitePawn();
		}
		return new Board(board, factory);
	}

	public Piece pieceAt(Position position) {
		return pieces()[position.row()][position.column()];
	}

	public Board move(Move move) {
		var pieces = copy();
		pieces[move.posFrom().row()][move.posFrom().column()] = Piece.emptySpot;
		pieces[move.posTo().row()][move.posTo().column()] = move.pieceToMove();
		return new Board(pieces, factory);
	}

	private Piece[][] copy() {
		return Arrays.stream(pieces).
					 map(Piece[]::clone).toArray(a -> pieces.clone());
	}

	public Optional<Position> getKingPosition(Color friendlyColor) {
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				Piece piece = pieces[row][column];
				if (piece.hasColor(friendlyColor) && piece.isPieceType(PieceType.King))
					return Optional.of(vector(row, column));
			}
		}
		return Optional.empty();
	}

	public Map<Position, Piece> getPiecesInFieldOfVision(Position position, Color desiredColor) {
		Map<Position, Piece> result = HashMap.empty();
		var diagonalDirections = List.of(vector(1, 1),
										 vector(1, -1),
										 vector(-1, 1),
										 vector(-1, -1));
		var horizontalDirections = List.of(vector(-1, 0),
										   vector(1, 0),
										   vector(0, -1),
										   vector(0, 1));
		var allDirections = diagonalDirections.appendAll(horizontalDirections);
		for (var direction : allDirections) {
			Position curPos = position.add(direction);
			Piece piece = Piece.emptySpot;
			while (piece.isEmpty() && curPos.isInBounds()) {
				piece = pieceAt(curPos);
				curPos = curPos.add(direction);
			}
			if (piece.hasColor(desiredColor))
				result = result.put(curPos, piece);
		}
		return result;
	}
}
