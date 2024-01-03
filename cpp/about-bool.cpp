#include <iostream>
using namespace std;

void PrintTrueAndFalse(void)
{
    cout << "true: " << true << endl;
    cout << "false: " << false << endl;

    cout << "sizeof 1: " << sizeof(1) << endl;
    cout << "sizeof 0: " << sizeof(0) << endl;

    cout << "sizeof true: " << sizeof(true) << endl;
    cout << "sizeof false: " << sizeof(false) << endl;
}

int main(void)
{
    PrintTrueAndFalse();
}