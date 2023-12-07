#include <stdio.h>
#include <stdlib.h>

void DynamicMemoryAllocation()
{
    int *ptr1 = (int *)malloc(sizeof(int));
    int *ptr2 = (int *)malloc(sizeof(int) * 7);
    int i;

    printf("address of ptr1: %p \n", ptr1);
    printf("address of ptr2: %p \n", ptr2);

    *ptr1 = 20;
    for (i = 0; i < 7; i++)
    {
        ptr2[i] = i + 1;
    }

    printf("ptr1: %d \n", *ptr1);
    for (i = 0; i < 7; i++)
    {
        printf("ptr2: %d \n", ptr2[i]);
    }

    free(ptr1);
    free(ptr2);
}

char *ReadUserName(void)
{
    char *name = (char *)malloc(sizeof(char) * 30);
    printf("What's your name? ");
    scanf("%s", name);
    return name;
}

void ReadStringRight()
{
    char *name1;
    char *name2;
    name1 = ReadUserName();
    printf("name1: %s \n", name1);
    name2 = ReadUserName();
    printf("name2: %s \n", name2);

    printf("again name1: %s \n", name1);
    printf("again name2: %s \n", name2);

    free(name1);
    free(name2);
}

int main(int argc, char const *argv[])
{
    // DynamicMemoryAllocation();

    ReadStringRight();
}