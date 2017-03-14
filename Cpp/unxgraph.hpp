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

// Flush input stream through ENTER key.
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





