@SuppressWarnings("serial")
public class ExTableNotFound extends Exception{
	public ExTableNotFound(){super();}
	public ExTableNotFound(String message){super(message+" not found!");}
}
