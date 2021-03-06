//package edu.gatech.oad.antlab.person;
import java.util.Random;
import java.util.ArrayList;

/**
 *  A simple class for person 2
 *  returns their name and a
 *  modified string 
 *
 * @author Julia
 * @version 1.1
 */
public class Person2 {
    /** Holds the persons real name */
    private String name;
	 	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */

	 	//delete later

	 public Person2(String pname) {
	   name = pname;
	 }
	/**
	 * This method should take the string
	 * input and return its characters in
	 * random order.
	 * given "gtg123b" it should return
	 * something like "g3tb1g2".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
		String newString = new String("");
		Random rand = new Random();
		int size = 0;
		ArrayList<Integer> arr = new ArrayList<Integer>(input.length());
		while (newString.length() != input.length()) {
			int n = rand.nextInt(input.length());
			if (!(arr.contains(n))) {
				arr.add(n);
				newString += Character.toString(input.charAt(n));
			}
		}
	  return newString;
	}
	/**
	 * Return a string rep of this object
	 * that varies with an input string
	 *
	 * @param input the varying string
	 * @return the string representing the 
	 *         object
	 */
	public String toString(String input) {
	  return name + calc(input);
	}
}
