//
//  Capitalize.c
//  
//
//  All rights reserved by Nick Gallimore on 10/22/13

#include <stdio.h>
#include <ctype.h>
#include <dirent.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <ctype.h>
#include <stdbool.h>
#include <string.h>

int includeDir(char* dirName);
int excludeDir(char* dirName);
char* capital_name(char* in_fname);
int rename_file(char* in_fname, char* out_fname);

int main(void)
{
    /*
    printf("HERE");
    char* dir = argv[1];

    if (argc != 2 && argc != 3)
    {
        printf("Usage: ./Capitalize <directory>\n");
        return 0;
    }
     */
    char* dir = "test";
   /* if (strcmp(argv[2], "-i") == 0)
    {
        printf("HERE");
        int error = includeDir(dir);
        if (error != 0)
        {
            printf("Could not stat file:  %s\n", dir);
        }
        else
        {
            printf("Successful rename, terminating program.\n");
        }
    }
    else
    {*/
        int error = excludeDir(dir);
        if (error != 0)
        {
            printf("Program runtime error, please consult --help for program usage: \n Error: %d\n", error);
        }
        else
        {
            printf("Program was successful, now terminating.\n");
        }
    
}
/*  This is an alternative to strcopy, which gives us more control in future versions.
    Albeit to checking if first char isUpper already as it would require computing the value of an statement in O(n) plus the running time of the isUpper function:
    O(n = 4) + O(isLower(n)).
*/
char* capital_name(char* in_fname)
{
    /* changes first letter to upper regardless */
    char* out_fname;
    out_fname[0] = toupper(in_fname[0]);

    /* note the performance increase over using the following: 
        for (int i = 1; i < strlen(in_fname) i++); */
    
    /* copies every char of input fname to output fname excluding first */
    int len = strlen(in_fname);
    for (int i = 1; i < len; i++)
    {
        out_fname[i] = in_fname[i];
    }
    
    /* converts first char of output fname to uppercase */
    out_fname[0] = toupper(in_fname[0]);
    
    /* calls renameFile */
    return out_fname;
}
    
int rename_file(char* in_fname, char* out_fname)
{
    /* options to set file permissions */
    char *in_mode = "rb", *out_mode = "rwb";
    
    /* allocates memory for input and output file pointers */
    FILE *in_fptr, *out_fptr;
    
    /* error logging */
    int in_err = 0, out_err = 0;
    
    /* attempts to open input file */
    in_fptr = fopen(in_fname, in_mode);
    
    /* if input file doesn't exist */
    if (in_err != 0)
    {
        /* returns -1 and prints file name */
        fprintf(stderr, "Can't open input file %s!\n", in_fname);
        return -1;
    }
    
    /* attempts to create output file */
    fopen(out_fname, out_mode);
    
    /* checks if write was successful (proper write permissions) */
    if (out_err != 0)
    {
        fclose(out_fptr);
        return -1;
    }
    
    /* allocates 4 bytes to temporarily store each char in file */
    int letter = 0;
    
    /* while there is no errors */
    while(1)
    {
        /* temporarily store each letter of input file */
        letter = fgetc(in_fptr);
        
        /* checks if we've reached end of file */
        bool end_of_file = feof(in_fptr);
        
        /* write our letter to the output file */
        if (!end_of_file)
        {
            fputc(letter, out_fptr);
        }
        
        /* if we've reached end of file exit loop */
        else
        {
            break;
		}
    }
        
    /* close both files */
    fclose(in_fptr);
    fclose(out_fptr);
    
    /* function returns 0 errors */
    return  0;
}



int includeDir(char* dir)
{
    struct dirent *dp;
    DIR *dfd;

    char filename_qfd[100] ;
    char new_name_qfd[100] ;

    while ((dp = readdir(dfd)) != NULL)
    {
        struct stat stbuf ;
        sprintf( filename_qfd , "%s/%s",dir,dp->d_name) ;
        if( stat(filename_qfd,&stbuf ) == -1 )
        {
            printf("Unable to stat file: %s\n",filename_qfd) ;
            continue ;
        }
        else
        {
            char* new_name = capital_name(dir) ;// returns the new string
            // after removing reqd part
            sprintf(new_name_qfd,"%s/%s",dir,new_name) ;
            rename( filename_qfd , new_name_qfd ) ;
        }
    }
    return 0;
}
int excludeDir(char* dir)
{
    struct dirent *dp;
    DIR *dfd;
    
    char filename_qfd[100] ;
    char new_name_qfd[100] ;
    printf("no");
    while ((dp = readdir(dfd)) != NULL)
    {
        printf("in");
        struct stat stbuf ;
        sprintf( filename_qfd , "%s/%s",dir,dp->d_name) ;
        if( stat(filename_qfd,&stbuf ) == -1 )
        {
            printf("Unable to stat file: %s\n",filename_qfd) ;
            continue ;
        }
        
        if ( ( stbuf.st_mode & S_IFMT ) == S_IFDIR )
        {
            continue;
            // Skip directories
            printf("HERE1");

        }
        else
        {
            char* new_name = capital_name(dir) ;// returns the new string
            // after removing reqd part
            sprintf(new_name_qfd,"%s/%s",dir,new_name) ;
            rename( filename_qfd , new_name_qfd ) ;
            printf("HERE2");

        }
    }
    return 0;
}