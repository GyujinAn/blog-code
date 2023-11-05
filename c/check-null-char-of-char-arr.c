# include <stdio.h>

int main(int argc, char const *argv[])
{
    char str[]="hello";
    printf("size of array: %lu \n", sizeof(str));
    printf("null char of char type: %c \n", str[5]);
    printf("null char of int type: %d \n", str[5]);
    return 0;
}
