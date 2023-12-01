#include <stdio.h>

struct point
{
    int x;
    int y;
    struct point *ptr;
};

void PrintStruct()
{
    struct point pos = {3, 4};
    // printf("%d \n", pos);
    printf("%d \n", pos.x);
    printf("%d \n", pos.y);
    printf("%p \n", &pos);
    printf("%p \n", &pos.x);
    printf("%p \n", &pos.y);
}

void PrintStructArrayAddress()
{
    struct point arr[3];
    printf("%p \n", &arr);
    printf("%p \n", &arr[0]);
    printf("%p \n", &arr[1]);
    printf("%p \n", &arr[2]);
}

void PointRelation()
{
    struct point pos1 = {1, 1};
    struct point pos2 = {2, 2};
    struct point pos3 = {3, 3};

    pos1.ptr = &pos2;
    pos2.ptr = &pos3;
    pos3.ptr = &pos1;

    printf("(%d, %d) -> (%d, %d) \n ", pos1.x, pos1.y, pos1.ptr->x, pos1.ptr->y);
    printf("(%d, %d) -> (%d, %d) \n ", pos2.x, pos2.y, pos2.ptr->x, pos2.ptr->y);
    printf("(%d, %d) -> (%d, %d) \n ", pos3.x, pos3.y, pos3.ptr->x, pos3.ptr->y);
}

struct array
{
    int arr[3];
    int num;
};

void PrintArrayAddressInStruct()
{
    struct array arr;
    printf("%p \n", &arr);
    printf("%p \n", &arr.arr[0]);
    printf("%p \n", &arr.arr[1]);
    printf("%p \n", &arr.arr[2]);
    printf("%p \n", &arr.num);
}

typedef struct
{
    int num;
    void (*function_pointer)(int);

} SampleClass;

void SimpleMethod(int num)
{
    printf("this is simple method which is passed on %d \n", num);
}

typedef struct
{
    int num1;
    int num2;
    int num3[3];

} MyStruct;

void MyFunction(MyStruct parameter)
{
    printf("&parameter: %p\n", &parameter);
    printf("&parameter.num1: %p\n", &parameter.num1);
    printf("&parameter.num2: %p\n", &parameter.num2);
    printf("parameter.num3: %p\n", parameter.num3);
}

void FunctionParameterStructAddressIsCopied()
{
    MyStruct argument = {3, 4, {1, 2, 3}};
    printf("&argument: %p\n", &argument);
    printf("&argument.num1: %p\n", &argument.num1);
    printf("&argument.num2: %p\n", &argument.num2);
    printf("argument.num3: %p\n", argument.num3);
    MyFunction(argument);
}

typedef struct
{
    int num1;
    int num2[2];

} MyStruct2;

void AssignmentOperationOfStruct()
{
    MyStruct2 a = {3, {4, 5}};
    MyStruct2 b = a;
    printf("address of a: %p \n", &a.num1);
    printf("address of a: %p \n", &a.num2[0]);
    printf("address of a: %p \n", &a.num2[1]);
    printf("address of b: %p \n", &b);
}

typedef union ubox
{
    int mem1;
    int mem2;
    double mem3;

} UBox;

void UnionValAccess()
{
    UBox ubx;
    ubx.mem1 = 20;
    printf("%d \n", ubx.mem2);

    ubx.mem3 = 7.15;
    printf("%d \n", ubx.mem1);
    printf("%d \n", ubx.mem2);
    printf("%g \n", ubx.mem3);
}

int main(int argc, char const *argv[])
{
    // PrintStruct();

    // PrintStructArrayAddress();

    // PointRelation();

    // PrintArrayAddressInStruct();

    // SampleClass sampleClass = {3, SimpleMethod};
    // sampleClass.function_pointer(sampleClass.num);

    // FunctionParameterStructAddressIsCopied();

    // AssignmentOperationOfStruct();

    UnionValAccess();

    return 0;
}