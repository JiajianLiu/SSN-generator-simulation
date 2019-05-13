package SortingSSN;

import java.util.ArrayList;
import java.util.LinkedList;
/**
 * This class has the BucketSort
 *  method for the SSN ArrayList
 *  
 * This class is a modification of BucketSort.
 * @author jiajianliu
 *
 */
public class BucketSortForSSN {
	/**
	 * 	Creates a empty BucketSort Constructor
	 */
	public BucketSortForSSN()
	{
		
	}
	
	/**
	 * Sort the SSN ArrayList with BucketSort
	 * @param A A is the SSN ArrayLIst
	 * @return Returns the updated SSN ArrayList
	 */
	public ArrayList<SSN> BucketSort(ArrayList<SSN> A)
	{
		// get the last index of A
		int n = A.size() - 1;
		// initialize ArrayList B with A's last index
		ArrayList<LinkedList<SSN>> B = new ArrayList<LinkedList<SSN>>(n-1);
		// fill B with empty SSNs
		for (int i = 0; i < n; i++)
		{
			LinkedList<SSN> e = new LinkedList<SSN>();
			B.add(e);
		}
		
		// Put each SSN into corresponding buckets by dividing their addUp() with 1000000000
		for (int i = 0; i <= n; i++)
		{
			// Get the current SSN in A
			SSN currentInA = A.get(i);
			
			// make n into double
			double n1 = (double) n;
			// make currentInA.addUp() into double
			double n2 = (double) currentInA.addUp();
			// make 1000000000 into double
			double n3 = (double)1000000000;
			// get the index of where the currentInA.addUp() should be in the bucket
			double bucket = (n1 * n2) /n3;
			// make the bucket into int
			int bucketIndex = (int) bucket;
			// test codes
			sop("");
			sop(currentInA.getSSN());
			sop("bucketIndex = " + bucketIndex);
			
			
			// Put the current SSN in A into the bucket
			// based on its bucket index from above
			LinkedList<SSN> e = B.get(bucketIndex);
			e.add(currentInA);
		}
		
		// Use insertionSort to sort all the buckets
		for (int i = 0; i < n; i++)
		{
			InsertionSort(B.get(i));
		}
		
		// Create an ArrayList
		ArrayList<SSN> end = new ArrayList<SSN>();
		// Gets the ordered SSN from the buckets and 
		// put them into the ArrayList end from lowest to highest
		for (int i = 0; i < n; i++)
		{
			// test codes
			sop("bucket " + i);
				for (int j = 0; j < B.get(i).size(); j++)
				{
					// test codes
					sop(B.get(i).get(j).getSSN());
					end.add(B.get(i).get(j));
					
				}
		}
		
		// Return the updated SSN ArrayList
		return end;
		
	}
	
	/**
	 * Do insertion sort for the input SSN LinkedList
	 * @param A A is the input SSN LinkedList
	 */
	public void InsertionSort(LinkedList<SSN> A)
	{
		for (int i = 0; i < A.size(); i++)
		{
			// get the current SSN
			SSN key = A.get(i);
			// get the index of the SSN before the current one
			int j = i - 1;
			// if j is not negative, and SSN[j].addUp is greater than SSN[i].addUP
			// swap them
			while (j >= 0 && A.get(j).addUp() > key.addUp())
			{
				A.set(j+1, A.get(j));
				j = j -1;
				A.set(j+1, key);
			}
		}
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
//		LinkedList<SSN> a = new LinkedList<SSN>();
//		SSN one = new SSN(0,0,8);
//		SSN two = new SSN(0,1,1);
//		SSN three = new SSN(3,0,2);
//		SSN four = new SSN(0,0,3); 
//		SSN five = new SSN(0,0,5);
//		SSN six = new SSN(2,0,5);
//		SSN seven = new SSN(0,0,6);
//		SSN eight = new SSN(1,0,7);
//		
//		a.add(six);
//		a.add(one);
//		a.add(seven);
//		a.add(four);
//		a.add(eight);
//		a.add(two);
//		a.add(three);
//		a.add(five);
//		for (int i = 0; i < a.size(); i ++)
//		{
//			sop(a.get(i).getSSN());
//		}
//		
//		BucketSortForSSN n = new BucketSortForSSN();
//		n.InsertionSort(a);
//		sop("");
//		sop("");
//		sop("");
//		
//		for (int i = 0; i < a.size(); i ++)
//		{
//			sop(a.get(i).getSSN());
//		}
		
		ArrayList<SSN> a = new ArrayList<SSN>();
		SSN one = new SSN(0,0,8);
		SSN two = new SSN(0,90,1);
		SSN three = new SSN(3,0,2);
		SSN four = new SSN(0,0,3);
		SSN five = new SSN(0,0,5);
		SSN six = new SSN(712,0,5);
		SSN seven = new SSN(340,0,6);
		SSN eight = new SSN(999,99,9999);
		SSN nine = new SSN(0,0,0);
		
		a.add(six);
		a.add(one);
		a.add(seven);
		a.add(four);
		a.add(eight);
		a.add(two);
		a.add(three);
		a.add(five);
		a.add(nine);
		
		sop("Original ArrayList: ");
		
	for (int k = 0; k < a.size(); k++)
		{
				sop(a.get(k).getSSN());
		}
	
	BucketSortForSSN q = new BucketSortForSSN();
	a =	q.BucketSort(a);
		sop("");
		sop("");
		sop("After Sorting: ");
		for (int i = 0; i < a.size() ; i++)
		{
				sop(a.get(i).getSSN());
		}
		
		
	
	}

}
