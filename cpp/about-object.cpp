#include <iostream>
using namespace std;

class Simple
{
private:
    int num;

public:
    Simple(int n) : num(n)
    {
        cout << "New Object: " << this << endl;
    }

    Simple(const Simple &copy) : num(copy.num)
    {
        cout << "New Copy Object: " << this << endl;
    }

    ~Simple()
    {
        cout << "Destroy obj: " << this << endl;
    }
};

Simple SimpleFuncObj(Simple obj)
{
    cout << "Param ADR: " << &obj << endl;
    return obj;
}

int main(void)
{
    Simple obj(7);
    SimpleFuncObj(obj);

    cout << endl;
    Simple tempRef = SimpleFuncObj(obj);
    cout << "Return obj " << &tempRef << endl;

    cout << endl;
    return 0;
}