package nl.roka.app;

import nl.roka.chess.ChessGame;

public class Main implements Runnable {

	private final Printer printer;
	private final Input input;
	private final Protocol protocol;

	private ChessGame currentGame;

	private boolean running;

	public Main(Printer printer, Input input, Protocol protocol) {
		this.protocol = protocol;
		this.currentGame = new ChessGame().reset();
		this.printer = printer;
		this.input = input;
	}

	public static void main(String[] args) {
		var main = new Main(new Printer(), new Input(), new Protocol());
		main.start();
	}

	public void start() {
		running = true;
		var thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;
	}

	@Override
	public void run() {
		while (running && !Thread.currentThread().isInterrupted()) {
			printer.print(currentGame);
			var cmd = input.readCommand();
			switch (protocol.process(cmd)) {
				case QuitCmd -> doQuit();
				case ResetCmd -> doResetGame();
				case MoveCmd -> doMove(cmd.split(" ")[0], cmd.split(" ")[1]);
				default -> doHelp();
			}
		}
	}

	private void doResetGame()
	{
		currentGame = currentGame.reset();
	}

	private void doQuit()
	{
		running = false;
	}

	private void doMove(String from, String to)
	{
		try {
			currentGame = currentGame.move(from, to);
		} catch (IllegalArgumentException e) {
			printer.println("Error:  Move not allowed");
		}
	}

	private void doHelp()
	{
		printer.println("Que?");
	}
}
