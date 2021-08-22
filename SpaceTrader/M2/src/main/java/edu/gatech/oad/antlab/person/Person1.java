<<<<<<< HEAD
package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string
 *
 *  @author Kendall Abbott
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person1(String pname) {
        name = pname;
    }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  //Person 1 put your implementation here
<<<<<<< HEAD
	  return null;
=======
	   String subString = input.substring(0, 2);
       String otherString = input.substring(2);
       return otherString + subString;
>>>>>>> master
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
=======
package edu.gatech.oad.antlab.person;

/**
 *  A simple class for person 1
 *  returns their name and a
 *  modified string
 *
 *  @author Kendall Abbott
 *  @version 1.1
 */
public class Person1 {
  /** Holds the persons real name */
  private String name;
  	/**
	 * The constructor, takes in the persons
	 * name
	 * @param pname the person's real name
	 */
  public Person1(String pname) {
    name = pname;
  }
  	/**
	 * This method should take the string
	 * input and return its characters rotated
	 * 2 positions.
	 * given "gtg123b" it should return
	 * "g123bgt".
	 *
	 * @param input the string to be modified
	 * @return the modified string
	 */
	private String calc(String input) {
	  //Person 1 put your implementation here
		String string1 = input.substring(0, 2);
		String string2 = input.substring(2);
	 	return string2 + string1;
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
>>>>>>> 3161e3241148b32de608d0049e5de2241bef6e97
