/* File getinput.cpp
CS-111 library module
Miscellaneous input routines

Copyright (c) 1994 by Paul Bamberg
Written for Computer Science S-111 at Harvard University
Modified by Omri Traub 1998
*/

#include <stdlib.h>
#include <iostream.h>
#include <math.h>
#include <string.h>
#include <ctype.h>
#include "s111unx.hpp"

#define ENTER '\n'
#define NOInteger -1
#define MAX_ANSWER_SIZE 128



main()
{
    cout << "Hello World" << /.n;
    return = 0
}










" ";
    mygets(string);
    return(string);
}

/* Prompts for and Reads a signed integer
Uses default_value if user types no digits  */
int GetInteger(char msgadr[], int default_value)
{
    char str[6];
    cout << msgadr << " [ " << default_value << " ] : ";
    if (mygets(str) == NULL) {
	return default_value;
    } else {
	if (!isdigit(str[0])) {
	    return default_value;
	} else
	    return atoi(str);
    }
}

/* Prompts for and Reads a signed integer  */
int ReadInteger(char msgadr[])
{
    char str[6];
    do {
	cout << msgadr << " : ";
	while (mygets(str) == NULL);
	if (isdigit(str[0]))
	    return atoi(str);
    }
    while (YES);
}

/* Prompts for and Reads a boolean value
Uses default_value if user types ENTER before Y or N */
bool GetBoolean(char msgadr[], bool default_value)
{
    char ch;
    bool answer;
    if(default_value == YES)
	ch = 'Y';
    else
	ch = 'N';
    cout << msgadr << " [ " << ch << "]: ";
    while(YES) {
	ch = toupper(cin.get());
	if (ch == 'Y')
	    {
		answer = YES;
		cout << ch << "\n";
		do {
		    ch = cin.get();
		} while (ch != '\n');
		break;
	    }
	if (ch == 'N')
	    {
		answer = NO;
		cout << ch << "\n";
		do {
		    ch = cin.get();
		} while (ch != '\n');
		break;
	    }
	if (ch == ENTER)
	    {
		answer = default_value;
		if(default_value == YES)
		    ch = 'Y';
		else
		    ch = 'N';
		cout << ch << "\n";
		break;
	    }
    }
    return(answer);
	
}

/* Prompts for and Reads a boolean value */
bool ReadBoolean(char msgadr[])
{
    char ch;
    bool answer;
    cout << msgadr << " [Y, N] : ";
    while (YES) {
	ch = toupper(cin.get());
	if (ch == 'Y')
	    {
		answer = YES;
		break;
	    }
	if (ch == 'N')
	    {
		answer = NO;
		break;
	    }
    }
    cout << ch << "\n";
    return(answer);
}


int Read_keyint(char chrarr[],int intarr[])
{
    char ch;
    char *pch;
    do {
	ch = tolower(cin.get());
    } while ((pch = strchr(chrarr,ch)) == NULL);
    return intarr[pch - chrarr];
}


/* Allocates memory for a copy of a string (with leading spaces deleted)
and copies the string there.
Returns a pointer to the copy.    */

char *strsave(char *str)
{
    char *rptr;
    while (*str == ' ')
	++str;
    rptr = new char [strlen(str) +1];
    if (rptr != NULL)
	strcpy(rptr,str);
    return rptr;
}
