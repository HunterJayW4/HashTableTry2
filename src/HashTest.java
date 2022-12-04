import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author hunterwalp
 * HashTest class that tests the functionality of the HashTable class
 * using several parameters, including source type, debug level and load
 * leve.
 */
public class HashTest 
{
	public static void main(String[] args) throws IOException
	{
		//Below ensures the command line arguments are passed in correctly.
		if (args.length < 2 || args.length > 3)
		{
			throw new IOException("You must pass in three arguments. -input type, (1,2,3), load factor (0.0 - 1.0), -debug level (optional, 1, 2)");
		}
		int inputType = Integer.parseInt(args[0]);
		if (inputType != 1 && inputType != 2 && inputType != 3)
		{
			throw new IOException("You must pass in three arguments. -input type, (1,2,3), load factor (0.0 - 1.0), -debug level (optional, 1, 2)");
		}
		double load = Double.parseDouble(args[1]);
		if (load < 0.0 || load > 1)
		{
			throw new IOException("The load value must be a double between 0 and 1.");
		}
		int debug = -1;
		if (args.length == 3) {debug = Integer.parseInt(args[2]);}
		if (args.length == 3 & (debug != 0 && debug != 1))
		{
			throw new IOException("If you want to debug, the value must be 0 or 1, where 0 prints a summary to the console and 1 prints summary and dump files.");
		}

		//BufferedReader that reads in the file if using source 3, word-list.
		Random ran = new Random();
		long current = new Date().getTime();

		//Random # hashTables
		HashTable linRan = new HashTable(load, 0);
		HashTable dubRan = new HashTable(load, 0);

		//Date hashTables
		HashTable linDate = new HashTable(load, 0);
		HashTable dubDate = new HashTable(load, 0);

		//Word-list hashTables
		HashTable linWord = new HashTable(load, 0);
		HashTable dubWord = new HashTable(load, 0);

		//Files for debugging, here because I delete files below.
		File doubleDump = new File("double-dumpMine");
		File linearDump = new File("linear-dumpMine");



		//Inserts elements into the list while the load is < than desired load.
		//Input type 1 == random integers.
		if (inputType == 1)
		{
			while (linRan.getLoad() <= load)
			{
				HashObject object = new HashObject(ran.nextInt());
				linRan.linInsert(object);
				dubRan.dubInsert(object);
			}
			if (debug == -1 || debug == 0 || debug == 1)
			{
				System.out.println(linRan.hashString("Random Numbers", "Linear-Hashing"));
				System.out.println(dubRan.hashString("Random Numbers", "Double-Hashing"));
				if (debug == 1)
				{
					String linStr = "";
					String dubStr = "";
					linearDump.delete();
					doubleDump.delete();
					linearDump.createNewFile();
					doubleDump.createNewFile();
					try 
					{
						FileWriter writer = new FileWriter(linearDump, true);
						FileWriter dWriter = new FileWriter(doubleDump, true);
						int i = 0;
						while (i < linRan.getItems())
						{
							if (linRan.getIndex(i) != null)
							{
								linStr = "table[" + i + "]: " + linRan.getIndex(i).toString() + "\n";
							}
							if (dubRan.getIndex(i)!= null)
							{
								dubStr = "table[" + i + "]: " + dubRan.getIndex(i).toString() + "\n";
							}
							writer.write(linStr);
							dWriter.write(dubStr + "penis");
							dubStr = new String("");
							linStr = new String("");
							writer.flush();
							dWriter.flush();
							i++;
						}
					}
					catch (IOException e)
					{
						System.out.println("Unable to write to file.");

					}
				}
			}
		}
				//Input type 2 == Date objects.
				if (inputType == 2)
				{
					while (linDate.getLoad() <= load)
					{
						HashObject object = new HashObject(current);
						linDate.linInsert(object);
						dubDate.dubInsert(object);
						current++;
					}
					if (debug == -1 || debug == 0 || debug == 1)
					{
						System.out.println(linDate.hashString("Date", "Linear-Hashing"));
						System.out.println(dubDate.hashString("Date", "Double-Hashing"));
						if (debug == 1)
						{
							String linStr = "";
							String dubStr = "";
							linearDump.delete();
							doubleDump.delete();
							linearDump.createNewFile();
							doubleDump.createNewFile();
							try 
							{
								FileWriter writer = new FileWriter(linearDump, true);
								FileWriter dWriter = new FileWriter(doubleDump, true);
								int i = 0;
								while (i < 95791)
								{
									if (linDate.getIndex(i) != null)
									{
										linStr = "table[" + i + "]: " + linDate.getIndex(i).toString() + "\n";
									}
									if (dubDate.getIndex(i)!= null)
									{
										dubStr = "table[" + i + "]: " + dubDate.getIndex(i).toString() + "\n";
									}
									writer.write(linStr);
									dWriter.write(dubStr);
									dubStr = new String("");
									linStr = new String("");
									i++;
								}
							}
							catch (IOException e)
							{
								System.out.println("Unable to write to file.");

							}
						}
					}
				}
						//Input type 3 == word-list.
						if (inputType == 3)
						{
							BufferedReader objReader = new BufferedReader(new FileReader("word-list"));
							BufferedReader dubReader = new BufferedReader(new FileReader("word-list"));

							while (linWord.getLoad() <= load)
							{
								HashObject object = new HashObject(objReader.readLine());
								linWord.linInsert(object);
							}
							while (dubWord.getLoad() <= load)
							{
								HashObject object2 = new HashObject(dubReader.readLine());
								dubWord.dubInsert(object2);
							}

							if (debug == -1 || debug == 0 || debug == 1)
							{
								System.out.println(linWord.hashString("Word-List", "Linear-Hashing"));
								System.out.println(dubWord.hashString("Word-List", "Double-Hashing"));
								if (debug == 1)
								{
									String linStr = "";
									String dubStr = "";
									linearDump.delete();
									doubleDump.delete();
									linearDump.createNewFile();
									doubleDump.createNewFile();
									try 
									{
										FileWriter writer = new FileWriter(linearDump, true);
										FileWriter dWriter = new FileWriter(doubleDump, true);
										int i = 0;
										while (i < linWord.getSize())
										{
											if (linWord.getIndex(i) != null)
											{
												writer.write("table[" + i + "]: " + linWord.getIndex(i).toString() + "\n");
												writer.flush();
											}
											i++;
										}
										i = 0;
										while (i < dubWord.getSize())
										{
											if (dubWord.getIndex(i) != null)
											{
												dWriter.write("table[" + i + "]: " + dubWord.getIndex(i).toString() + "\n");
												dWriter.flush();
											}
											i++;
										}
									}
									catch (IOException e)
									{
										System.out.println("Unable to write to file.");
									}
								}
							}
						}
					}
	}


	





