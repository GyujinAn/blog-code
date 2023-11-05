# include <stdio.h>

void PrintNullCharOfStr() {
    printf("=== start PrintNullCharOfStr ===\n");

    char str[]="hello";

    // since the null char is included, the string size should be 6.
    printf("size of array: %lu \n", sizeof(str));

    // null char should not be printed.
    printf("null char of char type: %c \n", str[5]);

    // ASCII(American Standard Code for Information Interchange) code of null char is 0.
    printf("null char of int type: %d \n", str[5]);

    printf("=== end PrintNullCharOfStr ===\n\n");
}

void PrintDiffBwStrAndCharArray() {
    printf("=== start PrintDiffBwStrAndCharArray ===\n");

    char str[]="hello";
    char char_array[]={'h', 'e', 'l', 'l', 'o'};

    // since null char is included, the size should be 6
    printf("size of array: %lu \n", sizeof(str));

    // since null char is included, last ASCII code should be 0
    printf("last ASCII code: %d \n", str[sizeof(str)-1]);

    // since null char is not included, the size should be 5
    printf("size of array: %lu \n", sizeof(char_array));

    // since null char is not included, last ASCII code should be 111
    printf("last ASCII code: %d \n", char_array[sizeof(char_array)-1]);

    printf("=== end PrintDiffBwStrAndCharArray ===\n");    
}

int main(int argc, char const *argv[])
{
    PrintNullCharOfStr();

    PrintDiffBwStrAndCharArray();

}

