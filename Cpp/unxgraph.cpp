// Unix Common Graphics Library
// version 1.0 Sweeney 6/95
//
// These functions provide basic text graphics.
// The primary goal was to provide a Unix equivalent
// for ibmgraph.cpp and macgraph.cpp, version 2.1
// drawline() is not implemented

// Under Unix, the constants MAXROW, MAXCOL, HOME_ROW, And 
// HOME_COL, are for documentation purposes only.
// CHanges to these constants have no affect on the functions.

#define MAXROW      25 // Max number of rows on the screen  
#define MAXCOL      80 // Max number of columns on the screen
#define HOME_ROW     1 // Reference number for topmost row   
#define HOME_COL     1 // Reference number for leftmost column 

#define BEEP_CHAR '\007' // character code for system bell  

#define BOOL int  	// setup boolean type
#define NO 0		// a NO is same as false
#define YES 1		// a YES is same as true

//------------------------------------------------------

#ifndef SCREEN_HPP
#define SCREEN_HPP

#include <ctype.h>
#include <iostream.h>
#include <time.h>        // used for time()

enum CursorType {invisible,line,block};

class screen {
public:
static BOOL InitDone;
static int row;
static int col;
static int color;
static int   BottomRow;
static int   PromptLine;
static int   SavedX;
static int   SavedY;

screen();

// Position cursor at specified row and column
void Position (int row,int  column);

// Read row and column of current cursor position
void ReadCursor(int& row , int& column);

// Set style of cursor
void SetCursorType (CursorType style);

// Move cursor up n rows, but not above row 0
// This procedure cannot scroll the screen
void Up (int n);

// Move cursor down n rows, but not below row 23
// Row 24 is reserved for prompts
// This procedure cannot scroll the screen
void Down (int n);

// Move cursor n colums to right, but not past MAXCOL
// This command will not move the cursor to a different row
void Right (int n);

// Move cursor n colums to left, but not beyond column 0
// This command will not move the cursor to a different row
void Left (int n);

// Move cursor to top left corner of screen
void Home();

// Store cursor position, overwriting any previously saved positions
void SaveCursor();

// Restore most recently saved cursor position
void RestoreCursor();

// Make cursor visible
void CursorOn();

// Make cursor invisible
void CursorOff();

// These attribute settings affect only the specified positions
// on the screen.  They may be set either before or after
// characters are written there. Any combination of bold,
// underline, and reverse is possible

enum VideoAtt {normal,underline,reverse,bold};


// Restore to normal video
void SetNormal();

// Put into bold type (extra bright)
void SetBold();


// Put into reverse video, dark characters on light background.
void SetReverse();

// Erase block of characters on the screen
void EraseBlock (int top,int left,int bottom,int right);

// Erase numchar characters starting at the cursor
void EraseChars (int numchar);

// Erase from cursor to end of the line
void EraseRight();

// Erase from start of line to cursor
void EraseLeft();

// Erase from cursor to bottom of the screen
void EraseDown();

// Erase from top of screen to cursor.
void EraseUp();

// Erase entire screen.
void EraseAll();

// Clear screen then place cursor in upper left corner.
void HomeAndClear();

// Write a message on the prompt line
void Prompt (char* message);

// Clears prompt line
void ErasePrompt();

// Prints a prompt then waits for spacebar to be pressed.
void WaitForSpace();

// Pauses the machine for n seconds.
void Sleep(int n);

// Terminates program if ('Y') is pressed
BOOL ExitProgram();

// Prompts for a 'Y' or 'N' response from the user
BOOL Affirmative (char* Prompt);

// Beeps the speaker
void Beep();

// Wait for a key to be pressed. Return YES for any non-ASCII key.
// Set code to scancode for ASCII character, to extended scancode otherwise
BOOL ReadScancode (int& code);

// Wait for a key to be pressed. Return YES for any non-ASCII key.
// Set code to scancode for ASCII character, to extended scancode otherwise
// Code is 0 if no key is pressed
BOOL ScanKbd (int& code);


// RETURNS CODE for (PRESSED KEY WITHOUT ECHO
// CODES 0..127 ARE FOR NORMAL ASCII
// CODES 200..209 ARE FOR FUNCTION KEYS
// CODES 251..254 ARE FOR ARROW KEYS
// CODE 255 IS THE RETURN KEY
void ReadKeyboard (int& code);

// Waits for an arrow key to be pressed.
// Codes are: arrows (codes 1-4), 'Q' (code 0), RETURN key (code -1)
void GetArrows (int& Code);

// Waits for a function key to be pressed.
// Codes are: function keys (codes 0..9), 'Q' (code 0),
// RETURN key (code -1) */
void GetKeypad (int& Code);

// Draw horizontal line across from current cursor position
void HorizLine (int Length);

// Draw vertical line down, from current cursor position
// cursor ends up below last segment of line
void VertLine (int Height);

// Flush all characters from input stream through ENTER key.
void flushline();

};

// ----------------------------------------------
//File boxes;

/* Copyright (c) 1988-1994 by Paul Bamberg */

class box: public screen {
public:
	  int       TopRow;
	  int       LeftCol;
	  int       CellWidth;
	  int       CellHeight;
	  int       Created;
	  int       Drawn;

/*    DEFINE POSITION AND SIZE FOR A BOX    */
/*  width and height determine how many characters fit inside */
box (int Top,int  LeftEdge, int Height, int Width);

// The following operation creates an empty box.
// Use init() to set the box's parameters.
box ();

// The following operation initializes an existing box.
void init (int Top,int  LeftEdge, int Height, int Width);

/* DRAW AN ALREADY-CREATED BOX USING LINE GRAPHICS */
void DrawBox ();

/* ERASE A PREVIOUSLY-DRAWN BOX FROM THE SCREEN */
void EraseBox ();

/* POSITION CURSOR TO UPPER LEFT CORNER OF INSIDE OF BOX */
void PositionBox ();

};

// ----------------------------------------------------
//File grids;

/* Copyright 1988-1994 by Paul Bamberg */

class grid: public screen {
public:
	  int       TopRow;
	  int       LeftCol;
	  int       CellsDown;
	  int       CellsAcross;
	  int       CellWidth;
	  int       CellHeight;
	  int       CurrentRow;
	  int       CurrentCol;
	  BOOL       Created;
	  BOOL       Drawn;

/* CREATE, BUT DO NOT DRAW, A GRID          */
/* width and height control number of characters in each cell */
/* across and down control number of cells in the grid        */
grid (int Top,int LeftEdge,int Height,int Width,int Down,int Across);

/* DRAW PREVIOUSLY-CREATED GRID USING LINE GRAPHICS */
void DrawGrid ();


/* ERASE PREVIOUSLY-DRAWN GRID, INCLUDING INTERIOR OF CELLS */
void EraseGrid ();

/* POSITION CURSOR AT SPECIFIED CELL WITHIN GRID                 */
/* cell rows numbered from 1 at top, cell columns from 1 at left */
void PositionGrid (int row, int column);

/* ACTIVATES ARROW KEYS ; SELECT CELL WITHIN GRID                     */
/* ARROW KEYS MOVE CURSOR, RETURN CELL SELECTS CELL CONTAINING CURSOR, */
/* WHOSE COORDINATES ARE RETURNED AS PARAMETERS row && col            */
void SelectCell (int& SelectedRow, int& SelectedCol);

};

#endif


// ---------------------------------------------------------

BOOL screen::InitDone = NO;
int screen::row;
int screen::col;
int screen::color;
int screen::BottomRow;
int screen::PromptLine;
int screen::SavedX;
int screen::SavedY;

screen::screen()
{
     if (!InitDone) {
        cout << flush;  
	BottomRow = 24;
	PromptLine = 25;
	HomeAndClear();
	InitDone = YES;
     }
}

// Position cursor at specified row and column
void screen::Position (int row,int  column)
{
   Home();      //  Home cursor to avoid buffer overflow! 
   cout << '\033' << '[' << row << ';' << column << 'H';
}

// Read row and column of current cursor position
void screen::ReadCursor(int& row ,  int& column)
{
      ;  // not implemented, can't read cursor position
}

// Move cursor up n rows, but not above row 0
// This procedure cannot scroll the screen
void screen::Up (int n)
  {
      while( n > 0)
      {
         cout << '\033' << "[1A";
         n--;
      }
  }

// Move cursor down n rows, but not below row 23
// Row 24 is reserved for prompts
// This procedure cannot scroll the screen
void screen::Down (int n)
  {  
      while( n > 0)
      {
          cout << '\033' << "[1B";
          n--;
      }
  }

// Move cursor n colums to right, but not past MAXCOL
// This command will not move the cursor to a different row
void screen::Right (int n)
  {
      while( n > 0)
      {
          cout << '\033' << "[1C"; 
          n--;
      }
  }

// Move cursor n colums to left, but not beyond column 0
// This command will not move the cursor to a different row
void screen::Left (int n)
  {
      while( n > 0)
      {
          cout << '\033' << "[1D";
          n--;
      }
  }

// Move cursor to top left corner of screen
void screen::Home()
  {
    cout << '\033' << "[H"; 
  }

// Store cursor position, overwriting any previously saved positions
void screen::SaveCursor()
  {
     ;   // not implemented, can't read cursor position 
  }

// Restore most recently saved cursor position
void screen::RestoreCursor()
  {
     row = SavedX;
     col = SavedY;
     Position (SavedX, SavedY);
  }

void screen::SetCursorType (CursorType style)
{
      // not implemented
}

// Make cursor visible
void screen::CursorOn()
  {
    SetCursorType(line);
  }

// Make cursor invisible
void screen::CursorOff()
  {
    SetCursorType(invisible);
  }

// Restore to normal video
// The following function sets the local display
// so that all characters displayed after this function was called,
// would display using the default attributes, white-on-black,
// non-blinking. It has no effect if the default mode is already
// selected. The characters already on the screen are not changed.

void screen::SetNormal()
  {
      cout << '\033' << "[m"; 
  }

// Put into bold type (extra bright)
void screen::SetBold()
  {
       ;  // not implemented
  }

// Put into reverse video, dark characters on light background.
void screen::SetReverse()
  {
      cout << '\033' << "[7m"; 
  }

// Erase block of characters on the screen
void screen::EraseBlock (int top,int left,int bottom,int right)
{
    char buf [MAXCOL+1];
    for(col = left; col <= right; col++)
        buf[col-left] = ' ';
    buf[col-left] = '\0';
    for(row = top; row <= bottom; row++)
    {
          Position(row, left);
          cout << buf;
    }  
}

// Erase numchar characters starting at the cursor
// Does not test for writing past right mrgin.
void screen::EraseChars (int numchar)
{
     for( ; numchar > 0; numchar--)
        cout << " ";
}


// Erase from cursor to end of the line
void screen::EraseRight()
  {
      cout << '\033' << "[0K"; 
  }

// Erase from start of line to cursor
void screen::EraseLeft()
  {
      cout << '\033' << "[1K"; 
  }

// Erase from cursor to bottom of the screen
void screen::EraseDown()
  {
      cout << '\033' << "[0J"; 
  }

// Erase from top of screen to cursor.
void screen::EraseUp()
  {
      cout << '\033' << "[1J"; 
  }

// Erase entire screen.
void screen::EraseAll()
  {
      cout << '\033' << "[2J"; 
  }

// Clear screen then place cursor in upper left corner.
void screen::HomeAndClear()
  {
      cout << '\033' << "[2J"; 
      Position(0,0);
  }

// Write a message on the prompt line
void screen::Prompt (char* message)
  {
      Position(PromptLine,1);
      cout << message;
  }

// Clears prompt line
void screen::ErasePrompt()
  {
    EraseBlock(PromptLine,1,PromptLine,79);
  }


// Prints a prompt then waits for ENTER to be pressed.
void screen::WaitForSpace()
  {
    Position(PromptLine,1);
    Prompt("PRESS ENTER TO CONTINUE ");
    while(cin.get() != '\n') {}
    ErasePrompt();
  }

// Pauses the machine for n seconds.
void screen::Sleep(int n)
{
    long seconds;
    time_t startTime, endTime;
    seconds = (long) n;
    startTime = time((time_t) NULL);
    while((unsigned long int) (endTime = time((time_t) NULL))
              < (unsigned long int) startTime + seconds)
       { ; }
}

// Terminates program if ('Y') is pressed
BOOL screen::ExitProgram()
 {
    BOOL b;
    b = Affirmative("TYPE \"Y\" TO EXIT PROGRAM, \"N\" TO CONTINUE ");
    if (b)
	HomeAndClear();
    return (b );
 }


// Prompts for a 'Y' or 'N' response from the user
BOOL screen::Affirmative (char* message)
  {
    char ch;
    do {
      ErasePrompt();
      Position(PromptLine,1);    // prompt user with message
      SetBold();
      cout << message;
      SetNormal();
      ch = cin.get();
      ch = toupper(ch);
      cout << " " << ch;
    } while (! (ch == 'Y' || ch =='N'));
    ErasePrompt();
    return (ch == 'Y');
  }

// Beeps the speaker
void screen::Beep()
  {
    cout << '\007';
  }

// Wait for a key to be pressed. Return YES for any non-ASCII key.
// Set code to scancode for ASCII character, to extended scancode otherwise
BOOL screen::ReadScancode (int& code)
{
	code  = cin.get();
	if (code == 0) {
		code = cin.get();
		return YES;
	}
	else
	     return NO;
}

// Wait for a key to be pressed. Return YES for any non-ASCII key.
// Set code to scancode for ASCII character, to extended scancode otherwise
// Code is 0 if no key is pressed 
BOOL screen::ScanKbd (int& code)
{
      return YES;    //  not implemented
}

// RETURNS CODE for (PRESSED KEY WITHOUT ECHO
// CODES 0..127 ARE FOR NORMAL ASCII
// CODES 200..209 ARE FOR FUNCTION KEYS
// CODES 251..254 ARE FOR ARROW KEYS
// CODE 255 IS THE RETURN KEY
void screen::ReadKeyboard (int& Code)
  {
          ;    // not implemented
  }

// Waits for an arrow key to be pressed.
// Codes are: arrows (codes 1-4), 'Q' (code 0), RETURN key (code -1)
void screen::GetArrows (int& Code)
  {
      char arr[4];
      char ch;
      while( (((int) (ch = cin.get())) != 27)
              && (ch != 'q') && (ch != 'Q') && (ch != '\n') )
        { ; }
      if ( ((int) ch) == 27) {
          cin.read(&arr, 3);
          Code = ((int) (arr[1])) - 65;
      }
      else if ((ch == '\n') || (((int)ch) == 10) )
         Code = -1;
      else Code = 0;
  }

// Waits for a function key to be pressed.
// Codes are: function keys (codes 0..9), 'Q' (code 0),
// RETURN key (code -1) */
void screen::GetKeypad (int& Code)
  {
         ;  // not implemented
  }

// Draw horizontal line across from current cursor position
void screen::HorizLine (int Length)
  {
    for (int i = 1 ; i <= Length; i++) {
	//cout << (char)205;
	cout << '-';
    }
  }

// Draw vertical line down, from current cursor position
// cursor ends up below last segment of line
void screen::VertLine (int Height)
{
    for (int i = 1 ; i <= Height; i++) {
	//cout << (char)186;
	cout << '|';
	Left(1);
	Down(1);
      }
  }

void screen::flushline()
{
   char ch;
   while( (((int) (ch = cin.peek())) == 10) || (ch == 13))
     { ; }
}



// -------------------------------------------------------------

//File boxes;

/*    DEFINE POSITION && SIZE for (1 OF 100 POSSIBLE BOXES    */
/*  width and height determine how many characters fit inside */

box::box (int Top,int  LeftEdge,int  Height,int  Width)

  {
      if ((Top <22) && (LeftEdge <78) &&
	 ((LeftEdge + Width) <=79) &&
	 ((Top + Height) <=BottomRow)) {
	   Created = YES;
	   TopRow = Top;
	   LeftCol = LeftEdge;
	   CellWidth = Width;
	   CellHeight = Height;
	   Drawn = NO;
       } else {
	   Position(PromptLine,1);
	   cout << "ERROR IN CREATING BOX";
       }
  }

// The following operation creates an empty box.
// Use init() to establish the parameters.

box::box ()
  {
	   Created = NO;
	   Drawn = NO;
  }


/*    Initialize POSITION && SIZE for (1 OF 100 POSSIBLE BOXES    */
/*  width and height determine how many characters fit inside */

void box::init (int Top,int  LeftEdge,int  Height,int  Width)
   {
      if ((Top <22) && (LeftEdge <78) &&
	 ((LeftEdge + Width) <=79) &&
	 ((Top + Height) <=BottomRow)) {
	   Created = YES;
	   TopRow = Top;
	   LeftCol = LeftEdge;
	   CellWidth = Width;
	   CellHeight = Height;
	   Drawn = NO;
       } else {
	   Position(PromptLine,1);
	   cout << "ERROR IN INITIALIZING BOX";
       }
  }


/* DRAW AN ALREADY-CREATED BOX USING LINE GRAPHICS */

void box::DrawBox ()

  {
	Drawn = YES;
	Position(TopRow,LeftCol);
	//cout << (char)201;               /* Upper left corner */
	cout << '+';               /* Upper left corner */
	HorizLine(CellWidth);
	//cout << (char)187;               /* Upper right corner */
	cout << '+';               /* Upper right corner */
	Left(1);
	Down(1);
	VertLine(CellHeight);
	Position(TopRow+1,LeftCol);
	VertLine(CellHeight);
	//cout << (char)200;               /* Lower left corner */
	cout << '+';               /* Lower left corner */
	HorizLine(CellWidth);
	//cout << (char)188;                /* Lower right corner */
	cout << '+';                /* Lower right corner */
   }


/* ERASE A PREVIOUSLY-DRAWN BOX FROM THE SCREEN */

void box::EraseBox ()

  {
	Drawn = NO;
	for (int i = 0 ; i<=  CellHeight + 1; i++) {
	    Position(TopRow+i,LeftCol);
	    for (int j = 1 ; j <= CellWidth + 2; j++) {
	      cout << ' ';
	    };
	};
  }



/* POSITION CURSOR ; UPPER LEFT CORNER OF INSIDE OF BOX */
void box::PositionBox ()

  {
      if (Drawn)
	   Position(TopRow+1,LeftCol+1);
	else {
	   Position(PromptLine,1);
	   cout << "ERROR - WRITING TO UNDRAWN BOX ";
	}
  }

// --------------------------------------------------------------
//File grids;

/* CREATE, BUT DO NOT DRAW, A GRID          */
/* width and height control number of characters in each cell */
/* across and down control number of cells in the grid        */

grid::grid(int Top,int LeftEdge,int Height,int Width,int Down,int Across)
  {
      if ((Top <22) && (LeftEdge <78) &&
	 (LeftEdge + (Width+1)*Across <= 79) &&
	 (Top + (Height+1)*Down <= BottomRow) ) {
	     Created = YES;
	     Drawn = NO;
	     TopRow = Top;
	     LeftCol = LeftEdge;
	     CurrentRow = 1;
	     CurrentCol = 1;
	     CellWidth = Width;
	     CellHeight = Height;
	     CellsAcross = Across;
	     CellsDown = Down;
      }   else  {
	     Position(PromptLine,0);
	     cout << "ERROR IN CREATING GRID  ";
      }
  }

/* DRAW PREVIOUSLY-CREATED GRID USING LINE GRAPHICS */

void grid::DrawGrid ()
  {
     int i, j;
     BOOL t,b,r,l,v,h;
	Drawn = YES;
	for (i = 0 ; i <= (CellHeight+1)*CellsDown ; i++) {
	    Position(TopRow+i,LeftCol);
	    for (j = 0 ; j <= (CellWidth+1)*CellsAcross; j++) {
		 t = i == 0;
		 b = i == (CellHeight+1)*CellsDown;
		 l = j == 0;
		 r = j == (CellWidth+1)*CellsAcross;
		 h = i % (CellHeight+1) == 0;
		 v = j % (CellWidth+1) == 0;
		 if (t && l)
		   cout << '+';     /* Upper left corner */
		 else if (t && r)
		   cout << '+';     /* Upper right corner */
		 else if (b && l)
		   cout << '+';     /* Lower left corner */
		 else if (b && r)
		   cout << '+';     /* Bottom right corner */
		 else if (t && v)
		   cout << '+';     /* Top cross bar */
		 else if (b && v)
		   cout << '+';     /* Bottom cross bar */
		 else if (l && h)
		   cout << '+';     /* Horiz bar */
		 else if (r && h)
		   cout << '+';     /* Horiz bar */
		 else if (v && h)
		   cout << '+'; /* Cross */
		 else if (v)
		   cout << '|';
		 else if (h)
		   cout << '-';
		 else Right(1);
	       }
	  }
  }


/* ERASE PREVIOUSLY-DRAWN GRID, INCLUDING INTERIOR OF CELLS */

void grid::EraseGrid ()

  {
	Drawn = NO;
	for (int i = 0 ; i<= (CellHeight+1)*CellsDown; i++) {
	    Position(TopRow+i,LeftCol);
	    for (int j = 1 ;j <= (CellWidth+1)*CellsAcross+1; j++) {
	      cout << ' ';
	  }
	 }
  }

/* POSITION CURSOR AT SPECIFIED CELL WITHIN GRID                 */
/* cell rows numbered from 1 at top, cell columns from 1 at left */

void grid::PositionGrid (int row, int column)

  {
      if (! Drawn) {
	    Position(PromptLine,1);
	    cout << "ERROR - WRITING TO UNDRAWN GRID ";
      } else if (! ((row > 0) && (row <= CellsDown) && (column >0)
		    && (column <=CellsAcross))) {
		Position(PromptLine,0);
		cout << "ERROR IN POSITIONING FOR GRID";
      } else {
	      Position(TopRow + 1 + (row - 1) * (CellHeight + 1),
		LeftCol + 1 + (column - 1) * (CellWidth + 1));
      }
  }

/* ACTIVATES ARROW KEYS ; SELECT CELL WITHIN GRID                     */
/* ARROW KEYS MOVE CURSOR, RETURN CELL SELECTS CELL CONTAINING CURSOR, */
/* WHOSE COORDINATES ARE RETURNED AS PARAMETERS row && col            */

void grid::SelectCell (int& SelectedRow, int& SelectedCol)

  {
	int row, col;
	int code;
//	Position(PromptLine,1);
//	SetBold();
//	cout << "USE ARROW KEYS AND RETURN TO SELECT GRID CELL";
//	Beep();
//	SetNormal();
	Prompt("USE ARROW KEYS AND RETURN TO SELECT GRID CELL");

	row = CurrentRow;
	col = CurrentCol;
	PositionGrid(row, col);
	do {
	  GetArrows(code);
	  switch (code) {
	    case 1:  if (row == 1)
			row = CellsDown;
		     else
			row = (row - 2) % CellsDown + 1 ;
		     break;
	    case 2:  row = row % CellsDown + 1;
		     break;
	    case 3:  col = col % CellsAcross + 1;
		     break;
	    case 4:  if (col == 1)
			col = CellsAcross;
		     else
			col = (col - 2) % CellsAcross + 1;
		     break;
	    case 0:
//		  Position(PromptLine,0);
//		  EraseRight();
		  ErasePrompt();
		  CurrentRow = row;
		  CurrentCol = col;
	  }
	  PositionGrid(row,col);
	} while (code >= 0);
	SelectedRow = row;
	SelectedCol = col;
	ErasePrompt();
    }
