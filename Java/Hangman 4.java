import java.util.*;

class Hangman
{
    public static void  main (String [] args)
    {
        Bitset correctGuesses = new Bitset (128);
        Bitset lettersInWord = new Bitset (128);
        Bitset wrongGuesses = new Bitset (128);
	 
        final int WRONG_GUESS_ROW = 20;
        final int WIN_OR_LOSE_ROW = 3;
        final int GUESS_ROW = 22;
        final int SECRET_WORD_ROW = 12;
	 
        char letterGuessed;		// a user's "guess" of 1 letter		
        int mistakes = 0;		// # of incorrect guesses
    
        Scanner keyboard = new Scanner(System.in);
        System.out.print("What is the secret word? ");
        String secretWord = keyboard.nextLine().toLowerCase();
        TxtGrph.homeAndClear();
        for (int i = 0; i < secretWord.length(); i++)
        {
            lettersInWord.include (secretWord.charAt(i));
            TxtGrph.position (SECRET_WORD_ROW, 2*i + 1);
            System.out.print("_");
        }
        TxtGrph.position (WRONG_GUESS_ROW, 1);
        System.out.print ("WRONG GUESSES: ");
        do {
	       TxtGrph.position (GUESS_ROW, 1);
	       TxtGrph.eraseRight();
	       System.out.print(  " TRY A NEW LETTER: " );
	       do {
		      TxtGrph.position (GUESS_ROW, 20);
		      letterGuessed = keyboard.nextLine().toLowerCase().charAt(0); 
		  } while ( (correctGuesses.union (wrongGuesses)).member(letterGuessed));
 
	       if (lettersInWord.member(letterGuessed)) 
	       {		// the guess is correct; display every place
			        // it occurs in the secret word
		   for (int i = 0 ; i < secretWord.length(); i++)
                      if (letterGuessed == secretWord.charAt(i))
		      {
			   TxtGrph.position (SECRET_WORD_ROW-1, 2 * i + 1);
			   System.out.print(  letterGuessed);
                      }
		      TxtGrph.beep ();	
		      correctGuesses.include (letterGuessed);
	       }  
               else				// the guess was INCORRECT
	       {
	           wrongGuesses.include (letterGuessed);
	           TxtGrph.position (WRONG_GUESS_ROW, 2 * mistakes + 16);
	           System.out.print (letterGuessed);
	           mistakes++;
	       }
	   } while ((mistakes <= 8) && !(correctGuesses.equals(lettersInWord)));
	TxtGrph.position (WIN_OR_LOSE_ROW, 1);
	if (mistakes > 8) 
            System.out.println( "YOU LOSE -- the word was -- " + secretWord );
	else System.out.println( "YOU WIN\n\n");
  }
}
