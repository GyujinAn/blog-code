#include "Car.hpp"

int main(void)
{
    Car run;
    run.InitMembers();
    run.ShowCarState();
    run.Accel();
    run.Break();
}