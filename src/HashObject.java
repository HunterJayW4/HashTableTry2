
/**
 * @author hunterwalp
 * @param <T>
 * HashObject class that provides the structure
 * for a HashObject, used in a HashTable.
 * 
 */
public class HashObject<T> 
{
	//variables for HashObject
	private int duplicate;
	private int probeCount;
	private T key;
	
	//Default constructor.
	public HashObject()
	{
		this.duplicate = 0;
		this.probeCount = 1;
		this.key = null;
	}
	
	//Constructor that takes in a key as parameter
	public HashObject(T k)
	{
		this.duplicate = 0;
		this.probeCount = 1;
		this.key = k;
	}
	
	//equals method to determine whether two HashObjects are equal.
	public boolean equals(HashObject<T> key)
	{
		boolean equals = false;
		if (key.getKey() == this.getKey())
		{
			equals = true;	
		}
		return equals;
	}
	
	//alternative equals method.
	public boolean isEqual(HashObject<T> value)
	{
		Boolean b = false;
		if (value.hashCode() == this.hashCode())
		{
			if (value.getKey() == this.getKey())
			{
				b = true;
			}
		}
		return b;
	}
	
	@Override 
	public String toString()
	{
		String ret = "";
		ret += this.key + " " + this.duplicate + " " + this.probeCount;
		return ret;
	}
	
	//Getter for HashObject key
	public T getKey()
	{
		return this.key;
	}
	
	//Getter for HashObject probes
	public int getProbes()
	{
		return this.probeCount;
	}
	
	//Getter for HashObject duplicate count
	public int getDup()
	{
		return this.duplicate;
	}

	//method for increasing key
	public void increaseFreq()
	{
		this.duplicate++;
	}
	
	//increases probe count for HashObject 
	public void increaseProbes()
	{
		this.probeCount++;
	}
}
