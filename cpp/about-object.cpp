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
    cout << "Param Address: " << &obj << endl;
    return obj;
}

void showWhenCallingConstructorByCopy()
{
    Simple obj(7);
    SimpleFuncObj(obj);

    cout << endl;
    Simple tempRef = SimpleFuncObj(obj);
    cout << "Return obj " << &tempRef << endl;

    cout << endl;
}

class RealObject
{
private:
    int data;

public:
    RealObject(int num) : data(num)
    {
    }
    void ShowData()
    {
        cout << "Data: " << data << endl;
    }
    void Add(int num)
    {
        data += num;
    }
};

typedef struct Data
{
    int data;
    void (*ShowData)(Data *);
    void (*Add)(Data *, int);
} Data;

void ShowData(Data *This)
{
    cout << "Data: " << This->data << endl;
}

void Add(Data *This, int num)
{
    This->data += num;
}

void showHowToBeObjectOnMemory()
{
    // Object
    RealObject realObject(15);
    realObject.Add(17);
    realObject.ShowData();

    cout << endl
         << "=== on memory ===" << endl;

    // On memory
    Data object = {15, ShowData, Add};
    object.Add(&object, 17);
    object.ShowData(&object);
}

int main(void)
{
    // showWhenCallingConstructorByCopy();

    showHowToBeObjectOnMemory();

    return 0;
}