package login.Controllers;

public class BinarySearch {
	public static int search(int[] array, int value)
	{
		int first;
		int last;
		int middle;
		int position;
		boolean found;
		
		//Set the initial values
		first = 0;
		last = array.length - 1;
		position = -1;
		found = false;
		
		//Search for the value
		while(!found && first <= last)
		{
			//Calculate mid point
			middle = (first + last) / 2;
			
			//If value is found at midpoint...
			if(array[middle] == value)
			{
				found = true;
				position = middle;
			}
			//else if value is in lower half...
			else if (array[middle] > value)
				last = middle -1;
			//else if value is in upper half...
			else first = middle + 1;
		}
		
		//Return the position of the item, or -1
		//if it was not found
		return position;
	}
}
