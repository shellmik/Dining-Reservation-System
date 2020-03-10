import java.util.ArrayList;


public class Table implements Comparable<Table>{
	private String id;
	private int totPersons;
	private TState status;
	private boolean suggestedStatus;
	
	public Table(String aId, int totPersons)
	{
		this.id=aId;
		this.totPersons=totPersons;
		this.status=new TStateAvailable();
		this.suggestedStatus=false;
	}
	
	//Access Function
	public String getId()
	{
		return this.id;
	}
	
	public int getTotPersons()
	{
		return totPersons;
	}
	
	public static int getTotSeats(ArrayList<Table> tables)
	{
		int totSeats=0;
		for (Table t: tables)
		{
			totSeats+=t.getTotPersons();
		}
		return totSeats;
	}
	
	//AssignToReservation
	public boolean checkRepetition()
			throws ExRepeatedAllocationT 
	{
		if(status instanceof TStateAllocated)
		{
			throw new ExRepeatedAllocationT("Table " + id);	
		}
		return true;
	}
	
	public void assignToReservation(Assignment ass) 
	{
		status=new TStateAllocated(ass);
	}
	
	public void clearFromReservation()
	{
		status=new TStateAvailable();
	}
	
	public boolean isAllocated()
	{
		return (status instanceof TStateAllocated);
	}
	
	
	//AssignToSuggestion
	public void assignToSuggestion() 
	{
		this.suggestedStatus=true;
	}
	
	public void clearFromSuggestion() 
	{
		this.suggestedStatus=false;
	}
	
	public boolean isSuggested()
	{
		return suggestedStatus;
	}
	
	//Other Functions
	@Override
	public int compareTo(Table another) 
	{
		int codeThis=Integer.parseInt(this.id.substring(1, 3));
		int codeAnother=Integer.parseInt(another.id.substring(1, 3));
		if(this.totPersons!=another.totPersons)
		{
			if(this.totPersons > another.totPersons)
				return 1;
			return -1;
		}
		else if(codeThis!=codeAnother)
		{
			if(codeThis > codeAnother)
				return 1;
			return -1;
		}
		return 0;
	}
	
	@Override
	public String toString() 
	{
		return status.toString(this);
	}
}
