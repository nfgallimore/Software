int main (int argc, char *argv[])
{
    int r, line = 0, found = 0;
    float temp, t_tot = 0;
    char loc[32];

    FILE * input;
    input = fopen("words.txt", "r");

    if (input == NULL)
    {
        printf ("Error opening the file\n\n'");
        exit(EXIT_FAILURE);
    } else {

        if (argc == 2)
        {
            r = fscanf(input, "%s\n", loc);
            while (r != EOF)
            {
                    if(strcmp(argv[2], loc) == 0)
                    {
                        t_tot += temp;
                        found++;
                    }
                }
                else
                    printf ("Error, line %d in wrong format!\n\n", line);
                r = fscanf(input, "%f %s\n", &temp, loc);
            }
            printf ("The average temperature in %s is: %.1f\n\n", argv[2], (t_tot / found));
        }

    fclose(input);

    }
}