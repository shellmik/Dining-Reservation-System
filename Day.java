import java.io.*;
import java.util.Scanner;

public class Day implements Cloneable, Comparable<Day>{
	
	private int year;
	private int month;
	private int day;
	private static final String MonthNames="JanFebMarAprMayJunJulAugSepOctNovDec";
	
	//Constructor
	public Day(int y, int m, int d) {
		this.year=y;
		this.month=m;
		this.day=d;		
	}
	
	public Day(String sDay) {
		set(sDay);
	}

	 //Set year,month,day based on a string like 01-Mar-2019
	public void set(String sDay)
	{		
		String[] sDayParts = sDay.split("-");
		this.day = Integer.parseInt(sDayParts[0]); //Apply Integer.parseInt for sDayParts[0];
		this.year = Integer.parseInt(sDayParts[2]);
		this.month = MonthNames.indexOf(sDayParts[1])/3+1;
	}
	
	//Get year,month,day in a string like 01-Mar-2019
	public String get() {
		return this.toString();
	}
	
	//Convert year,month,day to a string like 01-Mar-2019
	@Override
	public String toString() 
	{		
		return day+"-"+ MonthNames.substring((month-1)*3, month*3) + "-"+ year; // (month-1)*3,(month)*3
	}
	
	//Clone a string
	@Override
	public Day clone() 
	{
		Day copy=null;
		try {
			copy = (Day) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return copy;
	}
	
	//Comparing
	@Override
	public int compareTo(Day another) {
		if(this.year!=another.year)
		{
			if(this.year > another.year)
				return 1;
			return -1;
		}
		else if(this.month!=another.month)
		{
			if(this.month > another.month)
				return 1;
			return -1;
		}
		else if(this.day!=another.day)
		{
			if(this.day > another.day)
				return 1;
			return -1;
		}
		return 0;
	}
	
	//Prepare for hashing
	@Override
	public int hashCode()
	{
		return year*10000+month*100+day;
	}
	
	@Override
	public boolean equals(Object otherObject)
	{
		if(otherObject==null)
			return false;
		
		if(this.getClass()!=otherObject.getClass())
			return false;
		
		Day otherDay =(Day)otherObject;
		
		if(!this.get().equals(otherDay.get()))
			return false;
		
		return true;
	}
	
	// check if a given year is a leap year
	static public boolean isLeapYear(int y)
	{
		if (y%400==0)
			return true;
		else if (y%100==0)
			return false;
		else if (y%4==0)
			return true;
		else
			return false;
	}
	
	// check if y,m,d valid
	static public boolean valid(int y, int m, int d)
	{
		if (m<1 || m>12 || d<1) return false;
		switch(m){
			case 1: case 3: case 5: case 7:
			case 8: case 10: case 12:
					 return d<=31; 
			case 4: case 6: case 9: case 11:
					 return d<=30; 
			case 2:
					 if (isLeapYear(y))
						 return d<=29; 
					 else
						 return d<=28; 
		}
		return false;
	}

	// Return a string for the day like dd MMM yyyy
	/*public String toString() {
		
		final String[] MonthNames = {
				"Jan", "Feb", "Mar", "Apr",
				"May", "Jun", "Jul", "Aug",
				"Sep", "Oct", "Nov", "Dec"};
		
		return day+" "+ MonthNames[month-1] + " "+ year;
	}*/
}
