# include <stdio.h>

void PrintPointerSizeAndAddress() {
    printf("=== start PrintPointerSizeAndAddress ===\n");

    char c1 = 'A';
    char c2 = 'B';
    int num1 = 5;
    int num2 = 6;

    char * c1_pointer = &c1;
    char * c2_pointer = &c2;
    int * num1_pointer = &num1;
    int * num2_pointer = &num2;

    printf("c1 pointer size: %lu \n", sizeof(c1_pointer));
    printf("c2 pointer size: %lu \n", sizeof(c2_pointer));
    printf("num1 pointer size: %lu \n", sizeof(num1_pointer));
    printf("num2 pointer size: %lu \n", sizeof(num2_pointer));

    printf("c1 memory address: %p \n", c1_pointer);
    printf("c2 memory address: %p \n", c2_pointer);
    printf("num1 memory address: %p \n", num1_pointer);
    printf("num2 memory address: %p \n", num2_pointer);

    printf("=== end PrintPointerSizeAndAddress ===\n\n");
}

int main(int argc, char const *argv[])
{

    PrintPointerSizeAndAddress();
    
    return 0;
}