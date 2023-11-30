#include <stdio.h>

void MyFunction1(int num)
{
    printf("hello num: %d \n", num);
}

void MyFunction2(char *str)
{
    printf("hello str: %s \n", str);
}

void FunctionPointer()
{
    void (*function_pointer1)(int) = MyFunction1;
    void (*function_pointer2)(char *) = MyFunction2;

    printf("address of function_pointer1: %p \n", function_pointer1);
    printf("address of function_pointer2: %p \n", function_pointer2);

    function_pointer1(3);
    function_pointer2("string");
}

void VoidTypePointer()
{
    int num = 20;
    void *pointer;

    pointer = &num;
    printf("%p \n", pointer);

    pointer = MyFunction1;
    printf("%p \n", pointer);
}

int main(int argc, char const *argv[])
{
    // FunctionPointer();

    VoidTypePointer();

    return 0;
}