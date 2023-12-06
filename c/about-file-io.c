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
    fclose(src);
    fclose(des);
}

void BinaryFileCopy()
{
    FILE *src = fopen("./exe/src.bin", "rb");
    FILE *des = fopen("./exe/dst.bin", "wb");
    char buf[20];
    int readCnt;

    if (src == NULL || des == NULL)
    {
        puts("failed file open");
    }

    while (1)
    {
        readCnt = fread((void *)buf, 1, sizeof(buf), src);
        printf("readCnt: %d \n", readCnt);

        if (readCnt < sizeof(buf))
        {
            if (feof(src) != 0)
            {
                fwrite((void *)buf, 1, readCnt, des);
                puts("completed file copy");
                break;
            }
            else
            {
                puts("failed file copy");
            }
            break;
        }
        fwrite((void *)buf, 1, sizeof(buf), des);
    }
    fclose(src);
    fclose(des);
}

typedef struct
{
    char name[10];
    char sex;
    int age;
} Friend;

void StructFileWriteRead()
{
    FILE *fp;
    Friend f1;
    Friend f2;

    fp = fopen("./exe/data.bin", "wb");
    printf("input name, sex, age: ");
    scanf("%s %c %d", f1.name, &(f1.sex), &(f1.age));
    fwrite((void *)&f1, sizeof(f1), 1, fp);
    fclose(fp);

    fp = fopen("./exe/data.bin", "rb");
    fread((void *)&f2, sizeof(f2), 1, fp);
    printf("%s %c %d \n", f2.name, f2.sex, f2.age);
    fclose(fp);
}

void MoveFileReWrPos()
{
    FILE *fp = fopen("./exe/data.txt", "wt");
    fputs("123456789", fp);
    fclose(fp);

    fp = fopen("./exe/data.txt", "rt");

    fseek(fp, -2, SEEK_END);
    putchar(fgetc(fp));

    fseek(fp, 2, SEEK_SET);
    putchar(fgetc(fp));

    fseek(fp, 2, SEEK_CUR);
    putchar(fgetc(fp));

    fclose(fp);
}

void TellFileReWrPos()
{
    long fpos;
    int i;

    FILE *fp = fopen("./exe/data.txt", "wt");
    fputs("1234-", fp);
    fclose(fp);

    fp = fopen("./exe/data.txt", "rt");

    for (i = 0; i < 4; i++)
    {
        putchar(fgetc(fp));
        fpos = ftell(fp);
        fseek(fp, -1, SEEK_END);
        putchar(fgetc(fp));
        fseek(fp, fpos, SEEK_SET);
    }

    fclose(fp);
}

int main(int argc, char const *argv[])
{
    // FirstFileWrite();

    // FirstFileRead();

    // TextDataFileWrite();

    // TextDataFileRead();

    // TextCharFileCopy();

    // TextStringFileCopy();

    // BinaryFileCopy();

    // StructFileWriteRead();

    // MoveFileReWrPos();

    TellFileReWrPos();
}