package SortingSSN;

import java.util.Collections;
/**
 * This class is the class for SSN object
 * The object SSN can be created and modified with 
 * the methods and constructor in this class
 * 
 * @author jiajianliu
 *
 */
public class SSN 
{
	private int areaNum;
	private int groupNum;
	private int serialNum;
	/**
	 * Constructs a SSN object with Area number,
	 * Group number, and Serial number
	 * @param area area is the Area Number of the SSN 
	 * @param group group is the Group Number of the SSN
	 * @param serial serial is the Serial Number of the SSN
	 */
	public SSN(int area, int group, int serial)
	{
		// Store the Area number, Group number, and Serial number
		this.areaNum = area;
		this.groupNum = group;
		this.serialNum = serial;
	}
	
	/**
	 * Gets the Area number of the SSN
	 * @return Returns the Area number of the SSN
	 */
	public int getAreaNum()
	{
		return areaNum;
	}
	
	/**
	 * Gets the Group number of the SSN
	 * @return Returns the Group number of the SSN
	 */
	public int getGroupNum()
	{
		return groupNum;
	}
	
	/**
	 * Gets the Serial number of the SSN
	 * @return Returns the Serial number of the SSN
	 */
	public int getSerialNum()
	{
		return serialNum;
	}
	
	/**
	 * Adds up the Area Number, serial Number, and Group Number with factors
	 * @return Returns the sum of the 3 numbers with factors
	 */
	public int addUp()
	{
		return (areaNum*1000000) + (groupNum*10000) + serialNum;
	}
	
	/**
	 * Gets the SSN in String format
	 * @return Returns the SSN in String format
	 */
	public String getSSN()
	{	
		// Converts the numbers into String
		String area = areaNum + "";
		String group = groupNum + "";
		String serial = serialNum + "";
		
		// modifies the Strings base on different situations
		if (areaNum < 100)
			area  =  "0" + areaNum;
		if (areaNum < 10)
			area = 	"00" + areaNum;
		if (groupNum < 10)
			group = "0" + groupNum;
		if (serialNum < 1000)
			serial =  "0" + serialNum;
		if (serialNum < 100)
			serial = 	"00" + serialNum;
		if (serialNum < 10)
			serial = "000" + serialNum;
	
		// Combines the 3 strings into 1
		String ssn = area + "-" + group + "-" + serial;
		// Returns the SSN in String format
		return ssn;
	}
	
	
}


