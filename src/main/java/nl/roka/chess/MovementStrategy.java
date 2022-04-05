package nl.roka.chess;

public interface MovementStrategy {
	MoveType getMoveType(Position positionFrom, Position positionTo);
}
