public class RStateTableAllocated implements RState{
	private Assignment assignment;
	public RStateTableAllocated(Assignment ass)
	{
		assignment=ass;
	}
	
	@Override
	public String toString()
	{
		String str=assignment.reservationToString();		
		return str;
	}
}
