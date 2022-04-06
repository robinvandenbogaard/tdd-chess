package nl.roka.app;

import java.util.Scanner;

public class Input {
	private final Scanner scanner;

	public Input() {
		scanner = new Scanner(System.in);
	}

	public String readCommand() {
		System.out.print("> ");
		return scanner.nextLine();
	}

	public boolean isQuit(String cmd) {
		return cmd.equalsIgnoreCase("q");
	}

	public boolean isMove(String cmd) {
		return cmd.matches("[abcdefgh][12345678] [abcdefgh][12345678]");
	}

	public boolean isReset(String cmd) {
		return cmd.equalsIgnoreCase("r");
	}
}
