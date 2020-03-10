
public class CmdSuggestTable implements Command{
	public void execute(String[] cmdParts)
	{
		try {
			//Exception Handling
			if(cmdParts.length<3)
				throw new ExInsufficientArguments();
			
			//Find the reservation
			String sDateDine=cmdParts[1];
			int ticketNumber=Integer.parseInt(cmdParts[2]);
			BookingOffice bo = BookingOffice.getInstance();
			Reservation r = bo.findReservation(sDateDine, ticketNumber);
			
			//Adding a table record
			TableRecordList trl = TableRecordList.getInstance();
			TableRecord tr=trl.addTableRecord(sDateDine);
			
			//Suggest Table
			r.checkRepetition();
			int totPersons=r.gettotPersons();
			String suggestion=tr.suggesTables(totPersons);
			System.out.printf("Suggestion for %d persons: %s\n",
					totPersons, suggestion);
			
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e){
			System.out.println(e.getMessage());
		} catch (ExDatePassed e){
			System.out.println(e.getMessage());
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExRepeatedAllocation e) {
			System.out.println(e.getMessage());
		}
	}
}
