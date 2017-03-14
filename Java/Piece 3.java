//  Piece.java
//  Created by Henry Leitner on July 5, 2013
//

import java.util.*;

abstract class Piece
{
    protected int pieceRow;
    protected int pieceColumn;
    
    public void placeOnChessBoard()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.print ("Type the ROW where your chess piece is located: ");
        pieceRow = keyboard.nextInt();
        System.out.print ("Type the COLUMN where your chess piece is located: ");
        pieceColumn = keyboard.nextInt();
        if ((pieceRow < 1) || (pieceRow > 8)
                           || (pieceColumn < 1) || (pieceColumn > 8))
            System.out.print ("Invalid input, but I'll try anyway");
        keyboard.nextLine();
    }
    

    abstract boolean attackingThisLocation (int row, int column);
}
