package nl.roka.chess.move;

public interface MovementStrategy {
	MoveType getMoveType(Position positionFrom, Position positionTo);
}
