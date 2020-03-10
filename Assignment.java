import java.util.ArrayList;


public class Assignment {
	Reservation reservation;
	ArrayList<Table> tables;
	
	//Constructor
	public Assignment(Reservation r, ArrayList<Table> ts) 
			throws ExNotEnoughSeats
	{
		reservation=r;
		tables=ts;
		if(r.gettotPersons()> Table.getTotSeats(tables))
			throw new ExNotEnoughSeats();
	}
	
	//Access Method
	public ArrayList<Table> getTables()
	{
		return tables;
	}
	
	//toString Methods
	public String tableToString(Table t)
	{
		String str=t.getId()+" (Ticket "+ reservation.getTicket()+ ")";
		return str;
	}

	public String reservationToString()
	{
		String str="Table assigned: ";
		for(Table t : tables)
		{
			str+=t.getId()+" ";
		}
		return str;
	}
}
