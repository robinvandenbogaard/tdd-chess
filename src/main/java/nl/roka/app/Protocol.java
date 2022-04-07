package nl.roka.app;

public class Protocol
{
	public Command process(String cmd)
	{
		if (cmd.equalsIgnoreCase("q"))
			return Command.QuitCmd;
		else if (cmd.equalsIgnoreCase("r"))
			return Command.ResetCmd;
		else if (cmd.matches("[abcdefgh][12345678] [abcdefgh][12345678]"))
			return Command.MoveCmd;
		return Command.Unknown;
	}
}
