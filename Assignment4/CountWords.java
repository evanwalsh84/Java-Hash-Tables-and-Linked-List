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

public class CountWords
{
  public static void main(String [] args)
  {
    //calls TextIterator class
    TextIterator text = new TextIterator(); 
    ArrayList <LinkedList<WordItem>> wordList = new ArrayList <LinkedList<WordItem>>(26);
    String word = "";
    String fileName;
		fileName = args[0];
		// open inventory file and read
    File userFile = new File(fileName);   
    //reads file
    text.readText(fileName);
    //Calls on the printResult method to print the statistics of the file. 
    readin(wordList, word, text);
    printResult(wordList);
		if (args.length < 1)
    {
       System.out.print("ERROR: insufficient number of command line ");
       System.out.println("arguments. Program aborted.");
       return;
    } 
  }
  public static void readin(ArrayList<LinkedList<WordItem>> wordList, String word, TextIterator text)
  {
  	//Loops 26 times to create new LinkedLists, one for each letter.
    for (int index = 0; index < 26; index++)
    	{
    		LinkedList <WordItem> alphabetList = new LinkedList <WordItem> ();
    		wordList.add(alphabetList);
    	}
   	//Loops through the text of the file until there are no more words.
		while(text.hasNext())
		{
			word = text.next();
			WordItem w = new WordItem(word);
			//Checks which List to place the word item in based on the first letter of the word.
			for ( char ch = 'a'; ch <='z'; ch++)
			{
				char input = word.charAt(0);
				//Checks to see if the first letter of the word is equal to the letter of the loop.
				if(input == ch)
				{
					LinkedList<WordItem> pos = wordList.get((int) input - (int)'a');
					Iterator<WordItem> itr = (pos.iterator());
					int count = 1;
					int x = 0;
					while(itr.hasNext())
					{
						WordItem temp = itr.next();
						//Checks if the word is alread present in the LinkedList and incremements it if found.
						if( w.getWord().equals(temp.getWord()))
						{
							temp.incCount();
							break;
						}
					}
					//If there are no more words in the loop, the new WordItem is added to the end of the LinkedList
					if(!(itr.hasNext()))
					{
						wordList.get((int) input - (int)'a').add(w);
     			}
				}
			}
		}
	}
	/* findMaxCount finds the WordItem in a LinkedList with the highest count and returns it. */
	public static WordItem findMaxCount(LinkedList<WordItem> data)
	{		
		//Declares a new iterator to go through the LinkedList.
		Iterator<WordItem> itr = data.iterator();
		WordItem biggest = itr.next();
		//Initializes a currentmax to compare against.
		int curMax = 0;
		//Loops through the linkedList comparing to find the largest WordItem.
		while(itr.hasNext())
		{
			WordItem index = itr.next();
			//Checks if the current position in the LinkedList is greater than the previous max'
			if(curMax < index.getCount())
			{
				//Sets biggest equal to the count of the current WordItem.
				curMax = index.getCount();
				biggest = index;
			}
		}
		return biggest;
	}
	/* printResult prints the number of words in each list and the word with the largest count. */
	public static void printResult(ArrayList<LinkedList<WordItem>> data)
	{
		int total = 0;
		//Loops through the 26 LinkedLists finding the largest WordItem and the total number of words.
		for (int i = 0; i < 26; i++)		
		{
				Iterator<WordItem> itr = data.get(i).iterator();
				//Checks the LinkedList to make sure it is not empty before comparing WordItems nad prints the statistics of the LinkedList position.
				if( data.get(i).size() != 0)
				{
					WordItem large = findMaxCount(data.get(i));
					String maxword = large.getWord();
					int x = data.get(i).size();
					System.out.printf("Letter %c %5d %15s %6d \n", maxword.charAt(0), x, 
														maxword, large.getCount());
				}
				//The following else statement occurs if the LinkedList is empty nad prints a zero.
				else
				{
					int emptychar = ((int)'a' + i);
					System.out.printf("Letter %c %5d \n", (char)emptychar, 0);
				}
				
		}
		//Counts the total words of all the LinkedLists combined and prints them out.
		int totalwords = 0;
		for (int r = 0; r < 26; r++)	
		{
			totalwords += data.get(r).size();
		}
		System.out.println("There were a total of " + totalwords + " unique words.");
	}
}
