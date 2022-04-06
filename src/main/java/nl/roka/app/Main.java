package nl.roka.app;

import nl.roka.chess.ChessGame;

public class Main implements Runnable {

	private final Printer printer;
	private final Input input;
	private ChessGame currentGame;

	public Main(Printer printer, Input input) {
		this.currentGame = new ChessGame().reset();
		this.printer = printer;
		this.input = input;
	}

	public static void main(String[] args) {
		var main = new Main(new Printer(), new Input());
		var thread = new Thread(main);
		thread.start();
	}

	@Override
	public void run() {
		var running = true;
		while (running && !Thread.currentThread().isInterrupted()) {
			printer.print(currentGame);
			var cmd = input.readCommand();
			if (input.isQuit(cmd)) {
				running = false;
			} else if (input.isReset(cmd)) {
				currentGame = currentGame.reset();
			} else if (input.isMove(cmd)) {
				var positions = cmd.split(" ");
				try {
					currentGame = currentGame.move(positions[0], positions[1]);
				} catch (IllegalArgumentException e) {
					printer.println("Error:  Move not allowed");
				}
			} else {
				System.out.println("Que?");
			}
		}
	}
}
