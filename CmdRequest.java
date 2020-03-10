
public class CmdRequest extends RecordedCommand implements Command{
	Reservation r;
	TableRecord tr;

	public void execute(String[] cmdParts)
	{
		try {
			//Exception Handling
			if(cmdParts.length<5)
				throw new ExInsufficientArguments();
			
			String guestName=cmdParts[1];
			String phoneNumber=cmdParts[2];
			int totPersons=Integer.parseInt(cmdParts[3]);//Wrong number format
			String sDateDine=cmdParts[4];
			
			//Adding a reservation
			BookingOffice bo = BookingOffice.getInstance();
			r = bo.addReservation(guestName, phoneNumber, totPersons, sDateDine);
			
			//Adding a table record
			TableRecordList trl = TableRecordList.getInstance();
			tr=trl.addTableRecord(sDateDine);
			
			//Undo and Redo
			addUndoCommand(this);
			clearRedoList();
			
			System.out.printf("Done. Ticket code for %s: %d\n", r.getDateDine(), r.getTicket());
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e){
			System.out.println(e.getMessage());
		} catch (ExRepeatedBooking e){
			System.out.println(e.getMessage());
		} catch (ExDatePassed e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		BookingOffice bo = BookingOffice.getInstance();
		bo.removeReservation(r);
		TableRecordList trl = TableRecordList.getInstance();
		trl.removeTableRecord(tr);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
		BookingOffice bo = BookingOffice.getInstance();
		bo.addReservation(r);
		TableRecordList trl = TableRecordList.getInstance();
		trl.addTableRecord(tr);
		addUndoCommand(this);
	}
}
