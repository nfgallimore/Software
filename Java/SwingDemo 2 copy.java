// This program creates a frame in which the major Swing "widgets"
//  are displayed

import java.awt.*;
import javax.swing.*;

class SwingDemo
{
    public static void main (String [] args)
    {

          JFrame f = new JFrame("Demo of Swing Components");
          Font font = new Font("Helvetica", Font.BOLD, 36);
	  f.setLayout( new FlowLayout( ) );
	
  	  JLabel label = new JLabel("JLabel");
	  label.setFont(font);

	  JButton button = new JButton( "JButton" );
	  button.setFont(font);
	  button.setForeground(Color.GREEN.darker());
	  button.setHorizontalTextPosition( JButton.CENTER );
	  //button.setBorderPainted(false);
	  //button.setContentAreaFilled( false );
	  JTextField textfield = new JTextField( "JTextField" );
	  textfield.setFont(font);
	  textfield.setForeground(Color.BLUE);
	
  	  JTextArea textarea = new JTextArea (4, 20);
	  textarea.setFont(font);
	  textarea.setForeground(Color.RED.darker());
	  textarea.setText( "JTextArea\ncan have\nmultiple"
  			     + " lines of text\nand be added into a JScrollPane\nstuff");
	  JScrollPane scroller = new JScrollPane( textarea );
	
	  JRadioButton radioBtn1 = new JRadioButton( "JRadioButton1" );
	  JRadioButton radioBtn2 = new JRadioButton( "JRadioButton2" );
	  JRadioButton radioBtn3 = new JRadioButton( "JRadioButton3" );
	  radioBtn1.setFont(font);
	  radioBtn2.setFont(font);
	  radioBtn3.setFont(font);
	  ButtonGroup radioGroup = new ButtonGroup( );
	  radioGroup.add( radioBtn1 );
	  radioGroup.add( radioBtn2 );
	  radioGroup.add( radioBtn3 );
	
	
	  DefaultListModel model = new DefaultListModel( );
	  JList list = new JList( model );
	  list.setFont(font);
	  list.setVisibleRowCount(3);
	  list.setForeground(Color.GRAY.darker());
	  for (int i =1; i < 5; i++)  model.addElement("JList: Option " + i);

	  JScrollPane listScroller = new JScrollPane( list );
	
	  JCheckBox checkbox1 = new JCheckBox( "JCheckBox1" );
	  JCheckBox checkbox2 = new JCheckBox( "JCheckBox2" );
	  checkbox1.setFont(font);
	  checkbox2.setFont(font);
	  checkbox1.setForeground(Color.ORANGE.darker());
	  checkbox2.setForeground(Color.ORANGE.darker());

	  JComboBox dropdown = new JComboBox( );
	  for (int i =1; i < 5; i++) dropdown.addItem("JComboBox: Option " + i);
	  dropdown.setFont(font);
	  dropdown.setForeground(Color.MAGENTA.darker());
	
	  f.add( label );
	  f.add(button);
	  f.add( textfield );
	  f.add( scroller );
	  f.add( radioBtn1 );
	  f.add( radioBtn2 );
	  f.add( radioBtn3 );
	  f.add( listScroller );
	  f.add( checkbox1 );
	  f.add( checkbox2 );
	  f.add( dropdown );
	  f.setSize(500, 500);
	  f.setVisible(true);
    }
}
