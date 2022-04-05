package nl.roka.chess.move;

import nl.roka.chess.piece.Color;

import static nl.roka.chess.move.MoveDirection.Down;
import static nl.roka.chess.move.MoveDirection.Up;

public interface MovementStrategy {
	static MovementStrategy pawn(Color color) {
		return new PawnMovement(color.equals(Color.White) ? Up : Down);
	}

	MoveType getMoveType(Position positionFrom, Position positionTo);
}
