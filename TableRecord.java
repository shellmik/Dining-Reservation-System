import java.util.ArrayList;
import java.util.Collections;


public class TableRecord implements Comparable<TableRecord>{
	private ArrayList<Table> allTables;
	private Day dateDine;
	
	//Constructor
	public TableRecord(String sDateDine) 
	{ 
		this.dateDine=new Day(sDateDine);
		allTables =new ArrayList<>();
		for(int i=1;i<=10;i++)
		{
			String id=null;
			if(i<10)
				id="T0"+Integer.toString(i);
			else
				id="T"+Integer.toString(i);
			allTables.add(new Table(id, 2));
		}
		for(int i=1;i<=6;i++)
		{
			String id="F0"+Integer.toString(i);
			allTables.add(new Table(id, 4));
		}
		for(int i=1;i<=3;i++)
		{
			String id="H0"+Integer.toString(i);
			allTables.add(new Table(id, 8));
		}
	}
	
	//Suggest Tables
	public String suggesTables(int totPersons){
		String suggestion="";
		for(Table t: allTables)
		{
			if(totPersons-8>=-1)
			{
				if((!t.isAllocated())&&(!t.isSuggested())&&t.getTotPersons()==8)
				{
					suggestion+=t.getId()+" ";
					t.assignToSuggestion();
					totPersons-=8;
				}
			}
			else break;
		}
		for(Table t: allTables)
		{
			if(totPersons-4>=-1)
			{
				if((!t.isAllocated())&&(!t.isSuggested())&&t.getTotPersons()==4)
				{
					suggestion+=t.getId()+" ";
					t.assignToSuggestion();
					totPersons-=4;
				}
			}
			else break;
			
		}

		for(Table t: allTables)
		{
			if(totPersons-2>=-1)
			{
				if((!t.isAllocated())&&(!t.isSuggested())&&t.getTotPersons()==2)
				{
					suggestion+=t.getId()+" ";
					t.assignToSuggestion();
					totPersons-=2;
				}
			}
			else break;
		}		

		for(Table t: allTables)
		{
			t.clearFromSuggestion();
		}
		
		if(totPersons>0)
			suggestion="Not enough tables";
		
		return suggestion;
	}
	
	//Cancel Booking
	public void cancelBooking(Assignment ass) {
		if(ass==null)
			return;
		else
		{
			ArrayList<Table> tables=ass.getTables();
			for(Table t: tables)
			{
				t.clearFromReservation();
			}
		}
	}
	
	public void recoverBooking(Assignment ass) {
		ArrayList<Table> tables=ass.getTables();
		for(Table t: tables)
		{
			t.assignToReservation(ass);
		}
	}
	
	//Find a table
		public Table findTable(String aId) 
				throws ExTableNotFound 
		{
			for (Table t: allTables)
			{
				if(aId.equals(t.getId()))
				{
					return t;
				}
			}
			throw new ExTableNotFound("Table Code " + aId);
		}
		
	//List table allocation
	public void listTableAllocations()
	{	//print the allocated tables
		System.out.println("Allocated tables: ");
		boolean NoneAllocated=true;
		for (Table t: allTables)
		{
			if(t.isAllocated()==true)
			{
				System.out.println(t.toString());
				NoneAllocated=false;
			}
		}
		if(NoneAllocated)
			System.out.println("[None]");
		System.out.println();

		//print the available tables
		System.out.println("Available tables: ");
		boolean NoneAvailable=true;
		for (Table t: allTables)
		{
			if(t.isAllocated()==false)
			{
				System.out.print(t.toString()+" ");
				NoneAvailable=false;
			}
		}
		if(NoneAvailable)
			System.out.println("[None]");
		System.out.println();
	}
	
	//Check if it's a valid TableRecord
	public boolean isValid(String sDateDine)
	{
		Day dateDine=new Day(sDateDine);
		if(this.dateDine.compareTo(dateDine)==0)
			return true;
		else
			return false;
	}
	
	@Override
	public int compareTo(TableRecord another) 
	{
		return this.dateDine.compareTo(another.dateDine);
	}

}
