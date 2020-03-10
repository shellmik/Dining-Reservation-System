
@SuppressWarnings("serial")
public class ExRepeatedAllocationT extends Exception{
	public ExRepeatedAllocationT(){super();}
	public ExRepeatedAllocationT(String message){super(message+" is already reserved by another booking!");}
}
