package nl.roka.chess;

import nl.roka.chess.move.MoveType;
import nl.roka.chess.move.MovementStrategy;
import nl.roka.chess.move.PawnMovement;
import nl.roka.chess.move.Position;

import static nl.roka.chess.move.MoveDirection.Down;
import static nl.roka.chess.move.MoveDirection.Up;

public enum Piece {
	BlackRook(new PawnMovement(Down)), BlackHorse(new PawnMovement(Down)), BlackBishop(
			new PawnMovement(Down)), BlackKing(new PawnMovement(Down)), BlackQueen(new PawnMovement(Down)), BlackPawn(
			new PawnMovement(Down)),
	WhiteRook(new PawnMovement(Up)), WhiteHorse(new PawnMovement(Up)), WhiteBishop(new PawnMovement(Up)), WhiteKing(
			new PawnMovement(Up)), WhiteQueen(new PawnMovement(Up)), WhitePawn(new PawnMovement(Up)),
	None(new PawnMovement(Up));

	private final MovementStrategy pawnMovement;

	Piece(MovementStrategy pawnMovement) {
		this.pawnMovement = pawnMovement;
	}

	public MoveType getMoveType(Position positionFrom, Position positionTo) {
		return pawnMovement.getMoveType(positionFrom, positionTo);
	}
}
