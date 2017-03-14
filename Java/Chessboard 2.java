// Chessboard.java 
// Unit 5 lecture notes

/**  
 *  Calculates and displays which positions
 *  a chess piece (for now, just bishops and knights) 
 *  can "attack" from a given location on a standard
 *  8 by 8 chessboard.
 *
 *  @author  Dr. H.H. Leitner
 *  @version Last modified:  July 13, 2013
 */

import java.util.*;

class Chessboard 
{
    /**
     * Locates the piece on the board, showing all possible moves, and
     * places a 'b' or 'w' for the remaining squares.
     */

    public static void main ( String [] args ) 
    {
        Piece p;
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Would you like to play with a Bishop or Knight? ");
        String answer = keyboard.nextLine();

        if (answer.charAt(0) == 'b' || answer.charAt(0) == 'B') p = new Bishop();
        else p = new Knight();

        p.placeOnChessBoard();
       
        int  indexRow, indexColumn;
        int columnDiff, rowDiff;
       
        System.out.println ("\n  1 2 3 4 5 6 7 8");
        
        for (indexRow = 1; indexRow <= 8; indexRow++) 
        {
            System.out.print (indexRow);        // number the rows
            for (indexColumn = 1; indexColumn <= 8; indexColumn++)
             { 
                if (p.attackingThisLocation( indexRow, indexColumn ))
                      System.out.print (" *"); 
                else
                    if ((indexColumn + indexRow) % 2 == 0) 
                         System.out.print (" b");
                    else  
                        System.out.print (" w");
             }
            System.out.println ();
        }
        System.out.println ();
    
    }
}
