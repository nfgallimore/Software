/* File getinput.hpp
CS-111 library module
Miscellaneous input routines

Copyright (c) 1994 by Paul Bamberg
Written for Computer Science S-111 at Harvard University
*/

#ifndef GETINPUT_HPP
#define GETINPUT_HPP

#include "s111unx.hpp"

/* reads a string into the buffer str */
/* Returns NULL if the string has length 0 */
char *mygets(char* str);

/* Prompts for and reads a string
Uses default_value if user type null string
Resulting string is on the stack and must be copied and saved */
char *GetString(char *prompt, char *default_value);

/* Prompts for and reads a string
Resulting string is on the stack and must be copied and saved */
char *ReadString(char *prompt);
/* Prompts for and reads an integer
Uses default_value if user types no digits  */
int GetInteger(char msgadr[], int default_value);

/* Prompts for and reads a signed integer  */
int ReadInteger(char msgadr[]);

/* Prompts for and reads a boolean value
Uses default_value if user types ENTER before Y or N */
bool GetBoolean(char msgadr[], bool default_value);

/* Prompts for and reads a boolean value */
bool ReadBoolean(char msgadr[]);

/* Prompts for and reads a real number
Uses default_value if user types no digits  */
double GetReal(char msgadr[] , double default_value);

/* Prompts for and reads a real number */
double ReadReal(char msgadr[]);

int ReadKeyint(char chrarr[],int intarr[]);

char *strsave(char *str);

#endif
