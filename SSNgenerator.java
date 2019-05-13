package SortingSSN;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
/**
 * This class is the user interface of the program
 * By running SSNgenerator, the user will be able to 
 * create a file with 300 random SSNs and files 
 * with sorted SSNs by entering file directory
 * 
 * @author jiajianliu
 *
 */
public class SSNgenerator {
	/**
	 * A method for printing any object received on parameter
	 * This method is abbreviation for System.out.println(Object)
	 * 
	 * @param o o is any object
	 */
	public static void sop(Object o)
	{
		System.out.println(o);
	}
	
	/**
	 * A method for comparing two SSNs
	 * It will return true if the two SSNs 
	 * have the exactly same Area number, 
	 * Group number, and Serial number.
	 * Otherwise, it will return false.
	 * 
	 * @param a a is the input SSN to be compared
	 * @param b	b is the input SSN to be compared
	 * @return Returns true or false base on the situation
	 */
	public static boolean CompareSSN(SSN a, SSN b)
	{
		// check if the SSNs are the same
		if (a.getAreaNum() == b.getAreaNum() &&
				a.getGroupNum() == b.getGroupNum() &&
				a.getSerialNum() == b.getSerialNum())
			return true; // return true if they are the same
		else 
			return false; // false otherwise
	}
	
	/**
	 * A method for converting a String to SSN
	 * It will return a SSN based on the String input
	 * 
	 * @param s s is the String input
	 * @return Returns a SSN object base on the String input
	 */
	public static SSN toSSN(String s)
	{
		// replace all the - in the String so that only the digits have left
		s = s.replaceAll("-", "");
		// get the 3 digits of area number
		String area = s.substring(0, 3);
		// get the 2 digits of group number
		String group = s.substring(3,5);
		// get the 4 digits of serial number
		String serial = s.substring(5,9);
		
		// convert the strings into integer
		int areaNum = Integer.parseInt(area);
		int groupNum = Integer.parseInt(group);
		int serialNum = Integer.parseInt(serial);
		
		// return the SSN 
		return new SSN(areaNum, groupNum,serialNum);
	}
	
	/**
	 * A method to print the total number of each area
	 *  base on the input ArrayList
	 * It will return a String of the statics
	 * 
	 * @param A A is the SSN ArrayList
	 * @return Returns a string of the statics
	 */
	public static String printTotal(ArrayList<SSN> A)
	{
		// initialize the areas to strings
		String NeCS = "NortheastCoast States";
		String SCS = "SouthCoast States";
		String MS = "SouthCoast States";
		String NwCS = "Northwest Coast States";
		String WCS = "West Coast States";
		// initialize counters for each area
		int NeCScount = 0;
		int SCScount = 0;
		int MScount = 0;
		int NwCScount = 0;
		int WCScount = 0;
		// counts how many SSNs in each area
		for (int i = 0; i < A.size(); i++)
		{
			int areaI = A.get(i).getAreaNum();
			if (areaI >= 0 && areaI <= 199)
			{
				NeCScount++;
			}
			if (areaI >= 200 && areaI <= 399)
			{
				SCScount++;
			}
			if (areaI >= 400 && areaI <= 599)
			{
				MScount++;
			}
			if (areaI >= 600 && areaI <= 799)
			{
				NwCScount++;
			}
			if (areaI >= 800 && areaI <= 999)
			{
				WCScount++;
			}
			
		}
		// returns the string with statics
		return NeCS +": " + NeCScount + " people \n" +
		SCS +": " + SCScount + " people \n" +
		MS +": " + MScount + " people \n" +
		NwCS +": " + NwCScount + " people \n" +
		WCS +": " + WCScount + " people";
	}
	
	// variable for saving directory of Random_SSN.txt
	static String dirOfRandom = "";
	// variable for checking if the Random been refreshed 
	// after the user created the files and relauch this program
	static boolean RandomRefreshed = false;
	
	/**
	 * The executing method for creating a user interface with functions.
	 */
	public static void main(String[] args) throws IOException 
	{
				//Create the user interface window
				JFrame parent = new JFrame("SSNgenerator");
				parent.setSize(400,400);
				parent.setLayout(new GridLayout(5,1));
				
				// Create the buttons with Button names
				JButton buttonRandom = new JButton("Create Random_SSN.txt");
				JButton buttonQuick = new JButton("Create Quick_SSN.txt");
				JButton buttonBucket = new JButton("Create Bucket_SSN.txt");
				JButton buttonRadix = new JButton("Create Radix_SSN.txt");
				JButton buttonClose = new JButton("Close/Exit");
				
				// Add the buttons to the user interface window
				parent.add(buttonRandom);
				parent.add(buttonQuick);
				parent.add(buttonBucket);
				parent.add(buttonRadix);
				parent.add(buttonClose);
				//Make the user interface appear
				parent.setVisible(true);
				

	// Creates a Random_SSN.txt file 
	// if the user click on this button
	// The order of the SSNs is randomized		
	buttonRandom.addActionListener(new java.awt.event.ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// randomSSN start
			
			// Initialize the variables needed
			ArrayList<SSN> randomSSN = new ArrayList<SSN>();
			int countIndex = 0;
			boolean repeat = false;
			
					// If the the number of SSNs hasn't reach 300, keep making random SSNs
	generateSSN:	while (countIndex < 300)
					{
						// creates the random area number, group number, and serial numbers
						int area = (int) (Math.random() * 1000);
						int group = (int) (Math.random() * 100);
						int serial = (int)(Math.random() * 10000);
						
						// store the new SSN with the 3 numbers in the randomSSN arrayList
						randomSSN.add(new SSN(area, group, serial));
						// increase the counter since the new SSN has been added
						countIndex++;
						
						// check if there is a pair of repeated SSN in the list
						for (int i = 0; i < randomSSN.size(); i++)
						{
							// get the current SSN
							SSN current = randomSSN.get(i);
							for (int j = 0; j < randomSSN.size(); j++)
							{
								// if the second SSN's index is not the same as current's index
								if (j != i)
								{
									// check if these two SSNs are the same, 
									// repeat will become true if they are the same
									// else, it will stay false
									repeat = CompareSSN(current, randomSSN.get(j));
								}
								// if there is repeated SSN in the array, start over
								if (repeat)
								{
									// empty the SSN ArrayList and countIndex
									countIndex = 0;
									randomSSN = new ArrayList<SSN>();
									// jump to the beginning of generating random SSNs
									continue generateSSN;
								}
						}
					} 
					}
			
			// Store user's file directory
			String input = JOptionPane.showInputDialog("Enter file direcotry:");
					
					// create file base on the directory
					File file = new File(input);
					boolean DirCheck = file.isDirectory();
					
					// Check if the directory exist if not,
					// pop up window with error message and require the 
					// user to re-enter the directory
					while (!DirCheck)
					{
						
						String input2 = JOptionPane.showInputDialog("The input Directory: " + input +
								" does not exist \n" + "Please re-enter file directory:" +
								"as this format: D:\\whatever\\whateverIn "
								+ "\n or C:\\something\\somethingIn");
						input = input2;
						file = new File(input);
						DirCheck = file.isDirectory();
						
					}
					// Create the file with the correct directory
					file = new File (input + "\\Random_SSN.txt");
					// save the directory of random
					dirOfRandom = input;
					// write the SSNs into the file
					try {	
							// initialize the FileWriter
							FileWriter writer = new FileWriter(file);
							// Write all the SSN inside the SSN ArrayList into the file
						    for (int l = 0; l < randomSSN.size(); l++)
							{
						    	// put the current SSN into the file
								writer.write(randomSSN.get(l).getSSN());
								// make the next SSN start on next line
								String newLine = System.getProperty("line.separator");
								writer.write(newLine);
								
							}
						    // close the writer
						    writer.close();
							// pops up a window with the location of the file			
							JOptionPane.showMessageDialog(null, "The Random_SSN.txt has been created at: "+ input + "\\Random_SSN.txt");
							RandomRefreshed = true;
							
					
					}
					catch(Exception e1)
					{
						// pops up a window with error message if Random_SSN has not been created
						JOptionPane.showMessageDialog(null, "The Random_SSN.txt has not been created yet");
						e1.getMessage();
					}
					// endRandomSSN
				
					
			
		}});
	
	// Creates a Quick_SSN.txt file 
	// if the user click on this button
	// The order of the SSNs is from the lowest to the highest
	buttonQuick.addActionListener(new java.awt.event.ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// QuickSort start
			
						// check if the Random_SSN.txt been created or refreshed yet
						if (RandomRefreshed)
						{	// initialize the directory of the Random_SSN.txt
							String inputQ = dirOfRandom + "\\Random_SSN.txt";
							// initialize the string for input directory
							String QSort = "";
							// initialize the SSN ArrayList
							ArrayList<SSN> Quick = new ArrayList<SSN>();
						try {
								// initialize the FileReader
								FileReader frQ = new FileReader(inputQ);
								// initialize the BufferedReader
								BufferedReader brQ = new BufferedReader(frQ);
								// initialize the StringBuilder
								StringBuilder content = new StringBuilder();
								// initialize String for each line
								String line;
								// check if the current line is a string with info
								while ((line = brQ.readLine())!= null)
								{
									// adding the current line into the string
									content.append(line);
									// Separates the lines
							        content.append(System.lineSeparator());
									
								}
								
								// store the lines into Array with each line in a index
								// getting rid of the separates at the same time
								String[] linesForQ = content.toString().split("\n");
								
								// convert the string into SSN and store in an ArrayList
								for (int i = 0; i < linesForQ.length; i++)
								{
									if (linesForQ[i] != "")
									{
										Quick.add(toSSN(linesForQ[i]));
									}
								
							}
							
							// Do QuickSort on the SSN ArrayList
							QuickSortForSSN q = new QuickSortForSSN();
							q.QuickSort(Quick, 0, Quick.size()-1);
							
							// Pops up a window to require the user to enter the file directory
							QSort = JOptionPane.showInputDialog("Enter file direcotry:");//"\\Quick_SSN.txt";
							// Creates the file base on the user directory
							File file2 = new File(QSort);
							boolean DirCheck = file2.isDirectory();
							// Check if the directory exist if not,
							// pop up window with error message and require the 
							// user to re-enter the directory
							while (!DirCheck)
							{
								String input2 = JOptionPane.showInputDialog("The input directory: "+ QSort + 
										" does not exist \n" + "Please re-enter file directory:" +
										"as this format: D:\\whatever\\whateverIn "
										+ "\n or C:\\something\\somethingIn");
								QSort = input2;
								file2 = new File(QSort);
								DirCheck = file2.isDirectory();
								
							}
							// Create the file with the correct directory
							file2 = new File(QSort + "\\Quick_SSN.txt");
							// initialize the FileWriter
							FileWriter writerQ = new FileWriter(file2);
							// Write all the SSN inside the SSN ArrayList into the file
						    for (int l = 0; l < Quick.size(); l++)
							{
						    	// put the current SSN into the file
								writerQ.write(Quick.get(l).getSSN());
								// make the next SSN start on next line
								String newLine = System.getProperty("line.separator");
								writerQ.write(newLine);
								
							}
						 // close the buffered writer
						brQ.close();
						// close writer
						writerQ.close();
						// pops up a window with the location of the file and the statics
						JOptionPane.showMessageDialog(null, "The Qucik_SSN.txt has been created/refreshed at: "+ QSort +"\\Quick_SSN.txt"
								+ "\n Here are the statics: \n" + printTotal(Quick));
						
						
					}
					catch(Exception e1)
					{
						// Pops up a window to tell the user that 
						// the Random_SSN.txt need to be created first
						JOptionPane.showMessageDialog(null, "The Creation of Quick_SSN has been cancelled");
						sop(e1.getMessage());
					}
						
						}
						else 
						{
							// Pops up a window to tell the user that 
							// the Random_SSN.txt need to be created first
							JOptionPane.showMessageDialog(null, "The Random_SSN.txt has not been created/refreshed yet,"+
									"\n" + " please create the Random_SSN.txt first");
						}
					
					
						// QuickSort end
			
		}});
		
			
	// Creates a Bucket_SSN.txt file 
	// if the user click on this button
	// The order of the SSNs is from the lowest to the highest	
	buttonBucket.addActionListener(new java.awt.event.ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// BucketSort start
			// check if the Random_SSN.txt been created or refreshed yet
			if (RandomRefreshed)
			{
			// initialize the directory of the Random_SSN.txt
			String inputB = dirOfRandom + "\\Random_SSN.txt";
			// initialize the string for input directory
			String BSort = "";
			// initialize the SSN ArrayList
			ArrayList<SSN> Bucket = new ArrayList<SSN>();
			
		try {
				// initialize the FileReader
				FileReader frB = new FileReader(inputB);
				// initialize the BufferedReader
				BufferedReader brB = new BufferedReader(frB);
				// initialize the StringBuilder
				StringBuilder content = new StringBuilder();
				// initialize String for each line
				String line;
				// check if the current line is a string with info
				while ((line = brB.readLine())!= null)
				{
					// adding the current line into the string
					content.append(line);
					// Separates the lines
			        content.append(System.lineSeparator());
					
				}
				
				// store the lines into Array with each line in a index
				// getting rid of the separates at the same time
				String[] linesForB = content.toString().split("\n");
				
				// convert the string into SSN and store in an ArrayList
				for (int i = 0; i < linesForB.length; i++)
				{
					if (linesForB[i] != "")
					{
						Bucket.add(toSSN(linesForB[i]));
					}
					
				}
				// Do Bucket Sort on the SSN ArrayList
				BucketSortForSSN b = new BucketSortForSSN();
				Bucket = b.BucketSort(Bucket);
				
				// Pops up a window to require the user to enter the file directory
				BSort = JOptionPane.showInputDialog("Enter file direcotry:");//"\\Bucket_SSN.txt";
				// Creates the file base on the user directory
				File file3 = new File(BSort);
				boolean DirCheck = file3.isDirectory();
				// Check if the directory exist if not,
				// pop up window with error message and require the 
				// user to re-enter the directory
				while (!DirCheck)
				{
					String input2 = JOptionPane.showInputDialog("The input directory: "+ BSort + 
							" does not exist \n" + "Please re-enter file directory:" +
							"as this format: D:\\whatever\\whateverIn "
							+ "\n or C:\\something\\somethingIn");
					BSort = input2;
					file3 = new File(BSort);
					DirCheck = file3.isDirectory();
					
				}
				// Create the file with the correct directory
				file3 = new File(BSort + "\\Bucket_SSN.txt");
				// initialize the FileWriter
				FileWriter writerB = new FileWriter(file3);
				// Write all the SSN inside the SSN ArrayList into the file
			    for (int l = 0; l < Bucket.size(); l++)
				{
			    	// put the current SSN into the file
					writerB.write(Bucket.get(l).getSSN());
					// make the next SSN start on next line
					String newLine = System.getProperty("line.separator");
					writerB.write(newLine);
					
				}
			 // close the buffered writer
			brB.close(); 
			// close writer
			writerB.close();
			// pops up a window with the location of the file and the statics
			JOptionPane.showMessageDialog(null, "The Bucket_SSN.txt has been created/refreshed at: "+ BSort +"\\Bucket_SSN.txt"
					+ "\n Here are the statics: \n" + printTotal(Bucket));
			
		}
		catch(Exception e1)
		{
			// Pops up a window to tell the user that 
			// the Random_SSN.txt need to be created first
			JOptionPane.showMessageDialog(null, "The Creation of Bucket_SSN is cancelled");
			sop(e1.getMessage());
		}
		
			}
			else 
			{
				// Pops up a window to tell the user that 
				// the Random_SSN.txt need to be created first
				JOptionPane.showMessageDialog(null, "The Random_SSN.txt has not been created/refreshed yet,"+
						"\n" + " please create the Random_SSN.txt first" );
			}
			// bucket end
			
		}}); 
	
	
	// Creates a Radix_SSN.txt file 
	// if the user click on this button
	// The order of the SSNs is from the lowest to the highest	
	buttonRadix.addActionListener(new java.awt.event.ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			// radixSort start
			// check if the Random_SSN.txt been created or refreshed yet
			if (RandomRefreshed)
			{
				// initialize the directory of the Random_SSN.txt
				String inputR = dirOfRandom + "\\Random_SSN.txt";
				// initialize the string for input directory
				String RSort = "";
				// initialize the SSN ArrayList
				ArrayList<SSN> Radix = new ArrayList<SSN>();
				try {
					// initialize the FileReader
					FileReader frR = new FileReader(inputR);
					// initialize the BufferedReader
					BufferedReader brR = new BufferedReader(frR);
					// initialize the StringBuilder
					StringBuilder content = new StringBuilder();
					// initialize String for each line
					String line;
					// check if the current line is a string with info
					while ((line = brR.readLine())!= null)
					{
						// adding the current line into the string
						content.append(line);
						// Separates the lines
				        content.append(System.lineSeparator());
						
					}
					
					// store the lines into Array with each line in a index
					// getting rid of the separates at the same time
					String[] linesForR = content.toString().split("\n");
					
					// convert the string into SSN and store in an ArrayList
					for (int i = 0; i < linesForR.length; i++)
					{
						if (linesForR[i] != "")
						{
							Radix.add(toSSN(linesForR[i]));
						}
						
					}
					// Do RadixSort on the SSN ArrayList
					RadixSortForSSN r = new RadixSortForSSN();
					r.RadixSort(Radix);
					
					// Pops up a window to require the user to enter the file directory
					RSort = JOptionPane.showInputDialog("Enter file direcotry:");//"\\Radix_SSN.txt";
					// Creates the file base on the user directory
					File file4 = new File(RSort);
					boolean DirCheck = file4.isDirectory();
					// Check if the directory exist if not,
					// pop up window with error message and require the 
					// user to re-enter the directory
					while (!DirCheck)
					{
						String input2 = JOptionPane.showInputDialog("The input directory: "+ RSort + 
								" does not exist \n" + "Please re-enter file directory:" +
								"as this format: D:\\whatever\\whateverIn "
								+ "\n or C:\\something\\somethingIn");
						RSort = input2;
						file4 = new File(RSort);
						DirCheck = file4.isDirectory();
						
					}
					// Create the file with the correct directory
					file4 = new File(RSort + "\\Radix_SSN.txt");
					// initialize the FileWriter
					FileWriter writerR = new FileWriter(file4);
					// Write all the SSN inside the SSN ArrayList into the file
				    for (int l = 0; l < Radix.size(); l++)
					{
				    	// put the current SSN into the file
						writerR.write(Radix.get(l).getSSN());
						// make the next SSN start on next line
						String newLine = System.getProperty("line.separator");
						writerR.write(newLine);
						
					}
				// close the buffered Reader
				brR.close();
				 // close the writer
				writerR.close();
				
				// pops up a window with the location of the file and the statics
				JOptionPane.showMessageDialog(null, "The Radix_SSN.txt has been created/refreshed at: "+ RSort +"\\Radix_SSN.txt"
						+ "\n Here are the statics: \n" + printTotal(Radix));
				}
			catch(Exception e1)
			{
				// Pops up a window to tell the user that 
				// the Random_SSN.txt need to be created first
				JOptionPane.showMessageDialog(null, "The Creation of Radix_SSN is cancelled");
				sop(e1.getMessage());
			}
		
		}
			else 
			{
				// Pops up a window to tell the user that 
				// the Random_SSN.txt need to be created first
				JOptionPane.showMessageDialog(null, "The Random_SSN.txt has not been created/refreshed yet,"+
						"\n" + " please create the Random_SSN.txt first");
			}
						// radix end
						
			
		}});
	
	
	// Exit the program if the user click on this button
			buttonClose.addActionListener(new ActionListener() {
				 public void actionPerformed (ActionEvent e) {
				  System.exit(0);
				 }
				});
		}
	}
	


