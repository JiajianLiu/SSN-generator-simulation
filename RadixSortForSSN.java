package SortingSSN;

import java.util.ArrayList;
/**
 * This class has the RadixSort
 *  method for the SSN ArrayList
 *  
 * This class is a modification of RadixSort.
 * @author jiajianliu
 *
 */
public class RadixSortForSSN {
	/**
	 * 	Creates a empty RadixSort Constructor
	 */
	public RadixSortForSSN()
	{
		
	}
	/**
	 * Sort the SSN ArrayList with RadixSOrt
	 * @param A A is the input SSN ArrayList
	 */
	public void RadixSort(ArrayList<SSN> A)
	{
		// Get the SSN with the max addUp() value in A
		SSN k1 = this.getMaxOfAddUp(A);
		// Get the addUp() value of k1
		int k = k1.addUp();
		
		// do counting sort for each digit in A
		// by having the e as the 10^n power
		for (int e = 1; k /e > 0; e *= 10) 
		{
			this.CountingSort(A, e);
		}
	}
	
	/**
	 * Do CountingSort for SSN ArrayList
	 * @param A A is an SSN ArrayList
	 * @param positionFactor positionFactor is the power of 10
	 */
	public void CountingSort(ArrayList<SSN> A, int positionFactor)
	{
		// positionFactor is power of 10 so that we can get 1 digit at a time
		// test codes
		sop("positionFactor = " + positionFactor);
		// initialize a SSN Array to store the sorted SSNs
		SSN [] done = new SSN [A.size()];
		// fill done with empty SSNs
		for (int i = 0; i <A.size(); i++)
		{
			done[i] = new SSN(0,0,0);
		}
		// initialize the counting Array
		int [] C = new int[10];
		
		// counts the appearance for 0-9 
		for (SSN s : A)
		{
			C[(s.addUp()/positionFactor)%10]++;
		}
		// test codes 
		sop("Counting Array before Adding:");
		for (int i = 0; i < C.length; i++)
		{
			sop("indexOFC = "+ i + " Element of C[i] = " + C[i]);
		}
		sop("");
		// Add up the counts 
		for (int i = 1; i < C.length; i++)
		{
			C[i] = C[i] + C[i-1];
		}
		
		// test codes
		sop("Counting Array atfer Adding:");
		for (int i = 0; i < C.length; i++)
		{
			sop("indexOFC = "+ i + " Element of C[i] = " + C[i]);
		}
		sop("");
		
		// sort the Array done based on Array C
		for (int i = A.size() - 1; i >= 0; i--)
		{
			// get the index of C corresponding to A backward
			int indexC = ((A.get(i).addUp()/positionFactor)%10) ;
			
			// test codes
			//sop("indexC = " + indexC );
			
			//  get the index of done corresponding to C backward
			int indexB = C[indexC];
			
			// test codes
			//sop("indexB = " + indexB);
			
			// put the SSN in A into done by the corresponding index
			done[indexB-1] = A.get(i); 
			// up date the counts in C
			C[indexC]--;
			
			// test codes
//			sop("");
//			sop("done:");
//			
//			for (int g = 0; g < done.length; g++)
//			{
//					sop(done[g].getSSN());
//			}
		}
		// test codes 
				sop("Counting Array after Sorting:");
				for (int i = 0; i < C.length; i++)
				{
					sop("indexOFC = "+ i + " Element of C[i] = " + C[i]);
				}
				sop("");
		
		// update A
		for (int i = 0; i < A.size(); i++)
		{
			A.set(i, done[i]);
		}
		
		
		
		
		
	}
	
	/**
	 * Find the SSN with the max addUp() in ArrayList A
	 * @param A A is the SSN ArrayList
	 * @return Returns the SSN with the max addUp() in A
	 */
	public SSN getMaxOfAddUp(ArrayList<SSN> A)
	{
		// Get the first SSN in A
		SSN max = A.get(0);
		// Compares max with all the SSN in A
		// Until none of the SSNs in A has greater addUp()
		for(int i = 1; i < A.size(); i++)
		{
			// Check if the current SSN has greater addUp()
			if (A.get(i).addUp() > max.addUp())
			{
				// let max becomes the SSN with the greater addUp()
				max = A.get(i);
			}
		}
		
		// return the SSN with the max addUp() in A
		return max;
	}
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
	
	// test codes 
	public static void main(String[] args)
	{
		ArrayList<SSN> a = new ArrayList<SSN>();
		SSN one = new SSN(0,0,8);
		SSN two = new SSN(300,7,1);
		SSN three = new SSN(0,0,2);
		SSN four = new SSN(2,0,3);
		SSN five = new SSN(0,0,4);
		SSN six = new SSN(1,0,5);
		SSN seven = new SSN(0,0,6);
		SSN eight = new SSN(0,0,7); 
		
		a.add(six);
		a.add(one);
		a.add(seven);
		a.add(four);
		a.add(eight);
		a.add(two);
		a.add(three);
		a.add(five);
		sop("Original ArrayList: ");
		for (int k = 0; k < a.size(); k++)
		{
				sop(a.get(k).getSSN());
		}
		
		
		RadixSortForSSN r = new RadixSortForSSN();
		SSN k1 = r.getMaxOfAddUp(a);
		int k = k1.addUp();
		sop("");
		//sop(k);
		//sop(r.getMaxOfAddUp(a).getSSN());
		//ArrayList<SSN> b  = r.CountingSort(a);
		
		
		//sop("");
//		for (int i = 0; i < b.size() ; i++)
//		{
//				sop(b.get(i).getSSN());
//		}
		
//		int huge = 213211232 % 100;
//		sop("huge = " + huge);
//		
//		int [] C = new int[10];
//		for (int i : C)
//		{
//			sop(i);
//		}
		
//		for (int exp = 1; k /exp > 0; exp *= 10) 
//		{
//			r.CountingSort(a, exp);
//		}
		r.RadixSort(a);
		sop("After Sorting: ");
		for (int g = 0; g < a.size(); g++)
		{
				sop(a.get(g).getSSN());
		}
	}

}
