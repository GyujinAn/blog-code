#include <iostream>
using namespace std;

namespace CAR_CONST
{
    enum
    {
        ID_LEN = 20
    };
}

struct Car
{
    char gamerID[CAR_CONST::ID_LEN];

    void ShowCarState();
};

void Car::ShowCarState()
{
    cout << "gamer id: " << gamerID << endl;
}

int main(void)
{
    Car run = {"hello"};
    run.ShowCarState();
    return 0;
}