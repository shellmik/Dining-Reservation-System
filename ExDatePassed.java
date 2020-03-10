
@SuppressWarnings("serial")
public class ExDatePassed extends Exception{
	public ExDatePassed(){super("Date has already passed!");}
	public ExDatePassed(String message){super(message);}
}
