// Unix Utilities Library
// Copyright (c) 1988-94 Paul Bamberg
// Written for Computer Science S-111 at Harvard University
//
// Version 1.1
// Revision List:
//     6/94 Sweeney  remove special std.h required.
//     8/94 Sweeney  added constant, MAX_NAME_LEN et.al.

#ifndef S111UNX_H
#define S111UNX_H

#include<stdlib.h>
#include<time.h>

const int NO    = 0;
const int YES   = 1;

const int MAX_NAME_LEN = 100;	// maximum number of chars for a filename

//typedef int int16;
//typedef unsigned long int uns32;

void randomize();
int random(int range);

#endif














