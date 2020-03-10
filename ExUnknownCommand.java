@SuppressWarnings("serial")
public class ExUnknownCommand extends Exception{
	public ExUnknownCommand(){super("Unknown command.");}
	public ExUnknownCommand(String message){super(message);}
}
