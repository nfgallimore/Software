package utils;

import java.io.*;

/** Keyboard.java - simple console i/o package;
 * no-frills keyboard input of integers, reals,
 * strings, and characters.
 *
 * @author M. Dennis Mickunas, June 9, 1997
 */

public class Keyboard {

/*
 * Annotated by C. Morrison, 6/99, javadoc comments added.
 * page references are to Sun/Addison-Wesley "Java Series" 
 * books:  JCL is "Java Class Libraries, Second ed., vol 1"
 */

static boolean iseof = false;
static char c;
static int i;
static double d;
static String s;

/* WARNING:  THE BUFFER VALUE IS SET TO 1 HERE TO OVERCOME
** A KNOWN BUG IN WIN95 (WITH JDK 1.1.3 ONWARDS)
*/

/* CM's notes:  it's the BufferedReader's buffer whose SIZE is
 *  set to 1.
 * 
 *  There are actually 3 buffers here!  
 *  System.in is buffered by default (JCL 1717)
 *  InputStreamReader has an 8k buffer,
 *  BufferedReader has a 1-char buffer
 */

static BufferedReader input 
       = new BufferedReader (
              new InputStreamReader(System.in),1);

  /** reads first int found in the input "stream",
   *  discards the rest of the line; Ignores initial whitespace.
   *
   *  @return the int, or 0 if at EOF
   */
  public static int readInt () {
    if (iseof) return 0;
    System.out.flush();
    try { 
      s = input.readLine(); 
    }
    catch (IOException e) {
      System.exit(-1);
    }
    if (s==null) {
      iseof=true; 
      return 0;
    }
    i = new Integer(s.trim()).intValue();
    return i;
  }


  /** reads first char found in the input "stream",
   *  including whitespace.
   *
   *  @return the char, or (char)0 if at EOF
   */
  public static char readChar () {
    if (iseof) return (char)0;
    System.out.flush();
    try { 
      i = input.read(); 
    }
    catch (IOException e) {
      System.exit(-1);
    }
    if (i == -1) {
      iseof=true; 
      return (char)0;
    }
    return (char)i;
  }

  /** reads first double found in the input "stream",
   *  discards the rest of the line; Ignores initial whitespace.
   *
   *  @return the double, or 0.0 if at EOF
   */
  public static double readDouble () {
    if (iseof) return 0.0;
    System.out.flush();
    try { 
      s = input.readLine();
    }
    catch (IOException e) {
      System.exit(-1);
    }
    if (s==null) {
      iseof=true; 
      return 0.0;
    }
    d = new Double(s.trim()).doubleValue();
    return d;
  }

  /** reads the next line in the input "stream", returns it
   *    as a java String.
   *  
   *
   *  @return the String, or null if at EOF
   */
  public static String readString () {
    if (iseof) return null;
    System.out.flush();
    try { 
      s=input.readLine();
    }
    catch (IOException e) {
      System.exit(-1);
    }
    if (s==null) {
      iseof=true; 
      return null;
    }
    return s;
  }

  public static boolean eof () {
    return iseof;
  }

}

