import java.util.ArrayList;
import java.util.Collections;


public class TableRecordList {
	
	private ArrayList<TableRecord> allTableRecords;
	
	//Singleton Pattern
	private static TableRecordList instance = new TableRecordList(); 
	private TableRecordList() { allTableRecords =new ArrayList<>();}
	public static TableRecordList getInstance(){ return instance; }
	
	//Adding a TableRecord
	public TableRecord addTableRecord(String sDateDine)
	{
		TableRecord another = new TableRecord(sDateDine);
		for(TableRecord tr: allTableRecords)
		{
			if(tr.compareTo(another)==0)
			{
				return tr;
			}
		}
		allTableRecords.add(another);
		Collections.sort(allTableRecords);
		return another; 
	}
	
	public void addTableRecord(TableRecord r)
	{
		allTableRecords.add(r);
	}
	
	//Removing a TableRecord
	public void removeTableRecord(TableRecord r)
	{
		allTableRecords.remove(r);
	}
	
	//Finding a TableRecord
	public TableRecord findTableRecord(String sDateDine)
	{
		for(TableRecord tr: allTableRecords)
		{
			if(tr.isValid(sDateDine))
				return tr;
		}
		return null;
	}
}
