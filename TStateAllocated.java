
public class TStateAllocated implements TState{
	private Assignment assignment;
	public TStateAllocated(Assignment ass)
	{
		assignment=ass;
	}
	
	@Override
	public String toString(Table t)
	{
		return assignment.tableToString(t);
	}
}
