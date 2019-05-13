package SortingSSN;

import java.util.ArrayList;
import java.util.Collections;
/**
 * This class has the QuickSort
 *  method for the SSN ArrayList
 *  
 * This class is a modification of QuickSort.
 * @author jiajianliu
 *
 */
public class QuickSortForSSN {
	
	/**
	 * 	Creates a empty QuickSort Constructor
	 */
	public QuickSortForSSN()
	{
		
	}
	
	/**
	 * Sort the SSN ArrayList with QuickSort
	 * 
	 * @param A A is the SSN ArrayList
	 * @param p p is the starting index
	 * @param r r is the pivot index
	 */
	public void QuickSort(ArrayList<SSN> A, int p, int r)
	{
		// check if p reach r yet,
		//stop the algorithm if p = r
		if (p < r)
		{
			// Do partiotion of A based on p and r
			int q = Partition(A,p,r);
			// QuickSOrt of the left subArrayLsit
			QuickSort(A,p,q-1);
			// QuickSOrt of the right subArrayLsit
			QuickSort(A,q+1,r);
		}
	}
	
	/**
	 * Do partition of the SSN ArrayList based 
	 * on starting index and pivot index
	 * 
	 * @param A A is the SSN ArrayList
	 * @param p p is the starting index
	 * @param r r is the pivot index
	 * @return Returns the index of the pivot
	 */
	public int Partition(ArrayList<SSN> A, int p, int r)
	{
		// get SSN in the pivot
		SSN x = A.get(r);
		// initialize i by p-1 
		int i = p - 1;
		
		// Do the swaps
		for (int j = p; j < r; j++)
		{
			// if the SSN in index j is less than or equal to pivot,
			// swap the SSN in j with i + 1
			if (A.get(j).addUp() <= x.addUp())
			{
				// increase i
				i = i + 1;
				// swap SSN in i and SSN in j
				Collections.swap(A, i, j);
			}
			
			// testing codes
//			sop("j after = " + j);
//			sop("i after = " + i);
//			sop("");
		}
		// swap the pivot and i when the j reaches pivot
		Collections.swap(A, i+1, r);
		//testing codes
//		for (int h = 0; h < A.size() ; h++)
//		{
//				sop(A.get(h).getSSN());
//		}
		
		// return the index of the pivot
		return i+1;
		
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
		SSN two = new SSN(0,1,1);
		SSN three = new SSN(3,0,2);
		SSN four = new SSN(0,0,3);
		SSN five = new SSN(0,0,5);
		SSN six = new SSN(2,0,5);
		SSN seven = new SSN(0,0,6);
		SSN eight = new SSN(1,0,7);
		
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
	sop("");
		QuickSortForSSN q = new QuickSortForSSN();
		q.QuickSort(a, 0, a.size()-1);
		sop("After Sorting:");
		for (int i = 0; i < a.size() ; i++)
		{
				sop(a.get(i).getSSN());
		}
		
	}

}
