package nl.roka.chess;

public record Move(Position posFrom, Piece pieceToMove, Position posTo, Piece pieceAtDestination) {

}
