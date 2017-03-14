// Chess.java
// Illustrate use of inheritance, radio buttons, and JPanels inside JPanels
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

/**  
 *  Calculates and displays which positions
 *  a chess piece (for now, just bishops and knights) 
 *  can "attack" from a given location on a standard
 *  8 by 8 chessboard.
 *
 *  @author  Dr. H.H. Leitner
 *  @version Last modified:  April 2, 2006
 */


class Chess extends JFrame implements ActionListener
{
   JButton [] [] chessboardLocations = new JButton [8] [8];
   JPanel chessArea = new JPanel();
   JPanel buttonPanel = new JPanel();
   JPanel userControlArea = new JPanel();
   Font f = new Font ("Helvetica", Font.BOLD, 18);
   JRadioButton bishopButton = new JRadioButton("Bishop", true);
   JRadioButton knightButton = new JRadioButton("Knight", false);
   JRadioButton queenButton = new JRadioButton("Queen", false);
   JRadioButton rookButton = new JRadioButton("Rook", false);
   JLabel first = new JLabel("FIRST, choose the type of piece to play with");
   JLabel second = new JLabel("SECOND, click a position on the board");
   JButton startButton = new JButton("THIRD, click *here* to see the attack!");
   JButton resetButton = new JButton("LASTLY, click *here* to reset the chessboard!");
   ChessPiece p = new Bishop();
   ButtonGroup buttonGroup = new ButtonGroup();

  public Chess ()
  {
     Container c = getContentPane();
     chessArea.setLayout(new GridLayout(8, 8));
     ImageIcon im = new ImageIcon("bishop.jpg");
     for (int i = 0; i < 8; i++)
       for (int j = 0; j < 8; j++)
       {    
           chessboardLocations[i][j] = new JButton();
           chessboardLocations[i][j].setFont(f);
           if ( (i+j) % 2 == 0) chessboardLocations[i][j].setBackground(Color.YELLOW);
           else chessboardLocations [i][j].setBackground(Color.RED.darker());
           chessArea.add(chessboardLocations[i][j]);
           chessboardLocations[i][j].addActionListener(this);
        }
    
     setUpChessPiece(bishopButton, knightButton, queenButton, rookButton);

     buttonPanel.setBorder(new TitledBorder(new EtchedBorder(), "Chess Piece"));
      
     startButton.addActionListener(this);
     resetButton.addActionListener(this);
   
     c.add(chessArea, BorderLayout.WEST);
     userControlArea.setLayout( new BoxLayout(userControlArea, BoxLayout.Y_AXIS));
     buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
     
     setupWidget(first, 20);
     setupWidget(buttonPanel, 45);
     setupWidget(second, 45);
     setupWidget(startButton, 45);
     setupWidget(resetButton, 5);
     userControlArea.setBackground(Color.GRAY.brighter());
     c.add(userControlArea, BorderLayout.CENTER);       
  }
    
  private void setUpChessPiece(JRadioButton... jrbs)
  {
     for (JRadioButton p : jrbs)
     {
       p.setFont(f);
       p.setIconTextGap(8);
       buttonGroup.add(p);
       buttonPanel.add(p);
       buttonPanel.add(Box.createVerticalStrut(10));
       p.addActionListener(this);
     }
  }
  
  private void setupWidget( Component c, int n)
  {
     c.setForeground(Color.MAGENTA.darker().darker());
     c.setFont(f);
     userControlArea.add(c);
     userControlArea.add(Box.createVerticalStrut(n));
  }
     

  private void resetTheBoard()
  {
    for (int i = 0; i < 8; i++)
      for (int j = 0; j < 8; j++)
      {
         chessboardLocations[i][j].setIcon (startButton.getDisabledIcon()); 
         if ( (i+j) % 2 == 0) chessboardLocations[i][j].setBackground(Color.YELLOW);
         else chessboardLocations [i][j].setBackground(Color.RED); 
      }
  }

  public void actionPerformed (ActionEvent event)
  {
      if (event.getSource() == bishopButton)  p = new Bishop(); 
      else if (event.getSource() == knightButton)  p = new Knight();
      else if (event.getSource() == queenButton)  p = new Queen();
      else if (event.getSource() == rookButton)  p = new Rook();
      else if (event.getSource() == resetButton) resetTheBoard(); 
      
      else if (event.getSource() == startButton)
      {
          for (int row = 0; row < 8; row++)
            for (int col = 0; col < 8; col++)
               if (p.attackingThisLocation(row, col)) 
               {
                  chessboardLocations[row][col].setText("");
                  chessboardLocations[row][col].setBackground(Color.BLUE);
                  chessboardLocations[row][col].setIcon(p.image);
               }
      }
      else   // we just clicked somewhere inside the chessboard
      { 
         for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                if (event.getSource() == chessboardLocations[i][j]) 
                { 
                   p.setRow(i); 
                   p.setColumn(j);
                   chessboardLocations[i][j].setText((i+1) + ", " + (j+1));
                }
                else chessboardLocations[i][j].setText("");
      } 
  }
       
  public static void main ( String[] args ) 
  {
       
     Chess myChessBoard = new Chess();
     myChessBoard.setSize(1000, 700);
     myChessBoard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     myChessBoard.setVisible(true);
    
  }
}
