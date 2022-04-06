package nl.roka.chess.move;

/**
 * NotAllowed means you can not make that move
 * Passive means you can move there if the spot is empty
 */

public enum MoveType {
	NotAllowed,
	Attack,
	Passive
}
