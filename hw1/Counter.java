/**
 * Counter class
 * @author Sam Park
 * @version 1.0
 */
public class Counter {

	private int count = 0;
	private int step = 1;

	public Counter()
	{
		// Nothing to do here
	}

	public Counter(int theStep)
	{
		int s;
		if ( (s = Math.abs(theStep)) >  0)
			step = s; 
	}
	
	public int getCount() {
		return count;
	}

	public void increment() {
		count += step;
	}

	public void decrement() {
		if (count > step ) 
			count -= step;
		else
			count = 0;
	}

	public void reset() {
		count = 0;
	}
}
