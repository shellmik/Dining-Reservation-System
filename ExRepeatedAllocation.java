
@SuppressWarnings("serial")
public class ExRepeatedAllocation extends Exception{
	public ExRepeatedAllocation(){super("Table(s) already assigned for this booking!");}
	public ExRepeatedAllocation(String message){super(message);}
}
