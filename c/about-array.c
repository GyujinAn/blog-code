#include <stdio.h>

void PrintArray()
{
    int arr[3] = {1, 2, 3};
    printf("arr: %p \n", arr);
    printf("arr[0]: %p \n", &arr[0]);
    printf("arr[1]: %p \n", &arr[1]);
    printf("arr[2]: %p \n", &arr[2]);
}

void MyFunction(int arr[3])
{
    printf("parameter array address: %p\n", arr);
}

void FunctionParameterArrayAddressIsCopied()
{
    int arr[3] = {1, 2, 3};
    printf("argument array address: %p\n", arr);
    MyFunction(arr);
}

int main(int argc, char const *argv[])
{
    // FunctionParameterArrayAddressIsCopied();

    PrintArray();
}