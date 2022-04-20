package nl.roka.chess.piece;

import nl.roka.chess.move.MoveType;
import nl.roka.chess.move.MovementStrategy;
import nl.roka.chess.move.Position;

import java.util.Objects;

import static nl.roka.chess.piece.Color.*;

final class PieceEntity implements Piece {
	private final PieceType type;
	private final Color color;
	private final MovementStrategy movement;

	PieceEntity(PieceType type, Color color,
				MovementStrategy movement) {
		this.type = type;
		this.color = color;
		this.movement = movement;
	}

	@Override
	public MoveType getMoveType(Position posFrom, Position posTo) {
		return movement.getMoveType(posFrom, posTo);
	}

	@Override
	public boolean isHostile(Color color) {
		return !this.color.equals(color);
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public PieceType getPieceType() {
		return type;
	}

	@Override
	public boolean isEmpty() {
		return this.equals(emptySpot);
	}

	@Override
	public boolean isNotEmpty() {
		return !isEmpty();
	}

	@Override
	public boolean hasColor(Color color) {
		return this.color.equals(color);
	}

	@Override
	public boolean isPieceType(PieceType type) {
		return this.type.equals(type);
	}

	@Override
	public Color getOppositeColor() {
		return switch (this.color) {
			case White -> Black;
			case Black -> White;
			case None -> None;
		};
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (PieceEntity) obj;
		return Objects.equals(this.type, that.type) &&
				Objects.equals(this.color, that.color);
	}

	@Override
	public int hashCode() {
		return Objects.hash(type, color);
	}

	@Override
	public String toString() {
		return "PieceEntity[" +
				"type=" + type + ", " +
				"color=" + color + ", " +
				"movement=" + movement + ']';
	}

}
