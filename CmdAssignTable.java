import java.util.ArrayList;


public class CmdAssignTable extends RecordedCommand implements Command{
	
	Reservation r;
	TableRecord tr;
	ArrayList<Table> tables=new ArrayList<>();
	Assignment ass;
	
	public void execute(String[] cmdParts)
	{
		try {
			//Exception handling
			if(cmdParts.length < 4)
				throw new ExInsufficientArguments();

			//Find the reservation
			String sDateDine=cmdParts[1];
			int ticketNumber=Integer.parseInt(cmdParts[2]);
			BookingOffice bo = BookingOffice.getInstance();
			r = bo.findReservation(sDateDine, ticketNumber);
			
			//Find the tables
			TableRecordList trl = TableRecordList.getInstance();
			tr = trl.findTableRecord(sDateDine);
			for(int i=3;i<cmdParts.length;i++)
			{
				Table t = tr.findTable(cmdParts[i]);
				tables.add(t);
			}
			
			//Make Assignment
			ass=new Assignment(r, tables);
			r.checkRepetition();
			for(Table t : tables)
			{
				t.checkRepetition();
			}
			r.assignTable(ass);
			for(Table t : tables)
			{
				t.assignToReservation(ass);
			}
			
			//Undo and Redo
			addUndoCommand(this);
			clearRedoList();
			
			System.out.print("Done.\n");
		} catch (NumberFormatException e) {
			System.out.println("Wrong number format!");
		} catch (ExInsufficientArguments e){
			System.out.println(e.getMessage());
		} catch (ExBookingNotFound e){
			System.out.println(e.getMessage());
		} catch (ExDatePassed e){
			System.out.println(e.getMessage());
		} catch (ExRepeatedAllocation e) {
			System.out.println(e.getMessage());
		} catch (ExTableNotFound e) {
			System.out.println(e.getMessage());
		} catch (ExRepeatedAllocationT e) {
			System.out.println(e.getMessage());
		} catch (ExNotEnoughSeats e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		r.clearTable();
		for(Table t : tables)
		{
			t.clearFromReservation();
		}
		addRedoCommand(this);
	}
	
	@Override
	public void redoMe()
	{
		try {
			r.checkRepetition();
			for(Table t : tables)
			{
				t.checkRepetition();
			}
			r.assignTable(ass);
			for(Table t : tables)
			{
				t.assignToReservation(ass);
			}
		} catch (ExRepeatedAllocation e) {
			System.out.println(e.getMessage());
		} catch (ExRepeatedAllocationT e) {
			System.out.println(e.getMessage());
		}
		addUndoCommand(this);
	}
}
