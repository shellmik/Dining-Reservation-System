import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class BookingOffice {
	
	private ArrayList<Reservation> allReservations;		

	//Singleton Pattern
	private static BookingOffice instance = new BookingOffice(); 
	private BookingOffice() { allReservations =new ArrayList<>();}
	public static BookingOffice getInstance(){ return instance; }

	//Adding a reservation
	public Reservation addReservation(String guestName, String phoneNumber,  int totPersons, String sDateDine) 
			throws ExRepeatedBooking, ExDatePassed
	{
		Reservation another = new Reservation(guestName, phoneNumber, totPersons, sDateDine);
		for(Reservation r: allReservations)
		{
			if(r.compareTo(another)==0)
				throw new ExRepeatedBooking();
		}
		allReservations.add(another);
		Collections.sort(allReservations); // allReservations
		return another; 
	}
	
	public void addReservation(Reservation r)
	{
		allReservations.add(r);
		Collections.sort(allReservations);
		r.addTicket();
	}
	
	//Removing a reservation
	public void removeReservation(Reservation r)
	{
		allReservations.remove(r);
		r.removeTicket();
	}
	
	//Canceling a reservation
	public void cancelReservation(Reservation r)
	{
		allReservations.remove(r);
	}
	
	//Recovering a reservation
	public void recoverReservation(Reservation r)
	{
		allReservations.add(r);
		Collections.sort(allReservations); 
	}
	
	//Finding a reservation
	public Reservation findReservation(String sDateDine, int ticketNumber) 
			throws ExBookingNotFound, ExDatePassed
	{
		for (Reservation r: allReservations)
		{
			if(r.isReservation(sDateDine,ticketNumber))
				return r;
		}
		throw new ExBookingNotFound();
	}
	
	//Listing a reservation
	public void listReservations()
	{
		System.out.println(Reservation.getListingHeader()); // Reservation
		for (Reservation r: allReservations)
			System.out.println(r.toString()); // r or r.toString()
	}

	//Listing pending requests
	public void listPendingRequests(String sDateDine) {
		int pendingRequests=0;
		int pendingGuests=0;
		Day dateDine=new Day(sDateDine);
		for (Reservation r: allReservations)
		{
			if(dateDine.compareTo(r.getDateDine())==0&&r.isPending())
			{
				pendingRequests++;
				pendingGuests+=r.gettotPersons();
			}
		}
		System.out.printf("\nTotal number of pending requests = %d "
				+ "(Total number of persons = %d)\n", pendingRequests, pendingGuests);
	}

}
