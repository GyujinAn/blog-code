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

void ArrayIsPointer() {
    printf("=== start ArrayIsPointer ===\n");

    int arr[3] = {0, 1, 2};
    int * ptr = &arr[0];

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

void PointerBaseArrayAccess() {
    printf("=== start PointerBaseArrayAccess ===\n");

    int arr[3] = {11, 22, 33};
    int * ptr = arr;
    printf("%d %d %d \n", *ptr, *(ptr+1), *(ptr+2));

    printf("%d ", *ptr); ptr++;
    printf("%d ", *ptr); ptr++;
    printf("%d ", *ptr); ptr--;
    printf("%d ", *ptr); ptr--;
    printf("%d ", *ptr); printf("\n");

    printf("=== end PointerBaseArrayAccess ===\n\n");
}

void TowStringType() {
    printf("=== start TowStringType ===\n");

    char str1[]="Hello";
    char * str2="World";
    printf("%s, %s \n", str1, str2);

    str1[0]='X';
    str2[0]='X'; // runtime err

    printf("%s, %s \n", str1, str2);
    
    printf("=== end TowStringType ===\n\n");
}

int main(int argc, char const *argv[])
{

    PrintPointerSizeAndAddress();

    ArrayIsPointer();

    PointerBaseArrayAccess();

    TowStringType();

    return 0;
}