package nl.roka.chess.move;

import nl.roka.chess.Piece;

public record Move(Position posFrom, Piece pieceToMove, Position posTo, Piece pieceAtDestination) {

}
