#include <iostream>
using namespace std;

class Point
{
private:
    int xpos, ypos;

public:
    Point(int x = 0, int y = 0) : xpos(x), ypos(y)
    {
    }

    void ShowPosition() const
    {
        cout << '[' << xpos << ", " << ypos << ']' << endl;
    }

    Point operator+(const Point &ref)
    {
        Point pos(xpos + ref.xpos, ypos + ref.ypos);
        return pos;
    }

    Point &operator++()
    {
        xpos += 1;
        ypos += 1;
        return *this;
    }

    Point operator++(int)
    {
        const Point retobj(xpos, ypos);
        xpos += 1;
        ypos += 1;
        return retobj;
    }

    Point operator*(int times)
    {
        Point pos(xpos * times, ypos * times);
        return pos;
    }

    friend Point operator-(const Point &pos1, const Point &pos2);
    friend Point &operator--(Point &ref);
    friend const Point operator--(Point &ref, int);
    friend Point operator*(int times, Point &ref);
    friend ostream &operator<<(ostream &, const Point &);
};

Point operator-(const Point &pos1, const Point &pos2)
{
    Point pos(pos1.xpos - pos2.xpos, pos1.ypos - pos2.ypos);
    return pos;
}

Point &operator--(Point &ref)
{
    ref.xpos -= 1;
    ref.ypos -= 1;
    return ref;
}

const Point operator--(Point &ref, int)
{
    const Point retobj(ref);
    ref.xpos -= 1;
    ref.ypos -= 1;
    return retobj;
}

Point operator*(int times, Point &ref)
{
    return ref * times;
}

ostream &operator<<(ostream &os, const Point &pos)
{
    os << '[' << pos.xpos << ", " << pos.ypos << ']' << endl;
    return os;
}

void showBinaryOperatorOverloading()
{
    Point pos1(3, 4);
    Point pos2(10, 20);
    Point pos3 = pos1 + pos2;

    pos1.ShowPosition();
    pos2.ShowPosition();
    pos3.ShowPosition();
}

void showUnaryOperatorOverloading()
{
    Point pos(1, 2);
    ++pos;
    pos.ShowPosition();
    --pos;
    pos.ShowPosition();

    ++(++pos);
    pos.ShowPosition();
    --(--pos);
    pos.ShowPosition();
}

void showPosteriorOperatorOverloading()
{
    Point pos(3, 5);
    Point cpy;
    cpy = pos--;
    cpy.ShowPosition();
    pos.ShowPosition();

    cpy = pos++;
    cpy.ShowPosition();
    pos.ShowPosition();
}

void showCommutativeOperatorOverloading()
{
    Point pos(1, 2);
    Point cpy;

    cpy = 3 * pos;
    cpy.ShowPosition();

    cpy = 2 * pos * 3;
    cpy.ShowPosition();
}

void showCoutOperatorOverloading()
{
    Point pos1(1, 3);
    cout << pos1;
    Point pos2(101, 303);
    cout << pos2;
}

class First
{
private:
    int num1, num2;

public:
    First(int n1 = 0, int n2 = 0) : num1(n1), num2(n2)
    {
        cout << "Here is constructor of First" << endl;
    }

    void ShowData()
    {
        cout << num1 << ", " << num2 << endl;
    }
};

class Second
{
private:
    int num3, num4;

public:
    Second(int n3 = 0, int n4 = 0) : num3(n3), num4(n4)
    {
        cout << "Here is constructor of Second" << endl;
    }

    void ShowData()
    {
        cout << num3 << ", " << num4 << endl;
    }

    Second &operator=(const Second &ref)
    {
        cout << "Second& operator=()" << endl;
        num3 = ref.num3;
        num4 = ref.num4;
        return *this;
    }
};

void showAssignmentOperatorOverloading()
{
    First fsrc(111, 222);
    First fcpy;
    Second ssrc(333, 444);
    Second scpy;

    fcpy = fsrc;
    scpy = ssrc;

    fcpy.ShowData();
    scpy.ShowData();

    First fob1, fob2;
    Second sob1, sob2;

    fob1 = fob2 = fsrc;
    sob1 = sob2 = ssrc;

    fob1.ShowData();
    fob2.ShowData();

    sob1.ShowData();
    sob2.ShowData();
}

class Person
{
private:
    char *name;
    int age;

public:
    Person(char *myname, int myage)
    {
        int len = strlen(myname) + 1;
        name = new char[len];
        strcpy(name, myname);
        age = myage;
    }

    void ShowPersonInfo() const
    {
        cout << "name: " << name << endl;
        cout << "age: " << age << endl;
    }

    Person &operator=(const Person &ref)
    {
        delete[] name;
        int len = strlen(ref.name) + 1;
        name = new char[len];
        strcpy(name, ref.name);
        age = ref.age;
        return *this;
    }

    ~Person()
    {
        delete[] name;
        cout << " called destructor! " << endl;
    }
};

void showAssignmentOperatorOverloadingBug()
{
    Person man1("Chris", 29);
    Person man2("Jackson", 22);
    man2 = man1;
    man1.ShowPersonInfo();
    man2.ShowPersonInfo();
}

class Hello
{
private:
    int num1, num2;

public:
    Hello(int n1 = 0, int n2 = 0) : num1(n1), num2(n2) {}

    void ShowData() { cout << num1 << "," << num2 << endl; }

    Hello &operator=(const Hello &ref)
    {
        cout << "First& operator=()" << endl;
        num1 = ref.num1;
        num2 = ref.num2;
        return *this;
    }
};

class World : public Hello
{
private:
    int num3, num4;

public:
    World(int n1, int n2, int n3, int n4) : Hello(n1, n2), num3(n3), num4(n4){};

    void ShowData()
    {
        Hello::ShowData();
        cout << num3 << "," << num4 << endl;
    }

    World &operator=(const World &ref)
    {
        cout << "Second& operator=()" << endl;
        // Hello::operator=(ref);
        num3 = ref.num3;
        num4 = ref.num4;
        return *this;
    }
};

void showBaseClassOverloadingOperator()
{
    World ssrc(111, 222, 333, 444);
    World scpy(0, 0, 0, 0);
    scpy = ssrc;
    scpy.ShowData();
}

int main(void)
{
    // showBinaryOperatorOverloading();

    // showUnaryOperatorOverloading();

    // showPosteriorOperatorOverloading();

    // showCommutativeOperatorOverloading();

    // showCoutOperatorOverloading();

    // showAssignmentOperatorOverloading();

    // showAssignmentOperatorOverloadingBug();

    showBaseClassOverloadingOperator();

    return 0;
}