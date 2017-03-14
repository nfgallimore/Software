static bool issegment(const char* str)
{
    if(!strncasecmp(str, "gs", 2))
        return true;
    if(!strncasecmp(str, "fs", 2))
        return true;
    if(!strncasecmp(str, "es", 2))
        return true;
    if(!strncasecmp(str, "ds", 2))
        return true;
    if(!strncasecmp(str, "cs", 2))
        return true;
    if(!strncasecmp(str, "ss", 2))
        return true;
    return false;
}

static const char* Intel2Nasm(const char* intel)
{
    char temp[256]="";
    static char nasm[256]="";
    memset(nasm, 0, sizeof(nasm));
    memset(temp, 0, sizeof(temp));

    int len=strlen(intel);
    for(int i=0,j=0; i<len; i++) //fix basic differences and problems
    {
        if(!strncasecmp(intel+i, "ptr", 3)) //remote "ptr"
        {
            i+=2;
            if(intel[i+1]==' ')
                i++;
            continue;
        }
        else if(!strncasecmp(intel+i, "  ", 2)) //remove double spaces
            continue;
        else if(intel[i]=='\t') //tab=space
        {
            j+=sprintf(temp+j, " ");
            continue;
        }
        j+=sprintf(temp+j, "%c", intel[i]);
    }
    len=strlen(temp);
    for(int i=0,j=0; i<len; i++)
    {
        if(temp[i]==' ' and temp[i+1]==',')
            continue;
        else if(temp[i]==',' and temp[i+1]==' ')
        {
            j+=sprintf(nasm+j, ",");
            i++;
            continue;
        }
        else if(issegment(temp+i) and temp[i+2]==' ')
        {
            j+=sprintf(nasm+j, "%c%c", temp[i], temp[i+1]);
            i+=2;
            continue;
        }
        else if(temp[i]==':' and temp[i+1]==' ')
        {
            j+=sprintf(nasm+j, ":");
            i++;
            continue;
        }
        j+=sprintf(nasm+j, "%c", temp[i]);
    }
    len=strlen(nasm);
    for(int i=0,j=0; i<len; i++)
    {
        if(issegment(nasm+i) and nasm[i+2]==':' and nasm[i+3]=='[')
        {
            j+=sprintf(temp+j, "[%c%c:", nasm[i], nasm[i+1]);
            i+=3;
            continue;
        }
        j+=sprintf(temp+j, "%c", nasm[i]);
    }
    strcpy(nasm, temp);
    return nasm;
}
