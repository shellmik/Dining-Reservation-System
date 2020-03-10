import java.util.HashMap;

public class BookingTicketController {
	private static HashMap<Day,Integer> tControllers=new HashMap<>();
	
	//Taking ticket
	public static int takeTicket(Day d)
	{
		Integer ticket=tControllers.get(d);
		if(ticket==null)
		{
			tControllers.put(d.clone(),2);
			return 1;
		}
		else
		{
			tControllers.put(d, ticket+1);
			return ticket;
		}
	}
	
	//Removing ticket
	public static int removeTicket(Day d)
	{
		Integer ticket=tControllers.get(d);
		tControllers.put(d, ticket-1);
		return ticket-2;
	}
	
	//Adding ticket
	public static int addTicket(Day d)
	{
		Integer ticket=tControllers.get(d);
		tControllers.put(d, ticket+1);
		return ticket;
	}
}
