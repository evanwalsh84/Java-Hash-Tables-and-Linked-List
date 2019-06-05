/*Evan Walsh
CSCI 3200, Section 001
Assignment 4
January 28, 2015

CountWords.java

Program reads a text file in from a redirected input. The text is added into a linked list
using a text iterator and the amount of words along with the most frequent word of each letter
is calculated. The program outputs the number of time the most frequent word appears for each letter
and the amount of times a unique word of that letter appears.

*/
import java.util.*;
import java.io.*;

public class CountWords2
{
  public static void main(String [] args)
  {
  // Declare and initialize list data structures
    //collection of 26 lists
    TextIterator text = new TextIterator(); //calls TextIterator class
    ArrayList <LinkedList<WordItem>> wordList = new ArrayList <LinkedList<WordItem>>(26);
    String word = "";
    //Scanner userFile ; //declare user file
    String fileName;
		fileName = args[0];
    File userFile = new File(fileName);   // open inventory file and read
    text.readText(fileName); //reads file
    printResult(wordList);
  }
    public static void readin(ArrayList<LinkedList<WordItem>> wordList)
    {
    for (int index = 0; index < 26; index++)
    	{
    		LinkedList <WordItem> alphabetList = new LinkedList <WordItem> ();
    		wordList.add(alphabetList);
    	}
			while(text.hasNext())
			{
				word = text.next();
				WordItem w = new WordItem(word);
				for ( char ch = 'a'; ch <='z'; ch++)
				{
					char input = word.charAt(0);
					if(input == ch)
					{
						LinkedList<WordItem> pos = wordList.get((int) input - (int)'a');
						Iterator<WordItem> itr = (pos.iterator());
						int count = 1;
						int x = 0;
						while(itr.hasNext())
						{
							WordItem temp = itr.next();
							if( w.getWord().equals(temp.getWord()))
							{
								temp.incCount();
								break;
							}
						}
						if(!(itr.hasNext()))
						{
							wordList.get((int) input - (int)'a').add(w);
     				}
			}
		}
	}
     if (args.length < 1)
     {
       System.out.print("ERROR: insufficient number of command line ");
       System.out.println("arguments. Program aborted.");
       return;
     }
     
     }
		public static WordItem findMaxCount(LinkedList<WordItem> data)
		{
			
				Iterator<WordItem> itr = data.iterator();
				WordItem biggest = itr.next();
				int curMax = 0;
			while(itr.hasNext())
			{
				WordItem index = itr.next();
				if(curMax < index.getCount())
					{
						curMax = index.getCount();
						biggest = index;
						//System.out.println(curMax);
						//System.out.println(biggest.getWord());
						
						//data.add(0, index);
					}
			}
			return biggest;
		}
		public static void printResult(ArrayList<LinkedList<WordItem>> data)
		{
		int total = 0;
		for (int i = 0; i < 26; i++)		
		{
				Iterator<WordItem> itr = data.get(i).iterator();
				if( data.get(i).size() != 0)
				{
				WordItem large = findMaxCount(data.get(i));
				String maxword = large.getWord();
				int x = data.get(i).size();
				
				System.out.printf("Letter %c %5d %15s %6d \n", maxword.charAt(0), x, 
														maxword, large.getCount());
				}
				
			}
			int totalwords = 0;
				for (int r = 0; r < 26; r++)	
				{
					totalwords += data.get(r).size();
				}
				System.out.println("There were a total of " + totalwords + " unique words.");
				//System.out.println(large.getCount());
		}
  }
