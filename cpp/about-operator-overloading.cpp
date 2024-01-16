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

    friend Point &operator--(Point &ref);
};

Point &operator--(Point &ref)
{
    ref.xpos -= 1;
    ref.ypos -= 1;
    return ref;
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

int main(void)
{
    // showBinaryOperatorOverloading();

    showUnaryOperatorOverloading();
}