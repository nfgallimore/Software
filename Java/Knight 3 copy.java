//  Knight.java
//  
//  Created by Henry Leitner on Thu Oct 31 2005
//  Modified on April 1, 2006

import javax.swing.*;

class Knight extends ChessPiece
{
    public Knight()
    {
      image = new ImageIcon("knight.jpg");
    }

    protected boolean attackingThisLocation (int indexRow, int indexColumn)
    {
      int columnDiff = pieceColumn - indexColumn;
      int rowDiff = pieceRow - indexRow;

      if ((columnDiff * columnDiff + rowDiff * rowDiff == 5) ||
          ( (columnDiff == 0) && (rowDiff == 0))) return true;
      else return false; 
               
    }
}
