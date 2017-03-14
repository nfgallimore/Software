
int size = sizeof(in_fptr);
char buffer[size];
size_t bytes;

while (0 < (bytes = fread(buffer, 1, sizeof(buffer), infile))
       fwrite(buffer, 1, bytes, outfile);