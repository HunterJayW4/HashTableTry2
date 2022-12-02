import java.util.Random;
/**
 * 
 * @author Hunter Walp
 * Generates a prime number to use. 
 */
public class PrimeCalculator
{
	
	public boolean testPrime(int testNum)
	{
		  boolean flag = false;
		    for (int i = 2; i <= testNum / 2; ++i) 
		    {
		      if (testNum % i == 0) 
		      {
		        flag = true;
		      }
		    }
		    return !flag;
//		//creates a new random #
//		Random rand = new Random();
//		//num to start for binary calc
//		int number = 0;
//		int base = Math.abs(rand.nextInt(500));
//		String binary = Integer.toBinaryString(testNum);
//		while(base < testNum)
//		{
//			number = base;
//			for(int i = 1; i < binary.length(); i++)
//			{
//				if (binary.charAt(i) == '0')
//				{
//					number = (int) ((Math.pow(number, 2))% testNum);
//				}
//				else if (binary.charAt(i) == '1')
//				{
//					number = (int) ((((Math.pow(number,2))% testNum) * base) % testNum);
//				} 
//			}
//			if (number != 1)
//			{
//				return false;
//			}
//			base ++;
//		}
//		return true;
	}
	
	public int getPrimes(int min, int max)
	{
		int prime2 = 0;
		while(prime2 == 0)
		{
			for (int i = min; i <= max; i++)
			{
				if (testPrime(i) == true)
				{
					if(testPrime(i + 2) == true)
					{
						prime2 = i + 2;
						return prime2;
					}
				}
			}
		}
		return 0;
	}
}