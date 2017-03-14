#include <stdio.h>
#include <math.h>
#include <string.h>
#include <stdlib.h>

#define LENGTH 5

typedef char *key;

typedef char *ciphertext;

typedef char *message;

key Gen(int n)
{
	//key k = "11111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
	key k = "12345";
	if (strlen(k) >= LENGTH)
	{
		return k;
	}
	else
	{
		return NULL;
	}
}


ciphertext Enc(key k, message m)
{
	ciphertext c;
	for (int i = 0, len = strlen(k); i < len; i++)
	{
		c[i] = (char) k[i] ^ m[i];
	}
	return c;
}

message Dec(key k, ciphertext c)
{
	message m;
	for (int i = 0, len = strlen(c) - 1; i < len; i++)
	{
		m[i] = (char) k[i] ^ c[i];
	}
	return m;
}

int main(void)
{
	printf("\nPlease enter a message: ");
	message m;
	malloc(sizeof(char[LENGTH]));
	scanf("%s", m);
	printf("\n%s", Enc(Gen(LENGTH), m));
}
