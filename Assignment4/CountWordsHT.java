/*Evan Walsh
CSCI 3200, Section 001
Bonus Program
April 30, 2015
*/
import java.io.*;
import java.util.*;
import java.lang.*;
/*This program takes an input text files and uses a hash table to store the input text file into an Array list of the class word item.
The program then sorts the arraylist and prints the most reoccuring word starting with each eltter and the number of unique words
*/

public class CountWordsHT
{

// hashtable is a global static, do not have to pass as parameter

  public static final int TABLESIZE = 11003;
  public static Hashtable<String,WordItem> hashTable = 
            new Hashtable<String,WordItem>(TABLESIZE);

  public static void main(String[] args)
  {
    if (args.length < 1)
    {
       System.out.print("ERROR: insufficient number of command line ");
       System.out.println("arguments. Program aborted.");
       return;
    }
    TextIterator book = new TextIterator();
    book.readText(args[0]);
    while (book.hasNext())
      storeWord(book.next());

    ArrayList<WordItem> values = new ArrayList<WordItem>(hashTable.values());
    Collections.sort(values);
 		frequentword(values);
    // for debugging purposes only
    //frequentPrint(values);
    
  }
  public static void storeWord(String word)
  {
    int i;
    int position;
    WordItem item;

    if (hashTable.containsKey(word))  // exists ==> update counter
    {
      hashTable.get(word).incCount();
    }
    else  // not present ==> create new
    {
      item = new WordItem(word);
      hashTable.put(word,item);
    }
  }

// prints out the 10 most frequently occurring words
// For debugging purposes only

  /*public static void frequentPrint(ArrayList<WordItem> values)
  {
     WordItem tempItem;
    
     int numDiff = values.size();
     for (int i = 1; i <= 10; i++)
     {
       tempItem = values.get(numDiff-i);
       System.out.printf("%4d. %15s %6d\n",i,tempItem.getWord(),
                                             tempItem.getCount());
     }
  }*/
  /*This functions takes in an ArrayList of WordItems and creates a new arraylist as well as an array. 
  It reads through the input arraylist and stores the 26 most frequent words based upon their alphabetical order
  into a new arraylist storing up to 26 words. Every time a word is read in through the input arraylist it incriments the
  position of the array at the location of the character of the alphabet.*/
  public static void frequentword(ArrayList<WordItem> values)
  {
  	int totalwords = 0;
  	int [] uniquewords = new int [25];
  	ArrayList<WordItem> mostfreq = new ArrayList<WordItem>(26);
  	int q = 0;
  	WordItem w = new WordItem();
  	//Adds a null value to allow for function methods to be called on it without crashes.
  	for(int initial = 0; initial < 26; initial++)
  	{
  		mostfreq.add(w);
  	}
  	//Loops through the input array list until there are no more words to read in.
  	for(int i=values.size()-1; i>=0; i--)
  	{
  		WordItem temp = values.get(i);
  		char position = temp.getWord().charAt(0);
  		int location = (int)position - (int)'a';
  		uniquewords[location]++;
  		//Checks the if the location in the array starting with the same letter is holding a word.
  		if(mostfreq.get(location) == w)
  		{
  			mostfreq.set(location, temp);
			}
  	}
  	//Loops through the arraylist and prints out the results.
  	for(int alphabet=0; alphabet<26;alphabet++)
  	{	
  		int x = (int)alphabet + (int)'a';
  		char b = (char) x;
  		//Checks if there were any input words starting with the current letter, represented through an integer.
  		if (mostfreq.get(alphabet).getCount() == 0)
  		{
  			System.out.printf("Letter %c %5d \n", b, 0);
  		}
  		else
  		{
  		//Keeps a running total of the number of unique words stored in the array.
  		totalwords+=uniquewords[alphabet];
  		//Gets the most frequent word from the list mostfreq at position of alphabet.
  		String holder = mostfreq.get(alphabet).getWord();
  		//Gets the count of the most frequent word from the list mostfreq at position of alphabet.
  		int holdercount = mostfreq.get(alphabet).getCount();
  		System.out.printf("Letter %c %5d %15s %6d \n", b, uniquewords[alphabet], holder, holdercount);
  		}
		}
			//Prints the total amount of unique words stored in the array.
			System.out.println("There were a total of " + totalwords + " unique words.");
  }
}
