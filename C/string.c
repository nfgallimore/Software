/* strings1.c */

#include <cs50.h>
#inclue <stdio.h>
#include <string.h>

int main(void)
{
	// get line of text
	string s = GetString();
	// print string, one character per line
	if (s != NULL)
	{
		for (int i = 0; i < strlen(s); i++)
		{
			char c = s[i];
			printf("%c\n", c);
		}
	}
	return 0;
}