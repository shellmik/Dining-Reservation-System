
@SuppressWarnings("serial")
public class ExRepeatedBooking extends Exception{
	public ExRepeatedBooking(){super("Booking by the same person for the dining date already exists!");}
	public ExRepeatedBooking(String message){super(message);}
}
