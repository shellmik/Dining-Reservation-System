
public class CmdListTableAllocations implements Command{
	public void execute(String[] cmdParts)
	{
		TableRecordList trl = TableRecordList.getInstance();
		TableRecord tr= trl.findTableRecord(cmdParts[1]);
		tr.listTableAllocations();
		
		BookingOffice bo = BookingOffice.getInstance();
		bo.listPendingRequests(cmdParts[1]);
	}
}
