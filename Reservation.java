import java.util.ArrayList;


public class Reservation implements Comparable<Reservation>{
	private String guestName;
	private String phoneNumber;
	private int totPersons;
	private Day dateDine;
	private Day dateRequest;
	private int ticket;
	private RState status;
	private Assignment assignment;
	
	//Constructor
	public Reservation(String guestName, String phoneNumber, int totPersons, String sDateDine)
			throws ExDatePassed
	{	
		this.guestName=guestName;//Set all object fields
		this.phoneNumber=phoneNumber;
		this.totPersons=totPersons;
		this.dateDine=new Day(sDateDine);
		this.dateRequest=(SystemDate.getInstance().clone());
		this.status=new RStatePending();
		this.ticket=BookingTicketController.takeTicket(this.dateDine);
		
		if(dateDine.compareTo(dateRequest)<0)
			throw new ExDatePassed();
	}
	
	//TicketRelated
	public void addTicket(){
		this.ticket=BookingTicketController.addTicket(this.dateDine);
	}
	
	public void removeTicket(){
		this.ticket=BookingTicketController.removeTicket(this.dateDine);
	}
	
	//Access functions
	public int getTicket(){
		return ticket;
	}
	
	public int gettotPersons() {
		return totPersons;
	}
	
	public Day getDateDine() {
		return dateDine;
	}
	
	public Assignment getAssignment() {
		return assignment;
	}
	
	//Check if it's a valid reservation
	public boolean isReservation(String sDateDine, int ticketNumber)
			throws ExDatePassed
	{
		Day dateDine=new Day(sDateDine);
		if(dateDine.compareTo(SystemDate.getInstance())<0)
			throw new ExDatePassed();
		if(this.dateDine.compareTo(dateDine)==0&&this.ticket==ticketNumber)
			return true;
		else
			return false;
	}
	
	//AssignTable
	public boolean checkRepetition()
			throws ExRepeatedAllocation  
	{
		if(status instanceof RStateTableAllocated)
		{
			throw new ExRepeatedAllocation();
		}
		return true;
	}
	
	public void assignTable(Assignment assignment) 
			throws ExRepeatedAllocation 
	{
		this.assignment=assignment;
		this.status= new RStateTableAllocated(assignment);
	}
	
	//TablesRelated
	public void clearTable() 
	{
		status=new RStatePending();
	}
	
	public boolean isPending()
	{
		return (status instanceof RStatePending);
	}
	
	//Other functions
	@Override
	public int compareTo(Reservation another) 
	{
		if(!(this.guestName.compareTo(another.guestName)==0))
			return this.guestName.compareTo(another.guestName);
		else if(!(this.phoneNumber.compareTo(another.phoneNumber)==0))
			return this.phoneNumber.compareTo(another.phoneNumber);
		else
			return this.dateDine.compareTo(another.dateDine);
	}
	
	@Override
	public String toString() 
	{
		//Learn: "-" means left-aligned
		return String.format("%-13s%-11s%-14s%-25s%4d       %s", 
				guestName, phoneNumber, dateRequest, dateDine+String.format(" (Ticket %d)", ticket), totPersons, status);
	}

	public static String getListingHeader() 
	{
		return String.format("%-13s%-11s%-14s%-25s%-11s%s",
				"Guest Name", "Phone", "Request Date", "Dining Date and Ticket", "#Persons", "Status");
	}



}
