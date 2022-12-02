/**
 * 
 * @author Hunter Walp
 * Hash Table Object.
 * @param <T>
 */
public class HashTable<T>
{
	private HashObject table[];
	private int numProbes;
	private int numDuplicates;
	private double load;
	private int size;
	private int numItems;
	private int totalProbes = 0;

	//Constructor for HashTable that takes in load and debuglevel.
	public HashTable(double load, int debuglevel)
	{
		PrimeCalculator p = new PrimeCalculator();
		this.size = p.getPrimes(95500, 96000);
		table = new HashObject[size];
		this.load = load;
	}


	//Insert Methods below--------------------------------
	//linInset is the linear-insert and probing method.
	@SuppressWarnings("unchecked")
	public void linInsert(HashObject<T> object)
	{
		int index = h1(object.getKey());
		int temp = index;
		int i = 0;
		while (temp < this.size)
		{
			if (table[temp] == null)
			{
				table[temp] = object;
				numItems++;
				break;
			}
			else if (table[temp].getKey().equals(object.getKey()))
			{
				table[temp].increaseFreq();
				numDuplicates++;
				break;
			}
			else
			{
				numProbes++;
				object.increaseProbes();
				i++;
				temp = i + h1(object.getKey());
			}
		}
	}
		
	//Double-hashing insert method.
	@SuppressWarnings("unchecked")
	public void dubInsert(HashObject<T> object)
	{
		int i = 0;
		int index = h1(object.getKey());
		int temp = index;
		while (temp < this.size)
		{
			if (table[temp] == null)
			{
				table[temp] = object;
				numItems++;
				break;
			}
			else if (table[temp].getKey().equals(object.getKey()))
			{
				table[temp].increaseFreq();
				numDuplicates++;
				break;
			}
			else
			{
				numProbes++;
				object.increaseProbes();
				if (i == 0)
					temp += h2(object.getKey());
				else
					temp += i * h2(object.getKey())
;				i++;
			}
		}
	}

	//Method for getting the load of the Table.
	public double getLoad()
	{
		if (this.numItems == 0)
		{
			return 0.0;
		}
		return (double)this.numItems/(double)this.size;
	}

	//Method that calls the PositiveMod method and returns the hash value.
	public int h1(T key)
	{
		return PositiveMod(key.hashCode(), this.size);
	}
	
	//Secondary hash value calculator.
	public int h2(T key) { return 1 + PositiveMod(key.hashCode(), (this.size - 2));}

	//positive Mod calculator 
	public int PositiveMod (int dividend, int divisor)
	{
		int value = dividend % divisor;
		if (value < 0)
			value += divisor;
		return value;
	}


	//returns the size of the table.
	public int getSize()
	{
		return this.size;
	}
	
	//returns the item at given index.
	public HashObject<T> getIndex(int index)
	{
		if (table[index] == null)
		{
			return null;
		}
		return table[index];
	}

	//returns the number of items.
	public int getItems() 
	{
		return this.numItems;
	}

	//toString method for given hash table.
	public String hashToStr()
	{
		String str = "";
		for (int i = 0; i < this.size - 1; i++)
		{
			if (this.table[i] == null){}
			else
			{
				str += ("table[" + i + "]: " + table[i].getKey() + " " + table[i].getDup() + " " + table[i].getProbes() + "\n"); 
			}
		}
		return str;
	}
	

	//alternative string method to output generic message.
	public String hashString(String DataType, String HashType)
	{
		String ret = "\n";
		ret += "A good table size is found: " + this.size + "\nData source type: " + DataType 
				+ "\nUsing " + HashType + "... \n" + "Input " + (numItems + numDuplicates) + " elements, " + "of which " + numDuplicates 
				+" duplicates" + "\nload factor = " + this.load + ", Avg. no. of probes " + (double)numProbes/numItems;
		return ret;
	}

}

