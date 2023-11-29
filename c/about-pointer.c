#include <stdio.h>

void PrintPointerSizeAndAddress()
{
    printf("=== start PrintPointerSizeAndAddress ===\n");

    char c1 = 'A';
    char c2 = 'B';
    int num1 = 5;
    int num2 = 6;

    char *c1_pointer = &c1;
    char *c2_pointer = &c2;
    int *num1_pointer = &num1;
    int *num2_pointer = &num2;

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

void ArrayIsPointer()
{
    printf("=== start ArrayIsPointer ===\n");

    int arr[3] = {0, 1, 2};
    int *ptr = &arr[0];

    printf("array variable: %p \n", arr);
    printf("first element: %p \n", &arr[0]);
    printf("second element: %p \n", &arr[1]);
    printf("third element: %p \n", &arr[2]);

    printf("%d %d \n", ptr[0], arr[0]);
    printf("%d %d \n", ptr[1], arr[1]);
    printf("%d %d \n", ptr[2], arr[2]);
    printf("%d %d \n", *ptr, *arr);

    printf("=== end ArrayIsPointer ===\n\n");
}

void PointerBaseArrayAccess()
{
    printf("=== start PointerBaseArrayAccess ===\n");

    int arr[3] = {11, 22, 33};
    int *ptr = arr;
    printf("%d %d %d \n", *ptr, *(ptr + 1), *(ptr + 2));

    printf("%d ", *ptr);
    ptr++;
    printf("%d ", *ptr);
    ptr++;
    printf("%d ", *ptr);
    ptr--;
    printf("%d ", *ptr);
    ptr--;
    printf("%d ", *ptr);
    printf("\n");

    printf("=== end PointerBaseArrayAccess ===\n\n");
}

void TowStringType()
{
    printf("=== start TowStringType ===\n");

    char str1[] = "Hello";
    char *str2 = "World";
    printf("%s, %s \n", str1, str2);

    str1[0] = 'X';
    // runtime err
    // str2[0]='X';

    // printf("%s, %s \n", str1, str2);

    printf("=== end TowStringType ===\n\n");
}

void DoublePointerAccess()
{
    printf("=== start DoublePointerAccess ===\n");
    double num = 3.14;
    double *ptr = &num;
    double **dptr = &ptr;
    double *ptr2;

    printf("%9p %9p \n", ptr, *dptr);
    printf("%9g %9g \n", num, **dptr);
    ptr2 = *dptr;
    *ptr2 = 10.99;
    printf("%9g, %9g \n", num, **dptr);
    printf("=== end DoublePointerAccess ===\n\n");
}

void TwoDimensionalArray()
{
    int arr2d[3][3];
    printf("%p \n", arr2d);
    printf("%p \n", arr2d[0]);
    printf("%p \n\n", &arr2d[0][0]);

    printf("%p \n", arr2d[1]);
    printf("%p \n\n", &arr2d[1][0]);

    printf("%p \n", arr2d[2]);
    printf("%p \n\n", &arr2d[2][0]);

    printf("sizeof(arr2d): %lu \n", sizeof(arr2d));
    printf("sizeof(arr2d[0]): %lu \n", sizeof(arr2d[0]));
    printf("sizeof(arr2d[1]): %lu \n", sizeof(arr2d[1]));
    printf("sizeof(arr2d[2]): %lu \n", sizeof(arr2d[2]));
}

void TwoDimensionalArrayPointer()
{
    int arr1[3][2];
    int arr2[2][3];

    printf("arr1: %p \n", arr1);
    printf("arr1+1: %p \n", arr1 + 1);
    printf("arr1+2: %p \n\n", arr1 + 2);

    printf("arr2: %p \n", arr2);
    printf("arr2+1: %p \n", arr2 + 1);
}

void TwoDimensionArrayNameAndArrayPointer()
{
    int arr1[2][2] = {
        {1, 2},
        {3, 4}};

    int arr2[3][2] = {
        {1, 2},
        {3, 4},
        {5, 6}};

    int arr3[4][2] = {
        {1, 2},
        {3, 4},
        {5, 6},
        {7, 8}};

    int(*ptr)[2];
    int i;

    ptr = arr1;
    printf("** show 2,2 arr1 **\n");
    for (i = 0; i < 2; i++)
        printf("%d %d \n", arr1[i][0], ptr[i][1]);

    ptr = arr2;
    printf("** show 3,2 arr2 **\n");
    for (i = 0; i < 3; i++)
        printf("%d %d \n", arr2[i][0], ptr[i][1]);

    ptr = arr3;
    printf("** show 4,2 arr1 **\n");
    for (i = 0; i < 4; i++)
        printf("%d %d \n", arr3[i][0], ptr[i][1]);
}

void ArrayPointerAndPointerArray()
{
    int num1 = 10, num2 = 20, num3 = 30, num4 = 40;
    int arr2d[2][4] = {
        {1, 2, 3, 4},
        {5, 6, 7, 8}};
    int i,
        j;

    int *pointer_array[4] = {&num1,
                             &num2,
                             &num3,
                             &num4};
    int(*array_pointer)[4] = arr2d;

    printf("%d %d %d %d \n", *pointer_array[0], *pointer_array[1], *pointer_array[2], *pointer_array[3]);
    for (i = 0; i < 2; i++)
    {
        for (j = 0; j < 4; j++)
            printf("%d ", array_pointer[i][j]);
        printf("\n");
    }
}

int main(int argc, char const *argv[])
{

    // PrintPointerSizeAndAddress();

    // ArrayIsPointer();

    // PointerBaseArrayAccess();

    // TowStringType();

    // DoublePointerAccess();

    // TwoDimensionalArray();

    // TwoDimensionalArrayPointer();

    // TwoDimensionArrayNameAndArrayPointer();

    // ArrayPointerAndPointerArray();

    // ArrayPointerAndPointerArray();

    return 0;
}