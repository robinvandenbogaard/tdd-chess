package nl.roka.chess.piece;

import nl.roka.chess.move.MoveType;
import nl.roka.chess.move.MovementStrategy;
import nl.roka.chess.move.Position;

import static nl.roka.chess.piece.PieceType.EmptySquare;

public interface Piece {
	Piece emptySquare = new PieceEntity(EmptySquare, Color.None,
										MovementStrategy.pawn(Color.White));

	MoveType getMoveType(Position posFrom, Position posTo);

	boolean isHostile(Color color);

	Color getColor();
}
