// Copyright 2013, Nicholas F. Gallimore, All Rights Reserved
// Produced at Harvard University Fall

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <time.h>
#include <cs50.h>

typedef struct node
{
	void* data;
	struct node* next;	
} node;

/*************************************************************/
///////////////////////  PROTOTYPES  /////////////////////////
/***********************************************************/

// node* prototypes
node* create(void* data);
node* insert_after(node* node, void* data);
node* insert_front(node* list, void* data);
node* find(node* node, int(*func)(void*,void*), void* data);

// integer prototypes
int remove_node(node* list, node* data);
int foreach(node* node, int(*func)(void*));
int printstring(void* str);
int findstring(void* listdata, void* searchdata);

// function prototypes
void printlist(node* list);
bool str_search(node* node, char* str);
node* remove_string(node* list, char* str);

// main menu prototypes
char i_option(char c, node* list);
char r_option(char c, node* list);
char s_option(char c, node* list);
char menu(char c, node* list);

/***********************************************************/
////////////////////// MAIN PROGRAM   //////////////////////
/**********************************************************/

int main(void)
{
    node *list;
    
	/* Create initial elements of list */
	list = create((void*)"Head");
	insert_after(list, (void*)"Tail");

	printf("Initial list:\n");
	printlist(list);
	char choice;
	
	do
	{
        printf("______________________________\nChoose one of the following:\n_____________________________\n\n");
        printf("(i). Insert.\n(r). Remove\n(s). Search\n(p). Print List\n(m). Main Menu\n(q). Quit.");
        printf("\n\n");
        choice = GetChar();
        printf("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        menu(choice, list);
    } 
    while (choice != 'q');
    return 0;
}

char menu(char choice, node* list)
{
    if (choice == 'i')
    {
        i_option(choice, list);
    }
    else if (choice == 'r')
    {
        r_option(choice, list);
    }
    else if (choice == 's')
    {
        s_option(choice, list);
    }
    else if (choice == 'p')
    {
        printlist(list);
    }
    else if (choice == 'm')
    {
        return 'm';
    }
    else if (choice == 'q')
    {
        return 'q';
    }
    else
    {
        return 'm';
    }
}
char i_option(char choice, node* list)
{
    printf("Enter an item to insert: ");
    char* input_insert = GetString();

    /* insert_after 1 extra element after second */
    insert_after(list->next, (void*)input_insert);
    printf("List after insertion:\n");
    printlist(list);
    return 'm';
}
char r_option(char choice, node* list)
{
    printf("Enter a string to remove: ");
    char* str = GetString();
    remove_string(list, str);
    return 'm';
}

char s_option(char choice, node* list)
{
    /* Search */
    printf("Enter a string to search for: ");

    char* str = GetString();

    if (str_search(list, str))
    {
	    printf("Found \"%s\"\n", str);
    }
    else 
    {
        printf("Did not find \"%s\".\n", str);
    }
    printlist(list);
    return 'm';
}
/*************************************************************/
//////////////  IMPLEMENTATION OF PROTOTYPES  ////////////////
/***********************************************************/
node* remove_string(node* list, char* str)
{
    if (str_search(list, str))
    {
        node* node = find(list, findstring, str);
        remove_node(list, node);
	    printf("List after removing inserted node:\n");
        printlist(list);
        return node;
    }
    else
    {
        printf("Sorry, your item was not found.");
        return NULL;
    }
}

bool str_search(node* list, char* str)
{
    return (find(list, findstring, str) != NULL);
}
    
void printlist(node* list)
{
    foreach(list, printstring);
    putchar('\n');
}
node* create(void* data)
{
	node* node;
	
	if(!(node = malloc(sizeof(node))))
	{
	    return NULL;
	}
    node-> data = data;
	node-> next = NULL;
	
	return node;
}

node* insert_after(node* node, void* data)
{
	struct node* newnode;
    newnode = create(data);
    newnode-> next = node-> next;
    node-> next = newnode;
	return newnode;
}

node* insert_front(node* list, void* data)
{
	node* newnode;
        newnode = create(data);
        newnode-> next = list;
	return newnode;
}

int remove_node(node* list, node* node)
{
	while (list-> next && list-> next != node)
	{
	    list = list-> next;
    }
    if (list-> next) 
    {
		list-> next = node-> next;
		free(node);
		return 0;		
	} 
	else 
	{
	    return -1;
    }
}

int foreach(node* node, int(*func)(void*))
{
	while (node) 
	{
		if (func(node-> data) != 0) 
		{
		    return -1;
		}
	    node = node-> next;
	}
	return 0;
}

node* find(node* node, int(*func)(void*, void*), void* data)
{
	while(node) 
	{
		if (func(node-> data, data ) > 0)
		{
	        return node;
		}
		node = node-> next;
	}
	return NULL;
}

int printstring(void* str)
{
	printf("%s\n", (char *)str);
	return 0;
}

int findstring(void* listdata, void* searchdata)
{
	return strcmp((char*)listdata, (char*)searchdata)?0:1;
}
