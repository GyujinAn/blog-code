#include <stdio.h>

void FirstFileWrite()
{
    FILE *fp = fopen("./exe/data.txt", "wt");
    if (fp == NULL)
    {
        puts("failed file open");
    }
    fputc('A', fp);
    fputc('B', fp);
    fputc('C', fp);
    fclose(fp);
}

void FirstFileRead()
{
    int ch, i;
    FILE *fp = fopen("./exe/data.txt", "rt");
    if (fp == NULL)
    {
        puts("failed file open");
    }

    for (i = 0; i < 3; i++)
    {
        ch = fgetc(fp);
        printf("%c \n", ch);
    }
    fclose(fp);
}

void TextDataFileWrite()
{
    FILE *fp = fopen("./exe/data.txt", "wt");
    if (fp == NULL)
    {
        puts("failed file open");
    }

    fputc('A', fp);
    fputc('B', fp);
    fputs("Hello \n", fp);
    fputs("World \n", fp);
    fclose(fp);
}

void TextDataFileRead()
{
    char str[30];
    int ch;
    FILE *fp = fopen("./exe/data.txt", "rt");
    if (fp == NULL)
    {
        puts("failed file open");
    }

    ch = fgetc(fp);
    printf("%c \n", ch);
    ch = fgetc(fp);
    printf("%c \n", ch);

    fgets(str, sizeof(str), fp);
    printf("%s", str);
    fgets(str, sizeof(str), fp);
    printf("%s", str);

    fclose(fp);
}

void TextCharFileCopy()
{
    FILE *src = fopen("./exe/src.txt", "rt");
    FILE *des = fopen("./exe/dst.txt", "wt");
    int cnt = 1;
    int ch;

    if (src == NULL || des == NULL)
    {
        puts("failed file open");
    }

    while ((ch = fgetc(src)) != EOF)
    {
        printf("%d : %c (feof: %d)\n", cnt, ch, feof(src));
        fputc(ch, des);
        cnt++;
    }

    if (feof(src) != 0)
    {
        puts("file copy is completed");
    }
    else
    {
        puts("file copy is failed");
    }

    fclose(src);
    fclose(des);
}

void TextStringFileCopy()
{
    FILE *src = fopen("./exe/src.txt", "rt");
    FILE *des = fopen("./exe/dst.txt", "wt");
    int cnt = 1;
    char str[20];

    if (src == NULL || des == NULL)
    {
        puts("failed file open");
    }

    while ((fgets(str, sizeof(str), src)) != NULL)
    {
        printf("%d(feof: %d) : %s \n", cnt, feof(src), str);
        fputs(str, des);
        cnt++;
    };

    if (feof(src) != 0)
    {
        puts("file copy is completed");
    }
    else
    {
        puts("file copy is failed");
    }
}

int main(int argc, char const *argv[])
{
    // FirstFileWrite();

    // FirstFileRead();

    // TextDataFileWrite();

    // TextDataFileRead();

    // TextCharFileCopy();

    TextStringFileCopy();
}