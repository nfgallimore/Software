int main (int argc, char **argv)
{
    FILE *fp = fopen(argv[1],"r");
    
    char buffer[256] = {0x0};
    
    while (fp != NULL && fget(buffer, sizeof(buffer), fp) != NULL)
    {
        if (strstr(buffer, "word1")
        {
            printf("%s", buffer);
        }
        else if (strstr(buffer, "word2")
        {
            printf("%s", buffer);
        }
    }
                 
    if (fp != NULL)
    {
        fclose(fp);
    }
                 
    return 0;
    
}