package nl.roka.chess.piece;

import nl.roka.chess.move.MovementStrategy;
import nl.roka.chess.move.RookMovementStrategy;

import java.util.Arrays;
import java.util.function.Function;

import static nl.roka.chess.piece.PieceType.*;

public class PieceFactory {

	public PieceBuilder builder() {
		return new PieceBuilder();
	}

	public Piece whitePawn() {
		return builder().type(Pawn).movement(MovementStrategy::pawn).white().build();
	}

	public Piece whiteRook() {
		return builder().type(Rook).movement(new RookMovementStrategy()).white().build();
	}

	public Piece whiteBishop() {
		return builder().type(Bishop).movement(MovementStrategy::pawn).white().build();
	}

	public Piece whiteKnight() {
		return builder().type(Knight).movement(MovementStrategy::pawn).white().build();
	}

	public Piece whiteKing() {
		return builder().type(King).movement(MovementStrategy::pawn).white().build();
	}

	public Piece whiteQueen() {
		return builder().type(Queen).movement(MovementStrategy::pawn).white().build();
	}

	public Piece blackPawn() {
		return builder().type(Pawn).movement(MovementStrategy::pawn).black().build();
	}

	public Piece blackRook() {
		return builder().type(Rook).movement(new RookMovementStrategy()).black().build();
	}

	public Piece blackBishop() {
		return builder().type(Bishop).movement(MovementStrategy::pawn).black().build();
	}

	public Piece blackKnight() {
		return builder().type(Knight).movement(MovementStrategy::pawn).black().build();
	}

	public Piece blackKing() {
		return builder().type(King).movement(MovementStrategy::pawn).black().build();
	}

	public Piece blackQueen() {
		return builder().type(Queen).movement(MovementStrategy::pawn).black().build();
	}

	public static class PieceBuilder {
		private Function<Color, MovementStrategy> movementFunction;
		private MovementStrategy movementStrategy;
		private PieceType type;
		private Color color;

		public PieceBuilder white() {
			this.color = Color.White;
			return this;
		}

		public PieceBuilder black() {
			this.color = Color.Black;
			return this;
		}

		public PieceBuilder colorless() {
			this.color = Color.None;
			return this;
		}

		public PieceBuilder movement(Function<Color, MovementStrategy> movementFunction) {
			this.movementFunction = movementFunction;
			return this;
		}

		public PieceBuilder movement(MovementStrategy movementStrategy) {
			this.movementStrategy = movementStrategy;
			return this;
		}

		public PieceBuilder type(PieceType type) {
			this.type = type;
			return this;
		}

		public Piece build() {
			if (type == null)
				throw new IllegalStateException("Type must be one of %s".formatted(Arrays.toString(values())));

			if (color == null)
				throw new IllegalStateException("Specify either black or white");

			MovementStrategy movement;
			if (movementFunction == null && movementStrategy == null)
				throw new IllegalStateException("Configure either movementFunction or movementStrategy, not both!");
			else if (movementStrategy != null)
				movement = movementStrategy;
			else
				movement = movementFunction.apply(color);

			return new PieceEntity(type, color, movement);
		}
	}
}
