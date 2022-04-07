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
}
