//  Knight.java
//  
//  Created by Henry Leitner on July 2, 2013
//

class Knight extends Piece
{
    boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;

      if ((columnDiff * columnDiff + rowDiff * rowDiff == 5) ||
          ( (columnDiff == 0) && (rowDiff == 0))) return true;
      else return false; 
               
    }
}
