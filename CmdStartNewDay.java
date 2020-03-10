
public class CmdStartNewDay extends RecordedCommand implements Command{
	String sDay;
	String pDay;
	
	public void execute(String[] cmdParts)
	{
		try {
			if(cmdParts.length<2)
				throw new ExInsufficientArguments();
			
			sDay=cmdParts[1];
			pDay=SystemDate.getInstance().get();
			SystemDate.getInstance().set(sDay);
			
			addUndoCommand(this);
			clearRedoList();
			
			System.out.println("Done.");
		} catch(ExInsufficientArguments e){
			System.out.println(e.getMessage());
		}
	}
	
	@Override
	public void undoMe()
	{
		SystemDate.getInstance().set(pDay);
		addRedoCommand(this); 
	}
	
	@Override
	public void redoMe()
	{
		SystemDate.getInstance().set(sDay);
		addUndoCommand(this);
	}
}
