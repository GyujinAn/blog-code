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

int main(void)
{
    // showBinaryOperatorOverloading();

    // showUnaryOperatorOverloading();

    // showPosteriorOperatorOverloading();

    // showCommutativeOperatorOverloading();

    showCoutOperatorOverloading();

    return 0;
}