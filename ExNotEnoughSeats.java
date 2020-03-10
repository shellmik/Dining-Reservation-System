
@SuppressWarnings("serial")
public class ExNotEnoughSeats extends Exception{
	public ExNotEnoughSeats(){super("Not enough seats for the booking!");}
	public ExNotEnoughSeats(String message){super(message);}
}
