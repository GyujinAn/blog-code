#include <iostream>
using namespace std;

void PrintReference(void)
{

    int num1 = 2024;
    int &num2 = num1;
    int &num3 = num2;
    // int &num4 = 30;
    // int &num5;
    // int &num6 = NULL;

    num2 = 3024;

    cout << "VAL: " << num1 << endl;
    cout << "REF: " << num2 << endl;
    cout << "REF: " << num3 << endl;

    cout << "VAL: " << &num1 << endl;
    cout << "REF: " << &num2 << endl;
    cout << "REF: " << &num3 << endl;
}

void PrintReferenceFromArray(void)
{
    int arr[3] = {1,
                  3,
                  5};

    int &ref1 = arr[0];
    int &ref2 = arr[1];
    int &ref3 = arr[2];

    cout << ref1 << endl;
    cout << ref2 << endl;
    cout << ref3 << endl;

    cout << &arr[0] << endl;
    cout << &arr[1] << endl;
    cout << &arr[2] << endl;

    cout << &ref1 << endl;
    cout << &ref2 << endl;
    cout << &ref3 << endl;
}

void ReferencePointer(void)
{
    int num = 12;
    int *ptr = &num;
    int **dptr = &ptr;

    int &ref = num;
    int *(&pref) = ptr;
    int **(&dpref) = dptr;

    cout << ref << endl;
    cout << *pref << endl;
    cout << **dpref << endl;
}

void SwapByReference(int &ref1, int &ref2)
{
    int temp = ref1;
    ref1 = ref2;
    ref2 = temp;
}

void PrintResultOfSwap()
{
    int val1 = 10;
    int val2 = 20;

    cout << "val1: " << val1 << endl;
    cout << "val2: " << val2 << endl;

    SwapByReference(val1, val2);

    cout << "val1: " << val1 << endl;
    cout << "val2: " << val2 << endl;
}

int &ReturnReference(int &ref)
{
    ref++;
    return ref;
}

int ReturnValue(int &ref)
{
    ref++;
    return ref;
}

void PrintResultOfReturnReference()
{
    int num1 = 1;
    int &num2 = ReturnReference(num1);
    int num3 = ReturnReference(num1);
    int num4 = ReturnValue(num1);

    num1++;
    num2++;
    num3 += 100;
    num4 += 1000;

    cout << "num1: " << num1 << endl;
    cout << "num2: " << num2 << endl;
    cout << "num3: " << num3 << endl;
    cout << "num4: " << num4 << endl;
}

int main(void)
{
    // PrintReference();

    // PrintReferenceFromArray();

    // ReferencePointer();

    // PrintResultOfSwap();

    PrintResultOfReturnReference();
}