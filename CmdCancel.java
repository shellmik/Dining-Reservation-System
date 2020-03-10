
public class CmdCancel extends RecordedCommand implements Command{
	Reservation r;
	Assignment ass;
	TableRecord tr;

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
			r = bo.findReservation(sDateDine, ticketNumber);
		
			//Finding a table record
			TableRecordList trl = TableRecordList.getInstance();
			tr=trl.findTableRecord(sDateDine);
			
			//Cancel a Booking
			bo.cancelReservation(r);
			ass=r.getAssignment();
			tr.cancelBooking(ass);
			
			//Undo and Redo
			addUndoCommand(this);
			clearRedoList();
			
			System.out.printf("Done.\n");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e){
			System.out.println(e.getMessage());
		} catch (ExDatePassed e){
			System.out.println(e.getMessage());
		} catch (ExBookingNotFound e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		BookingOffice bo = BookingOffice.getInstance();
		bo.recoverReservation(r);
		tr.recoverBooking(ass);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
		BookingOffice bo = BookingOffice.getInstance();
		bo.cancelReservation(r);
		tr.cancelBooking(ass);
		addUndoCommand(this); 
	}
}
