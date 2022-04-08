package nl.roka.chess.move;

/**
 * Attack means you can move there if the spot is empty or a hostile piece is there
 * AttackOnly means you can only move there if there is an hostile piece
 * NotAllowed means you can not make that move
 * Passive means you can move there if the spot is empty
 */

public enum MoveType {
	Attack,
	AttackOnly,
	NotAllowed,
	Passive
}
